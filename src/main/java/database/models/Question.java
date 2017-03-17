package database.models;

import database.DB;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import static org.jooq.impl.DSL.*;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class Question {
    private  static DSLContext db = DSL.using(DB.conn, SQLDialect.SQLITE);

    public static Record create (int user_id, String title, String description,
                                 boolean is_anonim) {
        return db.insertInto(table("questions"))
                .set(field("user_id"), user_id)
                .set(field("ustadz_id"), 0)
                .set(field("title"), title)
                .set(field("description"), description)
                .set(field("is_anonim"), is_anonim ? 1 : 0)
                .set(field("created_at"), new LocalDate() + " " + new LocalTime())
                .set(field("updated_at"), new LocalDate() + " " + new LocalTime())
                .returning(field("id"))
                .fetchOne();
    }
}
