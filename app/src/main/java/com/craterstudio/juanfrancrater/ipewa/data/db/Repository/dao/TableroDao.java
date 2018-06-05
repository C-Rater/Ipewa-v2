package com.craterstudio.juanfrancrater.ipewa.data.db.Repository.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.craterstudio.juanfrancrater.ipewa.data.db.Contrat.MyContrats;
import com.craterstudio.juanfrancrater.ipewa.data.db.MyOpenHelper;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tablero;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;

import java.util.ArrayList;

/**
 * Created by juanf on 25/02/2018.
 */

public class TableroDao {
    public ArrayList<Tablero> loadTableroByProyectos(String id) {

        final ArrayList<Tablero> list=new ArrayList<>();

        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();

        Cursor cursor = sqLiteDatabase.query(MyContrats.Tablero.TABLE_NAME,
                MyContrats.Tablero.ALL_COLUMN,
                MyContrats.Tablero.COLUMN_IDPROYECTO+"=?",
                new String[]{id},
                null,
                null,
                MyContrats.Tablero.DEFAULT_SORT,
                null);
        if(cursor.moveToFirst())
        {
            do{
                Tablero tablero =new Tablero(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4));
                list.add(tablero);
            }while (cursor.moveToNext());
        }
        MyOpenHelper.getInstance().closeDateBase();
        return list;
    }
    public void add(Tablero t) {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        ContentValues contentValuesT=createContent(t);
        sqLiteDatabase.insert(MyContrats.Tablero.TABLE_NAME,null,contentValuesT);
        MyOpenHelper.getInstance().closeDateBase();
    }
    private ContentValues createContent(Tablero t) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(BaseColumns._ID,t.get_ID());
        contentValues.put(MyContrats.Tablero.COLUMN_NAME,t.get_name());
        contentValues.put(MyContrats.Tablero.COLUMN_POSITION,t.get_position());
        contentValues.put(MyContrats.Tablero.COLUMN_CREATOR,t.get_creator());
        contentValues.put(MyContrats.Tablero.COLUMN_IDPROYECTO,t.get_idProyecto());
        return contentValues;
    }

    public void delete(String id) {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        sqLiteDatabase.delete(MyContrats.Tablero.TABLE_NAME, BaseColumns._ID+"=?",new String[]{ id} );
        MyOpenHelper.getInstance().closeDateBase();
    }
}
