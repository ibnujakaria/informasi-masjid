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
    private Label welcomeLabel, titleLabel, descriptionLabel, nameUserLabel, answerLabel, answerText;
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
        answerLabel = new Label("Jawaban");
        answerText = new Label();

        vBox.getChildren().addAll(backButton, welcomeLabel, titleLabel, answerButton,
                nameUserLabel, descriptionLabel, answerLabel, answerText);
        getChildren().addAll(vBox);
    }

    private void loadAndDrawQuestion() {
        answerButton.setVisible(Auth.isLogin() && Auth.isUstadz() && Question.isUnAnswered(question));
        System.out.println("question is unanswered: " + Question.isUnAnswered(question));
        titleLabel.setText(question.get("title").toString());
        descriptionLabel.setText(question.get("description").toString());
        nameUserLabel.setText(Question.getUser(question).get("name").toString());

        answerLabel.setVisible(Question.isAnswered(question));
        answerText.setVisible(Question.isAnswered(question));
        answerText.setText(Question.isAnswered(question) ? question.get("answer").toString() : "");
    }

    @Override
    protected void onAfterBack() {
        super.onAfterBack();
        updateQuestion();
        loadAndDrawQuestion();
        System.out.println("on after back executed");
    }

    private void updateQuestion() {
        System.out.println("update question");
        question = Question.getQuestionById(Integer.parseInt(question.get("id").toString()));
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });

        answerButton.setOnAction(event -> {
            if (getNextScene() == null || (getNextScene() != null && getNextScene().getClass().toString() != AnswerQuestionScene.class.toString())) {
                setNextScene(new AnswerQuestionScene(question));
            }

            moveNextScene();
        });
    }
}
