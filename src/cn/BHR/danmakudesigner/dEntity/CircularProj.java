package cn.BHR.danmakudesigner.dEntity;

import java.util.*;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import cn.BHR.danmakudesigner.*;
import cn.BHR.danmakudesigner.dataStructures.Task;
import cn.BHR.danmakudesigner.dialogs.DialogCircularProj;

public class CircularProj {
	public static Hashtable<String, CircularProj> Items = new Hashtable<String, CircularProj>();
	public String Name;
	public Vector2 Position;
	public Vector2 AbsPos;
	public float MidDir;
	public float DirRange;
	public int CountProjs;
	public int Cycle;
	public transient Button DesignButton;
	
	public int BeginTime = 0;
	public int EndTime = 999999;
	public float Velocity = 3f;
	public float RotateSpeed = 0f;
	
	public ArrayList<Task> Tasks = new ArrayList<Task>();
	
	public static void Create(Vector2 position, float midDir, float rangeDir, int count, int cycle, final String name)
	{
		CircularProj n = new CircularProj();
		n.Position = position;
		n.MidDir = midDir;
		n.DirRange = rangeDir;
		n.CountProjs = count;
		n.Name = name;
		n.Cycle = cycle;
		n.AbsPos = position.cpy().add(0, DesignScreen.UIStage.getHeight() - 675);
		Button button = new ImageButton(
				Main.ResourceMgr.Drawables.get("New_Circular_Proj"),
				Main.ResourceMgr.Drawables.get("New_Circular_Proj_Down"));
		button.setBounds(n.AbsPos.x - 32, n.AbsPos.y - 32, 64, 64);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				MainActivity.Instance.handler.post(new Runnable() {
					@Override
					public void run() {
						DialogCircularProj.Show(name);
					}
				});
			}
		});
		n.DesignButton = button;
		DesignScreen.MainGroup.addActor(button);
		Items.put(name, n);
	}
	
	public static void Modify(Vector2 position, float midDir, float rangeDir, int count, int cycle, String name)
	{
		CircularProj n = Items.get(name);
		n.Position = position;
		n.AbsPos.set(position).add(0, DesignScreen.UIStage.getHeight() - 675);
		n.DesignButton.setBounds(n.AbsPos.x - 32, n.AbsPos.y - 32, 64, 64);
		n.MidDir = midDir;
		n.DirRange = rangeDir;
		n.CountProjs = count;
		n.Cycle = cycle;
	}
	
	public static void ModifyMore(String name, int begin, int end, float veloc, float rotate)
	{
		CircularProj n = Items.get(name);
		n.BeginTime = begin;
		n.EndTime = end;
		n.Velocity = veloc;
		n.RotateSpeed = rotate;
	}
	
	public static void Remove(String name)
	{
		CircularProj n = Items.get(name);
		n.DesignButton.remove();
		n = null;
		Items.remove(name);
	}
	@Override
	public CircularProj clone() {
		CircularProj n = new CircularProj();
		n.AbsPos = this.AbsPos.cpy();
		n.BeginTime = this.BeginTime;
		n.CountProjs = this.CountProjs;
		n.Cycle = this.Cycle;
		n.DirRange = this.DirRange;
		n.EndTime = this.EndTime;
		n.MidDir = this.MidDir;
		n.Name = this.Name;
		n.Position = this.Position.cpy();
		n.RotateSpeed = this.RotateSpeed;
		n.Velocity = this.Velocity;
		n.Tasks.addAll(this.Tasks);
		return n;
	}
}
