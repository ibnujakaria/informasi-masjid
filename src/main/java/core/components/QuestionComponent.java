package core.components;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
        descriptionLabel = new Label("deskripsi");
        dateLabel = new Label("20-02-2017");
        seeDetailButton = new JFXButton("Lihat detail");

        VBox vBox = new VBox();
        HBox bottomBox = new HBox();
        bottomBox.getChildren().addAll(seeDetailButton);

        bottomBox.getChildren().addAll(dateLabel);
        vBox.getChildren().addAll(titleLabel, descriptionLabel, bottomBox);
        getChildren().addAll(vBox);
    }

    private void loadAndDrawQuestion() {
        super.onAfterNext();
        System.out.println("loadAndDrawQuestion");

        titleLabel.setText(question.get("title").toString());
        int limit = question.get("description").toString().length() - 1;
        limit = limit > 100 ? 100 : limit;
        descriptionLabel.setText(question.get("description").toString().substring(0, limit));
        dateLabel.setText(question.get("created_at") != null ? question.get("created_at").toString() : "no tanggal");
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
