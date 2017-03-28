package app;

import com.jfoenix.controls.JFXButton;
import core.auth.Auth;
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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Created by ibnujakaria on 3/10/17.
 */
public class Main extends Application {

    public static Stage primaryStage;
    public static double height = 600, width = 800;
    public static Properties prop = new Properties();
    private static InputStream inputStream;

    @Override
    public void start(Stage page) throws Exception {
        this.primaryStage = page;

        primaryStage.setTitle("Info Masjid");

        // check login
        Auth.checkPreviousLogin();

        Scene scene = new Scene(new Group(), width, height);
        primaryStage.setScene(scene);
        WelcomeScene welcomeScene = new WelcomeScene();
        scene.setRoot(welcomeScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            inputStream = new FileInputStream("config.properties");
            prop.load(inputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        DB.start();
        launch(args);
    }
}