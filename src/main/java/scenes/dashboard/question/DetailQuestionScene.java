package scenes.dashboard.question;

import app.Main;
import com.jfoenix.controls.JFXButton;
import core.auth.Auth;
import database.models.Question;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.jooq.Record;
import scenes.MyGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class DetailQuestionScene extends MyGroup {

    private Record question;
    private Label welcomeLabel, titleLabel, descriptionLabel,
            answerLabel, answerText, ustadzLabel, dateLabel;
    private JFXButton backButton, answerButton;
    private VBox vBox, header;
    BorderPane back;
    ToolBar toolBar;
    Separator hr;
    Pane pane;

    public DetailQuestionScene (Record question) {
        this.question = question;

        loadAndDrawQuestion();
    }

    @Override
    protected void prepareLayout() {
        vBox = new VBox();
        header = new VBox();
        String uri = Paths.get("dist/font/QuattrocentoSans-Regular.ttf").toUri().toString();
        Font.loadFont(uri,20);
        dateLabel = new Label();
        titleLabel = new Label();
        titleLabel.setId("titleQuestion");
        dateLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        dateLabel.setId("questionDate2");

        descriptionLabel = new Label();
        descriptionLabel.setId("questionDescription");
        descriptionLabel.prefWidthProperty().bind(Main.primaryStage.getScene().widthProperty().subtract(10));
        descriptionLabel.setWrapText(true);
        answerText = new Label();
        answerText.setId("answerTxt");
        answerText.prefWidthProperty().bind(Main.primaryStage.getScene().widthProperty().subtract(10));
        answerText.setWrapText(true);


        ustadzLabel = new Label();
        ustadzLabel.setVisible(true);
        backButton = new JFXButton("Back");
        answerButton = new JFXButton("Answer");
        pane = new Pane();
        pane.setPrefWidth(300);
        answerLabel = new Label("Jawaban");
        answerLabel.setId("answerLabel");

        hr = new Separator();
        hr.setOrientation(Orientation.HORIZONTAL);
        hr.setMinHeight(10);

        welcomeLabel = new Label("Detail Question");

        toolBar = new ToolBar();
        toolBar.prefWidthProperty().bind(Main.primaryStage.getScene().widthProperty());
        toolBar.setStyle("-fx-background-color: #ecf0f1;");

        toolBar.getItems().addAll(
                backButton, pane,welcomeLabel

        );
        header.getChildren().addAll(titleLabel,dateLabel);
        header.setMargin(titleLabel ,new Insets(1,1,1,10));
        header.setMargin(dateLabel ,new Insets(1,1,1,10));
        header.setStyle("-fx-background-color: #3498db;");
        vBox.prefHeightProperty().bind(Main.primaryStage.getScene().heightProperty());
        vBox.setMargin(descriptionLabel ,new Insets(15,1,1,10));
        vBox.setMargin(answerLabel,new Insets(1,1,1,10));
        vBox.setMargin(ustadzLabel,new Insets(1,1,1,10));
        vBox.setMargin(answerText,new Insets(15,1,1,10));
        vBox.setStyle("-fx-background-color: white;");
        back = new BorderPane();
        back.setTop(toolBar);
        back.setCenter(header);
        back.setBottom(vBox);
        ObservableList list = vBox.getChildren();
        list.addAll(descriptionLabel, answerButton, answerLabel, ustadzLabel, answerText);
        getChildren().addAll(back);
    }

    private void loadAndDrawQuestion() {
        answerButton.setVisible(Auth.isLogin() && Auth.isUstadz() && Question.isUnAnswered(question));

        System.out.println("question is unanswered: " + Question.isUnAnswered(question));
        titleLabel.setText(question.get("title").toString());
        descriptionLabel.setText(question.get("description").toString());

        String posterQuestion = "by "+ Question.getUser(question).get("name").toString();

        if (Question.isAnonim(question)) {
            posterQuestion = "Hamba Allah";
        }

        String dateQuestion = question.get("created_at") != null ? "Posted at "+question.get("created_at").toString().substring(0, 10) : "no tanggal";
        dateLabel.setText(dateQuestion + " " +posterQuestion);

        answerLabel.setVisible(Question.isAnswered(question));

        answerText.setVisible(Question.isAnswered(question));
        answerText.setText(Question.isAnswered(question) ? question.get("answer").toString() : "");

        if (Question.isAnswered(question)) {
            Record ustadz = Question.getUstadzWhoAnswer(question);
            ustadzLabel.setText("by : Ustadz " + ustadz.get("name").toString());
            ustadzLabel.setId("ustadzLbl");
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
