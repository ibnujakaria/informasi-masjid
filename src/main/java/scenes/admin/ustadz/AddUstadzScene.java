package scenes.admin.ustadz;

import app.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.models.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    Label addUstadzlbl;
    HBox hBox;
    BorderPane back,top;

    @Override
    protected void prepareLayout() {
        vBox = new VBox();
        back = new BorderPane();

        addUstadzlbl = new Label("Tambah Ustadz");
        addUstadzlbl.setId("titleSignUp");

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

        nameField.setMaxWidth(380);
        usernameField.setMaxWidth(380);
        emailField.setMaxWidth(380);
        passwordField.setMaxWidth(380);
        addressField.setMaxWidth(380);

        nameField.setMinHeight(50);
        usernameField.setMinHeight(50);
        emailField.setMinHeight(50);
        passwordField.setMinHeight(50);
        addressField.setMinHeight(50);

        GridPane buttons = new GridPane();
        buttons.setPadding(new Insets(0, 10, 0, 10));
        buttons.setVgap(10);
        buttons.setHgap(10);
        buttons.add(submitButton, 0, 2);
        buttons.setAlignment(Pos.CENTER);
        setvBoxFullScreen();

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

        vBox.setAlignment(Pos.CENTER);
        ObservableList list = vBox.getChildren();
        list.addAll(addUstadzlbl,nameField, usernameField, emailField,
                addressField, passwordField, buttons);

        back = new BorderPane();
        back.setTop(top);
        back.setCenter(vBox);
        getChildren().addAll(back);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
            setvBoxFullScreen();
        });

        submitButton.setOnAction(event -> {
            onSubmitButtonPressed();
            setvBoxFullScreen();
        });

        Main.primaryStage.getScene().widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setvBoxFullScreen();
            }
        });

        Main.primaryStage.getScene().heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setvBoxFullScreen();
            }
        });
    }

    private void onSubmitButtonPressed () {
        Record user = User.createUser(
                nameField.getText(), usernameField.getText(), emailField.getText(), addressField.getText(),
                passwordField.getText(), true, false
        );

        movePreviousScene();
        setvBoxFullScreen();
    }

    private void setvBoxFullScreen () {
        vBox.setMinHeight(Main.primaryStage.getScene().getHeight());
        vBox.setMinWidth(Main.primaryStage.getScene().getWidth());
    }
}
