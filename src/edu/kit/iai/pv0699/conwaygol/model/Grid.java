package edu.kit.iai.pv0699.conwaygol.model;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private Field fields[][];
    private List<Field> aliveFields = new ArrayList<>();
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
     *   initziert das Grid
     *   menge der fields wird anhand der fenster höhe und breite automatisch festgelegt
     */
    private void initGrid() {
        this.fields = new Field[((int) this.width) / (this.fieldSize + this.lineWidth)][((int) this.height) / (this.fieldSize + this.lineWidth)];

        System.out.println(((int) this.width) / (this.fieldSize + this.lineWidth) + "/" + ((int) this.height) / (this.fieldSize + this.lineWidth));

        for( int i = 0; i < this.width - this.fieldSize; i = (i + this.fieldSize + this.lineWidth) ) {
            for( int j = 0; j < this.height - this.fieldSize; j = (j + this.fieldSize + this.lineWidth) ) {
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

    public void setAlive(int x, int y, boolean isAlive) {
        this.fields[x][y].setIsAlive(isAlive);
    }

    public List<Field> getSurrFields(Field field) {
        int counter = 0;
        List<Field> surrFields = new ArrayList<>();
        do {
            try {
                switch( counter ) {
                    case 0:
                        counter++;
                        surrFields.add(this.fields[field.getX()][field.getY() - 1]);
                    case 1:
                        counter++;
                        surrFields.add(this.fields[field.getX() + 1][field.getY() - 1]);
                    case 2:
                        counter++;
                        surrFields.add(this.fields[field.getX() + 1][field.getY()]);
                    case 3:
                        counter++;
                        surrFields.add(this.fields[field.getX() + 1][field.getY() + 1]);
                    case 4:
                        counter++;
                        surrFields.add(this.fields[field.getX()][field.getY() + 1]);
                    case 5:
                        counter++;
                        surrFields.add(this.fields[field.getX() - 1][field.getY() + 1]);
                    case 6:
                        counter++;
                        surrFields.add(this.fields[field.getX() - 1][field.getY()]);
                    case 7:
                        counter++;
                        surrFields.add(this.fields[field.getX() - 1][field.getY() - 1]);
                }
            } catch( ArrayIndexOutOfBoundsException e ) {
                System.out.println("Field: " + field.toString() + " one surrounding field not found: counter = " + counter);
            }
        } while( counter < 8 );
        return surrFields;
    }

    public List<Field> getSurrAliveFields(Field field) {
        List<Field> surrFields = getSurrFields(field);
        List<Field> aliveSurrFields = new ArrayList<>();
        surrFields.forEach(surrField -> {
            if( surrField.getIsAlive() ) {
                aliveSurrFields.add(surrField);
            }
        });
        return aliveSurrFields;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        System.out.println("fields:\n");
        for( Field[] field : this.fields ) {
            for( int j = 0; j < this.fields[0].length; j++ ) {
                output.append(field[j].toString()).append("\n");
            }
        }
        output.append("aliveFields:\n");
        for( Field aliveField : this.aliveFields ) {
            output.append(aliveField.toString()).append("\n");
        }
        return output.toString();
    }
}
