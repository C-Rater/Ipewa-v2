package com.oyasumisoft.juanfrancrater.ipewa.ui.task.Presenter;

import android.content.Context;

import com.oyasumisoft.juanfrancrater.ipewa.R;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Interactor.EditTaskInteractor;

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
    public void EditTask(int id, String name, String description, String color, String deadLine, String priority, String difficulty, int _idProyecto) {
        interactor.EditTask(id,name,description,color,deadLine,priority,difficulty,_idProyecto);
    }

    @Override
    public void getIdList() {
       interactor.getIdList();
    }
}
