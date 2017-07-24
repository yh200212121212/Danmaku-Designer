package cn.BHR.danmakudesigner.rEntity;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import cn.BHR.danmakudesigner.*;
import cn.BHR.danmakudesigner.dEntity.CircularProj;

public class CircularGroup {
	public static ArrayList<CircularGroup> Items = new ArrayList<CircularGroup>();
	public ArrayList<Projectile> Projs;
	public CircularProj MainComponent;
	public int ExistTime;
	public int Active;
	protected TaskSystem taskSystem;
	protected int batchID;
	public CircularGroup(CircularProj component)
	{
		Active = 1;
		MainComponent = component.clone();
		ExistTime = 0;
		batchID = 0;
		Projs = new ArrayList<Projectile>(100);
		tmp = new Vector2(MainComponent.Velocity, 0);
		taskSystem = new TaskSystem(this, MainComponent.Tasks);
	}
	Vector2 tmp;
	public void Update()
	{
		ExistTime++;
		if (ExistTime > MainComponent.EndTime || ExistTime < MainComponent.BeginTime)
			return;
		float tmpBeginDir = MainComponent.MidDir - MainComponent.DirRange;
		float tmpEndDir = MainComponent.MidDir + MainComponent.DirRange;
		if ((MainComponent.Cycle <= 1 || (ExistTime - MainComponent.BeginTime) % MainComponent.Cycle == 1)
				&& Active != 0)
		{
			for (float dir = tmpBeginDir;
					dir <= tmpEndDir;
					dir += (tmpEndDir - tmpBeginDir) / (MainComponent.CountProjs - 1f))
			{
				Projectile proj = RunScreen.GlobalProjs.obtain();
				proj.Direction = dir;
				proj.Position.set(MainComponent.AbsPos);
				proj.Drawer.setSize(24, 24);
				proj.BatchID = batchID;
				RunScreen.MainGroup.addActor(proj.Drawer);
				Projs.add(proj);
			}
			batchID++;
		}
		MainComponent.MidDir += MainComponent.RotateSpeed / 90f;
		
		taskSystem.Update();
		
		for (int i=0; i<Projs.size(); i++)
		{
			Projectile proj = Projs.get(i);
			proj.Position.add(tmp.setAngle(proj.Direction));
			proj.Drawer.setPosition(proj.Position.x, proj.Position.y, Align.center);
			if (!proj.GetHitBox().overlaps(RunScreen.STAGEMAINRECT))
			{
				Projs.remove(i);
				RunScreen.GlobalProjs.free(proj);
				i--;
			}
		}
	}
}
