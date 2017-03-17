package scenes.dashboard.question;

import com.jfoenix.controls.JFXButton;
import core.auth.Auth;
import database.models.Question;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jooq.Record;
import scenes.MyGroup;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class DetailQuestionScene extends MyGroup {

    private Record question;
    private Label welcomeLabel, titleLabel, descriptionLabel, nameUserLabel;
    private JFXButton backButton, answerButton;
    private VBox vBox;

    public DetailQuestionScene (Record question) {
        this.question = question;

        loadAndDrawQuestion();
    }

    @Override
    protected void prepareLayout() {
        vBox = new VBox();

        backButton = new JFXButton("Back");
        answerButton = new JFXButton("Jawab");

        welcomeLabel = new Label("Detail");
        titleLabel = new Label();
        nameUserLabel = new Label();
        descriptionLabel = new Label();

        vBox.getChildren().addAll(backButton, welcomeLabel, titleLabel, answerButton,
                nameUserLabel, descriptionLabel);
        getChildren().addAll(vBox);
    }

    private void loadAndDrawQuestion() {
        answerButton.setVisible(Auth.isLogin() && Auth.isUstadz() && Question.isUnAnswered(question));

        titleLabel.setText(question.get("title").toString());
        descriptionLabel.setText(question.get("description").toString());
        nameUserLabel.setText(Question.getUser(question).get("name").toString());
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });
    }
}
