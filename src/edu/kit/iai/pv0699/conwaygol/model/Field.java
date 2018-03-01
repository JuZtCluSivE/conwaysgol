package edu.kit.iai.pv0699.conwaygol.model;

public class Field {

    private boolean isAlive = false;
    private int x;
    private int y;


    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setIsAlive(boolean isAlive){
        this.isAlive = isAlive;
    }

    public boolean getIsAlive(){
        return this.isAlive;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "Field{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
