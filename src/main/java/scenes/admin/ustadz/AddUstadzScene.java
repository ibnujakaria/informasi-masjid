package scenes.admin.ustadz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.models.User;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.jooq.Record;
import scenes.MyGroup;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class AddUstadzScene extends MyGroup {

    private VBox vBox;
    private JFXButton backButton, submitButton;
    JFXTextField nameField, usernameField, emailField, addressField;
    JFXPasswordField passwordField;

    @Override
    protected void prepareLayout() {
        vBox = new VBox();
        Label label = new Label("Tambah Ustadz");
        label.setFont(new Font(40));

        backButton = new JFXButton("Back");
        submitButton = new JFXButton("Submit");

        nameField = new JFXTextField();
        nameField.setPromptText("Name");

        usernameField = new JFXTextField();
        usernameField.setPromptText("Username");

        emailField = new JFXTextField();
        emailField.setPromptText("Email");

        passwordField = new JFXPasswordField();
        passwordField.setPromptText("Password");

        addressField = new JFXTextField();
        addressField.setPromptText("City");

        vBox.getChildren().addAll(backButton, label, nameField, usernameField, emailField,
                addressField, passwordField, submitButton);
        getChildren().addAll(vBox);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });

        submitButton.setOnAction(event -> {
            onSubmitButtonPressed();
        });
    }

    private void onSubmitButtonPressed () {
        Record user = User.createUser(
                nameField.getText(), usernameField.getText(), emailField.getText(), addressField.getText(),
                passwordField.getText(), true, false
        );

        movePreviousScene();
    }
}
