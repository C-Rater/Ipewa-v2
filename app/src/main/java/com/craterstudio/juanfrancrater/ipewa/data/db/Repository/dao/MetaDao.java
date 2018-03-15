package com.craterstudio.juanfrancrater.ipewa.data.db.Repository.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.craterstudio.juanfrancrater.ipewa.data.db.Contrat.MyContrats;
import com.craterstudio.juanfrancrater.ipewa.data.db.MyOpenHelper;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by juanf on 25/02/2018.
 */

public class MetaDao {
public static ArrayList<Meta> getMetasByProject(int id){
    final ArrayList<Meta> list = new ArrayList<>();

    final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();

    Cursor cursor = sqLiteDatabase.query(MyContrats.Metas.TABLE_NAME,
            MyContrats.Metas.ALL_COLUMN,
            MyContrats.Metas.COLUMN_IDPROYECTO + "=?",
            new String[]{String.valueOf(id)},
            null,
            null,
            MyContrats.Metas.DEFAULT_SORT,
            null);
        if(cursor.moveToFirst())

    {
        do {
            Meta meta = new Meta(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7));
            list.add(meta);
        } while (cursor.moveToNext());
    }
        MyOpenHelper.getInstance().closeDateBase();
        return list;
}
    public ArrayList getByDays(int days) {
        Calendar calendar =Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)+days);
        final ArrayList<Meta> metaArrayList=new ArrayList<>();
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        Cursor cursor = sqLiteDatabase.query(MyContrats.Metas.TABLE_NAME,
                MyContrats.Metas.ALL_COLUMN,
                MyContrats.Metas.COLUMN_DEADLINE+"<?",
                new String[]{String.valueOf(calendar.get(Calendar.YEAR))+"/"+ String.valueOf(calendar.get(Calendar.MONTH))+"/"+ String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))},
                null,
                null,
                MyContrats.Metas.DEFAULT_SORT,
                null);
        if(cursor.moveToFirst())
        {
            do{
                Meta meta = new Meta(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7));
                metaArrayList.add(meta);
            }while (cursor.moveToNext());
        }
        MyOpenHelper.getInstance().closeDateBase();
        return metaArrayList;
    }
}
