package scenes.dashboard.question;

import com.jfoenix.controls.*;
import core.auth.Auth;
import database.models.Question;
import javafx.scene.control.Label;
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

    @Override
    protected void prepareLayout() {
        VBox vBox = new VBox();

        Label welcome = new Label("Tulis Pertanyaan");
        welcome.setFont(new Font(40));

        titleField = new JFXTextField();
        titleField.setPromptText("Judul");

        descriptionField = new JFXTextArea();
        descriptionField.setPromptText("Pertanyaan");

        isAnonimCheckbox = new JFXCheckBox("Ajukan sebagai Hamba Allah");

        backButton = new JFXButton("Back");
        submitButton = new JFXButton("Submit");

        vBox.getChildren().addAll(backButton, welcome, titleField, descriptionField, isAnonimCheckbox, submitButton);
        getChildren().add(vBox);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });

        submitButton.setOnAction(event -> {
            Record question = Question.create(Auth.getUserId(), titleField.getText(), descriptionField.getText(),
                    isAnonimCheckbox.isSelected());

            MyGroup dashboardScene = (MyGroup) getPreviousScene();
            dashboardScene.setNextScene(new DashboardScene());
            dashboardScene.moveNextScene();
        });
    }
}
