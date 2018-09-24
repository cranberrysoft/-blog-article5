package com.cranberrysoft.apocalypse.model;

/**
 * @author Mariusz Dubielecki
 */
public class Pawn {

    //by default empty label;
    private String label = "";
    private Position position;

    public Pawn(Position position) {
        this.position = position;
    }

    public Pawn(Position position, String label) {
        this.position = position;
        this.label = label;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "label='" + label + '\'' +
                ", position=" + position +
                '}';
    }

}
