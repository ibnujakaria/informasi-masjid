package scenes.dashboard.question;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import core.auth.Auth;
import database.models.Question;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.jooq.Record;
import scenes.MyGroup;
import scenes.dashboard.question.components.QuestionComponent;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class ListQuestionScene extends MyGroup
{
    private JFXButton backButton;
    private JFXListView<QuestionComponent> questionList;
    private VBox questionListTapiVbox;

    @Override
    protected void prepareLayout() {
        VBox vBox = new VBox();

        Label label = new Label("Daftar Pertanyaan Saya");
        label.setFont(new Font(40));

        backButton = new JFXButton("Back");
        prepareQuestionList();

        vBox.getChildren().addAll(backButton, label, questionListTapiVbox);

        getChildren().add(vBox);
    }

    private void prepareQuestionList() {
        questionListTapiVbox = new VBox();

        for (Record question : Question.getQuestionsOfUserId(Auth.getUserId())) {
            questionListTapiVbox.getChildren().add(new QuestionComponent(question));
        }
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });
    }
}
