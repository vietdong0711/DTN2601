package org.example.entity;


import org.example.enums.PositionName;

public class Position {
    private int id;
    private PositionName name;

    public Position() {

    }

    public Position(int id, PositionName name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PositionName getName() {
        return name;
    }

    public void setName(PositionName name) {
        this.name = name;
    }
}
