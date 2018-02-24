package com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.oyasumisoft.juanfrancrater.ipewa.data.db.Contrat.MyContrats;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.MyOpenHelper;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Tarea;

import java.util.ArrayList;

/**
 * Created by juanf on 24/02/2018.
 */

public class TareaDao {
    public ArrayList<Tarea> loadAll() {
        final ArrayList<Tarea> tareaArrayList=new ArrayList<>();
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        Cursor cursor = sqLiteDatabase.query(MyContrats.Tareas.TABLE_NAME,
                MyContrats.Tareas.ALL_COLUMN,
                null,
                null,
                null,
                null,
                MyContrats.Tareas.DEFAULT_SORT,
                null);
        if(cursor.moveToFirst())
        {
            do{
                Tarea tarea=new Tarea(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7));
                tareaArrayList.add(tarea);
            }while (cursor.moveToNext());
        }
        MyOpenHelper.getInstance().closeDateBase();
        return tareaArrayList;
    }

    public void delete(int id) {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        sqLiteDatabase.delete(MyContrats.Tareas.TABLE_NAME, BaseColumns._ID+"=?",new String[]{ String.valueOf(id)} );
        MyOpenHelper.getInstance().closeDateBase();
    }

    public void set(int id, Tarea tarea) {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        ContentValues contentValues=createContent(tarea);
        sqLiteDatabase.update(MyContrats.Tareas.TABLE_NAME,contentValues,BaseColumns._ID+"=?",new String[]{ String.valueOf(id)});
        MyOpenHelper.getInstance().closeDateBase();
    }

    private ContentValues createContent(Tarea tarea) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(MyContrats.Tareas.COLUMN_NAME,tarea.get_name());
        contentValues.put(MyContrats.Tareas.COLUMN_DESCRIPTION,tarea.get_description());
        contentValues.put(MyContrats.Tareas.COLUMN_COLOR,tarea.get_color());
        contentValues.put(MyContrats.Tareas.COLUMN_DEADLINE,tarea.get_deadLine());
        contentValues.put(MyContrats.Tareas.COLUMN_DIFFICULTY,tarea.get_difficulty());
        contentValues.put(MyContrats.Tareas.COLUMN_PRIORITY,tarea.get_priority());
        contentValues.put(MyContrats.Tareas.COLUMN_IDPROYECTO,tarea.get_idProyecto());
        return contentValues;
    }

    public void add(String name, String description, String color, String deadLine, String priority, String difficulty, int idProyecto) {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        ContentValues contentValues=createContent(new Tarea(-1,name,description,color,deadLine,priority,difficulty,idProyecto));
        sqLiteDatabase.insert(MyContrats.Tareas.TABLE_NAME,null,contentValues);
        MyOpenHelper.getInstance().closeDateBase();
    }

    public void add(Tarea t) {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        ContentValues contentValues=createContent(t);
        sqLiteDatabase.insert(MyContrats.Tareas.TABLE_NAME,null,contentValues);
        MyOpenHelper.getInstance().closeDateBase();
    }
}
