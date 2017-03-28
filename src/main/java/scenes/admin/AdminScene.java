package scenes.admin;

import app.Main;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import scenes.MyGroup;
import scenes.admin.schedules.ListSchedulesScene;
import scenes.admin.users.ListUsersScene;
import scenes.admin.ustadz.AddUstadzScene;
import scenes.admin.ustadz.ListUstadzScene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class AdminScene extends MyGroup {
    VBox vBox;
    BorderPane back, top;
    HBox hBox;
    JFXButton backButton, listUstadzButton, listUsersButton,
            listQuestionsButton, listSchedulesButton, listRecordingButton;
    FlowPane flowPane;
    Image ustadzImg,userImg,questionImg,scheduleImg,recordingImg;

    @Override
    protected void prepareLayout() {
        vBox = new VBox();

        Label label = new Label("Admin");
        label.setFont(new Font(40));
        flowPane = new FlowPane();
        flowPane.setPadding(new Insets(5, 1, 5, 1));
        flowPane.setVgap(3);
        flowPane.setHgap(3);
        flowPane.setPrefWrapLength(100);
        flowPane.setStyle("-fx-background-color: DAE6F3;");

        try {
            userImg = new Image(new FileInputStream("dist/images/user/user.png"),40,40,false,false);
            ustadzImg= new Image(new FileInputStream("dist/images/user/ustadz.png"),40,40,false,false);
            questionImg = new Image(new FileInputStream("dist/images/user/ask.png"),40,40,false,false);
            scheduleImg = new Image(new FileInputStream("dist/images/user/schedule.png"),40,40,false,false);
            recordingImg = new Image(new FileInputStream("dist/images/user/recording.png"),40,40,false,false);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        backButton = new JFXButton("Back");
        listUstadzButton = new JFXButton();
        listUsersButton = new JFXButton();
        listQuestionsButton = new JFXButton();
        listSchedulesButton = new JFXButton();
        listRecordingButton = new JFXButton();

        listUsersButton.setGraphic(new ImageView(userImg));
        listUstadzButton.setGraphic(new ImageView(ustadzImg));
        listQuestionsButton.setGraphic(new ImageView(questionImg));
        listSchedulesButton.setGraphic(new ImageView(scheduleImg));
        listRecordingButton.setGraphic(new ImageView(recordingImg));

        hBox = titleBar(backButton, new Label(""));
//        hBox = new HBox();
//        hBox.setStyle("-fx-background-color: #ecf0f1;");
//        backButton.setAlignment(Pos.TOP_LEFT);
//        hBox.setAlignment(Pos.CENTER);
//        hBox.getChildren().addAll(backButton);
//        hBox.prefWidthProperty().bind(Main.primaryStage.widthProperty());

        top = new BorderPane();
        top.setLeft(backButton);
        top.setCenter(hBox);
        top.setStyle("-fx-background-color: #ecf0f1;");
        top.setMargin(backButton ,new Insets(5,1,5,8));

        setvBoxFullScreen();
        flowPane.getChildren().addAll(listUsersButton, listUstadzButton,
                listQuestionsButton, listSchedulesButton, listRecordingButton);

        back = new BorderPane();
        back.setTop(top);
        back.setCenter(flowPane);


        getChildren().addAll(back);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });

        listUstadzButton.setOnAction(event -> {
            setNextScene(new ListUstadzScene());
            moveNextScene();
        });

        listUsersButton.setOnAction(event -> {
            setNextScene(new ListUsersScene());
            moveNextScene();
        });

        listSchedulesButton.setOnAction(event -> {
            setNextScene(new ListSchedulesScene());
            moveNextScene();
        });

        listQuestionsButton.setOnAction(event -> {
            setNextScene(new ListQuestionScene());
            moveNextScene();
        });
    }

    private void setvBoxFullScreen () {
        vBox.setMinHeight(Main.primaryStage.getScene().getHeight());
        vBox.setMinWidth(Main.primaryStage.getScene().getWidth());
    }
}
