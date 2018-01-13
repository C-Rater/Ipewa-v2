package com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository;

import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Tarea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Repositorio de los Proyectos de la aplicacion
 * @author Juan Francisco Benítez López
 * @version 0.1.0
 */

public class ProjectRepository {
    //Declaracion
    private int lastID=0;
    private ArrayList<Proyecto> projects;

    private static ProjectRepository projectRepository;

    //Inicializacion
    //Inicializar todos los atributos de ámbito statico o de clase
    static {
        projectRepository = new ProjectRepository();
    }

    //El metodo ha de ser privado para garantizar que sólo hay una instancia de Repository

    private ProjectRepository() {
        this.projects = new ArrayList<>();
        initialize();
    }

    //Metodos

    private void initialize() {

        addProject( "Proyecto Integrado", "Proyecto Integrado del curso: Desarrollo App 'Ipewa', aplicacion con el objetivo de poder gestionar proyectos entre varios usuarios, permitiendo a estos tener acceso a distintas herramientas y elementos etc...",

                "Blue","2018/02/10");

        addProject( "Aprender un idioma", "Aprender un idioma antes de que acabe el 2019",

                "Black","2019/12/31");


    }

    public static ProjectRepository getInstance() {

        if (projectRepository == null)

            projectRepository = new ProjectRepository();

        return projectRepository;

    }


    public void addProject(String name,String description,String color, String deadLine ) {

        projects.add(new Proyecto(lastID++,name,description,color,deadLine));
    }
    public void addProject(Proyecto proyecto) {

        projects.add(proyecto);
    }
    public void deleteProject(Proyecto project) {

        projects.remove(project);
    }
    public Proyecto getProject(int id)
    {
        Iterator<Proyecto> iterator= getInstance().getProjects().iterator();
        Proyecto temp;
        while(iterator.hasNext())
        {
            temp=iterator.next();
            if(id==temp.get_ID())
            {
                return temp;
            }
        }
        return null;
    }
    public void setProject(int id, Proyecto pro)
    {
        Iterator<Proyecto> iterator= getInstance().getProjects().iterator();
        Proyecto temp;
        while(iterator.hasNext())
        {
            temp=iterator.next();
            if(pro.get_ID()==temp.get_ID())
            {
                iterator.remove();
            }
        }
        getInstance().addProject(pro);
    }

    public ArrayList<Proyecto> getProjects()
    {
        Collections.sort(projects);
        return projects;
    }

    public ArrayList<String> getNameProjects() {
        ArrayList<String> list=new ArrayList<>();
        Iterator<Proyecto> iterator= getInstance().getProjects().iterator();
        Proyecto temp;
        list.add("-");
        while(iterator.hasNext())
        {
            temp=iterator.next();
            list.add(temp.get_name());
        }
        return list;
    }

    public ArrayList<String> getIdProjects() {
        ArrayList<String> list=new ArrayList<>();
        Iterator<Proyecto> iterator= getInstance().getProjects().iterator();
        Proyecto temp;
        list.add("-1");
        while(iterator.hasNext())
        {
            temp=iterator.next();
            list.add(String.valueOf(temp.get_ID()));
        }
        return list;
    }
}
