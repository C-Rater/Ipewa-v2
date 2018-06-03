package com.craterstudio.juanfrancrater.ipewa.data.db.Repository.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.craterstudio.juanfrancrater.ipewa.data.db.Contrat.MyContrats;
import com.craterstudio.juanfrancrater.ipewa.data.db.MyOpenHelper;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;

import java.util.ArrayList;
import java.util.Calendar;

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
                Tarea tarea=new Tarea(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));
                tareaArrayList.add(tarea);
            }while (cursor.moveToNext());
        }
        MyOpenHelper.getInstance().closeDateBase();
        return tareaArrayList;
    }

    public void delete(String id) {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        sqLiteDatabase.delete(MyContrats.Tareas.TABLE_NAME, BaseColumns._ID+"=?",new String[]{ id} );
        MyOpenHelper.getInstance().closeDateBase();
    }

    public void set(String id, Tarea tarea) {
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
        contentValues.put(MyContrats.Tareas.COLUMN_IDTABLERO,tarea.get_idTablero());
        contentValues.put(MyContrats.Tareas.COLUMN_CREATOR,tarea.get_creator());

        return contentValues;
    }

    public void add(String name, String description, int color, String deadLine, String priority, String difficulty, String idProyecto,String idTablero,String creator) {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        ContentValues contentValues=createContent(new Tarea("a",name,description,color,deadLine,priority,difficulty,idProyecto,idTablero,creator));
        sqLiteDatabase.insert(MyContrats.Tareas.TABLE_NAME,null,contentValues);
        MyOpenHelper.getInstance().closeDateBase();
    }

    public void add(Tarea t) {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        ContentValues contentValues=createContent(t);
        sqLiteDatabase.insert(MyContrats.Tareas.TABLE_NAME,null,contentValues);
        MyOpenHelper.getInstance().closeDateBase();
    }

    public ArrayList<Tarea> loadByTab(int id) {
            final ArrayList<Tarea> tareaArrayList=new ArrayList<>();
            final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
            Cursor cursor = sqLiteDatabase.query(MyContrats.Tareas.TABLE_NAME,
                    MyContrats.Tareas.ALL_COLUMN,
                    MyContrats.Tareas.COLUMN_IDTABLERO+"=?",
                    new String[]{String.valueOf(id)},
                    null,
                    null,
                    MyContrats.Tareas.DEFAULT_SORT,
                    null);
            if(cursor.moveToFirst())
            {
                do{
                    Tarea tarea=new Tarea(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));
                    tareaArrayList.add(tarea);
                }while (cursor.moveToNext());
            }
            MyOpenHelper.getInstance().closeDateBase();
            return tareaArrayList;
    }

    public ArrayList getByDays(int days) {
        Calendar calendar =Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)+days);
        final ArrayList<Tarea> tareaArrayList=new ArrayList<>();
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        Cursor cursor = sqLiteDatabase.query(MyContrats.Tareas.TABLE_NAME,
                MyContrats.Tareas.ALL_COLUMN,
                MyContrats.Tareas.COLUMN_DEADLINE+"<?",
                new String[]{String.valueOf(calendar.get(Calendar.YEAR))+"/"+ String.valueOf(calendar.get(Calendar.MONTH))+"/"+ String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))},
                null,
                null,
                MyContrats.Tareas.DEFAULT_SORT,
                null);
        if(cursor.moveToFirst())
        {
            do{
                Tarea tarea=new Tarea(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));
                tareaArrayList.add(tarea);
            }while (cursor.moveToNext());
        }
        MyOpenHelper.getInstance().closeDateBase();
        return tareaArrayList;
    }

    public ArrayList<Tarea> getSortByDate() {

        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        Cursor cursor = sqLiteDatabase.query(MyContrats.Tareas.TABLE_NAME, null,
                null, null, null, null, MyContrats.Tareas.COLUMN_DEADLINE+" DESC ");

        final ArrayList<Tarea> tareaArrayList=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                Tarea tarea=new Tarea(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));
                tareaArrayList.add(tarea);
            }while (cursor.moveToNext());
        }
        MyOpenHelper.getInstance().closeDateBase();
        return tareaArrayList;
    }
}
