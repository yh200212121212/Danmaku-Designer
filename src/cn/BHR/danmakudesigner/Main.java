package cn.BHR.danmakudesigner;

import java.util.*;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import cn.BHR.danmakudesigner.enums.*;
import cn.BHR.danmakudesigner.resources.*;

public class Main extends Game {
	public static Main Instance;
	public static InputMultiplexer InputMgr;
	public static ResourceManager ResourceMgr;
	public static float Width;
	public static float Height;
	public static ScreenMode CurrentScreen = ScreenMode.Design;
	public static SpriteBatch Sbatch;
	public static Timer updateTimer;
	public static String ExtraLoad = null;
	@Override
	public void create() {
		Width = Gdx.graphics.getWidth();
		Height = Gdx.graphics.getHeight();
		
		Sbatch = new SpriteBatch();
		InputMgr = new InputMultiplexer();
		InputMgr.addProcessor(new InputAdapter(){
			@Override
			public boolean keyDown(int keycode) {
				switch (keycode) {
				case Keys.BACK:
					if (CurrentScreen == ScreenMode.Design)
						System.exit(0);
					else {
						CurrentScreen = ScreenMode.Design;
						setScreen(new DesignScreen());
					}
					return true;
				default:
					return false;
				}
			}
		});
		ResourceMgr = new ResourceManager();
		ResourceMgr.LoadAllTextures();
		
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setInputProcessor(InputMgr);
		setScreen(new DesignScreen());
		
		updateTimer = new Timer();
		updateTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				switch (CurrentScreen) {
				case Design:
					DesignScreen.Update();
					break;
				case Run:
					RunScreen.Update();
					break;
				}
			}
		}, 0, 11);
		
		Instance = this;
		
		if (ExtraLoad != null)
			DIO.Load(ExtraLoad);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render();
	}
}
