package com.example.goibibo.GOT;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Dragon{
    private String id;
    private String Name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Dragon(String id, String name) {
        this.id = id;
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Dragon{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }
}
