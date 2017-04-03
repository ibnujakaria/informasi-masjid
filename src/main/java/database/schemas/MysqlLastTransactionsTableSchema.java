package database.schemas;

import database.DB;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ibnujakaria on 3/10/17.
 */
public class MysqlLastTransactionsTableSchema extends MysqlSchema {
    @Override
    protected int getVersion() {
        return 1;
    }


    @Override
    protected void prepare() {
        UP_QUERIES[0] = "CREATE TABLE last_transactions (table_name varchar(255), action varchar(255), last_executed datetime)";

    }
}
