package scenes.dashboard.question;

import com.jfoenix.controls.JFXButton;
import core.auth.Auth;
import database.models.Question;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.jooq.Record;
import scenes.MyGroup;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class DetailQuestionScene extends MyGroup {

    private Record question;
    private Label welcomeLabel, titleLabel, descriptionLabel, nameUserLabel,
            answerLabel, answerText, ustadzLabel;
    private JFXButton backButton, answerButton;
    private VBox vBox;
    BorderPane back;
    ToolBar toolBar;
    Separator separator;
    Pane pane;

    public DetailQuestionScene (Record question) {
        this.question = question;

        loadAndDrawQuestion();
    }

    @Override
    protected void prepareLayout() {
        vBox = new VBox();

        titleLabel = new Label();
        titleLabel.setId("titleQuestion");
        nameUserLabel = new Label();
        nameUserLabel.setId("userLabel");
        descriptionLabel = new Label();
        answerText = new Label();
        ustadzLabel = new Label();
        ustadzLabel.setVisible(true);
        backButton = new JFXButton("Back");
        answerButton = new JFXButton("Jawab");
        pane = new Pane();
        pane.setPrefWidth(300);
        answerLabel = new Label("Jawaban");
        separator = new Separator();
        welcomeLabel = new Label("Detail");

        toolBar = new ToolBar();
        toolBar.setPrefWidth(800);

        toolBar.getItems().addAll(
                backButton, separator,pane,welcomeLabel

        );
        back = new BorderPane();
        back.setTop(toolBar);
        back.setCenter(vBox);
        ObservableList list = vBox.getChildren();
        list.addAll(titleLabel,
                nameUserLabel, descriptionLabel, answerLabel,answerButton, ustadzLabel, answerText);
        getChildren().addAll(back);
    }

    private void loadAndDrawQuestion() {
        answerButton.setVisible(Auth.isLogin() && Auth.isUstadz() && Question.isUnAnswered(question));
        System.out.println("question is unanswered: " + Question.isUnAnswered(question));
        titleLabel.setText(question.get("title").toString());
        descriptionLabel.setText(question.get("description").toString());
        nameUserLabel.setText(Question.getUser(question).get("name").toString());

        if (Question.isAnonim(question)) {
            nameUserLabel.setText("Hamba Allah");
        }

        answerLabel.setVisible(Question.isAnswered(question));
        answerText.setVisible(Question.isAnswered(question));
        answerText.setText(Question.isAnswered(question) ? question.get("answer").toString() : "");

        if (Question.isAnswered(question)) {
            Record ustadz = Question.getUstadzWhoAnswer(question);
            ustadzLabel.setText("Oleh: " + ustadz.get("name").toString());
            ustadzLabel.setVisible(true);

        }
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
