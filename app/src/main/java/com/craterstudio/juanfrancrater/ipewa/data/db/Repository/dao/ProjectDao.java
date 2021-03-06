package com.craterstudio.juanfrancrater.ipewa.data.db.Repository.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.craterstudio.juanfrancrater.ipewa.data.db.Contrat.MyContrats;
import com.craterstudio.juanfrancrater.ipewa.data.db.MyOpenHelper;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;

import java.util.ArrayList;

/**
 * Created by juanf on 24/02/2018.
 */

public class ProjectDao {
    public ArrayList<Proyecto> loadAll() {
        final ArrayList<Proyecto> proyectosArrayList = new ArrayList<>();
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        Cursor cursor = sqLiteDatabase.query(MyContrats.Proyectos.TABLE_NAME,
                MyContrats.Proyectos.ALL_COLUMN,
                null,
                null,
                null,
                null,
                MyContrats.Proyectos.DEFAULT_SORT,
                null);
        if (cursor.moveToFirst()) {
            do {
                Proyecto proyecto = new Proyecto(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4),cursor.getString(5));
                proyectosArrayList.add(proyecto);
            } while (cursor.moveToNext());
        }
        MyOpenHelper.getInstance().closeDateBase();
        return proyectosArrayList;
    }

    public void delete(int id) {
        try{
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        sqLiteDatabase.delete(MyContrats.Proyectos.TABLE_NAME, BaseColumns._ID+"=?",new String[]{(String.valueOf( id))} );
        MyOpenHelper.getInstance().closeDateBase();}catch (Exception e)
        {
            Log.d("Error",e.getMessage());}
    }

    public void set(int id, Proyecto p) {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        ContentValues contentValues=createContent(p);
        sqLiteDatabase.update(MyContrats.Proyectos.TABLE_NAME,contentValues,BaseColumns._ID+"=?",new String[]{ String.valueOf(id)});
        MyOpenHelper.getInstance().closeDateBase();
    }

    private ContentValues createContent(Proyecto p) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(MyContrats.Proyectos.COLUMN_NAME,p.get_name());
        contentValues.put(MyContrats.Proyectos.COLUMN_COLOR,p.get_color());
        contentValues.put(MyContrats.Proyectos.COLUMN_DEADLINE,p.get_deadLine());
        contentValues.put(MyContrats.Proyectos.COLUMN_DESCRIPTION,p.get_description());
        contentValues.put(MyContrats.Proyectos.COLUMN_CREATOR,p.get_creator());

        return contentValues;
    }

    public long add(Proyecto p) {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        ContentValues contentValuesP=createContent(p);
       long id= sqLiteDatabase.insert(MyContrats.Proyectos.TABLE_NAME,null,contentValuesP);
        MyOpenHelper.getInstance().closeDateBase();
        return id;
    }
}
