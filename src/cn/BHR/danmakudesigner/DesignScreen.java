package cn.BHR.danmakudesigner;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.*;

import cn.BHR.danmakudesigner.dEntity.CircularProj;
import cn.BHR.danmakudesigner.ui.NewCircularProjButton;
import cn.BHR.danmakudesigner.ui.RunButton;

public class DesignScreen extends ScreenAdapter {
	public static Stage UIStage;
	public static Rectangle STAGEMAINRECT;
	public static Actor MainBackGround;
	static Group bottomBar;
	public static Group MainGroup;
	@Override
	public void show() {
		UIStage = new Stage(new FitViewport(540, 540f / Main.Width * Main.Height), Main.Sbatch);
		STAGEMAINRECT = new Rectangle(0, UIStage.getHeight() - 675, 540, 675);
		DrawHelper.InitMagicPixel();
		
		MainBackGround = new Image(DrawHelper.MagicPixel);
		MainBackGround.setBounds(STAGEMAINRECT.x, STAGEMAINRECT.y, STAGEMAINRECT.width, STAGEMAINRECT.height);
		MainBackGround.setColor(Color.DARK_GRAY);
		MainGroup = new Group();
		MainGroup.addActor(MainBackGround);
		
		bottomBar = new Group();
		Image bottomBarActor = new Image(DrawHelper.MagicPixel);
		bottomBarActor.setBounds(0, 0, 540, 136);
		bottomBarActor.setColor(Color.GRAY);
		Image bottomBG = new Image(DrawHelper.MagicPixel);
		bottomBG.setBounds(0, 0, 540, UIStage.getHeight() - 675);
		bottomBG.setColor(Color.BLACK);
		bottomBar.addActor(bottomBG);
		bottomBar.addActor(bottomBarActor);
		bottomBar.addActor(NewCircularProjButton.Create());
		bottomBar.addActor(RunButton.Create());
		
		UIStage.addActor(MainGroup);
		UIStage.addActor(bottomBar);
		
		for (CircularProj g : CircularProj.Items.values()) {
			MainGroup.addActor(g.DesignButton);
		}
		
		Main.InputMgr.addProcessor(UIStage);
		super.show();
	}
	@Override
	public void hide() {
		Main.InputMgr.removeProcessor(UIStage);
		super.hide();
	}
	/*Vector2 pnt = new Vector2();
	Vector2 p0 = new Vector2();
	Vector2 p1 = new Vector2(120, 0);
	Vector2 p2 = new Vector2(280, 400);
	Vector2 p3 = new Vector2(400, 400);
	Vector2 tmp = new Vector2();
	Rectangle rct = new Rectangle(0, 0, 3, 3);*/
	@Override
	public void render(float delta) {
		UIStage.act(delta);
		UIStage.draw();
		/*Main.Sbatch.begin();
		for (int i=0; i<400; i++)
		{
			Bezier.cubic(pnt, i/400f, p0, p1, p2, p3, tmp);
			rct.setPosition(pnt);
			DrawHelper.DrawMagicPixel(Main.Sbatch, Color.CYAN, rct);
		}
		Main.Sbatch.end();*/
		super.render(delta);
	}
	
	public static void Update()
	{
		
	}
}
