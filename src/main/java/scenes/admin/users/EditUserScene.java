package scenes.admin.users;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.models.jooq.User;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jooq.Record;
import scenes.MyGroup;

/**
 * Created by ibnujakaria on 19/03/17.
 */
public class EditUserScene extends MyGroup {

    private VBox vbox;
    private JFXButton backButton, submitButton;
    private Record user;
    private Label label;
    private JFXTextField nameField, usernameField, emailField, addressField;
    private JFXPasswordField passwordField;

    public EditUserScene(Record user) {
        this.user = user;
        loadUserAndDraw();
    }

    @Override
    protected void prepareLayout() {
        vbox = new VBox();

        label = new Label();
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

        vbox.getChildren().addAll(backButton, label, nameField, usernameField, emailField,
                addressField, passwordField, submitButton);

        getChildren().addAll(vbox);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });

        submitButton.setOnAction(event -> {
            doEditProcess();
        });
    }

    private void loadUserAndDraw () {
        label.setText("Edit " + (User.isUstadz(user) ? "Ustadz" : "User"));
        nameField.setText(user.get("name").toString());
        usernameField.setText(user.get("username").toString());
        emailField.setText(user.get("email").toString());
        passwordField.setText(user.get("password").toString());
        addressField.setText(user.get("address") != null ? user.get("address").toString() : "");
    }

    private void doEditProcess () {
        String password = null;

        if (!passwordField.getText().equals(user.get("password").toString())) {
            password = passwordField.getText();
        }

        User.update(user, nameField.getText(), usernameField.getText(), emailField.getText(),
                addressField.getText(), password, User.isUstadz(user), User.isAdmin(user));

        movePreviousScene();
    }
}
