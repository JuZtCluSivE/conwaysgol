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
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Conways Game of Life");

        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(800, 110);
        root.getChildren().add(canvas);

        final int fieldSize = 10;
        final int lineWidth = 1;
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline loop = new Timeline();
        loop.setCycleCount(Timeline.INDEFINITE);

        Grid grid = new Grid(canvas.getWidth(), canvas.getHeight(), fieldSize, lineWidth);
        Field fields[][] = grid.getFields();

        System.out.println(fields.length);
        System.out.println(fields[0].length);

        for( int i = 1; i <= fields.length; i++ ) {
            Line line = new Line(
                    (i * fieldSize) + (i * lineWidth),
                    0,
                    (i * fieldSize) + (i * lineWidth),
                    (fields[0].length * fieldSize) + (fields[0].length * lineWidth) //bis zum ende der höhe nicht arraybreite als höhe
            );
            root.getChildren().add(line);
        }

        for( int j = 1; j <= fields[0].length; j++ ) {
            Line line = new Line(
                    0,
                    (j * fieldSize) + (j * lineWidth),
                    (fields.length * fieldSize) + (fields.length * lineWidth), //bis zum ende der breite nicht arrayhöhe als breite
                    (j * fieldSize) + (j * lineWidth)
            );
            root.getChildren().add(line);
        }




        /*
        for( int i = 0; i < fields.length; i++) {
            Line line = new Line(0, (i * fieldSize + i), (fields.length * fieldSize + fields.length), (i * fieldSize + i));
            root.getChildren().add(line);
        }



        for( int j = 0; j < fields[0].length; j++) {
            Line line = new Line((j * fieldSize + j), 0, (j * fieldSize + j), (fields[0].length * fieldSize + fields[0].length));
            root.getChildren().add(line);
        }*/

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.01),
                ae -> {

                });

        loop.getKeyFrames().add(kf);
        loop.play();

        primaryStage.show();

    }
}
