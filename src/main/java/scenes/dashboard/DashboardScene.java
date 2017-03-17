package scenes.dashboard;

import com.jfoenix.controls.JFXButton;
import core.auth.Auth;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import scenes.MyGroup;
import scenes.dashboard.question.ListQuestionScene;
import scenes.dashboard.question.PostNewQuestionScene;
import scenes.publics.auth.LoginScene;


/**
 * Created by ibnujakaria on 3/15/17.
 */
public class DashboardScene extends MyGroup {
    private JFXButton logoutButton, adminButton, addQuestion, myListQuestions;

    @Override
    protected void prepareLayout() {
        Label label = new Label("Dashboard");
        label.setFont(new Font(40));
        Label welcome = new Label("Selamat datang: " + Auth.getUser().get("name"));
        logoutButton = new JFXButton("Logout");

        adminButton = new JFXButton("Admin");
        adminButton.setVisible(Auth.isAdmin());

        addQuestion = new JFXButton("Tanya sesuatu");
        addQuestion.setVisible(!Auth.isUstadz() && !Auth.isAdmin());

        myListQuestions = new JFXButton("Daftar Pertanyaan Saya");
        myListQuestions.setVisible(!Auth.isUstadz() && !Auth.isAdmin());

        System.out.println(Auth.getUser().get("username"));
        System.out.println("role -> " + Auth.getUser().get("role"));
        System.out.println(Auth.isAdmin() ? "Iki admin" : "Iki guduk admin");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, welcome, adminButton, addQuestion, myListQuestions, logoutButton);
        getChildren().add(vBox);
    }

    @Override
    protected void addListeners() {
        logoutButton.setOnAction(event -> {
            Auth.logout();

            setPreviousScene(new LoginScene());
            movePreviousScene();
        });

        adminButton.setOnAction(event -> {
            System.out.println("Halaman admin!");
        });

        addQuestion.setOnAction(event -> {
            setNextScene(new PostNewQuestionScene());
            moveNextScene();
        });

        myListQuestions.setOnAction(event -> {
            setNextScene(new ListQuestionScene());
            moveNextScene();
        });
    }
}
