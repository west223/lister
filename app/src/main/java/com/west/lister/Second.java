package com.west.lister;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usr1 on 8/11/14.
 */
public class Second extends Fragment { //if extends ListFragment then can cos a exception

    public TaskDBHelper db;
    public List<Task> list;
    public ListerAdapter adapter;
    public CheckBox checkBox1;
    public Button button;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_layout, container, false);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ListView listTask = (ListView)getView().findViewById(R.id.listView1);

        db = new TaskDBHelper(getActivity()); //reference to Database
        list =  db.getAllTasks();

        adapter = new ListerAdapter(getActivity(), R.layout.list_inner_view, list, null);
        listTask.setAdapter(adapter);
    }

    public class ListerAdapter extends ArrayAdapter<Task>{

        Context context;
        CheckBox chk;
        public ListView listView1;

        public List<Task> taskList = new ArrayList<Task>();

        int layoutResourceId;

        public ListerAdapter(Context context, int layoutResourceId, List<Task> objects, ListView listView){
            super(context, layoutResourceId, objects);

            this.layoutResourceId = layoutResourceId;
            this.taskList = objects;
            this.context = context;
            this.listView1 = listView;

        }

        class ViewHolder{
            TextView text;
            CheckBox chk;
        }

        public View getView(int position, View convertView, ViewGroup parent){

            CheckBox chk = null;
            if (convertView == null){

                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    //inflater of main custom View
                convertView = inflater.inflate(R.layout.list_inner_view, parent, false);
                chk = (CheckBox)convertView.findViewById(R.id.checkBox1);
                convertView.setTag(chk);

                chk.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Task changeTask = (Task) cb.getTag();
                        changeTask.setStatus(cb.isChecked() == true ? 1 : 0);

                        db.updateTask(changeTask);
                    }
                });
            }else {
                chk = (CheckBox) convertView.getTag();
            }

            Task current = taskList.get(position);
            chk.setText(current.getTaskName());
            chk.setChecked(current.getStatus() == 1 ? true : false);
            chk.setTag(current);

            return  convertView;
        }

        public List<Task> getTaskList(){
            return taskList;
        }
    }

}

