package com.oyasumisoft.juanfrancrater.ipewa.data.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.oyasumisoft.juanfrancrater.ipewa.data.db.Contrat.MyContrats;
import com.oyasumisoft.juanfrancrater.ipewa.util.ThisApplication;

import java.util.concurrent.atomic.AtomicInteger;

public class MyOpenHelper extends SQLiteOpenHelper {

    private volatile static MyOpenHelper singleton;
    private SQLiteDatabase db;
    private AtomicInteger openCounter = new AtomicInteger();

    public MyOpenHelper( ){
        super(ThisApplication.getContext(), MyContrats.DATABASE_NAME,null ,MyContrats.DATABASE_VERSION );
    }

    public synchronized static MyOpenHelper getInstance() {
        if (singleton==null) {
            singleton = new MyOpenHelper();
        }
        return singleton;
    }

    public synchronized SQLiteDatabase openDateBase(){
        if (openCounter.incrementAndGet() == 1) {
            db = getWritableDatabase();
        }
        return db;
    }

    public synchronized  void closeDateBase(){
        if (openCounter.decrementAndGet()==0){
            db.close();
        }
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.execSQL(MyContrats.Proyectos.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(MyContrats.Proyectos.SQL_INSERT_ENTRIES);
            sqLiteDatabase.execSQL(MyContrats.Tablero.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(MyContrats.Tablero.SQL_INSERT_ENTRIES);
            sqLiteDatabase.execSQL(MyContrats.Tareas.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(MyContrats.Tareas.SQL_INSERT_ENTRIES);
            sqLiteDatabase.execSQL(MyContrats.Metas.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(MyContrats.Metas.SQL_INSERT_ENTRIES);
            sqLiteDatabase.execSQL(MyContrats.Actividad.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(MyContrats.Actividad.SQL_INSERT_ENTRIES);
            sqLiteDatabase.setTransactionSuccessful();
        }catch (SQLiteException e)
        {
            Log.d("Error",e.getMessage());
        }finally {
            sqLiteDatabase.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            db.beginTransaction();
            db.execSQL(MyContrats.Actividad.SQL_DELETE_ENTRIES);
            db.execSQL(MyContrats.Metas.SQL_DELETE_ENTRIES);
            db.execSQL(MyContrats.Tareas.SQL_DELETE_ENTRIES);
            db.execSQL(MyContrats.Tablero.SQL_DELETE_ENTRIES);
            db.execSQL(MyContrats.Proyectos.SQL_DELETE_ENTRIES);
            onCreate(db);
            db.setTransactionSuccessful();
        }catch (SQLiteException e)
        {
        }finally {
            db.endTransaction();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=1");
            }
        }
    }
}
