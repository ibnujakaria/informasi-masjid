package core.components;

import app.Main;
import com.jfoenix.controls.JFXButton;
import database.models.Question;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.jooq.Record;
import scenes.MyGroup;
import scenes.dashboard.question.DetailQuestionScene;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class QuestionComponent extends MyGroup {

    private Record question, user, ustadz;
    private Label titleLabel, descriptionLabel, dateLabel;
    private JFXButton seeDetailButton;
    private MyGroup parent;

    public QuestionComponent (Record question, MyGroup parent) {
        this.question = question;
        loadAndDrawQuestion();
        this.parent = parent;
    }

    @Override
    protected void prepareLayout() {
        System.out.println("Prepare layout");
        titleLabel = new Label("judul");
        titleLabel.setId("questionTitle");
        descriptionLabel = new Label("deskripsi");
        descriptionLabel.setPrefWidth(755);
        descriptionLabel.setWrapText(true);
        descriptionLabel.setId("questionDescription");
        dateLabel = new Label("20-02-2017");
        dateLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        dateLabel.setId("questionDate");
        seeDetailButton = new JFXButton("Lihat detail");
        seeDetailButton.setId("questionSeeDetailButton");

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);

        VBox vBox = new VBox();
        HBox bottomBox = new HBox();
        vBox.prefWidthProperty().bind(Main.primaryStage.getScene().widthProperty().subtract(46));

        bottomBox.getChildren().addAll(spacer);
        bottomBox.getChildren().addAll(seeDetailButton);

        vBox.getChildren().addAll(titleLabel, dateLabel, descriptionLabel, bottomBox);
        getChildren().addAll(vBox);
    }

    private void loadAndDrawQuestion() {
        super.onAfterNext();
        System.out.println("loadAndDrawQuestion");

        titleLabel.setText(question.get("title").toString());
        int limit = question.get("description").toString().length() - 1;

        String posterQuestion = "by "+ Question.getUser(question).get("name").toString();

        if (Question.isAnonim(question)) {
            posterQuestion = "Hamba Allah";
        }

        String dateQuestion = question.get("created_at") != null ? "Posted at "+question.get("created_at").toString().substring(0, 10) : "no tanggal";
        dateLabel.setText(dateQuestion + " " +posterQuestion);
    }

    @Override
    protected void onAfterBack() {
        super.onAfterBack();
    }

    @Override
    protected void addListeners() {
        seeDetailButton.setOnAction(event -> {
            DetailQuestionScene detailQuestionScene = new DetailQuestionScene(question);
            setNextScene(detailQuestionScene);
            detailQuestionScene.setPreviousScene(parent);
            moveNextScene();
        });
    }
}
