package cn.BHR.danmakudesigner;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

public class DrawHelper {
	public static Color ALL = new Color(1, 1, 1, 1);
	public static Texture MagicPixel = null;
	public static void InitMagicPixel()
	{
		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		MagicPixel = new Texture(pixmap);
	}
	public static void DrawMagicPixel(Batch batch, Color color, Rectangle rect)
	{
		if (MagicPixel == null)
			InitMagicPixel();
		batch.setColor(color);
		batch.draw(MagicPixel, rect.x, rect.y, rect.width, rect.height);
		batch.setColor(ALL);
	}
}
