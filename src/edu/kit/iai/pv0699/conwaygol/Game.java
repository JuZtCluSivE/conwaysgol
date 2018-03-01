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

    private final int fieldSize = 30;
    private final int lineWidth = 4;

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
        grid.setAlive(1, 1, true);

        grid.setAlive(3, 7, true);


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

        System.out.println(grid.toString());

        //erzeugt den KeyFrame
        KeyFrame kf = new KeyFrame(
                //zeit die jeder Frame läuft
                Duration.seconds(1),
                //code der pro frame ausgeführt werden soll
                ae -> {
                    //Regeln anwenden
                    List<Field> shouldAliveFields = new ArrayList<>(grid.getAliveFields());
                    //Collections.copy(shouldAliveFields, grid.getAliveFields());

                    //REGELN, dann:
                    for( int i = 0; i < grid.getAliveFields().size(); i++ ) {
                        System.out.println("Rule");
                        shouldAliveFields.add(grid.getFields()[(shouldAliveFields.get(i).getX() + 1)][shouldAliveFields.get(i).getY()]);
                    }

                    //alte recs entfernen
                    for( int i = 0; i < grid.getAliveFields().size(); i++ ) {
                        System.out.println("del" + i);
                        if( !shouldAliveFields.contains(grid.getAliveFields().get(i)) ) {
                            System.out.println("ifdel");
                            root.getChildren().remove(grid.getAliveFields().get(i).getRec());
                        }
                    }


                    //aliveFields zeichnen
                    for( int i = 0; i < shouldAliveFields.size(); i++ ) {
                        System.out.println("draw" + i);
                        if( !(grid.getAliveFields().contains(shouldAliveFields.get(i))) ) {
                            System.out.println("ifdraw");
                            Rectangle rec = new Rectangle(
                                    (shouldAliveFields.get(i).getX() * this.fieldSize) + (shouldAliveFields.get(i).getX() * this.lineWidth),
                                    (shouldAliveFields.get(i).getY() * this.fieldSize) + (shouldAliveFields.get(i).getY() * this.lineWidth),
                                    (this.fieldSize) + this.lineWidth,
                                    (this.fieldSize) + this.lineWidth
                            );
                            rec.setFill(Color.BLACK);
                            root.getChildren().add(rec);
                            shouldAliveFields.get(i).setRec(rec);
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
