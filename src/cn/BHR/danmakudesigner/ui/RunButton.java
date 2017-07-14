package cn.BHR.danmakudesigner.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import cn.BHR.danmakudesigner.*;
import cn.BHR.danmakudesigner.enums.ScreenMode;

public class RunButton {
	public static Button Create()
	{
		Button button = new ImageButton(
				Main.ResourceMgr.Drawables.get("Run"),
				Main.ResourceMgr.Drawables.get("Run_Down"));
		button.setBounds(136, 20, 96, 96);
		button.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Main.CurrentScreen = ScreenMode.Run;
				Main.Instance.setScreen(new RunScreen());
			}
		});
		return button;
	}
}
