package cn.BHR.danmakudesigner.dialogs;

import java.io.*;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.*;
import cn.BHR.danmakudesigner.MainActivity;
import cn.BHR.danmakudesigner.resources.DIO;

public class DialogOpen {
	static AlertDialog dlg;
	public static void Show() {
		final ListView listView = new ListView(MainActivity.Instance);
		final ArrayList<String> items = new ArrayList<String>();
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.Instance);
		builder.setTitle("打开文件(长按删除)");
		final File[] files = new File(MainActivity.MainDir).listFiles();
		for (File file : files) {
			if (file.isFile() && file.getName().endsWith(".dpj"))
				items.add(file.getName());
		}
		listView.setAdapter(new ArrayAdapter<String>(MainActivity.Instance, android.R.layout.simple_list_item_1, items));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
				if (id == -1) return;
				File pFile = new File(MainActivity.MainDir + items.get((int)id));
				try {
					FileInputStream stream = new FileInputStream(pFile);
					byte[] bytes = new byte[stream.available()];
					stream.read(bytes);
					stream.close();
					String data = new String(bytes);
					DIO.Load(data);
				} catch (Exception e) {
					DialogError.Show(e.getLocalizedMessage());
				}
				dlg.dismiss();
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
				if (id == -1) return false;
				final int fid = (int)id;
				DialogConfirm.Show("即将删除 " + items.get((int)id), "确认删除？", new Runnable() {
					@SuppressWarnings("unchecked")
					@Override
					public void run() {
						File pFile = new File(MainActivity.MainDir + items.get(fid));
						items.remove(fid);
						pFile.delete();
						((ArrayAdapter<String>)listView.getAdapter()).notifyDataSetChanged();
					}
				}, null);
				return true;
			}
		});
		builder.setView(listView);
		dlg = builder.create();
		dlg.show();
	}
}
