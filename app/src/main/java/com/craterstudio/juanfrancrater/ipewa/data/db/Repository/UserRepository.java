package com.craterstudio.juanfrancrater.ipewa.data.db.Repository;

import com.craterstudio.juanfrancrater.ipewa.data.db.model.Usuarios;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by PcCom on 08/01/2018.
 */

public class UserRepository {
    ArrayList<Usuarios> usuarios;

    static UserRepository userRepository;

    static {
        userRepository = new UserRepository();
    }
    void inicializarUsuarios()
    {
        addUser(new Usuarios(1,"user","user@gmail.user","user",null));
    }

    private UserRepository()
    {
        this.usuarios = new ArrayList<>();
        inicializarUsuarios();
    }
    void addUser(Usuarios u)
    {
        usuarios.add(u);
    }
    public static UserRepository getInstance()
    {
        return userRepository;
    }

    public ArrayList<Usuarios> getUsuarios()
    {
        return  usuarios;
    }

    public boolean findEmail(String email) {
        Iterator<Usuarios> iterator= getInstance().getUsuarios().iterator();
        Usuarios temp;

        while(iterator.hasNext())
        {
            temp=iterator.next();
            if(email.equals(temp.get_email()))
            {
                return true;
            }
        }
        return false;
    }

    public boolean findUser(String user) {
        Iterator<Usuarios> iterator= getInstance().getUsuarios().iterator();
        Usuarios temp;

        while(iterator.hasNext())
        {
            temp=iterator.next();
            if(user.equals(temp.get_name()))
            {
                return true;
            }
        }
        return false;
    }

    public void addUser(String user, String password, String email) {
        addUser(new Usuarios(usuarios.size(),user,email,password,null));
    }

    public boolean enterUser(String username, String password) {

        Iterator<Usuarios> iterator= getInstance().getUsuarios().iterator();
        Usuarios temp;

        while(iterator.hasNext())
        {
            temp=iterator.next();
            if(username.equals(temp.get_name())&&temp.get_password().equals(password))
            {
                    return true;
            }
        }
        return false;
    }
}
