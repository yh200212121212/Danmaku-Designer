package cn.BHR.danmakudesigner;

import java.io.File;
import java.io.FileInputStream;

import com.badlogic.gdx.backends.android.*;

import android.content.*;
import android.net.Uri;
import android.os.*;
import android.view.View;
import android.webkit.WebView;
import cn.BHR.danmakudesigner.dialogs.DialogError;
import cn.BHR.danmakudesigner.resources.DIO;

public class MainActivity extends AndroidApplication
{
	public static WebView scriptKit;
	public static MainActivity Instance;
	public static String MainDir;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        Instance = this;
        scriptKit = new WebView(this);
        File maindir = getExternalFilesDir(null);
        if (!maindir.exists())
        	maindir.mkdirs();
        MainDir = maindir.getAbsolutePath() + "/";
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        View view = initializeForView(new Main(), cfg);
        setTitle("Design");
        setContentView(view);
        
        try {
	        Intent intent=getIntent();
	        Uri uri=(Uri)intent.getData();
	        String path=uri.getPath();
	        File pFile = new File(path);
			try {
				FileInputStream stream = new FileInputStream(pFile);
				byte[] bytes = new byte[stream.available()];
				stream.read(bytes);
				stream.close();
				String data = new String(bytes);
				Main.ExtraLoad = data;
			} catch (Exception e) {
				DialogError.Show(e.getLocalizedMessage());
			}
        }
        catch (Exception e) {
        	
		}
    }
}
