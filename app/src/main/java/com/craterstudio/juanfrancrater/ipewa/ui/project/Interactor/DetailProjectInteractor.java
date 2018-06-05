package com.craterstudio.juanfrancrater.ipewa.ui.project.Interactor;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.MetaRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.TableroRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.TareaRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;

/**
 * Created by PcCom on 09/01/2018.
 */

public class DetailProjectInteractor implements ProjectContrat.DetailProject.Interactor {

    DetailProjectListener listener;
    public DetailProjectInteractor(DetailProjectListener listener) {
        this.listener=listener;
    }

    @Override
    public void getProject(int id) {
        listener.reloadProject(ProjectRepository.getInstance().getProject(id));
    }

    @Override
    public void deleteProject(Proyecto detailProject) {
        TableroRepository.getInstance().deleteTablerosByProjectId(detailProject.get_ID());
        MetaRepository.getInstance().deleteMetasByProjectId(detailProject.get_ID());
        TareaRepository.getInstance().deleteTareasByProjectId(detailProject.get_ID());
        ProjectRepository.getInstance().deleteProject(detailProject);
        listener.reloadList();
    }

    public interface DetailProjectListener
    {
        void reloadProject(Proyecto proyecto);

        void reloadList();
    }
}
