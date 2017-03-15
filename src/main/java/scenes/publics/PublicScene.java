package scenes.publics;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import scenes.MyGroup;
import scenes.MyScene;
import scenes.publics.auth.LoginScene;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class PublicScene extends MyGroup {
    JFXButton backButton, loginButton, tanyaButton;

    @Override
    protected void prepareLayout() {
        VBox vBox = new VBox();
        Label label = new Label("PUBLIC SCENE");
        label.setFont(new Font(30));
        backButton = new JFXButton("Back");
        loginButton = new JFXButton("Masuk");
        tanyaButton = new JFXButton("Tanya");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(loginButton, tanyaButton);
        vBox.getChildren().addAll(backButton, label, buttons);
        getChildren().add(vBox);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });

        loginButton.setOnAction(event -> {
            setNextScene(new LoginScene());
            moveNextScene();
        });
    }

    @Override
    protected void onRender() {

    }
}
