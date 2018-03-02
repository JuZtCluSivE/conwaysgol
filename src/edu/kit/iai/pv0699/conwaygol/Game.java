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
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Game extends Application {

    private final int fieldSize = 10;
    private final int lineWidth = 1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Erstellt das Fenster und erzeugt ein canvas = fläche, auf der später gezeichnet wird
        primaryStage.setTitle("Conways Game of Life");
        primaryStage.setResizable(false);
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Canvas canvas = new Canvas(1000, 1000);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        //gameloop wird erzeugt
        Timeline loop = new Timeline();
        loop.setCycleCount(Timeline.INDEFINITE);

        //grid wird erzeugt
        Grid grid = new Grid(canvas.getWidth(), canvas.getHeight(), this.fieldSize, this.lineWidth);
        Field fields[][] = grid.getFields();
        primaryStage.setWidth((fields.length * fieldSize) + (fields.length * lineWidth));
        primaryStage.setHeight((fields[0].length * fieldSize) + (fields[0].length * lineWidth));

        //zeichnet vertikale Linien
        for( int i = 1; i < fields.length; i++ ) {
            Line line = new Line(
                    (i * this.fieldSize) + (i * this.lineWidth),
                    0,
                    (i * this.fieldSize) + (i * this.lineWidth),
                    (fields[0].length * this.fieldSize) + (fields[0].length * this.lineWidth)
            );
            line.setStrokeWidth(lineWidth);
            root.getChildren().add(line);
        }

        //zeichnet horizontale Linien
        for( int j = 1; j < fields[0].length; j++ ) {
            Line line = new Line(
                    0,
                    (j * this.fieldSize) + (j * this.lineWidth),
                    (fields.length * this.fieldSize) + (fields.length * this.lineWidth),
                    (j * this.fieldSize) + (j * this.lineWidth)
            );
            line.setStrokeWidth(lineWidth);
            root.getChildren().add(line);
        }

        //setzt isAlive der fields aus fields[][]

        //Wiki andere Objekte, Bsp 1.
        grid.setAlive(44, 42, true);
        grid.setAlive(45, 42, true);
        grid.setAlive(46, 42, true);

        grid.setAlive(44, 43, true);
        grid.setAlive(46, 43, true);
        grid.setAlive(44, 44, true);
        grid.setAlive(46, 44, true);

        grid.setAlive(44, 46, true);
        grid.setAlive(46, 46, true);
        grid.setAlive(44, 47, true);
        grid.setAlive(46, 47, true);

        grid.setAlive(44, 48, true);
        grid.setAlive(45, 48, true);
        grid.setAlive(46, 48, true);


        //Pulsator
        grid.setAlive(19, 20, true);
        grid.setAlive(20, 20, true);
        grid.setAlive(21, 20, true);

        grid.setAlive(19, 21, true);
        grid.setAlive(21, 21, true);


        grid.setAlive(19, 22, true);
        grid.setAlive(20, 22, true);
        grid.setAlive(21, 22, true);

        grid.setAlive(19, 23, true);
        grid.setAlive(20, 23, true);
        grid.setAlive(21, 23, true);

        grid.setAlive(19, 24, true);
        grid.setAlive(20, 24, true);
        grid.setAlive(21, 24, true);

        grid.setAlive(19, 25, true);
        grid.setAlive(20, 25, true);
        grid.setAlive(21, 25, true);


        grid.setAlive(19, 26, true);
        grid.setAlive(21, 26, true);

        grid.setAlive(19, 27, true);
        grid.setAlive(20, 27, true);
        grid.setAlive(21, 27, true);


        //System.out.println(grid.toString());

        //setzt aliveFields
        for( int i = 0; i < grid.getFields().length; i++ ) {
            for( int j = 0; j < grid.getFields()[0].length; j++ ) {
                if( grid.getFields()[i][j].getIsAlive() ) {
                    grid.getAliveFields().add(grid.getFields()[i][j]);
                    Rectangle rec = new Rectangle(
                            (grid.getFields()[i][j].getX() * this.fieldSize) + (grid.getFields()[i][j].getX() * this.lineWidth),
                            (grid.getFields()[i][j].getY() * this.fieldSize) + (grid.getFields()[i][j].getY() * this.lineWidth),
                            (this.fieldSize) + this.lineWidth,
                            (this.fieldSize) + this.lineWidth
                    );
                    rec.setFill(Color.BLACK);
                    root.getChildren().add(rec);
                    grid.getFields()[i][j].setRec(rec);
                }
            }
        }


        //erzeugt den KeyFrame
        KeyFrame kf = new KeyFrame(
                //zeit die jeder Frame läuft
                Duration.millis(200),
                //code der pro frame ausgeführt werden soll
                ae -> {
                    List<Field> aliveFields = grid.getAliveFields();
                    List<Field> shouldAliveFields = new ArrayList<>(aliveFields);

                    //Listsize
                    System.out.println(grid.getAliveFields().size() + "/" + shouldAliveFields.size());

                    //Regeln anwenden
                    aliveFields.forEach(aliveField -> {
                        List<Field> surrAliveFields = grid.getSurrAliveFields(aliveField);
                        if( surrAliveFields.size() < 2 || surrAliveFields.size() > 3 ) {
                            shouldAliveFields.remove(aliveField);
                        }
                        grid.getSurrFields(aliveField).forEach(surrField -> {
                            if( !surrField.getIsAlive() && !shouldAliveFields.contains(surrField) ) {
                                if( grid.getSurrAliveFields(surrField).size() == 3 ) {
                                    shouldAliveFields.add(surrField);
                                }
                            }
                        });
                    });

                    for( Field aliveField : grid.getAliveFields() ) {
                        if( !shouldAliveFields.contains(aliveField) ) {
                            System.out.println("ifdel");
                            aliveField.setIsAlive(false);
                            root.getChildren().remove(aliveField.getRec());
                        }
                    }

                    //aliveFields zeichnen
                    for( Field shouldField : shouldAliveFields ) {
                        if( !(grid.getAliveFields().contains(shouldField)) ) {
                            System.out.println("ifdraw");
                            Rectangle rec = new Rectangle(
                                    (shouldField.getX() * this.fieldSize) + (shouldField.getX() * this.lineWidth),
                                    (shouldField.getY() * this.fieldSize) + (shouldField.getY() * this.lineWidth),
                                    (this.fieldSize) + this.lineWidth,
                                    (this.fieldSize) + this.lineWidth
                            );
                            rec.setFill(Color.BLACK);
                            root.getChildren().add(rec);
                            shouldField.setIsAlive(true);
                            shouldField.setRec(rec);
                        }
                    }

                    System.out.println(grid.getAliveFields().size() + "/" + shouldAliveFields.size());
                    grid.setAliveFields(shouldAliveFields);
                });

        //keyframe wird dem loop hinzugefügt, gestartet und das fenster wird gezeigt
        loop.getKeyFrames().add(kf);
        loop.play();
        primaryStage.show();
    }
}
