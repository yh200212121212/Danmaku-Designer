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
	public CircularGroup(CircularProj component)
	{
		MainComponent = component;
		ExistTime = 0;
		Projs = new ArrayList<Projectile>(100);
	}
	public void Update()
	{
		ExistTime++;
		if (ExistTime % MainComponent.Cycle == 0)
		{
			for (float dir = MainComponent.BeginDir;
					dir <= MainComponent.EndDir;
					dir += (MainComponent.EndDir - MainComponent.BeginDir) / (MainComponent.CountProjs - 1f))
			{
				Projectile proj = RunScreen.GlobalProjs.obtain();
				proj.Direction = dir;
				proj.Position.set(MainComponent.AbsPos);
				proj.Drawer.setSize(24, 24);
				RunScreen.MainGroup.addActor(proj.Drawer);
				Projs.add(proj);
			}
		}
		Vector2 tmp = new Vector2(3, 0);
		for (Projectile proj : Projs)
		{
			proj.Position.add(tmp.setAngle(proj.Direction));
			proj.Drawer.setPosition(proj.Position.x, proj.Position.y, Align.center);
		}
	}
}
