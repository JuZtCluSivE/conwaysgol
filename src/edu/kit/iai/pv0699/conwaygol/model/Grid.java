package edu.kit.iai.pv0699.conwaygol.model;

import java.util.*;

public class Grid {

    private Field fields[][];
    private List<Field> aliveFields = new ArrayList<Field>();
    private double width;
    private double height;
    private int fieldSize;
    private int lineWidth;


    public Grid(double width, double height, int fieldSize, int lineWidth) {
        this.width = width;
        this.height = height;
        this.fieldSize = fieldSize;
        this.lineWidth = lineWidth;
        this.initGrid();
    }

    /*
    initziert das Grid
    menge der fields wird anhand der fenster höhe und breite automatisch festgelegt
     */
    private void initGrid() {
        fields = new Field[((int) this.width) / (this.fieldSize + this.lineWidth)][((int) this.height) / (this.fieldSize + this.lineWidth)];

        System.out.println(((int) this.width) / (this.fieldSize + this.lineWidth) + "/" + ((int) this.height) / (this.fieldSize + this.lineWidth) );

        for( int i = 0; i < this.width - this.fieldSize; i = (i + this.fieldSize + this.lineWidth) ) {
            for(  int j = 0; j < this.height - this.fieldSize; j = (j + this.fieldSize + this.lineWidth)) {
                this.fields[i / (this.fieldSize + this.lineWidth)][j / (this.fieldSize + this.lineWidth)] = new Field(i / (this.fieldSize + this.lineWidth), j / (this.fieldSize + this.lineWidth));
            }
        }
    }

    public Field[][] getFields() {
        return fields;
    }
    public List<Field> getAliveFields() {
        return aliveFields;
    }
    public void setAliveFields(List<Field> aliveFields) {
        this.aliveFields = aliveFields;
    }

    public void setAlive(int x, int y, boolean isAlive){
        this.fields[x][y].setIsAlive(isAlive);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        System.out.println("fields:\n");
        for( int i = 0; i < this.fields.length; i++ ) {
            for( int j = 0; j < this.fields[0].length; j++ ) {
                output.append(this.fields[i][j].toString() + "\n");
            }
        }
        output.append("aliveFields:\n");
        for (int i = 0; i < this.aliveFields.size(); i++){
            output.append(this.aliveFields.get(i).toString() + "\n");
        }
        return output.toString();
    }
}
