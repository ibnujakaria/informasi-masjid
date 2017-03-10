package database;

import database.schemas.UserTableSchema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by ibnujakaria on 3/10/17.
 */
public class DB {
    public static Connection conn;

    public static void connect ()
    {
        try {
            Class.forName("org.sqlite.JDBC");

            conn = DriverManager.getConnection("jdbc:sqlite:informasi_masjid.sqlite");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void start ()
    {
        connect();
        UserTableSchema userTableSchema = new UserTableSchema();
        userTableSchema.createTable();
    }
}
