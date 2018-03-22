package com.craterstudio.juanfrancrater.ipewa.data.db.Repository;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.dao.MetaDao;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;

import java.util.ArrayList;

/**
 * Created by juanf on 25/02/2018.
 */

public class MetaRepository {
    ArrayList<Meta> metas;
    MetaDao dao;

    static MetaRepository metaRepository;

    static {
        metaRepository = new MetaRepository();
    }


    private MetaRepository()
    {
        this.metas = new ArrayList<>();
        dao=new MetaDao();
    }

    public static MetaRepository getInstance()
    {
        return metaRepository;
    }
    public ArrayList<Meta> getMetasByProject(int id) {
        return dao.getMetasByProject(id);
    }
    public ArrayList<Meta> getMetasInDays(int id) {
        return dao.getByDays(id);
    }

    public void delete(Meta meta) {
        dao.delete(meta);
    }
    public void add(Meta meta) {
        dao.add(meta);
    }
    public void edit(Meta meta) {
        dao.edit(meta);
    }

    public void deleteMetasByProjectId(int id) {
        for(int i=0; i<metas.size();i++){
            if(metas.get(i).get_idProyecto()==id)
            { dao.delete(metas.get(i));}
        }

    }

    public ArrayList<Meta> getMetas() {
        return dao.loadAll();
    }

    public ArrayList<Meta> getMetasSortByDate() {
        return dao.getSortByDate();
    }
}
