package com.craterstudio.juanfrancrater.ipewa.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Pojo Tablero
 *  @author Juan Francisco Benítez López
 * @version 0.1.0
 */

public class Tablero implements Parcelable {
    int _ID;
    String _name;
    int _position;
    int _idProyecto;

    public int get_idProyecto() {
        return _idProyecto;
    }

    String _creator;

    public String get_creator() {
        return _creator;
    }

    public void set_creator(String _creator) {
        this._creator = _creator;
    }

    public Tablero(int _ID, String _name, int _position,int _idProyecto,String _creator) {
        this._ID = _ID;
        this._name = _name;
        this._position = _position;
        this._idProyecto=_idProyecto;
        this._creator=_creator;
    }

    protected Tablero(Parcel in) {
        _ID = in.readInt();
        _name = in.readString();
        _position = in.readInt();
        _idProyecto=in.readInt();
        _creator=in.readString();
    }

    public static final Creator<Tablero> CREATOR = new Creator<Tablero>() {
        @Override
        public Tablero createFromParcel(Parcel in) {
            return new Tablero(in);
        }

        @Override
        public Tablero[] newArray(int size) {
            return new Tablero[size];
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

    public int get_position() {
        return _position;
    }

    public void set_position(int _position) {
        this._position = _position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(_ID);
        parcel.writeString(_name);
        parcel.writeInt(_position);
        parcel.writeInt(_idProyecto);
        parcel.writeString(_creator);
    }
}
