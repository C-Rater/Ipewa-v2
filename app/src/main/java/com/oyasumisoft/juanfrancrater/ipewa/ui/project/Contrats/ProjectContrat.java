package com.oyasumisoft.juanfrancrater.ipewa.ui.project.Contrats;

import android.os.Parcelable;

import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Proyecto;

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

            void addProject(String nombre, String description, String color, String deadLine);
        }

        interface Interactor
        {

            void addProject(String nombre, String description, String color, String deadLine);
        }
    }
    interface DetailProject
    {
        interface View{
            void reloadProject(Proyecto proyecto);

            void reloadList();
        }

        interface Presenter{

            void getProject(int id);

            void deleteProject(Proyecto detailProject);
        }

        interface Interactor
        {

            void getProject(int id);

            void deleteProject(Proyecto detailProject);
        }
    }
    interface listProject
    {
        interface View{

            void openDetailProject(Parcelable object);
        }

        interface Presenter{
            void getProjectAtPosition(int position);
        }

        interface Interactor
        {
            void getProject(int position);
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
            void EditProject(int id, String name, String description, String color, String deadLine);

            void getProject(int editProyect);
        }

        interface Interactor
        {
            void EditProject(int id, String name, String description, String color, String deadLine);

            void getProject(int editProyect);
        }
    }

}
