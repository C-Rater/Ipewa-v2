package com.oyasumisoft.juanfrancrater.ipewa.ui.task.Interactor;

import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.TareaRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Tarea;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Presenter.EditTaskPresenter;

import java.util.ArrayList;

/**
 * Created by PcCom on 04/01/2018.
 */

public class EditTaskInteractor implements TaskContrat.editTask.Interactor {

    private EditTaskListener listener;

    public EditTaskInteractor(EditTaskListener listener) {
        this.listener=listener;
    }



    @Override
    public void EditTask(int id, String name, String description, String color, String deadLine, String priority, String difficulty, int _idProyecto) {
        if(!name.isEmpty()) {
            TareaRepository.getInstance().setTarea(id, new Tarea(id, name, description, color, deadLine, priority, difficulty,_idProyecto));
            listener.back();
        }else{
            listener.showEmptyName();
        }
    }

    @Override
    public void getIdList() {
        listener.fillIdList(ProjectRepository.getInstance().getIdProjects());
    }

    public interface EditTaskListener {
        void back();
        void showEmptyName();
        void fillIdList(ArrayList<String> idProjects);
    }
}
