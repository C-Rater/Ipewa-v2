package com.craterstudio.juanfrancrater.ipewa.data.db.Repository;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.dao.TableroDao;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tablero;

import java.util.ArrayList;

/**
 * Created by juanf on 25/02/2018.
 */

public class TableroRepository {
    ArrayList<Tablero> tableros;
    TableroDao dao;

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
}
