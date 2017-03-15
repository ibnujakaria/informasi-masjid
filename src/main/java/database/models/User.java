package database.models;

import database.DB;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import static org.jooq.impl.DSL.*;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class User {

    public static void createUser (String name, String username, String email, String address,
                                   String password, boolean is_ustadz, boolean is_admin) {
        DSLContext db = DSL.using(DB.conn, SQLDialect.SQLITE);


        db.insertInto(table("users"))
                .set(field("name"), name)
                .set(field("username"), username)
                .set(field("email"), email)
                .set(field("password"), password)
                .set(field("role"), is_admin ? "admin" : "member")
                .set(field("is_ustadz"), is_ustadz ? "true" : "false")
                .set(field("created_at"), new LocalDate() + " " + new LocalTime())
                .set(field("updated_at"), new LocalDate() + " " + new LocalTime())
                .execute();
    }
}
