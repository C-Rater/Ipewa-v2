package com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository;


import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Tarea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Repositorio de las Tareas de la aplicacion
 * @author Juan Francisco Benítez López
 * @version 0.1.0
 */
public class TareaRepository {
    ArrayList<Tarea> tareas;

    static TareaRepository tareaRepository;

    static {
        tareaRepository = new TareaRepository();
    }

    void inicializarTareas()
    {
        addTarea(new Tarea(1, "Limpiar", "Limpiar el polvo de la libreria", "Red","2017-10-10" ," Media", "Baja",0 ));
        addTarea(new Tarea(2, "Entregar DEINT", "Entregar practica 6", "Blue", "2017-10-10"," Alta", "Media" ,0));
        addTarea(new Tarea(3, "Pasar a limpio EINE", "Limpiar el polvo de la libreria", "Black","2017-10-10" ," Baja", "Alta",0 ));
        addTarea(new Tarea(4, "Entregar PSPRO", "Entregar practica 6", "Green", "2017-10-10"," Alta", "Baja",0 ));
        addTarea(new Tarea(5, "Examen x", "Limpiar el polvo de la libreria", "Red","2017-10-10" ," Baja", "Media" ,1));
        addTarea(new Tarea(6, "Examen x", "Entregar practica 6", "Yellow", "2017-10-10"," Media", "Alta",1 ));
        addTarea(new Tarea(7, "Examen x", "Limpiar el polvo de la libreria", "Blue","2017-10-10" ," Baja", "Alta" ,1));
        addTarea(new Tarea(8, "Examen x", "Entregar practica 6", "Red", "2017-10-10"," Media", "Alta",-1 ));
    }

    private TareaRepository()
    {
        this.tareas = new ArrayList<>();
        inicializarTareas();
    }
    void addTarea(Tarea t)
    {
        tareas.add(t);
    }
    public static TareaRepository getInstance()
    {
        return tareaRepository;
    }
    public ArrayList<Tarea> getTareas()
    {
        Collections.sort(tareas);
        return  tareas;
    }
    public ArrayList<Tarea> getTareasSortByDif()
    {

        Collections.sort(tareas, new Tarea.TareaOrderByDifficulty());
        return  tareas;
    }
    public ArrayList<Tarea> getTareasSortByPri()
    {
        Collections.sort(tareas, new Tarea.TareaOrderByPriority());
        return  tareas;
    }

    public void setTarea(int id, Tarea tarea) {
        Iterator<Tarea> iterator= getInstance().getTareas().iterator();
        Tarea temp;
        while(iterator.hasNext())
        {
            temp=iterator.next();
            if(tarea.get_ID()==temp.get_ID())
            {
                iterator.remove();
            }
        }
        getInstance().addTarea(tarea);
    }

    public void addTarea(String name, String description, String color, String deadLine, String priority, String difficulty, int idProyecto) {
    getInstance().addTarea(new Tarea(getTareas().size()+1,name,description,color,deadLine,priority,difficulty,idProyecto));
    }

    public ArrayList<Tarea> getTareasByProjectId(int id) {
        ArrayList<Tarea> tareasP= new ArrayList<>();

        Iterator<Tarea> iterator= getInstance().getTareas().iterator();
        Tarea temp;
        while(iterator.hasNext())
        {
            temp=iterator.next();
            if(temp.get_idProyecto()==id)
            {
                tareasP.add(temp);
            }
        }
        return tareasP;
    }
    public void deleteTareasByProjectId(int id) {

        Iterator<Tarea> iterator= getInstance().getTareas().iterator();
        Tarea temp;
        while(iterator.hasNext())
        {
            temp=iterator.next();
            if(temp.get_idProyecto()==id)
            {
               iterator.remove();
            }
        }
    }

    public void deleteTask(Tarea tarea) {
        Iterator<Tarea> iterator= getInstance().getTareas().iterator();
        Tarea temp;
        while(iterator.hasNext())
        {
            temp=iterator.next();
            if(temp.get_ID()==tarea.get_ID())
            {
                iterator.remove();
                return;
            }
        }
    }
}

