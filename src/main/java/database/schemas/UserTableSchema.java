package database.schemas;

import database.DB;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ibnujakaria on 3/10/17.
 */
public class UserTableSchema extends Schema {

    protected int VERSION = 1;
    protected String CLASS_NAME = UserTableSchema.class.toString();

    @Override
    protected int getVersion() {
        return 1;
    }


    @Override
    protected void prepare() {
        UP_QUERIES[0] = "create table users (id integer primary key autoincrement," +
                "name varchar (200) not null, email varchar (200) not null, password varchar (200)," +
                "address varchar (200) null, role integer default 0, " +
                "is_ustadz integer default 0, created_at text, updated_at text)";
    }
}
