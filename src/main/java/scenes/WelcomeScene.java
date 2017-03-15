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
import javafx.scene.text.Font;
import scenes.publics.PublicScene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class WelcomeScene extends MyGroup {
    VBox vBox;
    Label welcomeLabel;
    Image logo;
    ImageView imageView;
    JFXButton nextButton;

    @Override
    protected void prepareLayout() {
        welcomeLabel = new Label("Informasi Masjid");
        welcomeLabel.setFont(Font.font("FZXiHeiI-Z08",33));
        try {
            logo = new Image(new FileInputStream("dist/images/logo/Logo.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
                System.out.println("listener2: width: "+newValue);
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

    @Override
    protected void onRender() {
        setvBoxFullScreen();
    }

    private void setvBoxFullScreen () {
        System.out.println(this.getClass().toString() + ": setVBoxFullScreen");
        vBox.setMinHeight(MyScene.height);
        vBox.setMinWidth(MyScene.width);
    }
}
