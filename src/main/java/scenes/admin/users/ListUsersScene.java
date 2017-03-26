package scenes.admin.users;

import app.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import core.components.UserComponent;
import database.models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    JFXListView<VBox> listUser;
    BorderPane pane,top;
    HBox hBox;

    @Override
    protected void prepareLayout() {
        pane = new BorderPane();
        vbox = new VBox();
        backButton = new JFXButton("Back");
        listUsers = new VBox();
        label = new Label("Daftar Users");
        label.setId("listUsr");
        label.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label);
        vbox.setStyle("-fx-background-color: #e74c3c;");



        hBox = new HBox();
        hBox.setStyle("-fx-background-color: #ecf0f1;");
        backButton.setAlignment(Pos.TOP_LEFT);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(backButton);
        hBox.prefWidthProperty().bind(Main.primaryStage.widthProperty());

        top = new BorderPane();
        top.setLeft(backButton);
        top.setCenter(hBox);
        top.setStyle("-fx-background-color: #ecf0f1;");
        top.setMargin(backButton ,new Insets(5,1,5,8));

        pane.setTop(top);
        pane.setCenter(vbox);
        pane.setBottom(listUsers);
        getChildren().addAll(pane);
        displayUsers();
    }

    @Override
    protected void onAfterBack() {
        super.onAfterBack();
        displayUsers();
    }

    public void displayUsers() {
        listUser = new JFXListView<>();
        listUsers.getChildren().clear();
        listUser.prefHeightProperty().bind(Main.primaryStage.getScene().heightProperty());
        listUser.prefWidthProperty().bind(Main.primaryStage.getScene().widthProperty());
        for (Record user : User.getUsers()) {
            listUser.getItems().add(new VBox(new UserComponent(this, user)));
        }
        listUsers.getChildren().addAll(listUser);
    }

    @Override
    public void refreshContent() {
        super.refreshContent();
        displayUsers();
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });
    }
}
