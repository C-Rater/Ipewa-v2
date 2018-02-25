package com.oyasumisoft.juanfrancrater.ipewa.ui.task.Contrats;

import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Tarea;

import java.util.ArrayList;

/**
 * Created by PcCom on 31/12/2017.
 */

public interface TaskContrat {
    interface addTask
    {
        interface View{

            void showError(String error);

            void back();

            void fillIdList(ArrayList<String> idProjects);
        }

        interface Presenter{
            void addTask(String nombre, String description, String color, String deadLine, String priority, String difficulty,int _idProyecto, int idTablero);

            void getIdList();
        }

        interface Interactor
        {
            void addTask(String nombre, String description, String color, String deadLine, String priority, String difficulty,int _idProyecto, int idTablero);

            void getIdList();
        }
    }
    interface listTask
    {
        interface View{

            void reload();
        }

        interface Presenter{

            void delete(Tarea tarea);
        }

        interface Interactor
        {

            void delete(Tarea tarea);
        }
    }
    interface editTask

    {
        interface View{
            void back();
            void showError();
            void fillIdList(ArrayList<String> idProjects);
        }

        interface Presenter{
            void EditTask(int id, String name, String description, String color, String deadLine,String priority, String difficulty,int _idProyecto,int idTablero );
            void getIdList();
        }

        interface Interactor
        {
            void EditTask(int id, String name, String description, String color, String deadLine,String priority, String difficulty,int _idProyecto,int idTablero );
            void getIdList();
        }
    }

}
