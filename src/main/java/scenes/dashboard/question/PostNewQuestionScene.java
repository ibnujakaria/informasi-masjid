package scenes.dashboard.question;

import app.Main;
import com.jfoenix.controls.*;
import core.auth.Auth;
import database.models.jooq.Question;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.jooq.Record;
import scenes.MyGroup;
import scenes.dashboard.DashboardScene;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class PostNewQuestionScene extends MyGroup {
    private JFXTextField titleField;
    private JFXTextArea descriptionField;
    private JFXCheckBox isAnonimCheckbox;
    private JFXButton submitButton, backButton;
    private Label welcomeLabel;
    VBox vBox;
    HBox hBox;
    BorderPane back, top;

    @Override
    protected void prepareLayout() {
        vBox = new VBox();

        Label welcome = new Label("Tulis Pertanyaan");
        welcome.setFont(new Font(40));

        titleField = new JFXTextField();
        titleField.setPromptText("Judul");

        descriptionField = new JFXTextArea();
        descriptionField.setPromptText("Pertanyaan");

        isAnonimCheckbox = new JFXCheckBox("Ajukan sebagai Hamba Allah");

        backButton = new JFXButton("Back");
        welcomeLabel = new Label("Buat Pertanyaan");


        submitButton = new JFXButton("Submit");

        hBox = titleBar(backButton, welcomeLabel);
//        hBox = new HBox();
//        hBox.setStyle("-fx-background-color: #ecf0f1;");
//        backButton.setAlignment(Pos.TOP_LEFT);
//        welcomeLabel.setAlignment(Pos.CENTER);
//        hBox.setAlignment(Pos.CENTER);
//        hBox.getChildren().addAll(backButton,welcomeLabel);
//        hBox.prefWidthProperty().bind(Main.primaryStage.widthProperty());

        top = new BorderPane();
        top.setLeft(backButton);
        top.setCenter(hBox);
        top.setStyle("-fx-background-color: #ecf0f1;");
        top.setMargin(backButton ,new Insets(5,1,5,8));

        ObservableList list = vBox.getChildren();
        list.addAll(titleField, descriptionField, isAnonimCheckbox, submitButton);
        vBox.setMargin(titleField, new Insets(1,10,1,10));
        vBox.setMargin(descriptionField, new Insets(1,10,1,10));
        vBox.setMargin(isAnonimCheckbox, new Insets(5,1,5,10));
        vBox.setMargin(submitButton, new Insets(5,1,1,10));
        setvBoxFullScreen();
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
            Record question = Question.create(Auth.getUserId(), titleField.getText(), descriptionField.getText(),
                    isAnonimCheckbox.isSelected());

            MyGroup dashboardScene = (MyGroup) getPreviousScene();
            dashboardScene.setNextScene(new DashboardScene());
            dashboardScene.moveNextScene();
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

    private void setvBoxFullScreen () {
        vBox.setMinHeight(Main.primaryStage.getScene().getHeight());
        vBox.setMinWidth(Main.primaryStage.getScene().getWidth());
    }

}
