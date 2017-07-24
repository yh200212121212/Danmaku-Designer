package cn.BHR.danmakudesigner.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import cn.BHR.danmakudesigner.Main;
import cn.BHR.danmakudesigner.MainActivity;
import cn.BHR.danmakudesigner.dialogs.DialogOpen;
import cn.BHR.danmakudesigner.dialogs.DialogSave;

public class OpenSaveButtons {
	public static Button CreateOpen()
	{
		Button button = new ImageButton(
				Main.ResourceMgr.Drawables.get("Open"),
				Main.ResourceMgr.Drawables.get("Open_Down"));
		button.setBounds(252, 20, 96, 96);
		button.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y) {
				MainActivity.Instance.handler.post(new Runnable() {
					@Override
					public void run() {
						DialogOpen.Show();
					}
				});
			}
		});
		return button;
	}
	public static Button CreateSave()
	{
		Button button = new ImageButton(
				Main.ResourceMgr.Drawables.get("Save"),
				Main.ResourceMgr.Drawables.get("Save_Down"));
		button.setBounds(368, 20, 96, 96);
		button.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y) {
				MainActivity.Instance.handler.post(new Runnable() {
					@Override
					public void run() {
						DialogSave.Show();
					}
				});
			}
		});
		return button;
	}
}
