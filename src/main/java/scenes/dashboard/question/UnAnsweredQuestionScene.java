package scenes.dashboard.question;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import database.models.Question;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.jooq.Record;
import scenes.MyGroup;
import core.components.QuestionComponent;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class UnAnsweredQuestionScene extends MyGroup{

    private JFXButton backButton;
    private JFXListView<QuestionComponent> questionList;
    private VBox questionListTapiVbox;

    @Override
    protected void prepareLayout() {
        VBox vBox = new VBox();

        Label label = new Label("Pertanyaan Masuk");
        label.setFont(new Font(40));

        backButton = new JFXButton("Back");
        questionListTapiVbox = new VBox();
        prepareQuestionList();

        vBox.getChildren().addAll(backButton, label, questionListTapiVbox);

        getChildren().add(vBox);
    }

    @Override
    protected void onAfterBack() {
        super.onAfterBack();
        prepareQuestionList();
    }

    private void prepareQuestionList() {
        questionListTapiVbox.getChildren().clear();

        for (Record question : Question.getUnAnsweredQuestions()) {
            questionListTapiVbox.getChildren().add(new QuestionComponent(question, this));
        }
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });
    }
}
