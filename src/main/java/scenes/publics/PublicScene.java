package scenes.publics;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import scenes.MyGroup;
import scenes.MyScene;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class PublicScene extends MyGroup {
    JFXButton backButton;

    @Override
    protected void prepareLayout() {
        view = new MyScene(this, 800, 600);
        VBox vBox = new VBox();
        Label label = new Label("Hello GUYS! INI PUBLIC SCENE");
        backButton = new JFXButton("Back");

        vBox.getChildren().addAll(label, backButton);
        getChildren().add(vBox);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });
    }
}
