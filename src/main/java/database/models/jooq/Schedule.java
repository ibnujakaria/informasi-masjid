package database.models.jooq;

import database.DB;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import static org.jooq.impl.DSL.*;

/**
 * Created by ibnujakaria on 19/03/17.
 */
public class Schedule {

    private static DSLContext db = DSL.using(DB.mysql_conn, SQLDialect.MARIADB);
    private static DSLContext db_secondary = DSL.using(DB.conn, SQLDialect.SQLITE);

    public static String days[] = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Ahad"};

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

    public static Result<Record> get () {
        return db.select().from(table("schedules")).fetch();
    }

    public static String getUstadz(Record schedule) {
        if (schedule.get("ustadz") != null) {
            return (String) schedule.get("ustadz");
        }

        return (String) User.getUserById((int) schedule.get("ustadz_id")).get("name");
    }

    public static String getDateLabel (Record schedule) {
        if (((String) schedule.get("periodic")).equals("weekly")) {
            return "Setiap hari " + days[((int) schedule.get("interval")) - 1];
        } else if (((String) schedule.get("periodic")).equals("monthly") && ((String) schedule.get("interval_by")).equals("day")) {
            return "Setiap tanggal " + schedule.get("interval");
        } else if (((String) schedule.get("periodic")).equals("monthly") && ((String) schedule.get("interval_by")).equals("week")) {
            return "Setiap " + days[((int) schedule.get("interval")) - 1] + " ke-" + schedule.get("interval");
        } else if (((String) schedule.get("periodic")).equals("once")) {
            return "Tanggal " + schedule.get("exact_date");
        }
        return null;
    }
}