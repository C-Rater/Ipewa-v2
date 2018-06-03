package com.craterstudio.juanfrancrater.ipewa.data.db.Contrat;

import android.provider.BaseColumns;

/*
 * Created by juanf on 24/02/2018.
 */

public class MyContrats {
    public static final String DATABASE_NAME = "ipewapp";
    public static final int DATABASE_VERSION = 10;

    public static class Proyectos{
        public static final String TABLE_NAME = "proyecto";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_COLOR = "color";
        public static final String COLUMN_DEADLINE = "deadline";
        public static final String COLUMN_CREATOR="creator";
        public static final String[] ALL_COLUMN = {BaseColumns._ID,COLUMN_NAME, COLUMN_DESCRIPTION,COLUMN_COLOR,COLUMN_DEADLINE,COLUMN_CREATOR} ;
        public static final String DEFAULT_SORT = BaseColumns._ID;
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);
        public static final String SQL_CREATE_ENTRIES = String.format(

                "CREATE TABLE %s (%s TEXT PRIMARY KEY, " +

                        "%s TEXT NOT NULL," +
                        "%s TEXT," +
                        "%s INTEGER NOT NULL,"+
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL)",
                TABLE_NAME,
                BaseColumns._ID,
                COLUMN_NAME,COLUMN_DESCRIPTION,COLUMN_COLOR,COLUMN_DEADLINE,COLUMN_CREATOR);

    }
    public static class Metas{
        public static final String TABLE_NAME = "meta";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_COLOR = "color";
        public static final String COLUMN_DEADLINE = "deadline";
        public static final String COLUMN_PRIORITY = "priority";
        public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_CREATOR="creator";
        public static final String COLUMN_IDPROYECTO="idProyecto";

        public static final String[] ALL_COLUMN = {BaseColumns._ID,COLUMN_NAME, COLUMN_DESCRIPTION,COLUMN_COLOR,COLUMN_DEADLINE,COLUMN_PRIORITY,COLUMN_DIFFICULTY,COLUMN_IDPROYECTO,COLUMN_CREATOR} ;
        public static final String DEFAULT_SORT = BaseColumns._ID;

        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);
        public static final String SQL_CREATE_ENTRIES = String.format(

                "CREATE TABLE %s (%s TEXT PRIMARY KEY, " +

                        "%s TEXT NOT NULL," +
                        "%s TEXT," +
                        "%s INTEGER NOT NULL,"+
                        "%s TEXT NOT NULL,"+
                        "%s TEXT,"+
                        "%s TEXT," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "FOREIGN KEY(%s) REFERENCES %s(%s))",
                TABLE_NAME,

                BaseColumns._ID,
                COLUMN_NAME,COLUMN_DESCRIPTION,COLUMN_COLOR,COLUMN_DEADLINE,COLUMN_PRIORITY,COLUMN_DIFFICULTY,COLUMN_IDPROYECTO,COLUMN_CREATOR,COLUMN_IDPROYECTO,Proyectos.TABLE_NAME,BaseColumns._ID);

    }
    public static class Actividad{
        public static final String TABLE_NAME = "actividad";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_COLOR = "color";

        public static final String[] ALL_COLUMN = {BaseColumns._ID,COLUMN_NAME, COLUMN_DESCRIPTION,COLUMN_COLOR} ;
        public static final String DEFAULT_SORT = BaseColumns._ID;
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);
        public static final String SQL_CREATE_ENTRIES = String.format(

                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        "%s TEXT NOT NULL," +

                        "%s TEXT," +

                        "%s TEXT)",

                TABLE_NAME,

                BaseColumns._ID,

                COLUMN_NAME,

                COLUMN_DESCRIPTION,

                COLUMN_COLOR

        );


    }
    public static class Tablero{
        public static final String TABLE_NAME = "tablero";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_POSITION = "position";
        public static final String COLUMN_IDPROYECTO="idProyecto";

        public static final String COLUMN_CREATOR="creator";
        public static final String[] ALL_COLUMN = {BaseColumns._ID,COLUMN_NAME, COLUMN_POSITION,COLUMN_IDPROYECTO,COLUMN_CREATOR} ;
        public static final String DEFAULT_SORT = COLUMN_POSITION;

        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);
        public static final String SQL_CREATE_ENTRIES = String.format(

                "CREATE TABLE %s (%s TEXT PRIMARY KEY , " +

                        "%s TEXT NOT NULL," +
                        "%s INTEGER NOT NULL,"+
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "FOREIGN KEY(%s) REFERENCES %s(%s))",
                TABLE_NAME,

                BaseColumns._ID,
                COLUMN_NAME, COLUMN_POSITION,COLUMN_IDPROYECTO,COLUMN_CREATOR,COLUMN_IDPROYECTO,Proyectos.TABLE_NAME,BaseColumns._ID);

    }
    public static class Tareas{
        public static final String TABLE_NAME = "tarea";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_COLOR = "color";
        public static final String COLUMN_DEADLINE = "deadline";
        public static final String COLUMN_PRIORITY = "priority";
        public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_IDPROYECTO = "idProyecto";
        public static final String COLUMN_IDTABLERO = "idTablero";


        public static final String COLUMN_CREATOR="creator";

        public static final String[] ALL_COLUMN = {BaseColumns._ID,COLUMN_NAME, COLUMN_DESCRIPTION,COLUMN_COLOR,COLUMN_DEADLINE,COLUMN_PRIORITY,COLUMN_DIFFICULTY,COLUMN_IDPROYECTO,COLUMN_IDTABLERO,COLUMN_CREATOR} ;
        public static final String DEFAULT_SORT = BaseColumns._ID;

        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);
        public static final String SQL_CREATE_ENTRIES = String.format(

                "CREATE TABLE %s (%s TEXT PRIMARY KEY, " +

                        "%s TEXT NOT NULL," +
                        "%s TEXT," +
                        "%s INTEGER NOT NULL,"+
                        "%s TEXT NOT NULL,"+
                        "%s TEXT,"+
                        "%s TEXT,"+
                        "%s TEXT,"+
                        "%s TEXT," +
                        "%s TEXT NOT NULL," +
                        "FOREIGN KEY(%s) REFERENCES %s(%s))",
                TABLE_NAME,

                BaseColumns._ID,
                COLUMN_NAME,COLUMN_DESCRIPTION,COLUMN_COLOR,COLUMN_DEADLINE,COLUMN_PRIORITY,COLUMN_DIFFICULTY,COLUMN_IDPROYECTO,COLUMN_IDTABLERO,COLUMN_CREATOR,COLUMN_IDPROYECTO,Proyectos.TABLE_NAME,BaseColumns._ID,COLUMN_IDTABLERO);


    }
}
