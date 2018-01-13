package com.oyasumisoft.juanfrancrater.ipewa.data.db.model;

/**
 * Pojo Tablero
 *  @author Juan Francisco Benítez López
 * @version 0.1.0
 */

public class Tablero {
    int _ID;
    String _name;
    int _position;

    public Tablero(int _ID, String _name, int _position) {
        this._ID = _ID;
        this._name = _name;
        this._position = _position;
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

    public int get_position() {
        return _position;
    }

    public void set_position(int _position) {
        this._position = _position;
    }
}
