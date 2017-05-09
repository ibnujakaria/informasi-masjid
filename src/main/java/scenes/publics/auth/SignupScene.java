package scenes.publics.auth;

import app.Main;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import core.auth.Auth;
import database.models.jooq.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import org.jooq.Record;
import scenes.MyGroup;
import scenes.dashboard.DashboardScene;
import scenes.publics.PublicScene;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class SignupScene extends MyGroup {
    JFXButton signUpButton, backButton;
    JFXTextField nameField, usernameField, emailField, addressField;
    JFXPasswordField passwordField, passwordConfirmationField;
    VBox vBox;
    final KeyCombination kb = new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_ANY);
    Label titleSignUp, titleHeader;
    HBox hBox;
    BorderPane back,top;

    @Override
    protected void prepareLayout() {
        vBox = new VBox();

        titleHeader = new Label("Sign Up");
        backButton = new JFXButton("Back");

        back = new BorderPane();

        titleSignUp = new Label("Sign Up");
        titleSignUp.setId("titleSignUp");

        signUpButton = new JFXButton("Sign up");

        nameField = new JFXTextField();
        nameField.setPromptText("Name");

        usernameField = new JFXTextField();
        usernameField.setPromptText("Username");

        emailField = new JFXTextField();
        emailField.setPromptText("Email");

        passwordField = new JFXPasswordField();
        passwordField.setPromptText("Password");

        passwordConfirmationField = new JFXPasswordField();
        passwordConfirmationField.setPromptText("Type your password again");

        addressField = new JFXTextField();
        addressField.setPromptText("City");
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required");

        nameField.getValidators().add(validator);
        usernameField.getValidators().add(validator);
        emailField.getValidators().add(validator);
        passwordField.getValidators().add(validator);
        passwordConfirmationField.getValidators().add(validator);
        addressField.getValidators().add(validator);

        nameField.setMaxWidth(380);
        usernameField.setMaxWidth(380);
        emailField.setMaxWidth(380);
        passwordField.setMaxWidth(380);
        passwordConfirmationField.setMaxWidth(380);
        addressField.setMaxWidth(380);

        nameField.setMinHeight(50);
        usernameField.setMinHeight(50);
        emailField.setMinHeight(50);
        passwordField.setMinHeight(50);
        passwordConfirmationField.setMinHeight(50);
        addressField.setMinHeight(50);

        GridPane buttons = new GridPane();
        buttons.setPadding(new Insets(0, 10, 0, 10));
        buttons.setVgap(10);
        buttons.setHgap(10);
        buttons.add(signUpButton, 0, 2);
        buttons.setAlignment(Pos.CENTER);
        setvBoxFullScreen();

        hBox = titleBar(backButton, titleHeader);
//        hBox = new HBox();
//        hBox.setStyle("-fx-background-color: #ecf0f1;");
//        backButton.setAlignment(Pos.TOP_LEFT);
//        titleHeader.setAlignment(Pos.CENTER);
//        hBox.setAlignment(Pos.CENTER);
//        hBox.getChildren().addAll(backButton,titleHeader);
//        hBox.prefWidthProperty().bind(Main.primaryStage.widthProperty());

        top = new BorderPane();
        top.setLeft(backButton);
        top.setCenter(hBox);
        top.setStyle("-fx-background-color: #ecf0f1;");
        top.setMargin(backButton ,new Insets(5,1,5,8));

        vBox.setAlignment(Pos.CENTER);
        ObservableList list = vBox.getChildren();
        list.addAll(titleSignUp,nameField,usernameField,emailField, addressField,passwordField,passwordConfirmationField,buttons);

        back = new BorderPane();
        back.setTop(top);
        back.setCenter(vBox);
        getChildren().addAll(back);
    }

    @Override
    protected void addListeners() {
        nameField.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) nameField.validate();
        });
        usernameField.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) usernameField.validate();
        });
        emailField.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) emailField.validate();
        });
        passwordField.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) passwordField.validate();
        });
        passwordConfirmationField.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) passwordConfirmationField.validate();
        });

        setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (kb.match(event)) {
                    movePreviousScene();
                    setvBoxFullScreen();
                }
            }
        });

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER){
                    doSignUpProcess();
                    setvBoxFullScreen();
                }
            }
        });

        signUpButton.setOnAction(event -> {
            doSignUpProcess();
            setvBoxFullScreen();
        });

        backButton.setOnAction(event -> {
            if (previousScene == null) {
                setPreviousScene(new PublicScene());
            }
            movePreviousScene();
        });

        Main.primaryStage.getScene().widthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setvBoxFullScreen();
            }
        });

        Main.primaryStage.getScene().heightProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setvBoxFullScreen();
            }
        });
    }

    private void doSignUpProcess() {
        StackPane pane = new StackPane();
        getChildren().add(pane);
        JFXDialog dialog = new JFXDialog();
        dialog.setContent(new Label("Loading.."));
        dialog.show(pane);

        Record user = User.createUser(
                nameField.getText(), usernameField.getText(), emailField.getText(), addressField.getText(),
                passwordField.getText(), false, false
        );

        Auth.loginByUserRecord(user);
        moveToDashboardScene();
    }

    private void moveToDashboardScene() {
        setNextScene(new DashboardScene());
        moveNextScene();
        setvBoxFullScreen();
    }

    private void setvBoxFullScreen () {
        vBox.setMinHeight(Main.primaryStage.getScene().getHeight());
        vBox.setMinWidth(Main.primaryStage.getScene().getWidth());
    }
}
