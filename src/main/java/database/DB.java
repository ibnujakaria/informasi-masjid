package database;

import app.Main;
import database.schemas.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import static org.jooq.impl.DSL.*;

/**
 * Created by ibnujakaria on 3/10/17.
 */
public class DB {
    public static Connection conn, mysql_conn;

    public static void  connect ()
    {
        // Sqlite Connection
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

        // Mysql Connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String host = Main.prop.getProperty("app.db.mysql_host");
            String db_name = Main.prop.getProperty("app.db.mysql_database");
            String username = Main.prop.getProperty("app.db.mysql_username");
            String password = Main.prop.getProperty("app.db.mysql_password");
            mysql_conn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+db_name+"?useLegacyDatetimeCode=false&serverTimezone=UTC",username,password);
            System.out.println("MYSQL connection success!!!");
            Statement stmt = mysql_conn.createStatement();
            stmt.execute("" +
                    "create table if not exists migrations " +
                    "(class varchar(500) not null, version int default 1)");
        } catch(Exception e){
            System.out.println(e);
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

        LastLoginTableSchema lastLoginTableSchema = new LastLoginTableSchema();
        lastLoginTableSchema.up();

        MysqlUserTableSchema mysqlUserTableSchema = new MysqlUserTableSchema();
        mysqlUserTableSchema.up();

        MysqlQuestionTableSchema mysqlQuestionTableSchema = new MysqlQuestionTableSchema();
        mysqlQuestionTableSchema.up();

        MysqlScheduleTableSchema mysqlScheduleTableSchema= new MysqlScheduleTableSchema();
        mysqlScheduleTableSchema.up();

        MysqlLastLoginTableSchema mysqlLastLoginTableSchema= new MysqlLastLoginTableSchema();
        mysqlLastLoginTableSchema.up();

        MysqlLastTransactionsTableSchema mysqlLastTransactionsTableSchema= new MysqlLastTransactionsTableSchema();
        mysqlLastTransactionsTableSchema.up();
    }
}
