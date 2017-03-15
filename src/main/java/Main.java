import com.jfoenix.controls.JFXButton;
import database.DB;
import database.schemas.UserTableSchema;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scenes.WelcomeScene;


/**
 * Created by ibnujakaria on 3/10/17.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Info Masjid");

        WelcomeScene welcomeScene = new WelcomeScene();
        primaryStage.setScene(welcomeScene.getView());
        primaryStage.show();
    }

    public static void main(String[] args) {
        DB.start();
        launch(args);
    }
}