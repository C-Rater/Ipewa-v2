package com.craterstudio.juanfrancrater.ipewa.data.db.model;

/**
 * Pojo Actividad
 *  @author Juan Francisco Benítez López
 * @version 0.1.0
 */

public class Actividad {
    int _ID;
    String _name;
    String _description;
    String _color;

    public Actividad(int _ID, String _name, String _description, String _color) {
        this._ID = _ID;
        this._name = _name;
        this._description = _description;
        this._color = _color;
    }

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

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_color() {
        return _color;
    }

    public void set_color(String _color) {
        this._color = _color;
    }
}
