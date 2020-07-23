package sample;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Window;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.animation.Animation;

import javafx.scene.image.*;



public class Main extends Application {

    Integer satisfy,sala;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FlowPane root1=new FlowPane();
        primaryStage.setTitle("Welcome");
        Scene scene1=new Scene(root1, 700, 550);
        primaryStage.setScene(scene1);
        Image hr=new Image("file:HR.png");
        ImageView hr1=new ImageView(hr);
        Button b=new Button("Next-->",hr1);
        b.setContentDisplay(ContentDisplay.TOP);

        root1.getChildren().addAll(b);
        primaryStage.show();

        GridPane root2=createRegistrationFormPane();
        //addUIControls(root2);
        Label headerLabel = new Label("REGISTER");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        root2.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));


        Label nameLabel = new Label("Full Name : ");
        root2.add(nameLabel, 0,1);
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        root2.add(nameField, 1,1);


        Label emailLabel = new Label("Email ID : ");
        root2.add(emailLabel, 0, 2);
        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        root2.add(emailField, 1, 2);


        Label passwordLabel = new Label("Password : ");
        root2.add(passwordLabel, 0, 3);
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        root2.add(passwordField, 1, 3);


        Button submitButton = new Button("Register");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        root2.add(submitButton, 0, 4, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));


        //scene3
        GridPane root3=createRegistrationFormPane();

        Scene scene3=new Scene(root3,800,800);

        Label l1 = new Label();

        l1.setText("Rate the satisfaction level on a scale of 10:");
        l1.setWrapText(true);

        root3.add(l1, 0,0);
        TextField t1 = new TextField();
        t1.setPrefHeight(40);
        t1.setMaxWidth(300);
        root3.add(t1, 1,0);
        Label l4= new Label("Last Evaluation?");
        root3.add(l4, 0,11);
        l4.setWrapText(true);

        RadioButton good=new RadioButton("Good");
        RadioButton bad=new RadioButton("Bad");

        ToggleGroup tg2=new ToggleGroup();
        good.setToggleGroup(tg2);
        bad.setToggleGroup(tg2);
        root3.add(good,1,11);
        root3.add(bad,1,12);



        Label l2= new Label("Avg_monthly_hours:");
        l2.setWrapText(true);
        root3.add(l2, 0,1);

        TextField t2 = new TextField();
        t2.setPrefHeight(40);
        t2.setMaxWidth(300);
        root3.add(t2, 1,1);


        Label l3= new Label("No of projects:");
        root3.add(l3, 0,2);
        l3.setWrapText(true);
        TextField t3 = new TextField();
        t3.setPrefHeight(40);
        t3.setMaxWidth(300);
        root3.add(t3, 1,2);

        Label l5= new Label("No of years in the company:");
        root3.add(l5, 0,3);
        l5.setWrapText(true);
        TextField t5 = new TextField();
        t5.setPrefHeight(40);
        t5.setMaxWidth(300);
        root3.add(t5, 1,3);

        Label l6= new Label("Work Accident?");
        root3.add(l6, 0,5);
        l6.setWrapText(true);

        RadioButton yes=new RadioButton("Yes");
        RadioButton no=new RadioButton("No");

        ToggleGroup tg=new ToggleGroup();
        yes.setToggleGroup(tg);
        no.setToggleGroup(tg);
        root3.add(yes,1,5);
        root3.add(no,1,6);

        Label l7= new Label("Promotion in last 5 years?");
        root3.add(l7, 0,8);
        l7.setWrapText(true);

        RadioButton yes1=new RadioButton("Yes");
        RadioButton no1=new RadioButton("No");

        ToggleGroup tg1=new ToggleGroup();
        yes1.setToggleGroup(tg1);
        no1.setToggleGroup(tg1);
        root3.add(yes1,1,8);
        root3.add(no1,1,9);

        Label l8=new Label("Department:");
        root3.add(l8, 0,14);

        ObservableList<String> dept= FXCollections.observableArrayList("Accounting","HR","IT","Management","Marketing","Product Manager","RndD","Sales","Support","Technical");
        ComboBox<String> dep=new ComboBox<String>(dept);
        root3.add(dep,1,14);

        Label l9=new Label("Salary:");
        root3.add(l9, 0,15);

        ObservableList<String> salary= FXCollections.observableArrayList("Low","Medium","High");
        ComboBox<String> sal=new ComboBox<String>(salary);
        root3.add(sal,1,15);

        Button predict = new Button("PREDICT");
        predict.setPrefHeight(40);
        predict.setDefaultButton(true);
        predict.setPrefWidth(100);
        root3.add(predict, 0, 18, 2, 1);
        root3.setHalignment(predict, HPos.CENTER);
        root3.setMargin(predict, new Insets(20, 0,20,0));


        predict.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int workacc,prom,eval;
                 satisfy=Integer.parseInt(t1.getText());
                Integer avg=Integer.parseInt(t2.getText());
                Integer proj=Integer.parseInt(t3.getText());
                Integer company=Integer.parseInt(t5.getText());
                RadioButton rb=(RadioButton)tg.getSelectedToggle();
                if(rb.getText().matches("Yes"))
                    workacc=1;
                else
                    workacc=0;

                RadioButton rb1=(RadioButton)tg1.getSelectedToggle();
                if(rb1.getText().matches("Yes"))
                    prom=1;
                else
                    prom=0;

                RadioButton rb2=(RadioButton)tg2.getSelectedToggle();
                if(rb2.getText().matches("Good"))
                    eval=1;
                else
                    eval=0;

                String domain=dep.getValue();
                String salary=sal.getValue();
                if(salary.equals("High"))
                    sala=1;
                 else
                     sala=0;

                System.out.println(satisfy+" "+avg+" "+proj+" "+company+" "+workacc+" "+prom+" "+eval+" "+domain+" "+sala);



            }
        });



        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(nameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, root2.getScene().getWindow(), "Form Error!", "Please enter your name");
                    return;
                }
                if(emailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, root2.getScene().getWindow(), "Form Error!", "Please enter your email id");
                    return;
                }
                if(passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, root2.getScene().getWindow(), "Form Error!", "Please enter a password");
                    return;
                }
                primaryStage.setScene(scene3);
                primaryStage.setTitle("Details");
                primaryStage.show();
                // showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "", "Welcome " + nameField.getText());
            }
        });

        Scene scene2=new Scene(root2, 700, 550);


        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene2);
                primaryStage.setTitle("Register");
            }
        });




    }

    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }




    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


    public static void main(String[] args) {

        launch(args);
       // System.out.println(sala);
    }
}
