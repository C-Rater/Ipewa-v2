package com.oyasumisoft.juanfrancrater.ipewa.ui.project.Interactor;

import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Presenter.AddProjectPresenter;

/**
 * Created by PcCom on 31/12/2017.
 */

public class AddProjectInteractor implements ProjectContrat.addProject.Interactor {

    AddProjectListener listener;

    public AddProjectInteractor(AddProjectListener listener) {
        this.listener=listener;
    }

    @Override
    public void addProject(String nombre, String description, String color, String deadLine) {
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
