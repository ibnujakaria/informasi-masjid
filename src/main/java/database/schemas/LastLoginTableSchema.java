package database.schemas;

/**
 * Created by ibnujakaria on 20/03/17.
 */
public class LastLoginTableSchema extends Schema {
    @Override
    protected int getVersion() {
        return 1;
    }

    @Override
    protected void prepare() {
        UP_QUERIES[0] = "create table last_login (id integer primary key, " +
                "value varchar(500) not null)";
    }
}
