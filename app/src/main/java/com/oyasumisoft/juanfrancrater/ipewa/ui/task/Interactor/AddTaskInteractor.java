package com.oyasumisoft.juanfrancrater.ipewa.ui.task.Interactor;

import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.TareaRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Tarea;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Presenter.AddTaskPresenter;

import java.util.ArrayList;

/**
 * Created by PcCom on 04/01/2018.
 */

public class AddTaskInteractor implements TaskContrat.addTask.Interactor {

    AddProjectListener listener;
    public AddTaskInteractor(AddProjectListener listener) {
        this.listener= listener;
    }

    @Override
    public void addTask(String nombre, String description, String color, String deadLine, String priority, String difficulty, int _idProyecto) {
        if(!nombre.isEmpty()) {
            TareaRepository.getInstance().addTarea(nombre, description, color, deadLine, priority, difficulty,_idProyecto);
            listener.back();
        }else
        {
            listener.showEmptyError();
        }
    }

    @Override
    public void getIdList() {
        listener.fillIdList(ProjectRepository.getInstance().getIdProjects());
    }

    public interface AddProjectListener {
        void back();
        void showEmptyError();
        void fillIdList(ArrayList<String> idProjects);
    }
}
