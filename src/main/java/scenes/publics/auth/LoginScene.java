package scenes.publics.auth;

import app.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import core.auth.Auth;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import scenes.MyGroup;
import scenes.MyScene;
import scenes.dashboard.DashboardScene;
import scenes.publics.PublicScene;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class LoginScene extends MyGroup {
    JFXTextField usernameField;
    JFXPasswordField passwordField;
    JFXButton loginButton, signUpButton, backButton;
    Label errorLabel;
    VBox vBox;
    BorderPane back;
    final KeyCombination kb = new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_ANY);

    @Override
    protected void prepareLayout() {

        vBox = new VBox();

        Label header = new Label("Login");
        header.setFont(new Font(40));
        usernameField = new JFXTextField();
        usernameField.setPromptText("Username");

        passwordField = new JFXPasswordField();
        passwordField.setPromptText("Password");
        usernameField.setMaxWidth(320);
        passwordField.setMaxWidth(320);
        usernameField.setMinHeight(50);
        passwordField.setMinHeight(50);

        backButton = new JFXButton("Back");
        loginButton = new JFXButton("Login");
        signUpButton = new JFXButton("Sign up");

        errorLabel = new Label("Login gagal");
        errorLabel.setVisible(false);

        backButton.setAlignment(Pos.TOP_LEFT);
        back = new BorderPane();
        back.setPadding(new Insets(10, 10, 10, 10));
        back.setLeft(backButton);

        GridPane buttons = new GridPane();
        buttons.setPadding(new Insets(0, 10, 0, 10));
        buttons.setVgap(10);
        buttons.setHgap(10);
        buttons.add(loginButton, 0, 2);
        buttons.add(signUpButton, 1, 2);
        buttons.setAlignment(Pos.CENTER);
        setvBoxFullScreen();
//        vBox.setMargin(buttons, new Insets(10, 10, 10, 100));

        vBox.setAlignment(Pos.CENTER);
        ObservableList list = vBox.getChildren();
        list.addAll(header,usernameField,passwordField,errorLabel,buttons);
        getChildren().addAll(vBox);

    }

    @Override
    protected void addListeners() {
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (kb.match(event)) {
                    if (previousScene == null) {
                        setPreviousScene(new PublicScene());
                    }
                    movePreviousScene();
                }
            }
        });

        backButton.setOnAction(event -> {
            if (previousScene == null) {
                setPreviousScene(new PublicScene());
            }
            movePreviousScene();
        });

        loginButton.setOnAction(event -> {
            doLoginProcess();
        });

        signUpButton.setOnAction(event -> {
            PublicScene publicScene = (PublicScene) getPreviousScene();
            publicScene.setNextScene(new SignupScene());
            publicScene.moveNextScene();
        });
    }

    private void doLoginProcess() {
        boolean success = Auth.attemps(usernameField.getText(), passwordField.getText());
        errorLabel.setVisible(false);

        if (success) {
            setNextScene(new DashboardScene());
            moveNextScene();
        } else {
            errorLabel.setVisible(true);
        }
    }

    private void setvBoxFullScreen () {
        vBox.setMinHeight(Main.primaryStage.getScene().getHeight());
        vBox.setMinWidth(Main.primaryStage.getScene().getWidth());
    }
}
