package cn.BHR.danmakudesigner.resources;

import java.util.Hashtable;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;

import cn.BHR.danmakudesigner.DesignScreen;
import cn.BHR.danmakudesigner.Main;
import cn.BHR.danmakudesigner.MainActivity;
import cn.BHR.danmakudesigner.dEntity.CircularProj;
import cn.BHR.danmakudesigner.dialogs.DialogCircularProj;

public class DIO {
	public static String Save()
	{
		Json json = new Json();
		return json.toJson(CircularProj.Items);
	}
	@SuppressWarnings("unchecked")
	public static void Load(String data)
	{
		Json json = new Json();
		for (CircularProj element : CircularProj.Items.values())
			element.DesignButton.remove();
		CircularProj.Items = json.fromJson(Hashtable.class, data);
		for (final CircularProj element : CircularProj.Items.values()) {
			Button button = new ImageButton(
					Main.ResourceMgr.Drawables.get("New_Circular_Proj"),
					Main.ResourceMgr.Drawables.get("New_Circular_Proj_Down"));
			button.setBounds(element.AbsPos.x - 32, element.AbsPos.y - 32, 64, 64);
			button.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					MainActivity.Instance.handler.post(new Runnable() {
						@Override
						public void run() {
							DialogCircularProj.Show(element.Name);
						}
					});
				}
			});
			element.DesignButton = button;
			DesignScreen.MainGroup.addActor(button);
		}
	}
}
