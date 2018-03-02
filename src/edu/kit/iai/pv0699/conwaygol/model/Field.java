package edu.kit.iai.pv0699.conwaygol.model;

import javafx.scene.shape.Rectangle;

public class Field {

    private boolean isAlive = false;
    private int x;
    private int y;
    private Rectangle rec;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public Rectangle getRec() {
        return rec;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setRec(Rectangle rec) {
        this.rec = rec;
    }

    @Override
    public String toString() {
        return "Field{" +
                "x=" + x +
                ", y=" + y +
                ", isAlive=" + isAlive +
                '}';
    }
}
