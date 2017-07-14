package cn.BHR.danmakudesigner.rEntity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import cn.BHR.danmakudesigner.Main;

public class Projectile {
	public Vector2 Position = new Vector2();
	public Image Drawer = new Image(Main.ResourceMgr.Drawables.get("Proj0"));
	public float Direction;
}
