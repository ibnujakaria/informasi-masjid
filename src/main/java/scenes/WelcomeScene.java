package scenes;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import scenes.publics.PublicScene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class WelcomeScene extends MyGroup {
    VBox vBox;
    Label welcomeLabel;
    Image logo,background;
    ImageView imageView;
    JFXButton nextButton;

    @Override
    protected void prepareLayout() {
        view = new MyScene(this, 800, 600);
        String uri = Paths.get("dist/css/welcome.css").toUri().toString();
        view.getStylesheets().add(uri);
        welcomeLabel = new Label("Informasi Masjid");
        try {
            background = new Image(new FileInputStream("dist/images/logo/coba.jpg"));
            logo = new Image(new FileInputStream("dist/images/logo/logo-1.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImagePattern imagePattern = new ImagePattern(background);
        view.setFill(imagePattern);

        imageView = new ImageView(logo);

        imageView.setFitHeight(250);
        imageView.setFitWidth(250);
        imageView.setPreserveRatio(true);

        nextButton = new JFXButton("Next");

        vBox = new VBox();
        setvBoxFullScreen();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(imageView,welcomeLabel,nextButton);

        BorderPane border = new BorderPane();
        border.setCenter(vBox);
        this.getChildren().addAll(border);
    }

    @Override
    protected void addListeners() {
        view.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setvBoxFullScreen();
            }
        });

        view.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setvBoxFullScreen();
            }
        });

        nextButton.setOnAction(event -> {
            setNextScene(new PublicScene());
            moveNextScene();
        });
    }

    private void setvBoxFullScreen () {
        vBox.setMinHeight(view.getHeight());
        vBox.setMinWidth(view.getWidth());
    }
}
