package com.craterstudio.juanfrancrater.ipewa.ui.task.Interactor;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.TareaRepository;
import com.craterstudio.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    public void addTask(String nombre, String description, int color, String deadLine, String priority, String difficulty, int _idProyecto, int idTablero) {
        if(!nombre.isEmpty()) {
            TareaRepository.getInstance().addTarea(nombre, description, color, deadLine, priority, difficulty,_idProyecto,idTablero);
            listener.back();
        }else
        {
            listener.showEmptyError();
        }
    }

    @Override
    public void getIdList() {
        listener.fillIdList(ProjectRepository.getInstance().getIdProjects(0));
    }

    public interface AddProjectListener {
        void back();
        void showEmptyError();
        void fillIdList(ArrayList<Integer> idProjects);
    }
}
