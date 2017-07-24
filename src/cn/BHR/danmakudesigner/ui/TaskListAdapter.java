package cn.BHR.danmakudesigner.ui;

import java.util.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.*;
import cn.BHR.danmakudesigner.R;
import cn.BHR.danmakudesigner.dataStructures.Task;

public class TaskListAdapter extends BaseAdapter {
	
	private LayoutInflater inflater;
	private final List<Task> list;
	
	public TaskListAdapter(Context context, List<Task> tasks) {
		inflater = LayoutInflater.from(context);
		list = tasks;
	}

	@Override
	public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		final Task task = (Task)this.getItem(position);
		
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.task_list_item, null);
			viewHolder.begin = (ExtendedEditText)convertView.findViewById(R.id.task_edit_timeout);
			viewHolder.interval = (ExtendedEditText)convertView.findViewById(R.id.task_edit_interval);
			viewHolder.target = (ExtendedEditText)convertView.findViewById(R.id.task_edit_target);
			viewHolder.time = (ExtendedEditText)convertView.findViewById(R.id.task_edit_time);
			viewHolder.type = (Spinner)convertView.findViewById(R.id.task_type_spinner);
			viewHolder.delButton = (ImageButton)convertView.findViewById(R.id.task_btn_delete);
			convertView.setTag(viewHolder);
		}
		else
			viewHolder = (ViewHolder)convertView.getTag();
		
		
		viewHolder.time.clearTextChangedListeners();
		viewHolder.interval.clearTextChangedListeners();
		viewHolder.target.clearTextChangedListeners();
		viewHolder.begin.clearTextChangedListeners();
		
		if (!task.isNew())
		{
			viewHolder.begin.setText(String.valueOf(task.Timeout));
			viewHolder.interval.setText(String.valueOf(task.Interval));
			viewHolder.target.setText(task.Target);
			viewHolder.time.setText(String.valueOf(task.Duration));
			viewHolder.type.setSelection(task.Type, false);
		}
		
		viewHolder.type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				task.Type = pos;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				task.Type = 0;
			}
		});
		
		viewHolder.delButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				list.remove(task);
				TaskListAdapter.this.notifyDataSetChanged();
			}
		});
		
		viewHolder.time.addTextChangedListener(new TextViewChangeListener(0, task));
		viewHolder.interval.addTextChangedListener(new TextViewChangeListener(1, task));
		viewHolder.target.addTextChangedListener(new TextViewChangeListener(2, task));
		viewHolder.begin.addTextChangedListener(new TextViewChangeListener(3, task));
		
		return convertView;
	}
	
	public class ViewHolder {
        public ExtendedEditText target;
        public ExtendedEditText time;
        public ExtendedEditText begin;
        public ExtendedEditText interval;
        public Spinner type;
        public ImageButton delButton;
    }
	
	public class TextViewChangeListener implements TextWatcher {
		private int m_type;
		private Task m_task;
		public TextViewChangeListener(int type, Task task) {
			m_type = type;
			m_task = task;
		}
		@Override
		public void afterTextChanged(Editable text) {
			try
			{
				switch (m_type) {
				case 0:
					m_task.Duration = Integer.parseInt(text.toString());
					break;
				case 1:
					m_task.Interval = Integer.parseInt(text.toString());
					break;
				case 2:
					m_task.Target = text.toString();
					break;
				case 3:
					m_task.Timeout = Integer.parseInt(text.toString());
					break;
				default:
					break;
				}
			}
			catch (Exception e) {
				
			}
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			
		}
		
	}
}
