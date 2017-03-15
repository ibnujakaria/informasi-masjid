package scenes;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class WelcomeScene extends MyGroup {

    @Override
    protected void prepareLayout() {
        view = new MyScene(this, 800, 600);
        Label welcomeLabel = new Label("Informasi Masjid");
        Label loading = new Label("Loading...");

        welcomeLabel.setFont(new Font(30));
        getChildren().addAll(welcomeLabel, loading);
    }

    @Override
    protected void addListeners() {
    }
}
