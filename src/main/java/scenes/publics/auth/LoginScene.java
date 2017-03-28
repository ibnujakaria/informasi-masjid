package scenes.publics.auth;

import app.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToolbar;
import core.auth.Auth;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import scenes.MyGroup;
import scenes.dashboard.DashboardScene;
import scenes.publics.PublicScene;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class LoginScene extends MyGroup {
    JFXTextField usernameField;
    JFXPasswordField passwordField;
    JFXButton loginButton, signUpButton, backButton;
    Label errorLabel,header, title;
    VBox vBox;
    BorderPane back, top;
    HBox hBox;
    final KeyCombination kb = new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_ANY);

    @Override
    protected void prepareLayout() {
        title = new Label("Login");
        vBox = new VBox();
        backButton = new JFXButton("Back");
        header = new Label("Login");
        header.setId("titleLogin");
        usernameField = new JFXTextField();
        usernameField.setPromptText("Username");

        passwordField = new JFXPasswordField();
        passwordField.setPromptText("Password");
        usernameField.setMaxWidth(320);
        passwordField.setMaxWidth(320);
        usernameField.setMinHeight(50);
        passwordField.setMinHeight(50);

        loginButton = new JFXButton("Login");
        signUpButton = new JFXButton("Sign up");

        errorLabel = new Label("Login failed!");
        errorLabel.setVisible(false);

        hBox = titleBar(backButton, title);
//        hBox = new HBox();
//        hBox.setStyle("-fx-background-color: #ecf0f1;");
//        backButton.setAlignment(Pos.TOP_LEFT);
//        title.setAlignment(Pos.CENTER);
//        hBox.setAlignment(Pos.CENTER);
//        hBox.getChildren().addAll(backButton,title);
//        hBox.prefWidthProperty().bind(Main.primaryStage.widthProperty());

        top = new BorderPane();
        top.setLeft(backButton);
        top.setCenter(hBox);
        top.setStyle("-fx-background-color: #ecf0f1;");
        top.setMargin(backButton ,new Insets(5,1,5,8));

        GridPane buttons = new GridPane();
        buttons.setPadding(new Insets(0, 10, 0, 10));
        buttons.setVgap(10);
        buttons.setHgap(10);
        buttons.add(loginButton, 0, 2);
        buttons.add(signUpButton, 1, 2);
        buttons.setAlignment(Pos.CENTER);
        setvBoxFullScreen();

        vBox.setAlignment(Pos.CENTER);
        ObservableList list = vBox.getChildren();
        list.addAll(header,usernameField,passwordField,errorLabel,buttons);

        back = new BorderPane();
        back.setTop(top);
        back.setCenter(vBox);


        getChildren().addAll(back);
    }

    @Override
    protected void addListeners() {
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (kb.match(event)) {
                    if (previousScene == null) {
                        setPreviousScene(new PublicScene());
                        setvBoxFullScreen();
                    }
                    movePreviousScene();
                    setvBoxFullScreen();
                }
            }
        });

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER){
                    doLoginProcess();
                    setvBoxFullScreen();
                }
            }
        });

        loginButton.setOnAction(event -> {
            doLoginProcess();
            setvBoxFullScreen();
        });

        backButton.setOnAction(event -> {
            if (previousScene == null) {
                setPreviousScene(new PublicScene());
            }
            movePreviousScene();
            setvBoxFullScreen();
        });

        signUpButton.setOnAction(event -> {
            PublicScene publicScene = (PublicScene) getPreviousScene();
            publicScene.setNextScene(new SignupScene());
            publicScene.moveNextScene();
            setvBoxFullScreen();
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

    private void doLoginProcess() {
        boolean success = Auth.attemps(usernameField.getText(), passwordField.getText());
        errorLabel.setVisible(false);

        if (success) {
            setNextScene(new DashboardScene());
            moveNextScene();
            setvBoxFullScreen();
        } else {
            errorLabel.setVisible(true);
            setvBoxFullScreen();
        }
    }

    private void setvBoxFullScreen () {
        vBox.setMinHeight(Main.primaryStage.getScene().getHeight());
        vBox.setMinWidth(Main.primaryStage.getScene().getWidth());
    }
}
