package cn.BHR.danmakudesigner;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.FitViewport;

import cn.BHR.danmakudesigner.dEntity.CircularProj;
import cn.BHR.danmakudesigner.rEntity.CircularGroup;
import cn.BHR.danmakudesigner.rEntity.Projectile;

public class RunScreen extends ScreenAdapter {
	public static Stage UIStage;
	public static Rectangle STAGEMAINRECT;
	public static Actor MainBackGround;
	public static Group MainGroup;
	public static Pool<Projectile> GlobalProjs = new Pool<Projectile>(768){
		@Override
		protected Projectile newObject() {
			return new Projectile();
		}
	};
	static boolean ready = false;
	@Override
	public void show() {
		MainActivity.Instance.handler.post(new Runnable() {
			@Override
			public void run() {
				MainActivity.Instance.setTitle("Run");
			}
		});
		
		ready = false;
		UIStage = new Stage(new FitViewport(540, 540f / Main.Width * Main.Height), Main.Sbatch);
		STAGEMAINRECT = new Rectangle(0, UIStage.getHeight() - 675, 540, 675);
		DrawHelper.InitMagicPixel();
		
		MainBackGround = new Image(DrawHelper.MagicPixel);
		MainBackGround.setBounds(STAGEMAINRECT.x, STAGEMAINRECT.y, STAGEMAINRECT.width, STAGEMAINRECT.height);
		MainBackGround.setColor(Color.DARK_GRAY);
		MainGroup = new Group();
		MainGroup.addActor(MainBackGround);
		
		Image bottomBG = new Image(DrawHelper.MagicPixel);
		bottomBG.setBounds(0, 0, 540, UIStage.getHeight() - 675);
		bottomBG.setColor(Color.BLACK);
		
		UIStage.addActor(MainGroup);
		UIStage.addActor(bottomBG);
		
		for (CircularProj g : CircularProj.Items.values()) {
			CircularGroup.Items.add(new CircularGroup(g));
		}
		
		ready = true;
		
		super.show();
	}
	@Override
	public void render(float delta) {
		UIStage.draw();
		super.render(delta);
	}
	@Override
	public void hide() {
		ready = false;
		for (CircularGroup group : CircularGroup.Items)
			for (int i=0; i<group.Projs.size(); i++)
				GlobalProjs.free(group.Projs.valueAt(i));
		CircularGroup.Items.clear();
		super.hide();
	}
	public static void Update()
	{
		if (!ready) return;
		for (CircularGroup group : CircularGroup.Items) {
			group.Update();
		}
	}
}
