package scenes.publics;

import app.Main;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import scenes.MyGroup;
import scenes.MyScene;
import scenes.pieces.JadwalContent;
import scenes.pieces.PertanyaanContent;
import scenes.pieces.TopMenu;
import scenes.publics.auth.LoginScene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class PublicScene extends MyGroup {
//    VBox vBox;
//    JFXButton backButton, loginButton, tanyaButton;
    Image background;
    JFXTabPane tabPane;
    BorderPane borderPane;
    Tab tab1, tab2, tab3;
  
    @Override
    protected void prepareLayout() {
        String uri = Paths.get("dist/css/public-scene.css").toUri().toString();
        Main.primaryStage.getScene().getStylesheets().add(uri);

//        vBox = new VBox();
        tabPane = new JFXTabPane();
//        listSchedule = new JFXListView<Label>();
//        for(int i = 0 ; i < 4 ; i++) listSchedule.getItems().add(new Label("Item " + i));

        borderPane = new BorderPane();
        tab1 = new Tab();
        tab2 = new Tab();
        tab3 = new Tab();
        tab1.setText("Jadwal Kajian");
        tab2.setText("Tanya Ustadz");
        tab3.setText("Rekaman Kajian");

        tabPane.getTabs().addAll(tab1,tab2,tab3);

        // bind to take available space
        borderPane.prefHeightProperty().bind(Main.primaryStage.getScene().heightProperty());
        borderPane.prefWidthProperty().bind(Main.primaryStage.getScene().widthProperty());
        borderPane.setTop(new TopMenu());
        borderPane.setCenter(tabPane);

        try {
            background = new Image(new FileInputStream("dist/images/logo/blur.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImagePattern imagePattern = new ImagePattern(background);
        Main.primaryStage.getScene().setFill(imagePattern);

//        gak kepakek
//        Label label = new Label("PUBLIC SCENE");
//        label.setFont(new Font(30));
//        backButton = new JFXButton("Back");
//        loginButton = new JFXButton("Masuk");
//        tanyaButton = new JFXButton("Tanya");


//        GridPane buttons = new GridPane();
//        buttons.setPadding(new Insets(10, 10, 10, 10));
//        buttons.setVgap(5);
//        buttons.setHgap(5);
//        buttons.add(loginButton, 0, 2);
//        buttons.add(tanyaButton, 1, 2);
//        setvBoxFullScreen();
//        vBox.setMargin(buttons, new Insets(20, 20, 20, 320));
//        ObservableList list = vBox.getChildren();
        tab1.setContent(new JadwalContent());
        tab2.setContent(new PertanyaanContent(this));
        tab3.setContent(new Label("Content 3"));
        //Adding all the nodes to the observable list
//        list.addAll(borderPane);
        getChildren().add(borderPane);
    }

    @Override
    protected void addListeners() {
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
//        backButton.setOnAction(event -> {
//            movePreviousScene();
//        });

//        loginButton.setOnAction(event -> {
//            setNextScene(new LoginScene());
//            moveNextScene();
//        });

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
