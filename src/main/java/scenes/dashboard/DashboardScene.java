package scenes.dashboard;

import app.Main;
import com.jfoenix.controls.JFXButton;
import core.auth.Auth;
import database.models.Question;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import scenes.MyGroup;
import scenes.admin.AdminScene;
import scenes.dashboard.question.MyListQuestionScene;
import scenes.dashboard.question.PostNewQuestionScene;
import scenes.dashboard.question.UnAnsweredQuestionScene;
import scenes.pieces.PertanyaanContent;
import scenes.pieces.TopMenu;
import scenes.publics.PublicScene;


/**
 * Created by ibnujakaria on 3/15/17.
 */
public class DashboardScene extends MyGroup {
    BorderPane borderPane;
    private JFXButton homeButton, adminButton, addQuestion, myListQuestions, unansweredQuestionButton;

    @Override
    protected void prepareLayout() {
        Label label = new Label("Dashboard");
        label.setFont(new Font(40));
        Label welcome = new Label("Selamat datang: " + Auth.getUser().get("name"));
        homeButton = new JFXButton("Home");

        adminButton = new JFXButton("Admin");
//        adminButton.setVisible(Auth.isAdmin());
//
//        addQuestion = new JFXButton("Tanya sesuatu");
//        addQuestion.setVisible(!Auth.isUstadz() && !Auth.isAdmin());
//
//        myListQuestions = new JFXButton("Daftar Pertanyaan Saya");
//        myListQuestions.setVisible(!Auth.isUstadz() && !Auth.isAdmin());
//
//        unansweredQuestionButton = new JFXButton("Pertanyaan Masuk");
//        unansweredQuestionButton.setVisible(Auth.isUstadz());
//
//        System.out.println(Auth.getUser().get("username"));
//        System.out.println("role -> " + Auth.getUser().get("role"));
//        System.out.println(Auth.isAdmin() ? "Iki admin" : "Iki guduk admin");
//
//        VBox vBox = new VBox();
//        vBox.getChildren().addAll(label, welcome, adminButton, addQuestion, myListQuestions,
//                unansweredQuestionButton);

        VBox vBox;
        if (Auth.isAdmin()) {
            vBox = new VBox(label, welcome, adminButton);
        } else if (Auth.isUstadz()) {
            vBox = new PertanyaanContent(this, Question.getUnAnsweredQuestions());
        } else {
            vBox = new PertanyaanContent(this, Question.getQuestionsOfUserId(Auth.getUserId()));
        }

        borderPane = new BorderPane();
        // bind to take available space
        borderPane.prefHeightProperty().bind(Main.primaryStage.getScene().heightProperty());
        borderPane.prefWidthProperty().bind(Main.primaryStage.getScene().widthProperty());
        borderPane.setTop(new TopMenu(this, homeButton));
        borderPane.setCenter(vBox);
        getChildren().add(borderPane);
    }

    @Override
    protected void onAfterBack() {
        super.onAfterBack();
        System.out.println("Im back!");
        System.out.println("this you should call the query again");
    }

    @Override
    protected void addListeners() {
//        homeButton.setOnAction(event -> {
//            Auth.logout();
//
//            setPreviousScene(new LoginScene());
//            movePreviousScene();
//        });
        homeButton.setOnAction(event -> {
            setNextScene(new PublicScene());
            moveNextScene();
        });

        adminButton.setOnAction(event -> {
            setNextScene(new AdminScene());
            moveNextScene();
        });

//        addQuestion.setOnAction(event -> {
//            setNextScene(new PostNewQuestionScene());
//            moveNextScene();
//        });
//
//        myListQuestions.setOnAction(event -> {
//            setNextScene(new MyListQuestionScene());
//            moveNextScene();
//        });
//
//        unansweredQuestionButton.setOnAction(event -> {
//            setNextScene(new UnAnsweredQuestionScene());
//            moveNextScene();
//        });
    }
}
