package database.models;

import database.DB;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import static org.jooq.impl.DSL.*;

/**
 * Created by ibnujakaria on 19/03/17.
 */
public class Schedule {

    private  static DSLContext db = DSL.using(DB.conn, SQLDialect.SQLITE);

    public static void insert (String title, int ustadz_id, String ustadz, String description,
                               String periodic, String interval_by, String sub_interval_by, String interval,
                               String start_at, String exact_date) {
        System.out.println("title -> " + title);
        System.out.println("ustadz_id -> " + ustadz_id);
        System.out.println("ustadz -> " + ustadz);
        System.out.println("description -> " + description);
        System.out.println("periodic -> " + periodic);
        System.out.println("interval_by -> " + interval_by);
        System.out.println("sub_interval_by -> " + sub_interval_by);
        System.out.println("interval -> " + interval);
        System.out.println("start_at -> " + start_at);
        System.out.println("exact_date -> " + exact_date);

        db.insertInto(table("schedules"))
                .set(field("title"), title)
                .set(field("ustadz_id"), ustadz_id)
                .set(field("ustadz"), ustadz)
                .set(field("description"), description)
                .set(field("periodic"), periodic)
                .set(field("interval_by"), interval_by)
                .set(field("sub_interval_by"), sub_interval_by)
                .set(field("interval"), interval)
                .set(field("start_at"), start_at)
                .set(field("exact_date"), exact_date)
                .set(field("created_at"), new LocalDate() + " " + new LocalTime())
                .set(field("updated_at"), new LocalDate() + " " + new LocalTime())
                .execute();
    }
}