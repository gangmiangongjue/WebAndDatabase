package com.example.shaoxiaofei.webanddatabase;

/**
 * Created by shaoxiaofei on 27/12/2017.
 */

public class Person {
    private String name;
    private String des;

    public Person(){

    }
    public Person(String name ,String des){
        this.name = name;
        this.des = des;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
