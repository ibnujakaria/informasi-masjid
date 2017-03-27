package core.components;

import com.jfoenix.controls.JFXButton;
import core.auth.Auth;
import database.models.User;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jooq.Record;
import scenes.MyGroup;
import scenes.admin.users.EditUserScene;
import scenes.admin.users.ListUsersScene;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class UserComponent extends Group {

    private Record user;
    private VBox vBox;
    private Label nameLabel, emailLabel, usernameLabel, cityLabel;
    private JFXButton editButton, deleteButton;
    private MyGroup myParent;
    HBox hBox;

    public UserComponent (MyGroup myParent, Record user) {
        this.user = user;
        this.myParent = myParent;
        prepareLayout();
        addListeners();
    }

    private void prepareLayout () {
        vBox = new VBox();
        hBox = new HBox();

        nameLabel = new Label("Name : "+user.get("name").toString());
        nameLabel.setStyle("-fx-font-size: 20px;");
        usernameLabel = new Label("UserName : "+user.get("username").toString());
        usernameLabel.setStyle("-fx-font-size: 20px;");
        emailLabel = new Label("Email : " +user.get("email").toString());
        emailLabel.setStyle("-fx-font-size: 20px;");
        cityLabel = new Label(user.get("address") != null ? "Address : "+user.get("address").toString() : "");
        cityLabel.setStyle("-fx-font-size: 20px;");

        editButton = new JFXButton("Edit");

        deleteButton = new JFXButton("Hapus");

        editButton.setVisible(Auth.isLogin() && Auth.isAdmin());
        deleteButton.setVisible(Auth.isLogin() && Auth.isAdmin());
        hBox.setMargin(editButton, new Insets(1,5,1,5));
        hBox.setMargin(deleteButton, new Insets(1,5,1,5));
        hBox.getChildren().addAll(editButton,deleteButton);
        vBox.getChildren().addAll(nameLabel, usernameLabel, emailLabel,
                cityLabel, hBox);

        getChildren().addAll(vBox);
    }

    private void addListeners () {
        editButton.setOnAction(event -> {
            myParent.setNextScene(new EditUserScene(user));
            myParent.moveNextScene();
        });
        deleteButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Apakah anda yakin?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                User.deleteById((int)user.get("id"));
            }
            myParent.refreshContent();
        });
    }
}
