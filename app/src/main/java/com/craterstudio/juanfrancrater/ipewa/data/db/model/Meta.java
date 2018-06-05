package com.craterstudio.juanfrancrater.ipewa.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Pojo Meta
 *  @author Juan Francisco Benítez López
 * @version 0.1.0
 */

public class Meta implements Parcelable {
    int _ID;
    String _name;
    String _description;
    int _color;
    String _deadLine;
    String _priority;
    String _difficulty;
    int _idProyecto;
    String _creator;

    protected Meta(Parcel in) {
        _ID = in.readInt();
        _name = in.readString();
        _description = in.readString();
        _color = in.readInt();
        _deadLine = in.readString();
        _priority = in.readString();
        _difficulty = in.readString();
        _idProyecto = in.readInt();
        _creator = in.readString();
    }

    public static final Creator<Meta> CREATOR = new Creator<Meta>() {
        @Override
        public Meta createFromParcel(Parcel in) {
            return new Meta(in);
        }

        @Override
        public Meta[] newArray(int size) {
            return new Meta[size];
        }
    };

    public String get_creator() {
        return _creator;
    }

    public void set_creator(String _creator) {
        this._creator = _creator;
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

    public int get_color() {
        return _color;
    }

    public void set_color(int _color) {
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

    public Meta(int _ID, String _name, String _description, int _color, String _deadLine, String _priority, String _difficulty,int _idProyecto,String _creator) {
        this._ID = _ID;
        this._name = _name;
        this._description = _description;
        this._color = _color;
        this._deadLine = _deadLine;
        this._priority = _priority;
        this._difficulty = _difficulty;
        this._idProyecto=_idProyecto;
        this._creator=_creator;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(_ID);
        parcel.writeString(_name);
        parcel.writeString(_description);
        parcel.writeInt(_color);
        parcel.writeString(_deadLine);
        parcel.writeString(_priority);
        parcel.writeString(_difficulty);
        parcel.writeInt(_idProyecto);
        parcel.writeString(_creator);
    }
    public static class MetaOrderByPriority implements Comparator<Meta> {
        //NO ORDENA POR PRIORIDAD, SOLO POR DIFICULTAD, Y NO ENCUENTO EL ERROR
        @Override
        public int compare(Meta t1, Meta t2) {
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


    public static class MetaOrderByDifficulty implements Comparator<Meta> {

        @Override
        public int compare(Meta t1, Meta t2) {
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
}
