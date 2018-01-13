package com.oyasumisoft.juanfrancrater.ipewa.ui.project.Presenter;

import android.os.Parcelable;

import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Interactor.ListProjectInteractor;

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
    public void openEditProject(Parcelable object) {
        view.openDetailProject(object);
    }
}
