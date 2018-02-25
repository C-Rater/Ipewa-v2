package com.oyasumisoft.juanfrancrater.ipewa.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Pojo Tarea(implementa Comparable)
 *  @author Juan Francisco Benítez López
 * @version 0.1.0
 */

public class Tarea implements Comparable, Parcelable {
    int _ID;
    String _name;
    String _description;
    String _color;
    String _deadLine;
    String _priority;
    String _difficulty;
    int _idProyecto;
    int _idTablero;

    public Tarea(int _ID, String _name, String _description, String _color, String _deadLine, String _priority, String _difficulty,int _idProyecto,int _idTablero) {
        this._ID = _ID;
        this._name = _name;
        this._description = _description;
        this._color = _color;
        this._deadLine = _deadLine;
        this._priority = _priority;
        this._difficulty = _difficulty;
        this._idProyecto=_idProyecto;
        this._idTablero=_idTablero;

    }

    protected Tarea(Parcel in) {
        _ID = in.readInt();
        _name = in.readString();
        _description = in.readString();
        _color = in.readString();
        _deadLine = in.readString();
        _priority = in.readString();
        _difficulty = in.readString();
        _idProyecto=in.readInt();
        _idTablero=in.readInt();
    }

    public void set_idProyecto(int _idProyecto) {
        this._idProyecto = _idProyecto;
    }

    public int get_idProyecto() {
        return _idProyecto;
    }

    public static final Creator<Tarea> CREATOR = new Creator<Tarea>() {
        @Override
        public Tarea createFromParcel(Parcel in) {
            return new Tarea(in);
        }

        @Override
        public Tarea[] newArray(int size) {
            return new Tarea[size];
        }
    };

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

    public String get_deadLine() {
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

    @Override
    public int compareTo(@NonNull Object o) {
        return _name.compareTo(((Tarea) o).get_name());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_ID);
        dest.writeString(_name);
        dest.writeString(_description);
        dest.writeString(_color);
        dest.writeString(_deadLine);
        dest.writeString(_priority);
        dest.writeString(_difficulty);
        dest.writeInt(_idProyecto);
        dest.writeInt(_idTablero);
    }

    public int get_idTablero() {
        return _idTablero;
    }

    public static class TareaOrderByPriority implements Comparator<Tarea> {
    //NO ORDENA POR PRIORIDAD, SOLO POR DIFICULTAD, Y NO ENCUENTO EL ERROR
    @Override
    public int compare(Tarea t1, Tarea t2) {
        if (t1.get_priority() == t2.get_priority()) {
            return 0;
        } else if (t1.get_priority() == "Alta") {
            if (t2.get_priority() == "Baja") {
                return 1;
            } else if (t2.get_priority() == "Media") {
                return 1;
            }

        } else if (t1.get_priority() == "Media") {
            if (t2.get_priority() == "Baja") {
                return 1;
            } else if (t2.get_priority() == "Alta") {
                return -1;
            }
        } else if (t1.get_priority() == "Baja") {
            if (t2.get_priority() == "Media") {
                return -1;
            } else if (t2.get_priority() == "Alta") {
                return -1;
            }
        }
        if (t1.get_priority().isEmpty()) {
            if(t2.get_priority().isEmpty())
                return 0;
        }
        return 1;
    }
    }


    public static class TareaOrderByDifficulty implements Comparator<Tarea> {

        @Override
        public int compare(Tarea t1, Tarea t2) {
            if (t1.get_difficulty() == t2.get_difficulty()) {
                return 0;
            } else if (t1.get_difficulty() == "Alta") {
                if (t2.get_difficulty() == "Baja") {
                    return 1;
                } else if (t2.get_difficulty() == "Media") {
                    return 1;
                }

            } else if (t1.get_difficulty() == "Media") {
                if (t2.get_difficulty() == "Baja") {
                    return 1;
                } else if (t2.get_difficulty() == "Alta") {
                    return -1;
                }
            } else if (t1.get_difficulty() == "Baja") {
                if (t2.get_difficulty() == "Media") {
                    return -1;
                } else if (t2.get_difficulty() == "Alta") {
                    return -1;
                }
            }
            if (t1.get_difficulty().isEmpty()) {
                if(t2.get_difficulty().isEmpty())
                return 0;
            }
            return 1;
        }
    }

    @Override
    public String toString() {
        return _name;
    }
}
