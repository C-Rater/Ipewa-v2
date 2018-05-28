package com.craterstudio.juanfrancrater.ipewa.data.db.Repository;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.dao.MetaDao;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;
import com.craterstudio.juanfrancrater.ipewa.util.AppPreferencesHelper;
import com.google.firebase.auth.FirebaseAuth;

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
    public ArrayList<Meta> getMetasByProject(String id) {
        return dao.getMetasByProject(id);
    }
    public ArrayList<Meta> getMetasInDays(int id) {
        return dao.getByDays(id);
    }

    public void delete(Meta meta) {
        dao.delete(meta);
    }

    private AppPreferencesHelper sharedPreferences;
    public void add(Meta meta) {
        String creator= FirebaseAuth.getInstance().getCurrentUser().getUid();
        String creatorname= FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        int id= sharedPreferences.getLastIDMeta();
        dao.add(new Meta(String.valueOf(id)+creator,meta.get_name(),meta.get_description(),meta.get_color(),meta.get_deadLine(),meta.get_priority(),meta.get_difficulty(),meta.get_idProyecto(),creatorname));
        sharedPreferences.setLastIdMeta(id++);
    }
    public void edit(Meta meta) {
        dao.edit(meta);
    }

    public void deleteMetasByProjectId(String id) {
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

    public void delete(String i) {
        dao.delete(i);
    }
}
