package scenes.dashboard.question;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import database.models.Question;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jooq.Record;
import scenes.MyGroup;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class AnswerQuestionScene extends MyGroup {
    private VBox vbox;
    private Label welcomeLabel;
    private JFXTextArea answerField;
    private JFXButton backButton, submitButton;
    private Record question;

    public AnswerQuestionScene (Record question) {
        this.question = question;
    }

    @Override
    protected void prepareLayout() {
        vbox = new VBox();
        welcomeLabel = new Label("Jawab Pertanyaan");
        answerField = new JFXTextArea();
        answerField.setPromptText("Jawaban..");

        backButton = new JFXButton("Back");
        submitButton = new JFXButton("Submit");

        vbox.getChildren().addAll(backButton, welcomeLabel, answerField, submitButton);
        getChildren().addAll(vbox);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });

        submitButton.setOnAction(event -> {
            answerQuestion();
        });
    }

    private void answerQuestion() {
        Question.answerQuestion(Integer.parseInt(question.get("id").toString()), answerField.getText());
        movePreviousScene();
    }
}
