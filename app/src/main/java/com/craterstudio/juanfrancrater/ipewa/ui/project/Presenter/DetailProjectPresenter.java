package com.craterstudio.juanfrancrater.ipewa.ui.project.Presenter;

import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Interactor.DetailProjectInteractor;

/**
 * Created by PcCom on 09/01/2018.
 */

public class DetailProjectPresenter implements ProjectContrat.DetailProject.Presenter, DetailProjectInteractor.DetailProjectListener {

    ProjectContrat.DetailProject.View view;
    ProjectContrat.DetailProject.Interactor interactor;
    public DetailProjectPresenter(ProjectContrat.DetailProject.View view) {
        this.view=view;
        this.interactor=new DetailProjectInteractor(this);
    }

    @Override
    public void getProject(int id) {
        interactor.getProject(id);
    }

    @Override
    public void deleteProject(Proyecto detailProject) {

        interactor.deleteProject(detailProject);

    }

    @Override
    public void reloadProject(Proyecto proyecto) {
        view.reloadProject(proyecto);
    }


    @Override
    public void reloadList() {
        view.reloadList();
    }
}
