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
	float tmpBeginDir;
	float tmpEndDir;
	public CircularGroup(CircularProj component)
	{
		MainComponent = component;
		ExistTime = 0;
		Projs = new ArrayList<Projectile>(100);
		tmp = new Vector2(MainComponent.Velocity, 0);
		tmpBeginDir = MainComponent.BeginDir;
		tmpEndDir = MainComponent.EndDir;
	}
	Vector2 tmp;
	public void Update()
	{
		ExistTime++;
		if (ExistTime > MainComponent.EndTime || ExistTime < MainComponent.BeginTime)
			return;
		if (ExistTime % MainComponent.Cycle == 1)
		{
			for (float dir = tmpBeginDir;
					dir <= tmpEndDir;
					dir += (tmpEndDir - tmpBeginDir) / (MainComponent.CountProjs - 1f))
			{
				Projectile proj = RunScreen.GlobalProjs.obtain();
				proj.Direction = dir;
				proj.Position.set(MainComponent.AbsPos);
				proj.Drawer.setSize(24, 24);
				RunScreen.MainGroup.addActor(proj.Drawer);
				Projs.add(proj);
			}
		}
		tmpBeginDir += MainComponent.RotateSpeed / 90f;
		tmpEndDir += MainComponent.RotateSpeed / 90f;
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
