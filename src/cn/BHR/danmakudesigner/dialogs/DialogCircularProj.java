package cn.BHR.danmakudesigner.dialogs;

import com.badlogic.gdx.math.Vector2;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import cn.BHR.danmakudesigner.*;
import cn.BHR.danmakudesigner.dEntity.CircularProj;

@SuppressLint("InflateParams")
public class DialogCircularProj {
	public static void Show(final String name)
	{
		final boolean isnew = (name == null);
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.Instance);
		builder.setTitle(isnew ? "New Circular" : "Modify Circular");
		final View linearLayout = LayoutInflater.
				from(MainActivity.Instance)
				.inflate(R.layout.dialog_new_circular_proj, null);
		builder.setView(linearLayout);
		final EditText nameI = (EditText)linearLayout.findViewById(R.id.dlgncpinputname);
		final EditText beginDirI = (EditText)linearLayout.findViewById(R.id.dlgncpinputbegindir);
		final EditText endDirI = (EditText)linearLayout.findViewById(R.id.dlgncpinputenddir);
		final EditText countI = (EditText)linearLayout.findViewById(R.id.dlgncpinputcount);
		final EditText posxI = (EditText)linearLayout.findViewById(R.id.dlgncpinputposx);
		final EditText posyI = (EditText)linearLayout.findViewById(R.id.dlgncpinputposy);
		final EditText cycleI = (EditText)linearLayout.findViewById(R.id.dlgncpinputcycle);
		if (isnew)
		{
			for (int i=1;;i++)
			{
				String nname = "CircularProj" + i;
				if (!CircularProj.Items.containsKey(nname))
				{
					nameI.setText(nname);
					beginDirI.setText("0");
					endDirI.setText("360");
					countI.setText("10");
					posxI.setText("270");
					posyI.setText("337.5");
					cycleI.setText("30");
					break;
				}
			}
		}
		else
		{
			nameI.setText(name);
			nameI.setFocusable(false);
			CircularProj proj = CircularProj.Items.get(name);
			posxI.setText(String.valueOf(proj.Position.x));
			posyI.setText(String.valueOf(proj.Position.y));
			beginDirI.setText(String.valueOf(proj.BeginDir));
			endDirI.setText(String.valueOf(proj.EndDir));
			countI.setText(String.valueOf(proj.CountProjs));
			cycleI.setText(String.valueOf(proj.Cycle));
		}
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if (isnew)
				{
					CircularProj.Create(
							new Vector2(
									Float.parseFloat(posxI.getText().toString()),
									Float.parseFloat(posyI.getText().toString())),
							Float.parseFloat(beginDirI.getText().toString()),
							Float.parseFloat(endDirI.getText().toString()),
							Integer.parseInt(countI.getText().toString()),
							Integer.parseInt(cycleI.getText().toString()),
							nameI.getText().toString()
					);
				}
				else
				{
					CircularProj.Modify(
							new Vector2(
									Float.parseFloat(posxI.getText().toString()),
									Float.parseFloat(posyI.getText().toString())),
							Float.parseFloat(beginDirI.getText().toString()),
							Float.parseFloat(endDirI.getText().toString()),
							Integer.parseInt(countI.getText().toString()),
							Integer.parseInt(cycleI.getText().toString()),
							nameI.getText().toString()
					);
				}
			}
		});
		builder.setNegativeButton(isnew ? "Cancel" : "More...", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
			}
		});
		if (!isnew)
		{
			builder.setNeutralButton("DELETE", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					CircularProj.Remove(name);
				}
			});
		}
		builder.create().show();
	}
}
