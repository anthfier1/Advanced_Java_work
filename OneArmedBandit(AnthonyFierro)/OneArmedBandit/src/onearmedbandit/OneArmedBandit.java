package onearmedbandit;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Anthony Fierro
 */
public class OneArmedBandit extends Application {

    Random r1 = new Random();
    Random r2 = new Random();
    Random r3 = new Random();// have 3 random num generators
    ImageView leftIV;
    ImageView middleIV;
    ImageView rightIV;
    Boolean spinning = false;
    Thread spin = new Thread();
    Timer spinTimer = new Timer();

    @Override
    public void start(Stage primaryStage) {

        // create panes for the slot machine
        BorderPane mainScreen = new BorderPane();
        FlowPane picturePane = new FlowPane();
        mainScreen.setCenter(picturePane);

        Label cred = new Label("Credits: $");
        Label creditsLabel = new Label("1000");
        HBox creditsBox = new HBox();
        creditsBox.getChildren().addAll(cred, creditsLabel);
        creditsBox.setAlignment(Pos.CENTER);
        mainScreen.setLeft(creditsBox);

        // make media player
        File music = new File("8-Bit-Mayhem.mp3");
        File win = new File("Triangle-Bell.wav");
        Media winMusic = new Media(win.toURI().toString());
        MediaPlayer winPlayer = new MediaPlayer(winMusic);
        Media slotMusic = new Media(music.toURI().toString());
        MediaPlayer mPlayer = new MediaPlayer(slotMusic);

        // create buttons for start/stop and quit
        Button startStopButton = new Button("Start");
        Button quitButton = new Button("Quit");
        Button quitYes = new Button("Yes");
        Button quitNo = new Button("No");

        //create scene for quit popup
        Label areYourSureLabel = new Label("Are you sure you want to quit?");
        HBox quitButtonBox = new HBox();
        quitButtonBox.getChildren().addAll(quitYes, quitNo);
        VBox quitWindowBox = new VBox();
        quitWindowBox.getChildren().addAll(areYourSureLabel, quitButtonBox);
        quitWindowBox.setAlignment(Pos.CENTER);
        quitWindowBox.setPadding(new Insets(25));

        // create stage for the quit popup
        Scene quitWindow = new Scene(quitWindowBox, 250, 250);
        Stage quitStage = new Stage();
        quitStage.setScene(quitWindow);

        // make hbox for the start stop & quit buttons
        HBox slotButtonBox = new HBox();
        slotButtonBox.getChildren().addAll(startStopButton, quitButton);
        slotButtonBox.setAlignment(Pos.CENTER);
        slotButtonBox.setPadding(new Insets(25));
        mainScreen.setBottom(slotButtonBox);

        // make images
        URL appleURL = getClass().getResource("Apple.jpg");
        Image apple = new Image(appleURL.toString());

        URL BananaURL = getClass().getResource("Banana.jpg");
        Image banana = new Image(BananaURL.toString());

        URL orangeURL = getClass().getResource("Orange.png");
        Image orange = new Image(orangeURL.toString());

        URL pearURL = getClass().getResource("Pear.jpg");
        Image pear = new Image(pearURL.toString());

        URL pineappleURL = getClass().getResource("Pineapple.png");
        Image pineapple = new Image(pineappleURL.toString());

        URL strawberryURL = getClass().getResource("Strawberry.jpg");
        Image strawberry = new Image(strawberryURL.toString());

        //make array list of images
        ArrayList<Image> imageArray = new ArrayList<>();
        imageArray.add(apple);
        imageArray.add(banana);
        imageArray.add(orange);
        imageArray.add(pear);
        imageArray.add(pineapple);
        imageArray.add(strawberry);

        leftIV = new ImageView(imageArray.get(r1.nextInt(6)));
        leftIV.setFitHeight(250);
        leftIV.setFitWidth(250);
        middleIV = new ImageView(imageArray.get(r2.nextInt(6)));
        middleIV.setFitHeight(250);
        middleIV.setFitWidth(250);
        rightIV = new ImageView(imageArray.get(r3.nextInt(6)));
        rightIV.setFitHeight(250);
        rightIV.setFitWidth(250);
        picturePane.getChildren().addAll(leftIV, middleIV, rightIV);

        startStopButton.setOnAction(e -> {

            Runnable spinRunnable = new Runnable() {
                @Override
                public void run() {

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
            };
            // new Thread(spinRunnable).start(); 
            if ("Start".equals(startStopButton.getText())) {

                mPlayer.play();
                spinning = true;
                startStopButton.setText("Stop");

                spinTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        try {
                            while (spinning) {
                                leftIV.setImage(imageArray.get(r1.nextInt(6)));
                                Thread.sleep(200);
                                middleIV.setImage(imageArray.get(r2.nextInt(6)));
                                Thread.sleep(225);
                                rightIV.setImage(imageArray.get(r3.nextInt(6)));
                                Thread.sleep(260);
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                }, 200);
            } else {
                startStopButton.setText("Start");

                // if 3 random number are the same player wins 1000$
                // sounds of bells and whistles goes off to let player know they won. 
                mPlayer.stop();
                spinning = false;
                if (leftIV.getImage() == middleIV.getImage() && middleIV.getImage() == rightIV.getImage()) {
                    winPlayer.play();
                    int temp = Integer.parseInt(creditsLabel.getText());
                    temp = temp + 1000;
                    String tempCred = Integer.toString(temp);
                    creditsLabel.setText(tempCred);
                } // else player loses 1$ 
                else {
                    int temp = Integer.parseInt(creditsLabel.getText());
                    temp = temp - 1;
                    String tempCred = Integer.toString(temp);
                    creditsLabel.setText(tempCred);
                }
            }
        });

        quitButton.setOnAction(e
                -> {
            // make popup window to ask if the user really wants to quit
            quitStage.show();

        }
        );

        quitYes.setOnAction(e
                -> {
            // close everything
            System.exit(0);
        }
        );

        quitNo.setOnAction(e
                -> {
            // close the popup window
            quitStage.close();
        }
        );

        Scene scene = new Scene(mainScreen, 850, 600);

        primaryStage.setTitle(
                "One Armed Bandit");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args
    ) {
        launch(args
        );

    }
}
