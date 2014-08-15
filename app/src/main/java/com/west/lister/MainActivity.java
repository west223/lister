package com.west.lister;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity{

    public TaskDBHelper db;
    public List<Task> list;
//    public Second.ListerAdapter adapter;
    public CheckBox checkBox1;
    public Button button;
    String barTitle = "Some List";
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

         getActionBar().setDisplayHomeAsUpEnabled(true); //buck btn action bar

         ActionBar ab = getActionBar(); // action bar title
         ab.setTitle(barTitle);         //action bar title

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_bar, menu);
        return true;
    }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){ //ACTION BAR BUTTONS

            switch (item.getItemId()) {
                case R.id.btnPlus:
                    //open fragment third
                    openAddTask();

                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }

    public void openAddTask(){

        Fragment fr;
            fr = new Third_fragment();

            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_main, fr);
            fragmentTransaction.commit();
    }

}
