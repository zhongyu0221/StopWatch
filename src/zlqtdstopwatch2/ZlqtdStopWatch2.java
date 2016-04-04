/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//设计每一个秒和分 每次都call函数更新label数值 button 可见?
package zlqtdstopwatch2;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author dalemusser
 */
public class ZlqtdStopWatch2 extends Application {
//put the buttons and watches in a Hbox Vorder

    private ImageView hand;
    private Integer elapsedTime = 0; // seconds
    Timeline timeLine;
    private int sec1 = 0;
    private int sec2 = 0;
    private int min1 = 0;
    private int min2 = 0;
    Label digitalwatch;

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane(); //Grid排版
        grid.setAlignment(Pos.CENTER); //Layout置中
        //grid.setHgap(10); //水平距離
        //grid.setVgap(10); //垂直距離
        StackPane root = new StackPane();//for the watch

        ImageView clockFace = new ImageView();
        Image clockFaceImage = new Image(this.getClass().getResourceAsStream("clockface.png"));
        clockFace.setImage(clockFaceImage);

        hand = new ImageView();
        Image handImage = new Image(this.getClass().getResourceAsStream("hand.png"));
        hand.setImage(handImage);

        root.getChildren().addAll(clockFace, hand);

        Button start = new Button();//add three button
        start.setText("Start");
        Button stop = new Button();
        stop.setText("stop");
        Button reset = new Button();
        reset.setText("reset");

        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(start, stop, reset);


        digitalwatch = new Label();
        digitalwatch.setAlignment(Pos.CENTER);
        digitalwatch.setText(min1 + min2 + ":" + sec1 + sec2);
        digitalwatch.setFont(Font.font("Serif", FontWeight.NORMAL, 40));

        root.getChildren().addAll(hbBtn);
        
        grid.getChildren().addAll(digitalwatch);
      

        VBox all = new VBox();
        all.setAlignment(Pos.CENTER);
        all.getChildren().addAll(root,grid);
    
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeLine = new Timeline(new KeyFrame(Duration.millis(1000), actionEvent -> updateStopwatch()));//durantion is a class.milles is a static method
                timeLine.setCycleCount(Animation.INDEFINITE);
                timeLine.play();
            }
        });

        stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeLine.pause();
            }
        });

        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeLine.stop();
                resetWatch();

            }
        });

        Scene scene = new Scene(all,500,500);
        
       

        primaryStage.setTitle("Stopwatch");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void updateStopwatch() {
        elapsedTime++;//ms

        Integer rotation = elapsedTime * 6;//degree for one second
        hand.setRotate(rotation);
        
        
        sec2++;
        if (sec2 == 10) {
            sec1++;
            sec2 = 0;

            if (sec1 == 6) {
                min2++;
                sec1 = 0;

                if (min2 == 10) {
                    min1++;
                    min2 = 0;
                }
            }
        }
        digitalwatch.setText(min1 + min2 + ":" + sec1 + sec2);

    }

    public void resetWatch() {
        elapsedTime = 0;
        Integer rotation = elapsedTime * 6;//degree for one second
        hand.setRotate(rotation);

        min1 = 0;
        min2 = 0;
        sec1 = 0;
        sec2 = 0;
        digitalwatch.setText(min1 + min2 + ":" + sec1 + sec2);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
