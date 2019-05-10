package com.example.newpc.qrcode;

public class Lists {

    String _name;
    int _id;
    public Lists(){   }
    public Lists (int id, String name){
        this._id = id;
        this._name = name;
    }

    public  Lists(String name){
        this._name = name;
    }
    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }
    public void setID(int id){
        this._id = id;
    }
    public int getID(){
        return  this._id;
    }

}
