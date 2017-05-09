package app;

import core.auth.Auth;
import core.json.reader.QuestionJSONReader;
import core.socket.client.ClientOnSuccess;
import core.socket.client.api.QuestionSocketApi;
import database.DB;
import database.models.template.Question;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;
import scenes.WelcomeScene;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

import static org.jooq.impl.DSL.table;


/**
 * Created by ibnujakaria on 3/10/17.
 */
public class Main extends Application {

    public static Stage primaryStage;
    public static double height = 600, width = 800;
    public static Properties prop = new Properties();
    private static InputStream inputStream;
    public static org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

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

    public static void readProperties () {
        try {
            inputStream = new FileInputStream("config.properties");
            prop.load(inputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readProperties();

        DB.start();

//        launch(args);
        QuestionSocketApi.getAnsweredQuestion(new ClientOnSuccess() {
            @Override
            public void doWhatYouWant(Socket socket, List<String> headers, List<String> body) {
                String fullBody = "";
                for (String line: body) fullBody += line;

                try {
                    JSONObject questionObject = new JSONObject(fullBody);
                    List<Question> questions = QuestionJSONReader.parseQuestions(questionObject.getJSONArray("questions"));
                    for (Question q: questions) {
                        System.out.println(q);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}