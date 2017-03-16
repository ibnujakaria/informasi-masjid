package scenes.publics.auth;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import core.auth.Auth;
import javafx.scene.control.Label;
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

    @Override
    protected void prepareLayout() {

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

        errorLabel = new Label("Login gagal");
        errorLabel.setVisible(false);

        HBox buttons = new HBox();
        buttons.getChildren().addAll(loginButton, signUpButton);

        vBox.getChildren().addAll(backButton, header, usernameField, passwordField, errorLabel, buttons);
        getChildren().add(vBox);
    }

    @Override
    protected void addListeners() {
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
}
