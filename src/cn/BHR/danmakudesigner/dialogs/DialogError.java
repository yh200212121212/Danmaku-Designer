package cn.BHR.danmakudesigner.dialogs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import cn.BHR.danmakudesigner.MainActivity;

public class DialogError {
	public static void Show(String info)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.Instance);
		builder.setMessage(info);
		builder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
			}
		});
		builder.create().show();
	}
}
