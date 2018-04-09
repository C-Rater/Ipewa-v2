package com.craterstudio.juanfrancrater.ipewa.ui.project.Contrats;

import android.os.Parcelable;

import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;

import java.util.ArrayList;

/**
 * Created by PcCom on 31/12/2017.
 */

public interface ProjectContrat  {
    interface addProject
    {
        interface View{

            void showError(String error);

            void back();
        }

        interface Presenter{

            void addProject(String nombre, String description, int color, String deadLine);
        }

        interface Interactor
        {

            void addProject(String nombre, String description, int color, String deadLine);
        }
    }
    interface DetailProject
    {
        interface View{
            void reloadProject(Proyecto proyecto);

            void reloadList();
        }

        interface Presenter{

            void getProject(String id);

            void deleteProject(Proyecto detailProject);
        }

        interface Interactor
        {

            void getProject(String id);

            void deleteProject(Proyecto detailProject);
        }
    }
    interface listProject
    {
        interface View{
            void recargarProjects(ArrayList<Proyecto> proyectos);
            void openDetailProject(Parcelable object);
        }

        interface Presenter{
            void getProjectAtPosition(int position);

            void getProjects();
        }

        interface Interactor
        {
            void getProject(int position);

            void getProjects();
        }
    }
    interface editProject
    {
        interface View{
            void back();
            void showError(String error);
            void loadProject(Proyecto project);
        }

        interface Presenter{
            void EditProject(String id, String name, String description, int color, String deadLine,String creator);

            void getProject(String editProyect);
        }

        interface Interactor
        {
            void EditProject(String id, String name, String description, int color, String deadLine,String creator);

            void getProject(String editProyect);
        }
    }

}
