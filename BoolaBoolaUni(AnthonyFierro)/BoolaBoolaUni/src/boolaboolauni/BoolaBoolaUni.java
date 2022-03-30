/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boolaboolauni;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Anthony Fierro
 */
public class BoolaBoolaUni extends Application {

    // create the menubar
   

    @Override
    public void start(Stage primaryStage) {
        
        MenuBar MainMB = new MenuBar();
         
        GridPane demographicGridPane = new GridPane();
        GridPane nmDemographicPane = new GridPane();
        Pane quitPane = new Pane();
        
        
        // make the mainscreen        
        Text mainScreenInstructions = new Text("Please use the menu bar above for Admissions, Registration, or Reports!");
        mainScreenInstructions.setFont(Font.font(15));
        BorderPane mainScreen = new BorderPane();
        mainScreen.setTop(MainMB);
        mainScreen.setCenter(mainScreenInstructions);
        Scene scene = new Scene(mainScreen, 700, 500);
        
        // make the quit screen
        Label quitLabel = new Label("Are you sure you want to quit?");
        quitLabel.setFont(Font.font(15));
        Button quitYes = new Button("Yes");
        quitYes.setOnAction(e -> {System.exit(0);});
        Button quitNo = new Button("No");
        quitNo.setOnAction(e -> {
        primaryStage.setScene(scene);
        primaryStage.show(); 
        });
        HBox quitBtnBox = new HBox();
        quitBtnBox.getChildren().addAll(quitYes, quitNo);
        quitBtnBox.setAlignment(Pos.CENTER);
        VBox quitBox = new VBox();
        quitBox.getChildren().addAll(quitLabel,quitBtnBox);
        quitBox.setAlignment(Pos.CENTER);
        quitBox.setPadding(new Insets(30,30,30,30));
        quitPane.getChildren().add(quitBox);
        
        
        //create the Demographics page --------------------------------------
        Label ssnLB = new Label("Social Security Number:");
        Label firstNameLB = new Label("First Name:");
        Label middleInitLB = new Label("Middle Initial:");
        Label lastNameLB = new Label("Last Name:");
        Label streetLB = new Label("Street Address:");
        Label cityLB = new Label("City:");
        Label stateLB = new Label("State:");
        Label zipLB = new Label("Zip Code:");
        Label matriculation = new Label("Year of Matriculation: ");
        TextField ssnTF = new TextField();
        TextField firstNameTF = new TextField();
        TextField middleInitTF = new TextField();
        TextField lastNameTF = new TextField();
        TextField streetTF = new TextField();
        TextField cityTF = new TextField();
        TextField stateTF = new TextField();
        TextField zipTF = new TextField();
        ComboBox yearOfMatriculation = new ComboBox();
        yearOfMatriculation.getItems().add(0, "Freshman");
        yearOfMatriculation.getItems().add(1, "Sophmore");
        yearOfMatriculation.getItems().add(2, "Junior");
        yearOfMatriculation.getItems().add(3, "Senior");
        Label degreeLabel = new Label("What is your degree?");
        ComboBox degreeBox = new ComboBox();
        degreeBox.getItems().add(0, "Associate of Science in Computer Science");
        degreeBox.getItems().add(1, "Associate of Arts in Humanities");
        Label hsDipolmaLabel = new Label("High School Dipolma");
        Label immunizationLabel = new Label("Immunization");
        CheckBox hsDipomalCB = new CheckBox();
        CheckBox immunizationCB = new CheckBox();
    
        //matriculated grid pane -----------------------------------------------
        demographicGridPane.add(MainMB, 0, 0);
        demographicGridPane.add(ssnLB, 0, 0);
        demographicGridPane.add(ssnTF, 1, 0);
        demographicGridPane.add(firstNameLB, 0, 1);
        demographicGridPane.add(firstNameTF, 1, 1);
        demographicGridPane.add(middleInitLB, 0, 2);
        demographicGridPane.add(middleInitTF, 1, 2);
        demographicGridPane.add(lastNameLB, 0, 3);
        demographicGridPane.add(lastNameTF, 1, 3);
        demographicGridPane.add(streetLB, 0, 4);
        demographicGridPane.add(streetTF, 1, 4);
        demographicGridPane.add(cityLB, 0, 5);
        demographicGridPane.add(cityTF, 1, 5);
        demographicGridPane.add(stateLB, 0, 6);
        demographicGridPane.add(stateTF, 1, 6);
        demographicGridPane.add(zipLB, 0, 7);
        demographicGridPane.add(zipTF, 1, 7);
        demographicGridPane.add(matriculation, 0, 8);
        demographicGridPane.add(yearOfMatriculation, 1, 8);
        demographicGridPane.add(degreeLabel, 0, 9);
        demographicGridPane.add(degreeBox, 1, 9);
        demographicGridPane.add(hsDipolmaLabel, 0, 10);
        demographicGridPane.add(hsDipomalCB, 1, 10);
        demographicGridPane.add(immunizationLabel, 0, 11);
        demographicGridPane.add(immunizationCB, 1, 11);
        
        //nonmatriculated grid pane -----------------------------------------------
        
        nmDemographicPane.add(ssnLB, 0, 0);
        nmDemographicPane.add(ssnTF, 1, 0);
        nmDemographicPane.add(firstNameLB, 0, 1);
        nmDemographicPane.add(firstNameTF, 1, 1);
        nmDemographicPane.add(middleInitLB, 0, 2);
        nmDemographicPane.add(middleInitTF, 1, 2);
        nmDemographicPane.add(lastNameLB, 0, 3);
        nmDemographicPane.add(lastNameTF, 1, 3);
        nmDemographicPane.add(streetLB, 0, 4);
        nmDemographicPane.add(streetTF, 1, 4);
        nmDemographicPane.add(cityLB, 0, 5);
        nmDemographicPane.add(cityTF, 1, 5);
        nmDemographicPane.add(stateLB, 0, 6);
        nmDemographicPane.add(stateTF, 1, 6);
        nmDemographicPane.add(zipLB, 0, 7);
        nmDemographicPane.add(zipTF, 1, 7);
        nmDemographicPane.add(immunizationLabel, 0, 8);
        nmDemographicPane.add(immunizationCB, 1, 8);
        //--------------------------------------------------------------------------
        
        //make and add the menu items to the menubar
        //admission--------------------------------------------------------------------------------
        Menu admissionMenu = new Menu("_Admission");
        MenuItem matriculated = new MenuItem("Matriculated");
        MenuItem nonMatriculated = new MenuItem("Non-Matriculated");
        MenuItem quit = new MenuItem("Quit");

        // add items to the menu 
        admissionMenu.getItems().addAll(matriculated, nonMatriculated, new SeparatorMenuItem(), quit);
        // set accelerators
        matriculated.setAccelerator(KeyCombination.keyCombination("shortcut + M"));
        nonMatriculated.setAccelerator(KeyCombination.keyCombination("shortcut + N"));
        quit.setAccelerator(KeyCombination.keyCombination("shortcut + Q"));

        // set eventhandlers
        matriculated.setOnAction(e -> {
        
        Scene matriculatedScene = new Scene(demographicGridPane,700,500);
        primaryStage.setScene(matriculatedScene);
        primaryStage.show();
            
        }); // open up Demographic page and put in matriculated as degree
        nonMatriculated.setOnAction(e -> {
            
        Scene nonMatriculatedScene = new Scene(nmDemographicPane,700,500);
        primaryStage.setScene(nonMatriculatedScene);
        primaryStage.show();   
            
        }); // open up demograpic page and put in nonmatriculated as degree
        quit.setOnAction(e -> {
        Scene quitScene = new Scene(quitPane,700,500);
        primaryStage.setScene(quitScene);
        primaryStage.show();
        }); // open are you sure window with a yes or no button. 

        MainMB.getMenus().add(admissionMenu);
        
        //registration----------------------------------------------------------------------------------------
        Menu registrationMenu = new Menu("_Registration");
        MenuItem fullTime = new MenuItem("Full Time");
        MenuItem partTime = new MenuItem("Part Time");
        MenuItem nonCredit = new MenuItem("Non-Credit");

        // add items to the menu 
        registrationMenu.getItems().addAll(fullTime, partTime, new SeparatorMenuItem(), nonCredit);
        // set accelerators
        fullTime.setAccelerator(KeyCombination.keyCombination("shortcut + F"));
        partTime.setAccelerator(KeyCombination.keyCombination("shortcut + P"));
        nonCredit.setAccelerator(KeyCombination.keyCombination("shortcut + C"));

        // set eventhandlers
        fullTime.setOnAction(e -> {
        }); // open up registration page and show classes
        partTime.setOnAction(e -> {
        }); // open up registration page and show class
        nonCredit.setOnAction(e -> {
        }); // oopen up registration page and show non credit class

        MainMB.getMenus().add(registrationMenu);
        //reports----------------------------------------------------------------------------------------
        Menu reportsMenu = new Menu("R_eports");
        MenuItem receivables = new MenuItem("Receivables");
        MenuItem classSched = new MenuItem("Class Schedule");

        // add items to the menu 
        reportsMenu.getItems().addAll(receivables, classSched);
        // set accelerators
        receivables.setAccelerator(KeyCombination.keyCombination("shortcut + R"));
        classSched.setAccelerator(KeyCombination.keyCombination("shortcut + S"));

        // set eventhandlers
        receivables.setOnAction(e -> {
        }); // get ssn and find the amount due
        classSched.setOnAction(e -> {
        }); // get ssn and find classes that were registered

        MainMB.getMenus().add(reportsMenu);

        //----------------------------------------------------------------------------------------------------


        primaryStage.setTitle("Boola Boola University!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

/*    public void makeAdmissionMenu() {

        Menu admissionMenu = new Menu("_Admission");
        MenuItem matriculated = new MenuItem("Matriculated");
        MenuItem nonMatriculated = new MenuItem("Non-Matriculated");
        MenuItem quit = new MenuItem("Quit");

        // add items to the menu 
        admissionMenu.getItems().addAll(matriculated, nonMatriculated, new SeparatorMenuItem(), quit);
        // set accelerators
        matriculated.setAccelerator(KeyCombination.keyCombination("shortcut + M"));
        nonMatriculated.setAccelerator(KeyCombination.keyCombination("shortcut + N"));
        quit.setAccelerator(KeyCombination.keyCombination("shortcut + Q"));

        // set eventhandlers
        matriculated.setOnAction(e -> {
            
            demo
            
        }); // open up Demographic page and put in matriculated as degree
        nonMatriculated.setOnAction(e -> {
        }); // open up demograpic page and put in nonmatriculated as degree
        quit.setOnAction(e -> {
        }); // open are you sure window with a yes or no button. 

        MainMB.getMenus().add(admissionMenu);

    } */

  /*  public void makeRegistrationMenu() {

        Menu registrationMenu = new Menu("_Registration");
        MenuItem fullTime = new MenuItem("Full Time");
        MenuItem partTime = new MenuItem("Part Time");
        MenuItem nonCredit = new MenuItem("Non-Credit");

        // add items to the menu 
        registrationMenu.getItems().addAll(fullTime, partTime, new SeparatorMenuItem(), nonCredit);
        // set accelerators
        fullTime.setAccelerator(KeyCombination.keyCombination("shortcut + F"));
        partTime.setAccelerator(KeyCombination.keyCombination("shortcut + P"));
        nonCredit.setAccelerator(KeyCombination.keyCombination("shortcut + C"));

        // set eventhandlers
        fullTime.setOnAction(e -> {
        }); // open up registration page and show classes
        partTime.setOnAction(e -> {
        }); // open up registration page and show class
        nonCredit.setOnAction(e -> {
        }); // oopen up registration page and show non credit class

        MainMB.getMenus().add(registrationMenu);

    } */

/*    public void makeReportMenu() {

        Menu reportsMenu = new Menu("R_eports");
        MenuItem receivables = new MenuItem("Receivables");
        MenuItem classSched = new MenuItem("Class Schedule");

        // add items to the menu 
        reportsMenu.getItems().addAll(receivables, classSched);
        // set accelerators
        receivables.setAccelerator(KeyCombination.keyCombination("shortcut + R"));
        classSched.setAccelerator(KeyCombination.keyCombination("shortcut + S"));

        // set eventhandlers
        receivables.setOnAction(e -> {
        }); // get ssn and find the amount due
        classSched.setOnAction(e -> {
        }); // get ssn and find classes that were registered

        MainMB.getMenus().add(reportsMenu);

    }*/

}
