package com.craterstudio.juanfrancrater.ipewa.data.db.Repository;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.dao.TableroDao;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tablero;
import com.craterstudio.juanfrancrater.ipewa.util.AppPreferencesHelper;

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
    public ArrayList<Tablero> getTableros(String id)
    {
        tableros= dao.loadTableroByProyectos(id);
        return  tableros;
    }

    public void addTablero(String name,int position,String idProyecto ) {
        AppPreferencesHelper sharedPreferences=AppPreferencesHelper.getInstance();
        String creator=sharedPreferences.getCurrentUserID();
        String creatorname= sharedPreferences.getCurrentUserName();
        int id= sharedPreferences.getLastIDTablero();
        Tablero tablero =new Tablero(String.valueOf(id)+idProyecto,name,position,idProyecto,creatorname);
        dao.add(tablero);
        sharedPreferences.setLastIDTablero(id++);
    }

    public void deleteTablerosByProjectId(String id) {
        Iterator<Tablero> iterator= getInstance().getTableros(id).iterator();
        Tablero temp;
        while(iterator.hasNext())
        {
            temp=iterator.next();
            if(temp.get_idProyecto().equals(id))
            {
                dao.delete(temp.get_ID());
            }
        }
    }
}
