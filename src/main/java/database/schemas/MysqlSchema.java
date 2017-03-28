package database.schemas;

import database.DB;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.SQLException;
import java.sql.Statement;
import static org.jooq.impl.DSL.*;

/**
 * Created by ibnujakaria on 3/11/17.
 */
public abstract class MysqlSchema {
    private Statement stmt;
    protected String[] UP_QUERIES;
    protected String[] DOWN_QUERIES ;

    private DSLContext mysql_db;

    protected abstract int getVersion();
    protected abstract void prepare();

    public MysqlSchema ()
    {
        UP_QUERIES = new String[getVersion()];
        DOWN_QUERIES = new String[getVersion()];
        prepare();

        mysql_db = DSL.using(DB.mysql_conn, SQLDialect.MARIADB);

        Result<Record> result = mysql_db.select().from(table("migrations"))
                .where(field("class").equal(this.getClass().toString()))
                .fetch();

        if (result.size() == 0) {
            try {
                stmt = DB.mysql_conn.createStatement();
                stmt.execute("insert into migrations values (" +
                        "'"  + this.getClass().toString() + "', '"
                        + 0 + "')");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void up ()
    {
        if (isUnSynchronized()) {
            System.out.println(this.getClass().toString() + ": not synchronized with the version of schema is " + getVersion() + " and the version of migration is " + getVersionFromMigration()        );
            for (int i = getVersionFromMigration(); i < getVersion(); i++) {
                String sql = UP_QUERIES[i];
                if (sql != null && !sql.isEmpty()) {
                    mysql_db.execute(sql);
                    System.out.println(this.getClass().toString() + " is executing query[" + i + "]");
                }
            }

            makeItSynchronized();
        } else {
            System.out.println(this.getClass().toString() + "'s migration is synchronized");
        }
    }

    public void down ()
    {

    }

    private boolean isUnSynchronized ()
    {
        return getVersion() > getVersionFromMigration();
    }

    private int getVersionFromMigration ()
    {
        Result<Record> result = mysql_db.select().from(table("migrations"))
                .where(field("class").equal(this.getClass().toString()))
                .fetch();

        if (result.size() > 0) {
            Record r = result.get(0);
            int version = Integer.parseInt(r.get("version") + "");
            return version;
        }

        return 0;
    }

    private void makeItSynchronized()
    {
        try {
            stmt = DB.mysql_conn.createStatement();
            stmt.execute("update migrations set version = " + getVersion() + " where class = '" + this.getClass().toString() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
