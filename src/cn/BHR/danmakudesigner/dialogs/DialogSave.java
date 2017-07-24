package cn.BHR.danmakudesigner.dialogs;

import java.io.*;
import java.util.*;

import android.app.AlertDialog;
import android.content.DialogInterface;
import cn.BHR.danmakudesigner.*;
import cn.BHR.danmakudesigner.resources.DIO;

public class DialogSave {
	public static void Show() {
		final ArrayList<String> items = new ArrayList<String>();
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.Instance);
		builder.setTitle("选择保存到的位置");
		items.add("新建(New)...");
		final File[] files = new File(MainActivity.MainDir).listFiles();
		for (File file : files) {
			if (file.isFile() && file.getName().endsWith(".dpj"))
				items.add(file.getName());
		}
		builder.setItems(items.toArray(new String[items.size()]), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int which) {
				if (which == 0)
					DialogNewFile.Show();
				else
				{
					final String filename = items.get(which);
					DialogConfirm.Show("注意：" + filename + " 中原有内容会被覆盖", "确认覆盖 Overwrite", new Runnable() {
						@Override
						public void run() {
							FileOutputStream file;
							try {
								file = new FileOutputStream(MainActivity.MainDir + filename);
								file.write(DIO.Save().getBytes());
								file.close();
							} catch (Exception e) {
								DialogError.Show(e.getLocalizedMessage());
							}
						}
					}, null);
				}
			}
		});
		builder.create().show();
	}
}
