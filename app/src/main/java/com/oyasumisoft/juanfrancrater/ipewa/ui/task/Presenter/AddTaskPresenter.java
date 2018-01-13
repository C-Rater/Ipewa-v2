package com.oyasumisoft.juanfrancrater.ipewa.ui.task.Presenter;

import android.content.Context;

import com.oyasumisoft.juanfrancrater.ipewa.R;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Interactor.AddProjectInteractor;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Interactor.AddTaskInteractor;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.View.AddTaskActivity;

import java.util.ArrayList;

/**
 * Created by PcCom on 04/01/2018.
 */

public class AddTaskPresenter implements TaskContrat.addTask.Presenter, AddTaskInteractor.AddProjectListener {

    TaskContrat.addTask.Interactor interactor;
    TaskContrat.addTask.View view;
    Context context;
    public AddTaskPresenter(TaskContrat.addTask.View view,Context context) {
        this.view=view;
        interactor= new AddTaskInteractor(this);
        this.context=context;
    }

    @Override
    public void back() {
        view.back();
    }

    @Override
    public void showEmptyError() {
        view.showError(context.getString(R.string.ErrorEmptyName));
    }

    @Override
    public void fillIdList(ArrayList<String> idProjects) {
        view.fillIdList(idProjects);
    }

    @Override
    public void addTask(String nombre, String description, String color, String deadLine, String priority, String difficulty, int _idProyecto) {
        interactor.addTask(nombre,description,color,deadLine,priority,difficulty,_idProyecto);
    }

    @Override
    public void getIdList() {
        interactor.getIdList();
    }
}
