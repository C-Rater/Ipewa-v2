package com.craterstudio.juanfrancrater.ipewa.data.db.Repository;


import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.dao.TareaDao;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;
import com.google.firebase.auth.FirebaseAuth;

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
    TareaDao dao;

    static TareaRepository tareaRepository;

    static {
        tareaRepository = new TareaRepository();
    }


    private TareaRepository()
    {
        this.tareas = new ArrayList<>();
        dao=new TareaDao();
    }
    void addTarea(Tarea t)

    {
        dao.add(t);
    }

    public static TareaRepository getInstance()
    {
        return tareaRepository;
    }
    public ArrayList<Tarea> getTareas()
    {
        tareas= dao.loadAll();
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

    public void setTarea(String id, Tarea tarea) {
        dao.set(id,tarea);
    }

    public void addTarea(String name, String description, int color, String deadLine, String priority, String difficulty, String idProyecto, String idTablero) {
        String creator= FirebaseAuth.getInstance().getCurrentUser().getUid();
        dao.add(name,description,color,deadLine,priority,difficulty,idProyecto,idTablero,creator);
    }

    public ArrayList<Tarea> getTareasByProjectId(String id) {
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
    public void deleteTareasByProjectId(String id) {

        Iterator<Tarea> iterator= getInstance().getTareas().iterator();
        Tarea temp;
        while(iterator.hasNext())
        {
            temp=iterator.next();
            if(temp.get_idProyecto()==id)
            {
              dao.delete(temp.get_ID());
            }
        }
    }

    public void deleteTask(Tarea tarea) {
        dao.delete(tarea.get_ID());
    }

    public ArrayList getTareasByTablero(int tablero) {
     return dao.loadByTab(tablero);
    }

    public ArrayList getTareasInDays(int days){return dao.getByDays(days);}

    public ArrayList<Tarea> getTareasSortByDate() {
        return dao.getSortByDate();
    }

    public void deleteTask(String i) {
        dao.delete(i);
    }
}

