package com.craterstudio.juanfrancrater.ipewa.ui.project.Presenter;

import android.content.Context;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Interactor.EditProjectInteractor;

/**
 * Created by PcCom on 31/12/2017.
 */

public class EditProjectPresenter implements ProjectContrat.editProject.Presenter, EditProjectInteractor.EditProjectListener {

    ProjectContrat.editProject.View view;
    ProjectContrat.editProject.Interactor interactor;
    private Context context;
    public EditProjectPresenter(ProjectContrat.editProject.View view) {
        this.view=view;
        this.interactor=new EditProjectInteractor(this);
        this.context=(Context)view;
    }


    @Override
    public void EditProject(int id, String name, String description, int color, String deadLine,String creator) {
        interactor.EditProject(id,name,description,color,deadLine,creator);
    }

    @Override
    public void getProject(int editProyect) {
        interactor.getProject(editProyect);
    }

    @Override
    public void loadProject(Proyecto project) {
        view.loadProject(project);
    }

    @Override
    public void showErrorEmptyName() {
        view.showError(context.getString(R.string.ErrorEmptyName));
    }

    @Override
    public void back() {
        view.back();
    }
}
