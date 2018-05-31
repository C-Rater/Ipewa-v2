package com.craterstudio.juanfrancrater.ipewa.ui.Meta.Contrats;

import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;

import java.util.ArrayList;

/**
 * Created by usuario on 31/05/18.
 */

public interface MetaContrat {
    interface addMeta
    {
        interface View{

            void showError(String error);

            void back();

            void fillIdList(ArrayList<String> idProjects);
        }

        interface Presenter{
            void addMeta(String nombre, String description, int color, String deadLine, String priority, String difficulty,String _idProyecto, String idTablero);

            void getIdList();
        }

        interface Interactor
        {
            void addMeta(String nombre, String description, int color, String deadLine, String priority, String difficulty,String _idProyecto, String idTablero);

            void getIdList();
        }
    }
    interface listMeta
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
    interface editMeta

    {
        interface View{
            void back();
            void showError();
            void fillIdList(ArrayList<String> idProjects);
        }

        interface Presenter{
            void EditMeta(String id, String name, String description, int color, String deadLine,String priority, String difficulty,String _idProyecto,String idTablero,String creator );
            void getIdList();
        }

        interface Interactor
        {
            void EditMeta(String id, String name, String description, int color, String deadLine,String priority, String difficulty,String _idProyecto,String idTablero,String creator );
            void getIdList();
        }
    }
}
