package scenes.publics;

import app.Main;
import com.jfoenix.controls.*;
import database.models.Question;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import scenes.MyGroup;
import scenes.pieces.JadwalContent;
import scenes.pieces.PertanyaanContent;
import scenes.pieces.TopMenu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class PublicScene extends MyGroup {
    Image background;
    JFXTabPane tabPane;
    BorderPane borderPane;
    Tab tab1, tab2, tab3;

    JFXButton toDashboard;

    @Override
    protected void prepareLayout() {
        String uri = Paths.get("dist/css/public-scene.css").toUri().toString();
        Main.primaryStage.getScene().getStylesheets().add(uri);

        toDashboard = new JFXButton("Profile");

        tabPane = new JFXTabPane();
        tab1 = new Tab();
        tab2 = new Tab();
        tab3 = new Tab();
        tab1.setText("Jadwal Kajian");
        tab2.setText("Tanya Ustadz");
        tab3.setText("Rekaman Kajian");
        tab1.setContent(new JadwalContent());
        tab2.setContent(new PertanyaanContent(this, Question.getAnsweredQuestion()));
        tab3.setContent(new Label("Content 3"));
        tabPane.getTabs().addAll(tab1,tab2,tab3);

        borderPane = new BorderPane();
        borderPane.prefHeightProperty().bind(Main.primaryStage.getScene().heightProperty());
        borderPane.prefWidthProperty().bind(Main.primaryStage.getScene().widthProperty());
        borderPane.setTop(new TopMenu(this, toDashboard));
        borderPane.setCenter(tabPane);
        getChildren().add(borderPane);

        try {
            background = new Image(new FileInputStream("dist/images/logo/blur.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImagePattern imagePattern = new ImagePattern(background);
        Main.primaryStage.getScene().setFill(imagePattern);
    }

    @Override
    protected void addListeners() {
        toDashboard.setOnAction(event -> {
            movePreviousScene();
        });

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
        System.out.println(this.getClass().toString() + ": setVBoxFullScreen");
        borderPane.setMinHeight(Main.primaryStage.getScene().getHeight());
        borderPane.setMinWidth(Main.primaryStage.getScene().getWidth());
    }
}
