package database;

import database.schemas.QuestionTableSchema;
import database.schemas.ScheduleTableSchema;
import database.schemas.UserTableSchema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import static org.jooq.impl.DSL.*;

/**
 * Created by ibnujakaria on 3/10/17.
 */
public class DB {
    public static Connection conn;

    public static void  connect ()
    {
        try {
            Class.forName("org.sqlite.JDBC");

            conn = DriverManager.getConnection("jdbc:sqlite:informasi_masjid.sqlite");
            Statement stmt = conn.createStatement();
            stmt.execute("" +
                    "create table if not exists migrations " +
                    "(class varchar(500) not null, version int default 1)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void start ()
    {
        connect();
        UserTableSchema userTableSchema = new UserTableSchema();
        userTableSchema.up();

        QuestionTableSchema questionTableSchema = new QuestionTableSchema();
        questionTableSchema.up();

        ScheduleTableSchema scheduleTableSchema = new ScheduleTableSchema();
        scheduleTableSchema.up();
    }
}
