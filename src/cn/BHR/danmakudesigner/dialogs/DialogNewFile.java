package cn.BHR.danmakudesigner.dialogs;

import java.io.FileOutputStream;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import cn.BHR.danmakudesigner.*;
import cn.BHR.danmakudesigner.resources.DIO;

public class DialogNewFile {
	public static void Show() {
		final EditText editText = new EditText(MainActivity.Instance);
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.Instance);
		builder.setTitle("输入文件名").setView(editText);
		builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					FileOutputStream file;
					try {
						file = new FileOutputStream(MainActivity.MainDir + editText.getText().toString() + ".dpj");
						file.write(DIO.Save().getBytes());
						file.close();
					} catch (Exception e) {
						DialogError.Show(e.getLocalizedMessage());
					}
				}
			});
		builder.create().show();
	}
}
