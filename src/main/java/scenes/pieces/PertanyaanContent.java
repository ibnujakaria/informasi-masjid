package scenes.pieces;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import core.auth.Auth;
import core.components.QuestionComponent;
import database.models.Question;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.jooq.Record;
import org.jooq.Result;
import scenes.MyGroup;
import scenes.dashboard.question.PostNewQuestionScene;
import scenes.publics.PublicScene;
import scenes.publics.auth.LoginScene;

/**
 * Created by abdullah on 17/03/17.
 */
public class PertanyaanContent extends VBox {
    JFXButton tanyaButton;
    JFXListView<VBox> listPertanyaan;
    StackPane askPane;
    MyGroup myGroup;

    public PertanyaanContent(MyGroup myGroup, Result<Record> questions) {
        this.myGroup = myGroup;
        prepareLayout(myGroup, questions);

        if (Auth.isLogin() && !Auth.isUstadz() && !Auth.isAdmin()) addAskButton();
    }

    public void prepareLayout(MyGroup myGroup, Result<Record> questions) {
        listPertanyaan = new JFXListView<>();
        for (Record question : questions) {
            listPertanyaan.getItems().add(new VBox(new QuestionComponent(question, myGroup)));
        }

        getChildren().add(listPertanyaan);
    }

    private void addAskButton() {
        askPane = new StackPane();
        tanyaButton = new JFXButton("Tanya Sesuatu");
        askPane.getChildren().add(tanyaButton);
        askPane.setAlignment(Pos.BOTTOM_RIGHT);
        askPane.setMargin(tanyaButton, new Insets(0, 10, 10, 0));
        setVgrow(askPane, Priority.ALWAYS);
        getChildren().add(askPane);

        addListener();
    }

    private void addListener() {
        tanyaButton.setOnAction(event -> {
            myGroup.setNextScene(new PostNewQuestionScene());
            myGroup.moveNextScene();
        });
    }
}
