package cn.BHR.danmakudesigner.dEntity;

import java.util.Hashtable;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import cn.BHR.danmakudesigner.*;
import cn.BHR.danmakudesigner.dialogs.DialogCircularProj;

public class CircularProj {
	public static Hashtable<String, CircularProj> Items = new Hashtable<String, CircularProj>();
	public String Name;
	public Vector2 Position;
	public Vector2 AbsPos;
	public float BeginDir;
	public float EndDir;
	public int CountProjs;
	public int Cycle;
	public Button DesignButton;
	
	public static void Create(Vector2 position, float beginDir, float endDir, int count, int cycle, final String name)
	{
		CircularProj n = new CircularProj();
		n.Position = position;
		n.BeginDir = beginDir;
		n.EndDir = endDir;
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
	
	public static void Modify(Vector2 position, float beginDir, float endDir, int count, int cycle, String name)
	{
		CircularProj n = Items.get(name);
		n.Position = position;
		n.AbsPos.set(position).add(0, DesignScreen.UIStage.getHeight() - 675);
		n.DesignButton.setBounds(n.AbsPos.x - 32, n.AbsPos.y - 32, 64, 64);
		n.BeginDir = beginDir;
		n.EndDir = endDir;
		n.CountProjs = count;
		n.Cycle = cycle;
	}
	
	public static void Remove(String name)
	{
		CircularProj n = Items.get(name);
		n.DesignButton.remove();
		n = null;
		Items.remove(name);
	}
}
