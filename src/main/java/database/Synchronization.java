package database;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.jooq.*;
import org.jooq.impl.DSL;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

/**
 * Created by arifsubroto on 03/04/17.
 */
public class Synchronization {

    private  static DSLContext db = DSL.using(DB.mysql_conn, SQLDialect.MARIADB);

    public static Record upadateLastTransaction(String table_name, String action)
    {
        Result<Record> result = db.select().from(table("last_transactions"))
                .where(field("table_name").equal(table_name))
                .fetch();

        if (result.size() > 0) {
            UpdateSetMoreStep query = db.update(table("last_transactions"))
                    .set(field("last_executed"), new LocalDate() + " " + new LocalTime());

            query.where(field("table_name").equal(table_name)).and(field("action").equal(action))
                    .execute();
        }else{
            db.insertInto(table("last_transactions"))
                    .set(field("table_name"), table_name)
                    .set(field("action"), action)
                    .set(field("last_executed"), new LocalDate() + " " + new LocalTime())
                    .execute();
        }

        return null;
    }
}
