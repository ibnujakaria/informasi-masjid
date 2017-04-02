package database.schemas;

import database.DB;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ibnujakaria on 3/10/17.
 */
public class MysqlUserTableSchema extends MysqlSchema {
    @Override
    protected int getVersion() {
        return 4;
    }


    @Override
    protected void prepare() {
        UP_QUERIES[0] = "CREATE TABLE users (id int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(200) not null, email varchar(200) not null, password varchar(200), " +
                "address varchar(200) null, role int DEFAULT 0, is_ustadz int DEFAULT 0, " +
                "create_at datetime, update_at datetime)";

        UP_QUERIES[1] = "alter table users add column username varchar(200)";

        UP_QUERIES[2] = "alter table users change create_at created_at datetime";

        UP_QUERIES[3] = "alter table users change update_at updated_at datetime";
    }
}
