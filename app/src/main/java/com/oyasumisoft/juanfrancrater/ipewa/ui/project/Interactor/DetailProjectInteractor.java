package com.oyasumisoft.juanfrancrater.ipewa.ui.project.Interactor;

import com.google.firebase.messaging.FirebaseMessaging;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.TareaRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Presenter.DetailProjectPresenter;

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
        ProjectRepository.getInstance().deleteProject(detailProject);
        TareaRepository.getInstance().deleteTareasByProjectId(detailProject.get_ID());
        FirebaseMessaging.getInstance().subscribeToTopic(String.valueOf(detailProject.get_ID()));
        listener.reloadList();
    }

    public interface DetailProjectListener
    {
        void reloadProject(Proyecto proyecto);

        void reloadList();
    }
}
