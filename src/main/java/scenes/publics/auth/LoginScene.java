package scenes.publics.auth;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import scenes.MyGroup;
import scenes.MyScene;
import scenes.publics.PublicScene;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class LoginScene extends MyGroup {
    JFXTextField usernameField;
    JFXPasswordField passwordField;
    JFXButton loginButton, signUpButton, backButton;

    @Override
    protected void prepareLayout() {
        view = new MyScene(this, 800, 600);

        VBox vBox = new VBox();

        Label header = new Label("Login");
        header.setFont(new Font(40));
        usernameField = new JFXTextField();
        usernameField.setPromptText("Username");

        passwordField = new JFXPasswordField();
        passwordField.setPromptText("Password");

        backButton = new JFXButton("Back");
        loginButton = new JFXButton("Login");
        signUpButton = new JFXButton("Sign up");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(loginButton, signUpButton);

        vBox.getChildren().addAll(backButton, header, usernameField, passwordField, buttons);
        getChildren().add(vBox);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });

        signUpButton.setOnAction(event -> {
            PublicScene publicScene = (PublicScene) getPreviousScene();
            publicScene.setNextScene(new SignupScene());
            publicScene.moveNextScene();
        });
    }
}
