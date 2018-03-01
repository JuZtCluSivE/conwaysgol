package edu.kit.iai.pv0699.conwaygol.model;

public class Grid {

    private Field fields[][];
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

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for( int i = 0; i < this.fields.length; i++ ) {
            for( int j = 0; j < this.fields[0].length; j++ ) {
                output.append(this.fields[i][j].toString() + "\n");
            }
        }
        return output.toString();
    }
}
