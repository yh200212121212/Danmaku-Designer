package cn.BHR.danmakudesigner.dialogs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import cn.BHR.danmakudesigner.MainActivity;

public class DialogConfirm {
	public static void Show(String info, String title, final Runnable ok, final Runnable cancel)
	{
		info = info == null ? "确定吗? Are you sure?" : info;
		title = title == null ? "确认 Confirm" : title;
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.Instance);
		builder.setTitle(title);
		builder.setMessage(info);
		builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if (ok != null)
					ok.run();
			}
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if (cancel != null)
					cancel.run();
			}
		});
		builder.create().show();
	}
}
