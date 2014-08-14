package com.west.lister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usr1 on 8/11/14.
 */
public class TaskDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "taskManager";

    private static final String TABLE_TASKS = "tasks";

    private static final String KEY_ID = "id";
    private static final String KEY_LISTNAME = "listName";
    private static final String KEY_TASKNAME = "taskName";
    private static final String KEY_STATUS = "status";

    public TaskDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
             //   + KEY_LISTNAME + " TEXT, "  // Special List
                + KEY_TASKNAME + " TEXT, "
                + KEY_STATUS + " INTEGER)";
        db.execSQL(sql);
      //  db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop old table is exists
        db.execSQL("DROP TABLE IS EXISTS" + TABLE_TASKS);

        onCreate(db);
    }

    public void addTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TASKNAME, task.getTaskName());
        values.put(KEY_STATUS, task.getStatus());
        // values/ put LISTNAME
        db.insert(TABLE_TASKS, null, values);
        db.close();
    }

        //get from table
    public List<Task>getAllTasks(){
        List<Task> taskList = new ArrayList<Task>();

        String selectQuery = "SELECT  * FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Task task = new Task();
                task.setId(cursor.getInt(0));
             //   task.setListName(cursor.getString(1));  //Special List
                task.setTaskName(cursor.getString(1));
                task.setStatus(cursor.getInt(2));

                taskList.add(task);
            }while (cursor.moveToNext());
        }
        return taskList;
    }

    public void updateTask(Task task){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //updates
      //  values.put(KEY_LISTNAME, task.getListName());  //Special List
        values.put(KEY_TASKNAME, task.getTaskName());
        values.put(KEY_STATUS, task.getStatus());
        db.update(TABLE_TASKS, values, KEY_ID + " = ?" , new String[] {String.valueOf(task.getId())});
        db.close();
    }

    public void deleteTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_ID + "=?", new String[] {String.valueOf(task.getId())});
        db.close();
    }
        //delete special LIST_NAME and all tasks
    public void deleteAllTasks(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        //not yet working?
        db.delete(TABLE_TASKS, "", null);
        db.close();
    }



}
