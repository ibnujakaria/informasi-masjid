package scenes.publics;

import app.Main;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import scenes.MyGroup;
import scenes.MyScene;
import scenes.publics.auth.LoginScene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class PublicScene extends MyGroup {
    VBox vBox;
    JFXButton backButton, loginButton, tanyaButton;
    Image background;
    @Override
    protected void prepareLayout() {
        vBox = new VBox();

        String uri = Paths.get("dist/css/welcome.css").toUri().toString();

        try {
            background = new Image(new FileInputStream("dist/images/logo/blur.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImagePattern imagePattern = new ImagePattern(background);

        Label label = new Label("PUBLIC SCENE");
        label.setFont(new Font(30));
        backButton = new JFXButton("Back");
        loginButton = new JFXButton("Masuk");
        tanyaButton = new JFXButton("Tanya");


        GridPane buttons = new GridPane();
        buttons.setPadding(new Insets(10, 10, 10, 10));
        buttons.setVgap(5);
        buttons.setHgap(5);
        buttons.add(loginButton, 0, 2);
        buttons.add(tanyaButton, 1, 2);
        setvBoxFullScreen();
        vBox.setAlignment(Pos.CENTER);
        vBox.setMargin(buttons, new Insets(20, 20, 20, 320));
        ObservableList list = vBox.getChildren();

        //Adding all the nodes to the observable list
        list.addAll(backButton,label,buttons);
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
    protected void onAfterNext() {
        super.onAfterNext();
        setvBoxFullScreen();
    }

    @Override
    protected void onAfterBack() {
        super.onAfterBack();
        setvBoxFullScreen();
    }

    private void setvBoxFullScreen () {
        vBox.setMinHeight(Main.primaryStage.getScene().getHeight());
        vBox.setMinWidth(Main.primaryStage.getScene().getWidth());
    }
}
