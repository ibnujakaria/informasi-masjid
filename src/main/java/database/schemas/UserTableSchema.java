package database.schemas;

import database.DB;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ibnujakaria on 3/10/17.
 */
public class UserTableSchema extends Schema {
    @Override
    protected int getVersion() {
        return 6;
    }


    @Override
    protected void prepare() {
        UP_QUERIES[0] = "create table users (id integer primary key autoincrement," +
                "name varchar (200) not null, email varchar (200) not null, password varchar (200)," +
                "address varchar (200) null, role integer default 0, " +
                "is_ustadz integer default 0, created_at text, updated_at text)";

        UP_QUERIES[1] = "alter table users add column username varchar(200)";

        UP_QUERIES[2] = "alter table users rename to user_backup_temp";

        UP_QUERIES[3] = "create table users (id integer primary key autoincrement," +
                "name varchar (200) not null, email varchar (200) not null," +
                "address varchar (200) null, role integer default 0, " +
                "is_ustadz integer default 0, created_at text, updated_at text)";

        UP_QUERIES[4] = "insert into users select id, name, email, address, role, is_ustadz, " +
                "created_at, updated_at from user_backup_temp";

        UP_QUERIES[5] = "drop table user_backup_temp";
    }
}
