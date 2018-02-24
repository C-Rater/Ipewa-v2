package com.oyasumisoft.juanfrancrater.ipewa.data.db.Contrat;

import android.provider.BaseColumns;

/*
 * Created by juanf on 24/02/2018.
 */

public class MyContrats {
    public static final String DATABASE_NAME = "ipewapp";
    public static final int DATABASE_VERSION = 2;

    public static class Proyectos{
        public static final String TABLE_NAME = "proyecto";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_COLOR = "color";
        public static final String COLUMN_DEADLINE = "deadline";
        public static final String[] ALL_COLUMN = {BaseColumns._ID,COLUMN_NAME, COLUMN_DESCRIPTION,COLUMN_COLOR,COLUMN_DEADLINE} ;
        public static final String DEFAULT_SORT = BaseColumns._ID;
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);
        public static final String SQL_CREATE_ENTRIES = String.format(

                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        "%s TEXT NOT NULL," +
                        "%s TEXT," +
                        "%s TEXT NOT NULL,"+
                        "%s TEXT NOT NULL)",
                TABLE_NAME,
                BaseColumns._ID,
                COLUMN_NAME,COLUMN_DESCRIPTION,COLUMN_COLOR,COLUMN_DEADLINE);
        public static final String SQL_INSERT_ENTRIES = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES ('%s','%s','%s','%s')",
                TABLE_NAME,
                COLUMN_NAME,
                COLUMN_DESCRIPTION,
                COLUMN_COLOR,
                COLUMN_DEADLINE,
                "Aprender Japones",
                "Aprender Japones, conociendo tanto los tres alfabetos como saber hablarlo",
                "Red",
                "01/01/2020"
        );
    }
    public static class Metas{
        public static final String TABLE_NAME = "meta";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_COLOR = "color";
        public static final String COLUMN_DEADLINE = "deadline";
        public static final String COLUMN_PRIORITY = "priority";
        public static final String COLUMN_DIFFICULTY = "difficulty";

        public static final String[] ALL_COLUMN = {BaseColumns._ID,COLUMN_NAME, COLUMN_DESCRIPTION,COLUMN_COLOR,COLUMN_DEADLINE,COLUMN_PRIORITY,COLUMN_DIFFICULTY} ;
        public static final String DEFAULT_SORT = BaseColumns._ID;

        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);
        public static final String SQL_CREATE_ENTRIES = String.format(

                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        "%s TEXT NOT NULL," +
                        "%s TEXT," +
                        "%s TEXT NOT NULL,"+
                        "%s TEXT NOT NULL,"+
                        "%s TEXT,"+
                        "%s TEXT)",
                TABLE_NAME,

                BaseColumns._ID,
                COLUMN_NAME,COLUMN_DESCRIPTION,COLUMN_COLOR,COLUMN_DEADLINE,COLUMN_PRIORITY,COLUMN_DIFFICULTY);
        public static final String SQL_INSERT_ENTRIES = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s) VALUES ('%s','%s','%s','%s','%s','%s')",
                TABLE_NAME,
                COLUMN_NAME,
                COLUMN_DESCRIPTION,
                COLUMN_COLOR,
                COLUMN_DEADLINE,
                COLUMN_PRIORITY,
                COLUMN_DIFFICULTY,
                "1000 Palabras",
                "Aprender 1000 palabras de vocabulario",
                "Red",
                "10/08/2018",
                "Media",
                "Media"
        );
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
        public static final String SQL_INSERT_ENTRIES = String.format("INSERT INTO %s (%s,%s,%s) VALUES ('%s','%s','%s')",

                TABLE_NAME,

                COLUMN_NAME,

                COLUMN_DESCRIPTION,

                COLUMN_COLOR,

                "Idioma",

                "Aprender un idioma además del ingles",

                "Red"
        );

    }
    public static class Tablero{
        public static final String TABLE_NAME = "tablero";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_POSITION = "position";

        public static final String[] ALL_COLUMN = {BaseColumns._ID,COLUMN_NAME, COLUMN_POSITION} ;
        public static final String DEFAULT_SORT = BaseColumns._ID;

        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);
        public static final String SQL_CREATE_ENTRIES = String.format(

                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        "%s TEXT NOT NULL," +
                        "%s INTEGER NOT NULL)",
                TABLE_NAME,

                BaseColumns._ID,
                COLUMN_NAME, COLUMN_POSITION);
        public static final String SQL_INSERT_ENTRIES = String.format("INSERT INTO %s (%s,%s) VALUES ('%s','%s')",
                TABLE_NAME,
                COLUMN_NAME,
                COLUMN_POSITION,
                "TO DO",
                "1"
        );
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

        public static final String[] ALL_COLUMN = {BaseColumns._ID,COLUMN_NAME, COLUMN_DESCRIPTION,COLUMN_COLOR,COLUMN_DEADLINE,COLUMN_PRIORITY,COLUMN_DIFFICULTY,COLUMN_IDPROYECTO} ;
        public static final String DEFAULT_SORT = BaseColumns._ID;

        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);
        public static final String SQL_CREATE_ENTRIES = String.format(

                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        "%s TEXT NOT NULL," +
                        "%s TEXT," +
                        "%s TEXT NOT NULL,"+
                        "%s TEXT NOT NULL,"+
                        "%s TEXT,"+
                        "%s TEXT,"+
                        "%s INTEGER)",
                TABLE_NAME,

                BaseColumns._ID,
                COLUMN_NAME,COLUMN_DESCRIPTION,COLUMN_COLOR,COLUMN_DEADLINE,COLUMN_PRIORITY,COLUMN_DIFFICULTY,COLUMN_IDPROYECTO);

        public static final String SQL_INSERT_ENTRIES = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s) VALUES ('%s','%s','%s','%s','%s','%s','%s')",
                TABLE_NAME,
                COLUMN_NAME,
                COLUMN_DESCRIPTION,
                COLUMN_COLOR,
                COLUMN_DEADLINE,
                COLUMN_PRIORITY,
                COLUMN_DIFFICULTY,
                COLUMN_IDPROYECTO,
                "10 Palabras",
                "Aprender 10 palabras de vocabulario",
                "Blue",
                "10/05/2018",
                "Baja",
                "Baja",
                "1"
        );
    }

    public static class Usuarios{
        //Los datos de usuarios en FireBase???
    }
}
