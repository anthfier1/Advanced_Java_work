/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midterm;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.concurrent.Task;

/**
 *
 * @author Anthony Fierro
 */
public class Midterm extends Application {

    @Override
    public void start(Stage primaryStage) {

        //---------------------------------------------------------------
        final int AMOUNT_OF_PICKS = 6;
        long time = System.currentTimeMillis();

        int[] picks = new int[6];
        int[] winningNum = new int[6];

        GridPane mainScreen = new GridPane();
        Label quickLabel = new Label("Quick Pick");
        Label personLabel = new Label("Personal");
        CheckBox quickPick = new CheckBox();
        CheckBox personal = new CheckBox();
        Label yourPicks = new Label("Your Picks:");
        TextField pick1 = new TextField();
        TextField pick2 = new TextField();
        TextField pick3 = new TextField();
        TextField pick4 = new TextField();
        TextField pick5 = new TextField();
        TextField pick6 = new TextField();
        quickPick.setOnAction(e -> {
            if (personal.isSelected()) {
                personal.fire();
            }
            for (int i = 0; i < AMOUNT_OF_PICKS; i++) {

                picks[i] = ((int) (Math.random() * 50) + 1);

            }
            for (int i = 0; i < AMOUNT_OF_PICKS; i++) {
                for (int j = 0; j < AMOUNT_OF_PICKS; j++) {
                    if (picks[i] == picks[j]) {
                        picks[i] = ((int) (Math.random() * 50) + 2);
                    }
                }
            }
            pick1.setText(Integer.toString(picks[0]));
            pick2.setText(Integer.toString(picks[1]));
            pick3.setText(Integer.toString(picks[2]));
            pick4.setText(Integer.toString(picks[3]));
            pick5.setText(Integer.toString(picks[4]));
            pick6.setText(Integer.toString(picks[5]));
        });
        personal.setOnAction(e -> {
            if (quickPick.isSelected()) {
                quickPick.fire();
            }
            pick1.clear();
            pick2.clear();
            pick3.clear();
            pick4.clear();
            pick5.clear();
            pick6.clear();
        });
        //---------------------------------------------------------------
        HBox checkHBox = new HBox();
        checkHBox.getChildren().addAll(quickLabel, quickPick, personLabel, personal);
        checkHBox.setAlignment(Pos.CENTER);
        mainScreen.add(checkHBox, 6, 0);
        HBox pickHBox = new HBox();
        pickHBox.getChildren().addAll(yourPicks, pick1, pick2, pick3, pick4, pick5, pick6);
        pickHBox.setAlignment(Pos.CENTER);
        pickHBox.setPadding(new Insets(30, 30, 30, 30));
        mainScreen.add(pickHBox, 6, 1);
        Label winner = new Label("Winners: ");
        TextField win1 = new TextField();
        TextField win2 = new TextField();
        TextField win3 = new TextField();
        TextField win4 = new TextField();
        TextField win5 = new TextField();
        TextField win6 = new TextField();
        HBox winHBox = new HBox();
        winHBox.getChildren().addAll(winner, win1, win2, win3, win4, win5, win6);
        winHBox.setAlignment(Pos.CENTER);
        winHBox.setPadding(new Insets(30, 30, 30, 30));
        mainScreen.add(winHBox, 6, 2);
        //---------------------------------------------------------------
        Button start = new Button("Start");
        Button stop = new Button("Stop");
        Button reset = new Button("Reset");
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(start, stop, reset);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(30, 30, 30, 30));
        mainScreen.add(buttonBox, 6, 3);
        //-------------------------------------------------------------
        Label threeofSix = new Label("3 Of 6");
        Label fourofSix = new Label("4 Of 6");
        Label fiveofSix = new Label("5 Of 6");
        Label sixofSix = new Label("6 Of 6");
        Label drawings = new Label("Drawings");
        Label years = new Label("Years");
        TextField threeofSixTF = new TextField();
        TextField fourofSixTF = new TextField();
        TextField fiveofSixTF = new TextField();
        TextField sixofSixTF = new TextField();
        TextField drawingsTF = new TextField();
        TextField yearsTF = new TextField();
        long startTime = System.currentTimeMillis();

        HBox matchingBox = new HBox();
        matchingBox.getChildren().addAll(threeofSix, threeofSixTF, fourofSix, fourofSixTF, fiveofSix, fiveofSixTF);
        matchingBox.setAlignment(Pos.CENTER);
        matchingBox.setPadding(new Insets(30, 30, 30, 30));
        mainScreen.add(matchingBox, 6, 4);

        HBox matchingBox2 = new HBox();
        matchingBox2.getChildren().addAll(sixofSix, sixofSixTF, drawings, drawingsTF, years);
        matchingBox2.setAlignment(Pos.CENTER);
        matchingBox2.setPadding(new Insets(30, 30, 30, 30));
        mainScreen.add(matchingBox2, 6, 5);
        mainScreen.add(yearsTF, 7, 5);
        //-------------------------------------------------------------

        start.setOnAction(e -> {

            int games = 0;
            int matchingNum;
            int threeMatches;
            int fourMatches;
            int fiveMatches;
            int sixMatches = 0;
            int drawingsNum = 0;
            int year = 1;

            while (sixMatches != 1) {
                games++;
                matchingNum = 0;
                threeMatches = 0;
                fourMatches = 0;
                fiveMatches = 0;
                sixMatches = 0;

                for (int i = 0; i < AMOUNT_OF_PICKS; i++) {
                    winningNum[i] = (int) ((Math.random() * 50) + 1);
                }
                for (int i = 0; i < AMOUNT_OF_PICKS; i++) {
                    for (int j = 0; j < AMOUNT_OF_PICKS; j++) {
                        if (winningNum[i] == winningNum[j]) {
                            winningNum[i] = ((int) (Math.random() * 50) -1);
                        }
                    }

                    win1.setText(Integer.toString(winningNum[0]));
                    win2.setText(Integer.toString(winningNum[1]));
                    win3.setText(Integer.toString(winningNum[2]));
                    win4.setText(Integer.toString(winningNum[3]));
                    win5.setText(Integer.toString(winningNum[4]));
                    win6.setText(Integer.toString(winningNum[5]));

                    for (int k = 0; k < AMOUNT_OF_PICKS; k++) {
                        for (int j = 0; j < AMOUNT_OF_PICKS; j++) {
                            if (picks[i] == winningNum[j]) {
                                matchingNum++;
                            }
                        }
                    }
                    if (matchingNum == 3) {
                        threeMatches++;
                        threeofSixTF.setText(Integer.toString(threeMatches));
                    }
                    if (matchingNum == 4) {
                        fourMatches++;
                        fourofSixTF.setText(Integer.toString(fourMatches));
                    }
                    if (matchingNum == 5) {
                        fiveMatches++;
                        fiveofSixTF.setText(Integer.toString(fiveMatches));
                    }
                    if (matchingNum == 6) {
                        sixMatches++;
                        sixofSixTF.setText(Integer.toString(sixMatches));
                    }

                    drawingsNum++;
                    drawingsTF.setText(Integer.toString(drawingsNum));

                }
            }
            });

            stop.setOnAction(e -> {

            });

            reset.setOnAction(e -> {
                pick1.clear();
                pick2.clear();
                pick3.clear();
                pick4.clear();
                pick5.clear();
                pick6.clear();
                win1.clear();
                win2.clear();
                win3.clear();
                win4.clear();
                win5.clear();
                win6.clear();
                threeofSixTF.clear();
                fourofSixTF.clear();
                fiveofSixTF.clear();
                sixofSixTF.clear();
                yearsTF.clear();
                drawingsTF.clear();
            });
            //-------------------------------------------------------------

            Scene scene = new Scene(mainScreen, 1200, 600);
            primaryStage.setTitle("Midterm");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        /**
         * @param args the command line arguments
         */

    public static void main(String[] args) {
        launch(args);
    }

}
