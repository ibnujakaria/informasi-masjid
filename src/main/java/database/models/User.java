package database.models;

import com.google.common.hash.Hashing;
import database.DB;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.nio.charset.Charset;

import static org.jooq.impl.DSL.*;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class User {
    private  static DSLContext db = DSL.using(DB.conn, SQLDialect.SQLITE);

    public static Record createUser (String name, String username, String email, String address,
                                   String password, boolean is_ustadz, boolean is_admin) {

        String hashedPassword = null;
        hashedPassword = Hashing.sha256().newHasher().putString(password, Charset.defaultCharset()).hash().toString();
        db.insertInto(table("users"))
                .set(field("name"), name)
                .set(field("username"), username)
                .set(field("email"), email)
                .set(field("password"), hashedPassword)
                .set(field("role"), is_admin ? 1 : 0)
                .set(field("address"), address)
                .set(field("is_ustadz"), is_ustadz ? 1 : 0)
                .set(field("created_at"), new LocalDate() + " " + new LocalTime())
                .set(field("updated_at"), new LocalDate() + " " + new LocalTime())
                .execute();

        return getUserByUsername(username);
    }

    public static Record update (Record user, String name, String username, String email, String address,
                                 String password, boolean is_ustadz, boolean is_admin) {
        String hashedPassword = null;
        if (password != null) {
            hashedPassword = Hashing.sha256().newHasher().putString(password, Charset.defaultCharset()).hash().toString();
        }

        UpdateSetMoreStep query = db.update(table("users"))
                .set(field("name"), name)
                .set(field("username"), username)
                .set(field("email"), email)
                .set(field("role"), is_admin ? 1 : 0)
                .set(field("address"), address)
                .set(field("is_ustadz"), is_ustadz ? 1 : 0)
                .set(field("created_at"), new LocalDate() + " " + new LocalTime())
                .set(field("updated_at"), new LocalDate() + " " + new LocalTime());

        if (password != null) {
            query = query.set(field("password"), hashedPassword);
        }

        query.where(field("id").equal(Integer.parseInt(user.get("id").toString())))
                .execute();

        return getUserByUsername(username);
    }

    public static Record getUserByUsername (String username)
    {
        Result<Record> result = db.select().from(table("users"))
                .where(field("username").equal(username))
                .fetch();

        if (result.size() > 0) {
            return result.get(0);
        }

        return null;
    }

    public static Result<Record> getUstadz ()
    {
        return db.select().from(table("users"))
                .where(field("is_ustadz").equal(1))
                .orderBy(field("id").desc())
                .fetch();
    }

    public static Record getUserById(int user_id) {
        return db.select().from(table("users"))
                .where(field("id").equal(user_id))
                .fetchOne();
    }

    public static Result<Record> getUsers () {
        return db.select().from(table("users"))
                .where(field("is_ustadz").equal(0))
                .orderBy(field("id").desc())
                .fetch();
    }

    public static boolean isUstadz(Record user) {
        return Integer.parseInt(user.get("is_ustadz").toString()) == 1;
    }

    public static boolean isAdmin(Record user) {
        return Integer.parseInt(user.get("role").toString()) == 1;
    }
}
