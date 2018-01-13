package com.oyasumisoft.juanfrancrater.ipewa.ui.project.Presenter;

import android.content.Context;

import com.oyasumisoft.juanfrancrater.ipewa.R;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Interactor.AddProjectInteractor;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Interactor.ListProjectInteractor;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.View.AddProjectActivity;

/**
 * Created by PcCom on 31/12/2017.
 */

public class AddProjectPresenter implements ProjectContrat.addProject.Presenter, AddProjectInteractor.AddProjectListener {

    ProjectContrat.addProject.Interactor interactor;
    ProjectContrat.addProject.View view;
    Context context;
    public AddProjectPresenter(ProjectContrat.addProject.View view,Context context) {
        this.view=view;
        interactor= new AddProjectInteractor(this);
        this.context=context;
        }

    @Override
    public void addProject(String nombre, String description, String color, String deadLine) {
        interactor.addProject(nombre,description,color,deadLine);
    }

    @Override
    public void showEmptyNameError() {
        view.showError(context.getString(R.string.ErrorEmptyName));
    }

    @Override
    public void onSuccess() {
        view.back();
    }
}
