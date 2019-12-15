package com.example.goibibo.GOT;
public class Rules {
    private String id;
    private int time;
    private int noOfAnimals;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNoOfAnimals() {
        return noOfAnimals;
    }

    public void setNoOfAnimals(int noOfAnimals) {
        this.noOfAnimals = noOfAnimals;
    }

    public Rules(String id, int time, int noOfAnimals) {
        this.id = id;
        this.time = time;
        this.noOfAnimals = noOfAnimals;
    }

    @Override
    public String toString() {
        return "Rules{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", noOfAnimals='" + noOfAnimals + '\'' +
                '}';
    }
}
