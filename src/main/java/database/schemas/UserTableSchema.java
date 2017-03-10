package database.schemas;

import database.DB;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ibnujakaria on 3/10/17.
 */
public class UserTableSchema {
    private Statement stmt;
    public void createTable ()
    {
        try {
            stmt = DB.conn.createStatement();

//            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
