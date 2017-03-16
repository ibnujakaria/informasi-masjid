package app;

import com.jfoenix.controls.JFXButton;
import database.DB;
import database.models.User;
import database.schemas.UserTableSchema;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import scenes.WelcomeScene;


/**
 * Created by ibnujakaria on 3/10/17.
 */
public class Main extends Application {

    public static Stage primaryStage;
    public static double height = 400, width = 400;

    @Override
    public void start(Stage page) throws Exception {
        this.primaryStage = page;

        primaryStage.setTitle("Info Masjid");

        WelcomeScene welcomeScene = new WelcomeScene();
        Scene scene = new Scene(welcomeScene, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        DB.start();
        launch(args);
    }
}