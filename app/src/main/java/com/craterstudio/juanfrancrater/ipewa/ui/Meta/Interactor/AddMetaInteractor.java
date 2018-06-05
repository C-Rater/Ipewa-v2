package com.craterstudio.juanfrancrater.ipewa.ui.Meta.Interactor;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.MetaRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;
import com.craterstudio.juanfrancrater.ipewa.ui.Meta.Contrats.MetaContrat;

import java.util.ArrayList;

/**
 * Created by usuario on 31/05/18.
 */

public class AddMetaInteractor implements MetaContrat.addMeta.Interactor {

    AddProjectListener listener;
    public AddMetaInteractor(AddProjectListener listener) {
        this.listener= listener;
    }

    @Override
    public void addMeta(String nombre, String description, int color, String deadLine, String priority, String difficulty, int _idProyecto) {
        if(!nombre.isEmpty()) {
            MetaRepository.getInstance().add(new Meta(0,nombre, description, color, deadLine, priority, difficulty,_idProyecto,"creator"));
            listener.back();
        }else
        {
            listener.showEmptyError();
        }
    }

    @Override
    public void getIdList() {
        listener.fillIdList(ProjectRepository.getInstance().getIdProjects(1));
    }

    public interface AddProjectListener {
        void back();
        void showEmptyError();
        void fillIdList(ArrayList<Integer> idProjects);
    }
}
