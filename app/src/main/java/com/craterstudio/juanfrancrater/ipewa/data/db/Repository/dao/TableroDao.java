package com.craterstudio.juanfrancrater.ipewa.data.db.Repository.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.craterstudio.juanfrancrater.ipewa.data.db.Contrat.MyContrats;
import com.craterstudio.juanfrancrater.ipewa.data.db.MyOpenHelper;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tablero;

import java.util.ArrayList;

/**
 * Created by juanf on 25/02/2018.
 */

public class TableroDao {
    public ArrayList<Tablero> loadTableroByProyectos(int id) {

        final ArrayList<Tablero> list=new ArrayList<>();

        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();

        Cursor cursor = sqLiteDatabase.query(MyContrats.Tablero.TABLE_NAME,
                MyContrats.Tablero.ALL_COLUMN,
                MyContrats.Tablero.COLUMN_IDPROYECTO+"=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                MyContrats.Tablero.DEFAULT_SORT,
                null);
        if(cursor.moveToFirst())
        {
            do{
                Tablero tablero =new Tablero(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4));
                list.add(tablero);
            }while (cursor.moveToNext());
        }
        MyOpenHelper.getInstance().closeDateBase();
        return list;
    }
}
