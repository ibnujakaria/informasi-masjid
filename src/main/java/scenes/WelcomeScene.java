package scenes;

import app.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToolbar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.util.Duration;
import scenes.publics.PublicScene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class WelcomeScene extends MyGroup {
    VBox vBox;
    BorderPane border;
    Label welcomeLabel;
    Image logo,background;
    ImageView imageView;
    JFXButton nextButton;
    String font1,font2,font3,font4;

    @Override
    protected void prepareLayout() {
        font1 = Paths.get("dist/font/OpenSans-Regular.ttf").toUri().toString();
        font2 = Paths.get("dist/font/ABeeZee-Regular.ttf").toUri().toString();
        font3 = Paths.get("dist/font/CaviarDreams.ttf").toUri().toString();
        font4 = Paths.get("dist/font/QuattrocentoSans-Regular.ttf").toUri().toString();
        Font.loadFont(font1,20);
        Font.loadFont(font2,20);
        Font.loadFont(font3,20);
        Font.loadFont(font4,20);
        String uri = Paths.get("dist/css/welcome.css").toUri().toString();
        Main.primaryStage.getScene().getStylesheets().add(uri);

        welcomeLabel = new Label("Informasi Masjid");
        try {
            background = new Image(new FileInputStream("dist/images/logo/coba.jpg"));
            logo = new Image(new FileInputStream("dist/images/logo/logo-1.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImagePattern imagePattern = new ImagePattern(background);
        Main.primaryStage.getScene().setFill(imagePattern);

        imageView = new ImageView(logo);

        imageView.setFitHeight(250);
        imageView.setFitWidth(250);
        imageView.setPreserveRatio(true);

        nextButton = new JFXButton("Next");

        vBox = new VBox();
        setvBoxFullScreen();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(imageView,welcomeLabel);


        border = new BorderPane();
        border.setCenter(vBox);
        this.getChildren().addAll(border);
    }

    @Override
    protected void addListeners() {
        Timeline auto = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            setNextScene(new PublicScene());
            moveNextScene();
        }));
        auto.setCycleCount(1);
        auto.play();

        Main.primaryStage.getScene().widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setvBoxFullScreen();
                System.out.println("listener2: width: "+newValue);
            }
        });

        Main.primaryStage.getScene().heightProperty().addListener(new ChangeListener<Number>() {
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

    @Override
    protected void onAfterBack() {
        super.onAfterBack();
        setvBoxFullScreen();
    }

    private void setvBoxFullScreen () {
        System.out.println(this.getClass().toString() + ": setVBoxFullScreen");
        vBox.setMinHeight(Main.primaryStage.getScene().getHeight());
        vBox.setMinWidth(Main.primaryStage.getScene().getWidth());
    }
}
