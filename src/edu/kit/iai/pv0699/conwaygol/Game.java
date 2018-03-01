package edu.kit.iai.pv0699.conwaygol;

import edu.kit.iai.pv0699.conwaygol.model.Field;
import edu.kit.iai.pv0699.conwaygol.model.Grid;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Game extends Application {

    private final int fieldSize = 10;
    private final int lineWidth = 1;
    private List<Rectangle> paintedRecs = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Erstellt das Fenster und erzeugt ein canvas = fläche, auf der später gezeichnet wird
        primaryStage.setTitle("Conways Game of Life");
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(1000, 800);
        root.getChildren().add(canvas);


        GraphicsContext gc = canvas.getGraphicsContext2D();

        //gameloop wird erzeugt
        Timeline loop = new Timeline();
        loop.setCycleCount(Timeline.INDEFINITE);

        //grid wird erzeugt
        Grid grid = new Grid(canvas.getWidth(), canvas.getHeight(), this.fieldSize, this.lineWidth);
        Field fields[][] = grid.getFields();

        //zeichnet vertikale Linien
        for( int i = 1; i <= fields.length; i++ ) {
            Line line = new Line(
                    (i * this.fieldSize) + (i * this.lineWidth),
                    0,
                    (i * this.fieldSize) + (i * this.lineWidth),
                    (fields[0].length * this.fieldSize) + (fields[0].length * this.lineWidth) //bis zum ende der höhe nicht arraybreite als höhe
            );
            root.getChildren().add(line);
        }

        //zeichnet horizontale Linien
        for( int j = 1; j <= fields[0].length; j++ ) {
            Line line = new Line(
                    0,
                    (j * this.fieldSize) + (j * this.lineWidth),
                    (fields.length * this.fieldSize) + (fields.length * this.lineWidth), //bis zum ende der breite nicht arrayhöhe als breite
                    (j * this.fieldSize) + (j * this.lineWidth)
            );
            root.getChildren().add(line);
        }

        //setzt isAlive der fields aus fields[][]
        grid.setAlive(30,30,true);


        //setzt aliveFields
        for(int i = 0; i < grid.getFields().length; i++){
            for (int j = 0; j < grid.getFields()[0].length; j++) {
                if (grid.getFields()[i][j].getIsAlive()){
                    grid.getAliveFields().add(grid.getFields()[i][j]);
                }
            }
        }

        System.out.println(grid.toString());

        //erzeugt den KeyFrame
        KeyFrame kf = new KeyFrame(
                //zeit die jeder Frame läuft
                Duration.seconds(1),
                //code der pro frame ausgeführt werden soll
                ae -> {
                    //alte recs entfernen
                    for(int i = 0; i < this.paintedRecs.size(); i++){
                        
                    }
                    //aliveFields zeichnen
                    for(int i = 0; i < grid.getAliveFields().size(); i++){
                        Rectangle rec = new Rectangle(
                                (grid.getAliveFields().get(i).getX() * this.fieldSize) + (grid.getAliveFields().get(i).getX() * this.lineWidth),
                                (grid.getAliveFields().get(i).getY() * this.fieldSize) + (grid.getAliveFields().get(i).getY() * this.lineWidth),
                                (this.fieldSize),
                                (this.fieldSize)
                        );
                        rec.setFill(Color.BLACK);
                        this.paintedRecs.add(rec);
                    }
                });

        //keyframe wird dem loop hinzugefügt, gestartet und das fenster wird gezeigt
        loop.getKeyFrames().add(kf);
        loop.play();
        primaryStage.show();

    }


}
