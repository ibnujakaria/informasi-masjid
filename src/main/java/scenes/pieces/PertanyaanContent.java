package scenes.pieces;

import app.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import core.auth.Auth;
import core.components.QuestionComponent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import org.jooq.Record;
import org.jooq.Result;
import scenes.MyGroup;
import scenes.dashboard.question.PostNewQuestionScene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by abdullah on 17/03/17.
 */
public class PertanyaanContent extends VBox {
    JFXButton tanyaButton;
    Image ask;
    JFXListView<VBox> listPertanyaan;
    StackPane askPane;
    MyGroup myGroup;

    public PertanyaanContent(MyGroup myGroup, Result<Record> questions) {
        this.myGroup = myGroup;
        prepareLayout(myGroup, questions);

        if (Auth.isLogin() && !Auth.isUstadz() && !Auth.isAdmin()) addAskButton();
    }

    public PertanyaanContent(MyGroup myGroup, Result<Record> questions, Label welcome) {
        this.myGroup = myGroup;
        getChildren().add(welcome);
        prepareLayout(myGroup, questions);
        addAskButton();
    }

    public void prepareLayout(MyGroup myGroup, Result<Record> questions) {
        listPertanyaan = new JFXListView<>();
        listPertanyaan.prefHeightProperty().bind(Main.primaryStage.getScene().heightProperty());
        for (Record question : questions) {
            listPertanyaan.getItems().add(new VBox(new QuestionComponent(question, myGroup)));
        }

        getChildren().add(listPertanyaan);
    }

    private void addAskButton() {
        try {
            ask = new Image(new FileInputStream("dist/images/user/ask.png"),18,27,false,false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        askPane = new StackPane();
        askPane.setStyle("-fx-background-color: white;");
        tanyaButton = new JFXButton("+");
        tanyaButton.setId("tanyaBtn");
        tanyaButton.setGraphic(new ImageView(ask));
        askPane.getChildren().add(tanyaButton);

        askPane.setAlignment(Pos.BOTTOM_RIGHT);
        askPane.setMargin(tanyaButton, new Insets(10, 20, 10, 0));
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
