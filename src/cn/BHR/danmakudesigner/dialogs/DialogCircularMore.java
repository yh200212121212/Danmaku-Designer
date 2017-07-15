package cn.BHR.danmakudesigner.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import cn.BHR.danmakudesigner.MainActivity;
import cn.BHR.danmakudesigner.R;
import cn.BHR.danmakudesigner.dEntity.CircularProj;

@SuppressLint("InflateParams")
public class DialogCircularMore {
	public static void Show(final String name)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.Instance);
		builder.setTitle("More - " + name);
		final View linearLayout = LayoutInflater.
				from(MainActivity.Instance)
				.inflate(R.layout.dialog_circular_more, null);
		builder.setView(linearLayout);
		final EditText beginI = (EditText)linearLayout.findViewById(R.id.dlgcpmtimebegin);
		final EditText endI = (EditText)linearLayout.findViewById(R.id.dlgcpmtimeend);
		final EditText velocI = (EditText)linearLayout.findViewById(R.id.dlgcpmveloc);
		final EditText rotateI = (EditText)linearLayout.findViewById(R.id.dlgcpmrotate);
		CircularProj proj = CircularProj.Items.get(name);
		beginI.setText(String.valueOf(proj.BeginTime));
		endI.setText(String.valueOf(proj.EndTime));
		velocI.setText(String.valueOf(proj.Velocity));
		rotateI.setText(String.valueOf(proj.RotateSpeed));
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				CircularProj.ModifyMore(name,
						Integer.parseInt(beginI.getText().toString()),
						Integer.parseInt(endI.getText().toString()),
						Float.parseFloat(velocI.getText().toString()),
						Float.parseFloat(rotateI.getText().toString()));
			}
		});
		builder.create().show();
	}
}
