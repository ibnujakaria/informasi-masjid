package scenes.admin.users;

import com.jfoenix.controls.JFXButton;
import core.components.UserComponent;
import database.models.User;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.jooq.Record;
import scenes.MyGroup;

/**
 * Created by ibnujakaria on 19/03/17.
 */
public class ListUsersScene extends MyGroup {
    private VBox vbox, listUsers;
    private Label label;
    private JFXButton backButton;

    @Override
    protected void prepareLayout() {
        vbox = new VBox();
        label = new Label("Daftar Users");
        label.setFont(new Font(40));
        backButton = new JFXButton("Back");
        listUsers = new VBox();

        vbox.getChildren().addAll(backButton, label, listUsers);
        getChildren().addAll(vbox);

        displayUsers();
    }

    private void displayUsers() {
        listUsers.getChildren().clear();
        for (Record user : User.getUsers()) {
            listUsers.getChildren().add(new UserComponent(user));
        }
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });
    }
}
