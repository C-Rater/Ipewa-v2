package com.craterstudio.juanfrancrater.ipewa.ui.task.Presenter;

import android.content.Context;

import com.craterstudio.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.task.Interactor.EditTaskInteractor;

import java.util.ArrayList;

/**
 * Created by PcCom on 04/01/2018.
 */

public class EditTaskPresenter implements TaskContrat.editTask.Presenter,EditTaskInteractor.EditTaskListener {

    TaskContrat.editTask.View view;
    TaskContrat.editTask.Interactor interactor;
    private Context context;
    public EditTaskPresenter(TaskContrat.editTask.View view) {
        this.view=view;
        this.interactor=new EditTaskInteractor(this);
        this.context=(Context)view;
    }

    @Override
    public void back() {
        view.back();
    }

    @Override
    public void showEmptyName() {
        view.showError();
    }

    @Override
    public void fillIdList(ArrayList<String> idProjects) {
        view.fillIdList(idProjects);
    }

    @Override
    public void EditTask(String id, String name, String description, int color, String deadLine, String priority, String difficulty, String _idProyecto,String idTablero, String creator) {
        interactor.EditTask(id,name,description,color,deadLine,priority,difficulty,_idProyecto, idTablero,creator);
    }

    @Override
    public void getIdList() {
       interactor.getIdList();
    }
}
