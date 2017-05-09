package scenes.dashboard;

import app.Main;
import com.jfoenix.controls.JFXButton;
import core.auth.Auth;
import database.models.jooq.Question;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import scenes.MyGroup;
import scenes.admin.AdminScene;
import scenes.pieces.PertanyaanContent;
import scenes.pieces.TopMenu;
import scenes.publics.PublicScene;


/**
 * Created by ibnujakaria on 3/15/17.
 */
public class DashboardScene extends MyGroup {
    BorderPane borderPane;
    private JFXButton homeButton, adminButton, addQuestion, myListQuestions, unansweredQuestionButton;
    private Label label, welcome;

    @Override
    protected void prepareLayout() {
//        label = new Label("Dashboard");
//        label.setFont(new Font(40));
        String ustadz = Auth.isUstadz()?"Ustadz ":"";
        welcome = new Label("Selamat datang: " + ustadz + Auth.getUser().get("name"));
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


        borderPane = new BorderPane();
        // bind to take available space
        borderPane.prefHeightProperty().bind(Main.primaryStage.getScene().heightProperty());
        borderPane.prefWidthProperty().bind(Main.primaryStage.getScene().widthProperty());
        borderPane.setTop(new TopMenu(this, homeButton, "Dashboard"));
        borderPane.setCenter(getAskingContent());
        getChildren().add(borderPane);
    }

    @Override
    protected void onAfterBack() {
        super.onAfterBack();
        System.out.println("Im back!");
        System.out.println("this you should call the query again");

        borderPane.setCenter(getAskingContent());
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

    private VBox getAskingContent() {
//        HBox hBox = new HBox(welcome);
        if (Auth.isAdmin()) {
            return  new VBox(welcome, adminButton);
        } else if (Auth.isUstadz()) {
            return new PertanyaanContent(this, Question.getUnAnsweredQuestions(), welcome);
        } else {
            return new PertanyaanContent(this, Question.getQuestionsOfUserId(Auth.getUserId()), welcome);
        }
    }
}
