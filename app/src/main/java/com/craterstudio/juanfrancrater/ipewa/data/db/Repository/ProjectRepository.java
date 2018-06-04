package com.craterstudio.juanfrancrater.ipewa.data.db.Repository;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.dao.ProjectDao;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tablero;
import com.craterstudio.juanfrancrater.ipewa.util.AppPreferencesHelper;
import com.craterstudio.juanfrancrater.ipewa.util.ThisApplication;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Repositorio de los Proyectos de la aplicacion
 * @author Juan Francisco Benítez López
 * @version 0.1.0
 */

public class ProjectRepository {
    //Declaracion
    private ArrayList<Proyecto> projects;
    private ProjectDao dao;

    private static ProjectRepository projectRepository;

    //Inicializacion
    //Inicializar todos los atributos de ámbito statico o de clase
    static {
        projectRepository = new ProjectRepository();
    }

    //El metodo ha de ser privado para garantizar que sólo hay una instancia de Repository

    private ProjectRepository() {
        this.projects = new ArrayList<>();
        dao= new ProjectDao();
    }

    public static ProjectRepository getInstance() {

        if (projectRepository == null)

            projectRepository = new ProjectRepository();

        return projectRepository;

    }

    public void addProject(String name,String description,int color, String deadLine ) {
        AppPreferencesHelper sharedPreferences=AppPreferencesHelper.getInstance();
        String creator=sharedPreferences.getCurrentUserID();
        String creatorname= sharedPreferences.getCurrentUserName();
        int id= sharedPreferences.getLastIDProject();
        Proyecto proyecto =new Proyecto(String.valueOf(id)+creatorname,name,description,color,deadLine,creatorname);
        dao.add(proyecto);
        TableroRepository repository = TableroRepository.getInstance();
        repository.addTablero("To Do",0,proyecto.get_ID());
        sharedPreferences.setLastIDProject(id++);
    }
    public void addProject(Proyecto proyecto) {

        addProject(proyecto.get_name(),proyecto.get_description(),proyecto.get_color(),proyecto.get_deadLine());
    }
    public void deleteProject(Proyecto project) {

        dao.delete(project.get_ID());
    }
    public Proyecto getProject(String id)
    {
        Iterator<Proyecto> iterator= getInstance().getProjects().iterator();
        Proyecto temp;
        while(iterator.hasNext())
        {
            temp=iterator.next();
            if(id.equals(temp.get_ID()))
            {
                return temp;
            }
        }
        return null;
    }
    public void setProject(String id, Proyecto pro)
    {
        dao.set(id,pro);
    }

    public ArrayList<Proyecto> getProjects()
    {
       projects=dao.loadAll();
        return projects;
    }

    public ArrayList<String> getNameProjects() {
        ArrayList<String> list=new ArrayList<>();
        Iterator<Proyecto> iterator= dao.loadAll().iterator();
        Proyecto temp;
        list.add("-");
        while(iterator.hasNext())
        {
            temp=iterator.next();
            list.add(temp.get_name());
        }
        return list;
    }
    public ArrayList<String> getNameProjects(int tipoSpin) {
        ArrayList<String> list=new ArrayList<>();
        Iterator<Proyecto> iterator= dao.loadAll().iterator();
        Proyecto temp;
        if(tipoSpin!=-1)
        list.add("-");
        while(iterator.hasNext())
        {
            temp=iterator.next();
            list.add(temp.get_name());
        }
        return list;
    }

    public ArrayList<String> getIdProjects(int i) {
        ArrayList<String> list=new ArrayList<>();
        ArrayList<Proyecto> proyectos = dao.loadAll();
        Proyecto temp;
        if(i==0) {
            list.add("-1");
        }
       for(int j=0; j<proyectos.size();j++)
           list.add(proyectos.get(j).get_ID());
        return list;
    }
}
