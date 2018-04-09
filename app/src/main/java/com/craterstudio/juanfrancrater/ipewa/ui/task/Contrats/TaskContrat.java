package com.craterstudio.juanfrancrater.ipewa.ui.task.Contrats;

import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;

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
            void addTask(String nombre, String description, int color, String deadLine, String priority, String difficulty,String _idProyecto, String idTablero);

            void getIdList();
        }

        interface Interactor
        {
            void addTask(String nombre, String description, int color, String deadLine, String priority, String difficulty,String _idProyecto, String idTablero);

            void getIdList();
        }
    }
    interface listTask
    {
        interface View{

            void reload();
            void reload(ArrayList<Tarea> tareas);
        }

        interface Presenter{

            void delete(Tarea tarea);
            void obtenerTareas();

        }

        interface Interactor
        {

            void delete(Tarea tarea);

            void obtenerTareas();
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
            void EditTask(String id, String name, String description, int color, String deadLine,String priority, String difficulty,String _idProyecto,String idTablero,String creator );
            void getIdList();
        }

        interface Interactor
        {
            void EditTask(String id, String name, String description, int color, String deadLine,String priority, String difficulty,String _idProyecto,String idTablero,String creator );
            void getIdList();
        }
    }

}
