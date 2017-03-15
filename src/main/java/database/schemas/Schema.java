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
public abstract class Schema {
    private Statement stmt;
    protected String[] UP_QUERIES;
    protected String[] DOWN_QUERIES ;
    private DSLContext db;

    protected abstract int getVersion();
    protected abstract void prepare();

    public Schema ()
    {
        UP_QUERIES = new String[getVersion()];
        DOWN_QUERIES = new String[getVersion()];
        prepare();

        db = DSL.using(DB.conn, SQLDialect.SQLITE);
        Result<Record> result = db.select().from(table("migrations"))
                .where(field("class").equal(this.getClass().toString()))
                .fetch();

        if (result.size() == 0) {
            try {
                stmt = DB.conn.createStatement();
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
                    db.execute(sql);
                }
            }

            makeItSynchronized();
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
        Result<Record> result = db.select().from(table("migrations"))
                .where(field("class").equal(this.getClass().toString()))
                .fetch();

        if (result.size() > 0) {
            System.out.println("there is already a migration");
            Record r = result.get(0);
            int version = Integer.parseInt(r.get("version") + "");
            return version;
        }

        return 0;
    }

    private void makeItSynchronized()
    {
        try {
            stmt = DB.conn.createStatement();
            stmt.execute("update migrations set version = " + getVersion() + " where class = '" + this.getClass().toString() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
