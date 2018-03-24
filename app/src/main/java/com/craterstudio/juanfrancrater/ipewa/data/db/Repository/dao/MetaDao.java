package com.craterstudio.juanfrancrater.ipewa.data.db.Repository.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

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
            Meta meta = new Meta(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7));
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
                Meta meta = new Meta(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7));
                metaArrayList.add(meta);
            }while (cursor.moveToNext());
        }
        MyOpenHelper.getInstance().closeDateBase();
        return metaArrayList;
    }

    public void delete(Meta meta) {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        sqLiteDatabase.delete(MyContrats.Metas.TABLE_NAME, BaseColumns._ID+"=?",new String[]{ String.valueOf(meta.get_ID())} );
        MyOpenHelper.getInstance().closeDateBase();
    }

    public void add(Meta meta) {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        ContentValues contentValues=createContent(meta);
        sqLiteDatabase.insert(MyContrats.Metas.TABLE_NAME,null,contentValues);
        MyOpenHelper.getInstance().closeDateBase();
    }

    public void edit(Meta meta) {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        ContentValues contentValues=createContent(meta);
        sqLiteDatabase.update(MyContrats.Metas.TABLE_NAME,contentValues,BaseColumns._ID+"=?",new String[]{ String.valueOf(meta.get_ID())});
        MyOpenHelper.getInstance().closeDateBase();
    }
    private ContentValues createContent(Meta meta) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(MyContrats.Metas.COLUMN_NAME,meta.get_name());
        contentValues.put(MyContrats.Metas.COLUMN_DESCRIPTION,meta.get_description());
        contentValues.put(MyContrats.Metas.COLUMN_COLOR,meta.get_color());
        contentValues.put(MyContrats.Metas.COLUMN_DEADLINE,meta.get_deadLine());
        contentValues.put(MyContrats.Metas.COLUMN_DIFFICULTY,meta.get_difficulty());
        contentValues.put(MyContrats.Metas.COLUMN_PRIORITY,meta.get_priority());
        contentValues.put(MyContrats.Metas.COLUMN_IDPROYECTO,meta.get_idProyecto());
        return contentValues;
    }

    public ArrayList<Meta> loadAll() {
        final ArrayList<Meta> list = new ArrayList<>();

        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();

        Cursor cursor = sqLiteDatabase.query(MyContrats.Metas.TABLE_NAME,
                MyContrats.Metas.ALL_COLUMN,
                null,
                null,
                null,
                null,
                MyContrats.Metas.DEFAULT_SORT,
                null);
        if(cursor.moveToFirst())

        {
            do {
                Meta meta = new Meta(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7));
                list.add(meta);
            } while (cursor.moveToNext());
        }
        MyOpenHelper.getInstance().closeDateBase();
        return list;
    }

    public ArrayList<Meta> getSortByDate() {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        Cursor cursor = sqLiteDatabase.query(MyContrats.Metas.TABLE_NAME, null,
                null, null, null, null, MyContrats.Metas.COLUMN_DEADLINE+" DESC ");

        final ArrayList<Meta> metaArrayList=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                Meta meta=new Meta(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7));
                metaArrayList.add(meta);
            }while (cursor.moveToNext());
        }
        MyOpenHelper.getInstance().closeDateBase();
        return metaArrayList;
    }

    public void delete(int i) {
        final SQLiteDatabase sqLiteDatabase = MyOpenHelper.getInstance().openDateBase();
        sqLiteDatabase.delete(MyContrats.Metas.TABLE_NAME, BaseColumns._ID+"=?",new String[]{String.valueOf(i)} );
        MyOpenHelper.getInstance().closeDateBase();
    }
}
