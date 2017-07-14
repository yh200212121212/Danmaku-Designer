package cn.BHR.danmakudesigner;

import com.badlogic.gdx.backends.android.*;

import android.os.*;
import android.view.View;
import android.webkit.WebView;

public class MainActivity extends AndroidApplication
{
	public static WebView scriptKit;
	public static MainActivity Instance;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        Instance = this;
        scriptKit = new WebView(this);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        View view = initializeForView(new Main(), cfg);
        setTitle("Design");
        setContentView(view);
    }
}
