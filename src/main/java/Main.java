import com.jfoenix.controls.JFXButton;
import database.DB;
import database.schemas.UserTableSchema;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Created by ibnujakaria on 3/10/17.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 300);

        JFXButton button = new JFXButton("Tanya Ustadz");
        root.getChildren().add(button);
        primaryStage.setTitle("Info Masjid");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        DB.start();
        launch(args);
    }
}