package com.craterstudio.juanfrancrater.ipewa.data.db.model;

/**
 * Pojo Meta
 *  @author Juan Francisco Benítez López
 * @version 0.1.0
 */

public class Meta {
    int _ID;
    String _name;
    String _description;
    String _color;
    String _deadLine;
    String _priority;
    String _difficulty;
    int _idProyecto;

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

    public String  get_deadLine() {
        return _deadLine;
    }

    public void set_deadLine(String _deadLine) {
        this._deadLine = _deadLine;
    }

    public String get_priority() {
        return _priority;
    }

    public void set_priority(String _priority) {
        this._priority = _priority;
    }

    public String get_difficulty() {
        return _difficulty;
    }

    public void set_difficulty(String _difficulty) {
        this._difficulty = _difficulty;
    }

    public Meta(int _ID, String _name, String _description, String _color, String _deadLine, String _priority, String _difficulty,int _idProyecto) {
        this._ID = _ID;
        this._name = _name;
        this._description = _description;
        this._color = _color;
        this._deadLine = _deadLine;
        this._priority = _priority;
        this._difficulty = _difficulty;
        this._idProyecto=_idProyecto;
    }

    public int get_idProyecto() {
        return _idProyecto;
    }

    public void set_idProyecto(int _idProyecto) {
        this._idProyecto = _idProyecto;
    }

    @Override
    public String toString() {
        return _name;
    }
}
