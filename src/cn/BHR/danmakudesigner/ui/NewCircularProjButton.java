package cn.BHR.danmakudesigner.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import cn.BHR.danmakudesigner.*;
import cn.BHR.danmakudesigner.dialogs.DialogCircularProj;

public class NewCircularProjButton {
	public static Button Create()
	{
		Button button = new ImageButton(
				Main.ResourceMgr.Drawables.get("New_Circular_Proj"),
				Main.ResourceMgr.Drawables.get("New_Circular_Proj_Down"));
		button.setBounds(20, 20, 96, 96);
		button.addListener(new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y) {
						MainActivity.Instance.handler.post(new Runnable() {
							@Override
							public void run() {
								DialogCircularProj.Show(null);
							}
						});
					}
				});
		return button;
	}
}
