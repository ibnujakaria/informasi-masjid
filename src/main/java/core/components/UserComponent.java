package core.components;

import com.jfoenix.controls.JFXButton;
import core.auth.Auth;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jooq.Record;
import scenes.MyGroup;
import scenes.admin.users.EditUserScene;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class UserComponent extends Group {

    private Record user;
    private VBox vBox;
    private Label nameLabel, emailLabel, usernameLabel, cityLabel;
    private JFXButton editButton, deleteButton;
    private MyGroup myParent;

    public UserComponent (MyGroup myParent, Record user) {
        this.user = user;
        this.myParent = myParent;
        prepareLayout();
        addListeners();
    }

    private void prepareLayout () {
        vBox = new VBox();

        nameLabel = new Label(user.get("name").toString());
        usernameLabel = new Label(user.get("username").toString());
        emailLabel = new Label(user.get("email").toString());
        cityLabel = new Label(user.get("address") != null ? user.get("address").toString() : "");

        editButton = new JFXButton("Edit");
        deleteButton = new JFXButton("Hapus");

        editButton.setVisible(Auth.isLogin() && Auth.isAdmin());
        deleteButton.setVisible(Auth.isLogin() && Auth.isAdmin());

        vBox.getChildren().addAll(nameLabel, usernameLabel, emailLabel,
                cityLabel, new HBox(editButton, deleteButton));

        getChildren().addAll(vBox);
    }

    private void addListeners () {
        editButton.setOnAction(event -> {
            myParent.setNextScene(new EditUserScene(user));
            myParent.moveNextScene();
        });
    }
}
