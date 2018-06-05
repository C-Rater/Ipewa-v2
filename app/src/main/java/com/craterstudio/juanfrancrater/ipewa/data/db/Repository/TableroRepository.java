package com.craterstudio.juanfrancrater.ipewa.data.db.Repository;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.dao.TableroDao;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tablero;
import com.craterstudio.juanfrancrater.ipewa.util.AppPreferencesHelper;
import com.craterstudio.juanfrancrater.ipewa.util.ThisApplication;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by juanf on 25/02/2018.
 */

public class TableroRepository {
    private ArrayList<Tablero> tableros;
    private TableroDao dao;

    static TableroRepository tableroRepository;

    static {
        tableroRepository = new TableroRepository();
    }

    public static TableroRepository getInstance()
    {
        return tableroRepository;
    }

    private TableroRepository()
    {
        this.tableros = new ArrayList<>();
        dao=new TableroDao();
    }
    public ArrayList<Tablero> getTableros(int id)
    {
        tableros= dao.loadTableroByProyectos(id);
        return  tableros;
    }

    public void addTablero(String name,int position,int idProyecto ) {
        AppPreferencesHelper sharedPreferences= ThisApplication.getAppPreferencesHelper().getInstance();
        String creatorname= sharedPreferences.getCurrentUserName();
        Tablero tablero =new Tablero(0,name,position,idProyecto,creatorname);
        dao.add(tablero);
    }

    public void deleteTablerosByProjectId(int id) {
        Iterator<Tablero> iterator= getInstance().getTableros(id).iterator();
        Tablero temp;
        while(iterator.hasNext())
        {
            temp=iterator.next();
            if(temp.get_idProyecto()==id)
            {
                dao.delete(temp.get_ID());
            }
        }
    }
}
