package scenes.admin;

import com.jfoenix.controls.JFXButton;
import core.components.QuestionComponent;
import database.models.jooq.Question;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.jooq.Record;
import scenes.MyGroup;

/**
 * Created by basithdj on 3/21/17.
 */
public class ListQuestionScene extends MyGroup{
    private VBox vbox, listQuestion;
    private Label label;
    private JFXButton backButton;

    @Override
    protected void prepareLayout() {
        vbox = new VBox();
        label = new Label("Daftar Pertanyaan");
        label.setFont(new Font(40));
        backButton = new JFXButton("Back");
        listQuestion = new VBox();

        vbox.getChildren().addAll(backButton, label, listQuestion);
        getChildren().addAll(vbox);

        displayQuestion();

    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });
    }

    @Override
    protected void onAfterBack() {
        super.onAfterBack();
        displayQuestion();
    }

    @Override
    public void refreshContent() {
        super.refreshContent();
        displayQuestion();
    }

    public void displayQuestion(){
        listQuestion.getChildren().clear();
        for (Record question : Question.getAnsweredQuestion()) {
            listQuestion.getChildren().add(new QuestionComponent(question,this));
        }
    }
}
