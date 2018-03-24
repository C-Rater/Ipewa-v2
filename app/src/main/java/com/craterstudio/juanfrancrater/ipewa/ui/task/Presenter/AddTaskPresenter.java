package com.craterstudio.juanfrancrater.ipewa.ui.task.Presenter;

import android.content.Context;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.task.Interactor.AddTaskInteractor;

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
    public void addTask(String nombre, String description, int color, String deadLine, String priority, String difficulty, int _idProyecto, int idTablero) {
        interactor.addTask(nombre,description,color,deadLine,priority,difficulty,_idProyecto,idTablero);
    }

    @Override
    public void getIdList() {
        interactor.getIdList();
    }
}
