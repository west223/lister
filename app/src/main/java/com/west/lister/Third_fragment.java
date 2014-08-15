package com.west.lister;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * Created by usr1 on 8/13/14.
 */
public class Third_fragment extends Fragment{


    public TaskDBHelper db;
    public List<Task> list;
    public Second.ListerAdapter adapter;
    public CheckBox checkBox1;
    public Button button;
    //String s;
    private EditText editTextTask;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.third_layout, container, false);
        //edit text on third_layout.xml
        editTextTask = (EditText) view.findViewById(R.id.editText1);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        db = new TaskDBHelper(getActivity());

        list = db.getAllTasks();

        Button btnAdd =(Button) getActivity().findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String s = editTextTask.getText().toString();
                if (s.equalsIgnoreCase("")) {

                } else{
                    Task task = new Task(s, 0);
                    db.addTask(task);
                    editTextTask.setText("");
                    System.out.println(" tarrget: " + s);

                    Fragment fr;

                    fr = new Second();  //wos class Second
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_main, fr); //fragment_main
                    fragmentTransaction.commit();
                }
            }
        });


    }
}
