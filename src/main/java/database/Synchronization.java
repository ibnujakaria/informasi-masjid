package database;

import app.Main;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.jooq.*;
import org.jooq.impl.DSL;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

/**
 * Created by arifsubroto on 03/04/17.
 */
public class Synchronization {

    private  static DSLContext db = DSL.using(DB.mysql_conn, SQLDialect.MARIADB);
    private  static DSLContext db_secondary = DSL.using(DB.conn, SQLDialect.SQLITE);

    public static Record upadateLastTransaction(String table_name, String action)
    {
        Result<Record> result = db.select().from(table("last_transactions"))
                .where(field("table_name").equal(table_name))
                .fetch();

        if (result.size() > 0) {
            UpdateSetMoreStep query = db.update(table("last_transactions"))
                    .set(field("last_executed"), Main.dtf.print(new DateTime()));

            query.where(field("table_name").equal(table_name)).and(field("action").equal(action))
                    .execute();
        }else{
            db.insertInto(table("last_transactions"))
                    .set(field("table_name"), table_name)
                    .set(field("action"), action)
                    .set(field("last_executed"), Main.dtf.print(new DateTime()))
                    .execute();
        }

        return null;
    }


    public static Record upadateLastTransactionLocal(String table_name, String action)
    {
        Result<Record> result = db_secondary.select().from(table("last_transactions"))
                .where(field("table_name").equal(table_name))
                .fetch();

        if (result.size() > 0) {
            UpdateSetMoreStep query = db_secondary.update(table("last_transactions"))
                    .set(field("last_executed"), Main.dtf.print(new DateTime()));

            query.where(field("table_name").equal(table_name)).and(field("action").equal(action))
                    .execute();
        }else{
            db_secondary.insertInto(table("last_transactions"))
                    .set(field("table_name"), table_name)
                    .set(field("action"), action)
                    .set(field("last_executed"), Main.dtf.print(new DateTime()))
                    .execute();
        }

        return null;
    }
}
