package scenes;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class WelcomeScene extends MyGroup {
    VBox vBox;
    Label welcomeLabel;
    ObservableList list;
    Image logo;
    ImageView imageView;

    @Override
    protected void prepareLayout() {
        view = new MyScene(this, 800, 600, Color.BEIGE);
        welcomeLabel = new Label("Informasi Masjid");
        welcomeLabel.setFont(Font.font("FZXiHeiI-Z08",33));
        try {
            logo = new Image(new FileInputStream("/home/basithdj/IdeaProjects/informasi-masjid/dist/images/logo/Logo.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        imageView = new ImageView(logo);

        imageView.setFitHeight(250);
        imageView.setFitWidth(250);
        imageView.setPreserveRatio(true);

        vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setMargin(imageView, new Insets(20,100,20,300));
        vBox.setMargin(welcomeLabel, new Insets(20,100,20,300));

        list = vBox.getChildren();
        list.addAll(imageView,welcomeLabel);

        getChildren().addAll(vBox);
    }

    @Override
    protected void addListeners() {
    }
}
