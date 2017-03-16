package scenes.publics.auth;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import scenes.MyGroup;
import scenes.MyScene;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class SignupScene extends MyGroup {
    JFXButton backButton, signUpButton;
    JFXTextField nameField, usernameField, emailField;
    JFXPasswordField passwordField, passwordConfirmationField;
    JFXTextArea addressField;

    @Override
    protected void prepareLayout() {
        VBox vBox = new VBox();

        Label label = new Label("Sign up Scene");
        label.setFont(new Font(40));

        backButton = new JFXButton("Back");
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

        addressField = new JFXTextArea();
        addressField.setPromptText("Address");

        vBox.getChildren().addAll(backButton, label, nameField, usernameField,
                emailField, addressField, passwordField, passwordConfirmationField,
                signUpButton);
        getChildren().add(vBox);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });
    }
}
