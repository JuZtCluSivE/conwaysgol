package edu.kit.iai.pv0699.conwaygol.model;

public class Field {

    private boolean isAlive = false;
    private int x;
    private int y;


    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Field{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
