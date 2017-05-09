package scenes.dashboard.question;

import app.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import database.models.jooq.Question;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import org.jooq.Record;
import scenes.MyGroup;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class AnswerQuestionScene extends MyGroup {
    private VBox vBox;
    private Label welcomeLabel, toolbarTitle;
    private JFXTextArea answerField;
    private JFXButton backButton, submitButton;
    private Record question;
    BorderPane back;
//    ToolBar toolBar;
//    Pane pane;

    public AnswerQuestionScene (Record question) {
        this.question = question;
    }

    @Override
    protected void prepareLayout() {
        vBox = new VBox();

        welcomeLabel = new Label("Jawab Pertanyaan");
        answerField = new JFXTextArea();
        answerField.setPromptText("Jawaban..");
        answerField.setMaxWidth(480);
        backButton = new JFXButton("Back");
        submitButton = new JFXButton("Submit");
//        pane = new Pane();
//        pane.setPrefWidth(300);
        toolbarTitle = new Label("Answer Question");

//        GridPane buttons = new GridPane();
//        buttons.setPadding(new Insets(0, 10, 0, 10));
//        buttons.setVgap(10);
//        buttons.setHgap(10);
//        buttons.add(submitButton, 0, 2);
//        buttons.setAlignment(Pos.CENTER);
        setvBoxFullScreen();

//        toolBar = new ToolBar();
//        toolBar.setPrefWidth(800);
//
//        toolBar.getItems().addAll(
//                backButton, pane,toolbarTitle
//
//        );
        back = new BorderPane();
        HBox hBox = titleBar(backButton, toolbarTitle);
        back.setTop(hBox);

        vBox.setAlignment(Pos.CENTER);
        vBox.setMargin(submitButton, new Insets(10));
        ObservableList list = vBox.getChildren();
        list.addAll(welcomeLabel, answerField,submitButton);
        back.setCenter(vBox);
        getChildren().addAll(back);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
            setvBoxFullScreen();
        });

        submitButton.setOnAction(event -> {
            answerQuestion();
            setvBoxFullScreen();
        });

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    answerQuestion();
                    setvBoxFullScreen();
                }
            }
        });

        Main.primaryStage.getScene().widthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setvBoxFullScreen();
            }
        });

        Main.primaryStage.getScene().heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setvBoxFullScreen();
            }
        });
    }

    private void answerQuestion() {
        Question.answerQuestion(Integer.parseInt(question.get("id").toString()), answerField.getText());
        movePreviousScene();
    }

    private void setvBoxFullScreen () {
        vBox.setMinHeight(Main.primaryStage.getScene().getHeight());
        vBox.setMinWidth(Main.primaryStage.getScene().getWidth());
    }
}
