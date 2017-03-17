package scenes.pieces;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import scenes.publics.PublicScene;
import scenes.publics.auth.LoginScene;

/**
 * Created by abdullah on 17/03/17.
 */
public class PertanyaanContent extends GridPane {
    JFXButton loginButton, tanyaButton;
    PublicScene publicScene;

    public PertanyaanContent(PublicScene publicScene) {
        setPadding(new Insets(10, 10, 10, 10));
        setVgap(5);
        setHgap(5);

        loginButton = new JFXButton("Masuk");
        tanyaButton = new JFXButton("Tanya");

        add(loginButton, 0, 2);
        add(tanyaButton, 1, 2);

        this.publicScene = publicScene;

        addListener(loginButton);
    }

    private void addListener(JFXButton button) {
        button.setOnAction(event -> {
            publicScene.setNextScene(new LoginScene());
            publicScene.moveNextScene();
        });
    }
}
