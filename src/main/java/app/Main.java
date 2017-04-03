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
import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import scenes.WelcomeScene;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
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

        DSLContext db = DSL.using(DB.mysql_conn, SQLDialect.MARIADB);
        Record last_server_transaction = db.select().from(table("last_transactions"))
                .fetchOne();

        Timestamp timestamp = (Timestamp) last_server_transaction.get("last_executed");
        Instant timeInstant = new Instant(timestamp.getTime());

        System.out.println("timestamp -> " + timestamp);
        System.out.println("joda convert -> " + Main.dtf.print(timeInstant));

        if (true) return;
        launch(args);
    }
}