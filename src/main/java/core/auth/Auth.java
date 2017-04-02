package core.auth;

import com.google.common.hash.Hashing;
import database.DB;
import database.models.User;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.nio.charset.Charset;

import static org.jooq.impl.DSL.*;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class Auth {

    private static boolean is_login = false;
    private static Record user;
    private static DSLContext db = DSL.using(DB.mysql_conn, SQLDialect.MARIADB);

    public static boolean attemps (String username, String password)
    {
        String hashedPassword;
        hashedPassword = Hashing.sha256().newHasher()
                .putString(password, Charset.defaultCharset())
                .hash().toString();

        Result<Record> result = db.select()
                .from(table("users"))
                .where(field("username").equal(username))
                .and(field("password").equal(hashedPassword))
                .fetch();

        if (result.size() > 0) {
            user = result.get(0);
            is_login = true;

            synchronizeLastLogin();
            return true;
        }

        return false;
    }

    private static void synchronizeLastLogin() {
        Record lastLogin = db.select().from(table("last_login")).fetchOne();
        if (lastLogin == null) {
            db.insertInto(table("last_login"))
                    .set(field("value"), user.get("id"))
                    .execute();
        } else {
            db.update(table("last_login"))
                    .set(field("value"), user.get("id"))
                    .execute();
        }
    }

    public static boolean loginByUserRecord (Record user)
    {
        if (user != null && user.get("username") != null) {
            Auth.user = user;
            is_login = true;

            synchronizeLastLogin();
            return true;
        }

        return false;
    }

    public static void logout ()
    {
        is_login = false;
        user = null;

        db.deleteFrom(table("last_login")).execute();
    }

    public static boolean isLogin ()
    {
        return is_login;
    }

    public static boolean isAdmin () {
        if (is_login) {
            return Integer.parseInt(user.get("role").toString()) == 1;
        }
        return false;
    }

    public static boolean isUstadz () {
        if (is_login) {
            return Integer.parseInt(user.get("is_ustadz").toString()) == 1;
        }

        return false;
    }

    public static Record getUser ()
    {
        return user;
    }

    public static int getUserId() {
        return Integer.parseInt(getUser().get("id").toString());
    }

    public static void checkPreviousLogin() {
        Record lastLogin = db.select().from(table("last_login")).fetchOne();

        if (lastLogin != null) {
            Record lastUser = User.getUserById(Integer.parseInt(lastLogin.get("value").toString()));
            loginByUserRecord(lastUser);
        }
    }
}
