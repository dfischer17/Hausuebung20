package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.TextView;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaskAdapter extends BaseAdapter {
    private List<Task> taskList;
    private int layoutId;
    private LayoutInflater inflater;

    public TaskAdapter(Context ctx, int layoutId, List<Task> taskList) {
        this.layoutId = layoutId;
        this.taskList = taskList;
        this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = taskList.get(position);
        View listItem = (convertView == null) ?
                inflater.inflate(this.layoutId, null) : convertView;

        ((TextView) listItem.findViewById(R.id.taskName))
        .setText(task.getText());

        ((TextView) listItem.findViewById(R.id.dueDate))
                .setText(task.getDueTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        return listItem;
    }
}
