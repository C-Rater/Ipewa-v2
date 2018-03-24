package com.craterstudio.juanfrancrater.ipewa.ui.project.Interactor;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;

/**
 * Created by PcCom on 31/12/2017.
 */

public class AddProjectInteractor implements ProjectContrat.addProject.Interactor {

    AddProjectListener listener;

    public AddProjectInteractor(AddProjectListener listener) {
        this.listener=listener;
    }

    @Override
    public void addProject(String nombre, String description, int color, String deadLine) {
        if(nombre.length()<1)
        {
            listener.showEmptyNameError();
        }else
        {
            ProjectRepository.getInstance().addProject(nombre,description,color,deadLine);
            listener.onSuccess();
        }
    }
    public interface AddProjectListener
    {
        void showEmptyNameError();

        void onSuccess();
    }
}
