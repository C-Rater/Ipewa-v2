package com.oyasumisoft.juanfrancrater.ipewa.data.db.model;

import java.io.File;

/**
 * Pojo Usuarios
 *  @author Juan Francisco Benítez López
 * @version 0.1.0
 */

public class Usuarios {
    int _ID;
    String _name;
    String _email;
    String _password;
    File _image;

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public File get_image() {
        return _image;
    }

    public void set_image(File _image) {
        this._image = _image;
    }

    public Usuarios(int _ID, String _name, String _email, String _password, File _image) {
        this._ID = _ID;
        this._name = _name;
        this._email = _email;
        this._password = _password;
        this._image = _image;
    }
}
