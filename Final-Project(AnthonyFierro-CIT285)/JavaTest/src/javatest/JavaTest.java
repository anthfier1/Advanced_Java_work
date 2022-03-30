package javatest;

import com.sun.jndi.dns.DnsContextFactory;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

/**
 *
 * @author Anthony Fierro
 */
public class JavaTest extends Application {

    //arrays for all the questions and answers
    ArrayList<String> easy = new ArrayList<>();
    ArrayList<String> medium = new ArrayList<>();
    ArrayList<String> hard = new ArrayList<>();
    ArrayList<String> veryHard = new ArrayList<>();
    ArrayList<String> easyAnswers = new ArrayList<>();
    ArrayList<String> mediumAnswers = new ArrayList<>();
    ArrayList<String> hardAnswers = new ArrayList<>();
    ArrayList<String> veryHardAnswers = new ArrayList<>();
    int correct = 0;
    int answered = 0;
    int tries = 0;
    int currentNum = 0;
    String username = new String();
    String pass = new String();
    String questString = new String();
    Text tooManyTries = new Text("You can only attempt the quiz two times");
    Random currentQA = new Random();
    Random randomAnswersForRadioButtons = new Random();
    Label timeLeft = new Label();
    Timer quizTime = new Timer();
    Label ScoreLabel = new Label();
    final int numQuestions = 20;
    int num;

    @Override
    public void start(Stage primaryStage) {

        tooManyTries.setFont(Font.font(15));
        initEasy();
        initMedium();
        initHard();
        initVeryHard();
        initEasyAnswers();
        initMediumAnswers();
        initHardAnswers();
        initVeryHardAnswers();

        // pane for the quiz
        BorderPane testPane = new BorderPane();

        //home page pane
        Label usernameLabel = new Label("Enter your first name: ");
        TextField userTF = new TextField();
        Label passwordLabel = new Label("Enter a password: ");
        TextField passTF = new TextField();
        HBox userHBox = new HBox();
        userHBox.getChildren().addAll(usernameLabel, userTF);
        HBox passHBox = new HBox();
        passHBox.getChildren().addAll(passwordLabel, passTF);
        VBox loginVBox = new VBox();
        loginVBox.getChildren().addAll(userHBox, passHBox);
        loginVBox.setAlignment(Pos.CENTER);
        testPane.setCenter(loginVBox);

        // buttons for the test
        Button nextButton = new Button("Next Question");
        Button finishButton = new Button("Finish");
        Button backToLoginScreen = new Button("Go Back To The Login Screen.");

        HBox testButtonsBox = new HBox();
        testButtonsBox.getChildren().addAll(nextButton, finishButton);

        RadioButton r1 = new RadioButton();
        RadioButton r2 = new RadioButton();
        RadioButton r3 = new RadioButton();
        RadioButton r4 = new RadioButton();
        RadioButton r5 = new RadioButton();
        ToggleGroup answerGroup = new ToggleGroup();
        r1.setToggleGroup(answerGroup);
        r2.setToggleGroup(answerGroup);
        r3.setToggleGroup(answerGroup);
        r4.setToggleGroup(answerGroup);
        r5.setToggleGroup(answerGroup);

        VBox answerButtonsBox = new VBox();
        answerButtonsBox.getChildren().addAll(r1, r2, r3, r4, r5);
        answerButtonsBox.setAlignment(Pos.CENTER);

        Label question = new Label();
        question.setAlignment(Pos.CENTER);
        HBox qHBox = new HBox();
        qHBox.getChildren().add(question);
        qHBox.setAlignment(Pos.CENTER);
        testPane.setTop(qHBox);

        Button startQuizButton = new Button("Start Quiz");
        startQuizButton.setOnAction(e -> {
            this.correct = 0;
            this.username = userTF.getText();
            this.pass = passTF.getText();
            tries++;

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {

                String minutes = "10";
                String seconds = "00";
                int min = 0;
                int sec = 0;

                @Override
                public void run() {

                    try {
                        min = Integer.parseInt(minutes);
                        sec = Integer.parseInt(seconds) - 1;

                        if (Integer.parseInt(minutes) < 0) {
                            Platform.runLater(() -> {
                                timeLeft.setText("Time Is Up");
                                testPane.setCenter(loginVBox);
                                testPane.setBottom(startQuizButton);
                            });

                        }
                        if (sec < 0) {
                            min = min - 1;
                            sec = 59;
                        }
                        minutes = Integer.toString(min);
                        seconds = Integer.toString(sec);
                        Platform.runLater(() -> {
                            timeLeft.setText(minutes + ":" + seconds);
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 0, 1000);

            if (tries > 2) {
                testPane.setCenter(tooManyTries);
            } else {
                currentNum = currentQA.nextInt(numQuestions);
                testPane.setCenter(answerButtonsBox);
                question.setText(medium.get(currentNum));
                questString = question.getText();
                r3.setText(mediumAnswers.get(currentNum));
                r1.setText(mediumAnswers.get(currentQA.nextInt(numQuestions)));
                r2.setText(mediumAnswers.get(currentQA.nextInt(numQuestions)));
                r4.setText(mediumAnswers.get(currentQA.nextInt(numQuestions)));
                r5.setText(mediumAnswers.get(currentQA.nextInt(numQuestions)));
                testPane.setBottom(testButtonsBox);

            }
        });

        nextButton.setOnAction(e -> {
            num = currentNum;
            answered++;

            if (r3.isSelected() && questString.equals(easy.get(num))) {

                correct++;

                currentNum = currentQA.nextInt(numQuestions);
                testPane.setCenter(answerButtonsBox);
                question.setText(medium.get(currentNum));
                questString = question.getText();
                r3.setText(mediumAnswers.get(currentNum));
                r1.setText(mediumAnswers.get(currentQA.nextInt(numQuestions)));
                r2.setText(mediumAnswers.get(currentQA.nextInt(numQuestions)));
                r4.setText(mediumAnswers.get(currentQA.nextInt(numQuestions)));
                r5.setText(mediumAnswers.get(currentQA.nextInt(numQuestions)));
            } else {
                currentNum = currentQA.nextInt(numQuestions);
                testPane.setCenter(answerButtonsBox);
                question.setText(easy.get(currentNum));
                r3.setText(easyAnswers.get(currentNum));
                r1.setText(easyAnswers.get(currentQA.nextInt(numQuestions)));
                r2.setText(easyAnswers.get(currentQA.nextInt(numQuestions)));
                r4.setText(easyAnswers.get(currentQA.nextInt(numQuestions)));
                r5.setText(easyAnswers.get(currentQA.nextInt(numQuestions)));

            }

            if (r3.isSelected() && questString.equals(medium.get(num))) {

                correct++;
                currentNum = currentQA.nextInt(numQuestions);
                testPane.setCenter(answerButtonsBox);
                question.setText(hard.get(currentNum));
                questString = question.getText();
                r3.setText(hardAnswers.get(currentNum));
                r1.setText(hardAnswers.get(currentQA.nextInt(numQuestions)));
                r2.setText(hardAnswers.get(currentQA.nextInt(numQuestions)));
                r4.setText(hardAnswers.get(currentQA.nextInt(numQuestions)));
                r5.setText(hardAnswers.get(currentQA.nextInt(numQuestions)));
            } else {
                currentNum = currentQA.nextInt(numQuestions);
                testPane.setCenter(answerButtonsBox);
                question.setText(easy.get(currentNum));
                questString = question.getText();
                r3.setText(easyAnswers.get(currentNum));
                r1.setText(easyAnswers.get(currentQA.nextInt(numQuestions)));
                r2.setText(easyAnswers.get(currentQA.nextInt(numQuestions)));
                r4.setText(easyAnswers.get(currentQA.nextInt(numQuestions)));
                r5.setText(easyAnswers.get(currentQA.nextInt(numQuestions)));

            }

            if (r3.isSelected() && questString.equals(hard.get(num))) {

                correct++;
                currentNum = currentQA.nextInt(numQuestions);
                testPane.setCenter(answerButtonsBox);
                question.setText(veryHard.get(currentNum));
                questString = question.getText();
                r3.setText(veryHardAnswers.get(currentNum));
                r1.setText(veryHardAnswers.get(currentQA.nextInt(numQuestions)));
                r2.setText(veryHardAnswers.get(currentQA.nextInt(numQuestions)));
                r4.setText(veryHardAnswers.get(currentQA.nextInt(numQuestions)));
                r5.setText(veryHardAnswers.get(currentQA.nextInt(numQuestions)));
            } else {
                currentNum = currentQA.nextInt(numQuestions);
                testPane.setCenter(answerButtonsBox);
                question.setText(easy.get(currentNum));
                questString = question.getText();
                r3.setText(medium.get(currentNum));
                r1.setText(medium.get(currentQA.nextInt(numQuestions)));
                r2.setText(medium.get(currentQA.nextInt(numQuestions)));
                r4.setText(medium.get(currentQA.nextInt(numQuestions)));
                r5.setText(medium.get(currentQA.nextInt(numQuestions)));

            }

            if (r3.isSelected() && questString.equals(veryHard.get(num))) {

                correct++;
                currentNum = currentQA.nextInt(numQuestions);
                testPane.setCenter(answerButtonsBox);
                question.setText(veryHard.get(currentNum));
                questString = question.getText();
                r3.setText(veryHardAnswers.get(currentNum));
                r1.setText(veryHardAnswers.get(currentQA.nextInt(numQuestions)));
                r2.setText(veryHardAnswers.get(currentQA.nextInt(numQuestions)));
                r4.setText(veryHardAnswers.get(currentQA.nextInt(numQuestions)));
                r5.setText(veryHardAnswers.get(currentQA.nextInt(numQuestions)));
            } else {
                currentNum = currentQA.nextInt(numQuestions);
                testPane.setCenter(answerButtonsBox);
                question.setText(hard.get(currentNum));
                questString = question.getText();
                r3.setText(hardAnswers.get(currentNum));
                r1.setText(hardAnswers.get(currentQA.nextInt(numQuestions)));
                r2.setText(hardAnswers.get(currentQA.nextInt(numQuestions)));
                r4.setText(hardAnswers.get(currentQA.nextInt(numQuestions)));
                r5.setText(hardAnswers.get(currentQA.nextInt(numQuestions)));

            }

            System.out.println(r3.getText());
            System.out.println(answered);
            System.out.println(correct);
            System.out.println(question.getText());
            System.out.println(questString);

            if (answered >= 20) {
                answered = 0;
                double grade = calcGrade();

                if (grade >= 65.00 && grade < 75.00) {
                    ScoreLabel.setText("You are a Java Certified Programmer!");
                    ScoreLabel.setFont(Font.font(20));
                    testPane.setCenter(ScoreLabel);
                } else if (grade >= 75.00 && grade < 85.00) {
                    ScoreLabel.setText("You are a Java Certified Developer!!");
                    ScoreLabel.setFont(Font.font(20));
                    testPane.setCenter(ScoreLabel);
                } else if (grade >= 85.00 && grade <= 100.00) {
                    ScoreLabel.setText("You are a Java Certified Architect!");
                    ScoreLabel.setFont(Font.font(20));
                    testPane.setCenter(ScoreLabel);
                } else {
                    ScoreLabel.setText("You Failed. Please try again.");
                    ScoreLabel.setFont(Font.font(20));
                    testPane.setCenter(ScoreLabel);
                }

                testPane.setBottom(backToLoginScreen);
            }

        });

        finishButton.setOnAction(e -> {

            answered = 0;
            double grade = calcGrade();

            if (grade >= 65.00 && grade < 75.00) {
                ScoreLabel.setText("You are a Java Certified Programmer!");
            } else if (grade >= 75.00 && grade < 85.00) {
                ScoreLabel.setText("You are a Java Certified Developer!!");
            } else if (grade >= 85.00 && grade <= 100.00) {
                ScoreLabel.setText("You are a Java Certified Architect!");
            } else {
                ScoreLabel.setText("You Failed. Please try again.");
            }
            testPane.setBottom(backToLoginScreen);
        });

        backToLoginScreen.setOnAction(e -> {

            testPane.setCenter(loginVBox);
            testPane.setBottom(startQuizButton);

        });

        testPane.setBottom(startQuizButton);
        testPane.setRight(timeLeft);
        Scene scene = new Scene(testPane, 850, 600);

        primaryStage.setTitle("Java Quiz");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void initEasy() {

        easy.add("What is the difference between a constructor and method?");
        easy.add("A string is inmutable");
        easy.add("How many primative types are there?");
        easy.add("for (int i = 2; i < 125; i++)" + "\n" + "system.out.println(\"hi\")" + "\n" + "How many times will this code print hi?");
        easy.add("An abstract class can be instanciated.");
        easy.add("What does the super keyword do?");
        easy.add("What are the characteristics of an overloaded method?");
        easy.add("Can Arraylists hold primative type data?");
        easy.add("Is the the finally clause needed in a try{}catch{}finally{} for the code to compile without errors? ");
        easy.add("What is the value that Math.Random() produces?");
        easy.add("What type is the result of Math.Random()?");
        easy.add("What is the ASCII Value of \"A\"?");
        easy.add("You cannot add Characters together by their ASCII values.");
        easy.add("What does it mean if a method, class, or variable is static?");
        easy.add("for (int i = 0; i < 10; i++)\n{arrayX[i] = new Circle();} \n is it legal to have an array of objects like the code above?");
        easy.add("public final int var = 10; \n Can var be assigned a new value?");
        easy.add("What is an abstract class?");
        easy.add("int x; \n System.out.print(t); \n What will this code print out?");
        easy.add("All int's are signed");
        easy.add("What are the different kinds of constructors?");

    }

    public void initMedium() {
        medium.add("What are wrapper classes?");
        medium.add("What is the difference between equals() and == ?");
        medium.add("What are the access modifiers in Java?");
        medium.add("You must instanciate the Math class to use its methods.");
        medium.add("What does it mean if a method is depricated?");
        medium.add("String x = \"hello\";" + "\n" + "x = \"world\"" + "\n" + "If strings are inmutable will there be errors in this code.");
        medium.add("You can delete dynamic memory in java.");
        medium.add("What does System.gc(); do?");
        medium.add("What is the name of the default package given in java?");
        medium.add("When does a NumberFormatException occur?");
        medium.add("Where is dynamic memory stored in java?");
        medium.add("Where are primatives stored in java?");
        medium.add("How many bytes are in an int value?");
        medium.add("public static void main(String[] args){} \n What are args in the main function?");
        medium.add("There are no deconstructors in Java.");
        medium.add("What happens if you remove the static keyword from the main method declearation?");
        medium.add("import java.io.*; \n What does the star mean in this declearation?");
        medium.add("What is the superclass of all errors and exceptions?");
        medium.add("What class is used to handle all exceptions?");
        medium.add("How many subscripts are in this array? \n int[][] matrix = new int[5][5]");

    }

    public void initHard() {
        hard.add("What is sql?");
        hard.add("What is the tomcat server used for?");
        hard.add("static{ system.out.println(\"Will this code run?\"} \n Will this code run without errors?");
        hard.add("In JavaFX a border pane's side are named North, South, East, West.");
        hard.add("In Swing a border layout's side are named North, South, East, West.");
        hard.add("In HTML every opening tag needs a closing tag.");
        hard.add("public <E> void (E x){System.out.println(x); } \n What type will x be in this method? ");
        hard.add("button.setOnAction(e ->{}); \n What is the name of the \"e -> {}\"?");
        hard.add("What is the \"this\" keyword used for?");
        hard.add("Java allows objects to have multiple inheritance.");
        hard.add("What is a thread?");
        hard.add("What class does a servlet class extend?");
        hard.add("What does CSS stand for?");
        hard.add("What is CSS used for?");
        hard.add("What class do you use to display an image?");
        hard.add("What class do you use to play music or audio?");
        hard.add("Why is metadata useful in sql?");
        hard.add("A quick sort is the most efficient sort algorithm.");
        hard.add("E generic = new E(); \n Is this legal code?");
        hard.add("Generic types cannot be static.");

    }

    public void initVeryHard() {

        veryHard.add("public static int isThisRecursion(int x){ \n return x * isThisRecursion(x - 1);} \n is this an example of recursion done properly?");
        veryHard.add("With floating point numbers is there high order truncation?");
        veryHard.add("With floating point numbers there will be low order truncation.");
        veryHard.add("Which ports cannot be used?");
        veryHard.add("What is the IP Address of localhost?");
        veryHard.add("In HTML what is the correct tag to use java code?");
        veryHard.add("What keyword in HTML hides data entered when moving to a new page?");
        veryHard.add("What can you use javabeans for?");
        veryHard.add("What do you use to change the UI within while using mulitple threads?");
        veryHard.add("preparedStatement = connection.prepareStatement(\"Enter text Here\") \n What do prepared statments do in sql?");
        veryHard.add("preparedStatement.setString(1, S1);\n"
                + "preparedStatement.setString(2, S2);\n"
                + "preparedStatement.setString(3, S3);\n"
                + "preparedStatement.executeUpdate(); \n Will this code modify an sql table?");
        veryHard.add("What will set an accelerator in a menu?");
        veryHard.add("When updating an SQL table in java, you MUST use a try catch block.");
        veryHard.add("How does the computer read time?");
        veryHard.add("What does XHTML stand for?");
        veryHard.add("What is polymorphism?");
        veryHard.add("You cannot create your own exceptions classes.");
        veryHard.add("A scanner will read and write to and from a file.");
        veryHard.add("public void initalizeDB() throws SQLException, ClassNotFoundException{\n"
                + "\n"
                + "try {\n"
                + "Class.forName(\"com.mysql.jdbc.Driver\");\n"
                + "connection = DriverManager.getConnection(\"jdbc:mysql://localhost/mydata\", \"root\", \"root\");\n"
                + "stmt = connection.createStatement();\n"
                + "} catch (ClassNotFoundException | SQLException ex) {\n"
                + "ex.printStackTrace();\n"
                + "}\n"
                + "}"
                + "\n What will this method do?");
        veryHard.add("What does the ResultSet get from the sql database?");

    }

    public void initEasyAnswers() {
        easyAnswers.add("A constructor is used to initalize an object. A method is a behavoir");
        easyAnswers.add("True");
        easyAnswers.add("eight");
        easyAnswers.add("123");
        easyAnswers.add("False");
        easyAnswers.add("Call the parent classes constructor or methods.");
        easyAnswers.add("Same name but different parameters.");
        easyAnswers.add("No, only objects");
        easyAnswers.add("No");
        easyAnswers.add("0.0 - 1.0");
        easyAnswers.add("double");
        easyAnswers.add("65");
        easyAnswers.add("False");
        easyAnswers.add("It does not have to be instanciated");
        easyAnswers.add("Yes");
        easyAnswers.add("No, it is a constant value");
        easyAnswers.add("A class that contains abstract methods and cannot be instantiated");
        easyAnswers.add("0");
        easyAnswers.add("True");
        easyAnswers.add("Default and parameterized");

    }

    public void initMediumAnswers() {
        mediumAnswers.add("A class that turns primatives into objects");
        mediumAnswers.add("equals() is used for objects and == is used for primative types");
        mediumAnswers.add("Friendly, Private, Protected, Public");
        mediumAnswers.add("False");
        mediumAnswers.add("That it may not be supported in future releases");
        mediumAnswers.add("No, this is legal code");
        mediumAnswers.add("True");
        mediumAnswers.add("Sends a request to the garbage collector to delete dynamic memory");
        mediumAnswers.add("The class in all lowercase");
        mediumAnswers.add("When a non number is passed to a variable");
        mediumAnswers.add("The heap");
        mediumAnswers.add("The stack");
        mediumAnswers.add("4");
        mediumAnswers.add("Arguments from the command line");
        mediumAnswers.add("True");
        mediumAnswers.add("A runtime error occurs");
        mediumAnswers.add("Gets everything in the directory");
        mediumAnswers.add("Throwable");
        mediumAnswers.add("Throwable");
        mediumAnswers.add("25");

    }

    public void initHardAnswers() {
        hardAnswers.add("A database management system");
        hardAnswers.add("Display HTML code on the internet");
        hardAnswers.add("Yes");
        hardAnswers.add("False");
        hardAnswers.add("True");
        hardAnswers.add("True");
        hardAnswers.add("Whatever type is passed into the method");
        hardAnswers.add("A Lambda Expression");
        hardAnswers.add("A reference to a class instance variable");
        hardAnswers.add("False");
        hardAnswers.add("An object that facilitates the execution of a task");
        hardAnswers.add("HttpServlet");
        hardAnswers.add("Cascading Style Sheet");
        hardAnswers.add("Describing the presentation of HTML and other markup languages");
        hardAnswers.add("javafx.scene.image.ImageView");
        hardAnswers.add("javafx.scene.media.MediaPlayer");
        hardAnswers.add("It shows the basic data of a table or database.");
        hardAnswers.add("True");
        hardAnswers.add("No");
        hardAnswers.add("True");

    }

    public void initVeryHardAnswers() {
        veryHardAnswers.add("True");
        veryHardAnswers.add("No");
        veryHardAnswers.add("True");
        veryHardAnswers.add("0-1023");
        veryHardAnswers.add("127.0.0.1");
        veryHardAnswers.add("<%= %>");
        veryHardAnswers.add("Post");
        veryHardAnswers.add("Creating and sharing objects between different JSP pages");
        veryHardAnswers.add("Platform.runLater()");
        veryHardAnswers.add("Allow you to modify a table");
        veryHardAnswers.add("Yes");
        veryHardAnswers.add("x.setAccelerator(KeyCombination.keyCombination(\"shortcut + C\"));");
        veryHardAnswers.add("True, or else an error occurs.");
        veryHardAnswers.add("In milliseconds");
        veryHardAnswers.add("extensible hyper text markup language");
        veryHardAnswers.add("An methods that can overridden with different instances of a superclass");
        veryHardAnswers.add("false");
        veryHardAnswers.add("false");
        veryHardAnswers.add("Loads the driver to the desired database in SQL");
        veryHardAnswers.add("Whatever string used for the query");

    }

    public double calcGrade() {

        int incorrect = 20 - (this.correct / 4);
        double percentCorrect = ((double) this.correct / 20.0);
        double temp = 1.0 - percentCorrect;
        double incorrectPercent = incorrect * temp;
        double grade = ((double) this.correct * 5.0) - incorrectPercent;

        return grade;
    }

}
