package com.craterstudio.juanfrancrater.ipewa.ui.project.Presenter;

import android.os.Parcelable;

import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Interactor.ListProjectInteractor;

import java.util.ArrayList;

/**
 * Created by PcCom on 31/12/2017.
 */

public class ListProjectPresenter implements ProjectContrat.listProject.Presenter, ListProjectInteractor.ListProjectListener {

    private ProjectContrat.listProject.Interactor interactor;
    private ProjectContrat.listProject.View view;

    public ListProjectPresenter(ProjectContrat.listProject.View view) {
        this.view=view;
        this.interactor= new ListProjectInteractor(this);
    }

    @Override
    public void getProjectAtPosition(int position) {
        interactor.getProject(position);
    }

    @Override
    public void getProjects() {
        interactor.getProjects();
    }

    @Override
    public void openEditProject(Parcelable object) {
        view.openDetailProject(object);
    }

    @Override
    public void reloadProjects(ArrayList<Proyecto> projects) {
        view.recargarProjects(projects);
    }
}
