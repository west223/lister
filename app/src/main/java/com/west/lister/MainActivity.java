package com.west.lister;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity{

    public TaskDBHelper db;
    public List<Task> list;
    public ListerAdapter adapter;
    public CheckBox checkBox1;
    public Button button;
    String barTitle = "Some List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

         getActionBar().setDisplayHomeAsUpEnabled(true); //buck btn action bar

         ActionBar ab = getActionBar(); // action bar title
         ab.setTitle(barTitle);         //action bar title




        db = new TaskDBHelper(this);
        list = db.getAllTasks();

        adapter = new ListerAdapter(this, R.layout.second_layout, list, null);

        final ListView listTask = (ListView) findViewById(R.id.listView1);
        //listTask.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);

        //My custom buttons on menu bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
                                                 // Layout ???
                convertView = inflater.inflate(R.layout.second_layout, parent, false);
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
            chk.setText(current.getStatus());
            chk.setChecked(current.getStatus() == 1 ? true : false);
            chk.setTag(current);

            return  convertView;
        }

        public List<Task> getTaskList(){
            return taskList;
        }
    }

}
