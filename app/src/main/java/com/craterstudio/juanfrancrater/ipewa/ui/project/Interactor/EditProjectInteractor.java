package com.craterstudio.juanfrancrater.ipewa.ui.project.Interactor;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;

/**
 * Created by PcCom on 31/12/2017.
 */

public class EditProjectInteractor implements ProjectContrat.editProject.Interactor{

    private final EditProjectListener listener;

    public EditProjectInteractor(EditProjectListener listener) {
        this.listener=listener;
    }

    @Override
    public void EditProject(int id, String name, String description, String color, String deadLine) {
        if(name.length()<1)
        {
            listener.showErrorEmptyName();
        }else
        {
            ProjectRepository.getInstance().setProject(id,new Proyecto(id,name,description,color,deadLine));
            listener.back();
        }

    }

    @Override
    public void getProject(int editProyect) {
        listener.loadProject(ProjectRepository.getInstance().getProject(editProyect));
    }

    public interface EditProjectListener
    {
        void loadProject(Proyecto project);
            void showErrorEmptyName();
            void back();
    }
}
