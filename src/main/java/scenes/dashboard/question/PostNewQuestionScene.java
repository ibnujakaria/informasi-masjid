package scenes.dashboard.question;

import com.jfoenix.controls.*;
import core.auth.Auth;
import database.models.Question;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
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
    Pane pane;
    ToolBar toolBar;

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

        pane = new Pane();
        pane.setPrefWidth(300);
        backButton = new JFXButton("Back");
        welcomeLabel = new Label("Buat Pertanyaan");

        toolBar = new ToolBar();
        toolBar.setPrefWidth(800);

        toolBar.getItems().addAll(
                backButton, pane,welcomeLabel
        );

        submitButton = new JFXButton("Submit");

        vBox.getChildren().addAll(toolBar, titleField, descriptionField, isAnonimCheckbox, submitButton);
        vBox.setSpacing(15);
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
