package cn.BHR.danmakudesigner.resources;

import java.util.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public class ResourceManager {
	public Hashtable<String, Texture> Textures = new Hashtable<String, Texture>();
	public Hashtable<String, Drawable> Drawables = new Hashtable<String, Drawable>();
	public void LoadTexture(String path, String name, boolean GenerateDrawable)
	{
		Texture ttx = new Texture(Gdx.files.internal("textures/" + path));
		Textures.put(name, ttx);
		if (GenerateDrawable)
		{
			TextureRegion tregion = new TextureRegion(ttx);
			TextureRegionDrawable drawable = new TextureRegionDrawable(tregion);
			Drawables.put(name, drawable);
		}
	}
	
	public void LoadAllTextures()
	{
		String[] paths = new String[] {
				"ui/buttons/New_Circular_Proj.png",
				"ui/buttons/New_Circular_Proj_Down.png",
				"ui/buttons/Run.png",
				"ui/buttons/Run_Down.png",
				"proj/0.png"
		};
		String[] names = new String[] {
				"New_Circular_Proj",
				"New_Circular_Proj_Down",
				"Run",
				"Run_Down",
				"Proj0"
		};
		for (int i=0; i<paths.length; i++)
			LoadTexture(paths[i], names[i], true);
	}
}
