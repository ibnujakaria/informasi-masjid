package database.models;

import database.DB;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.jooq.*;
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

    public static Result<Record> getQuestionsOfUserId (int user_id) {
        Result<Record> results = db.select().from(table("questions"))
                .where(field("user_id").equal(user_id))
                .orderBy(field("id").desc())
                .fetch();

        for (Record r : results) {
            System.out.println(r.get("title"));
        }

        return results;
    }

    public static Result<Record> getUnAnsweredQuestions() {
        return db.select().from(table("questions"))
                .where(field("answer").isNull())
                .orderBy(field("id").desc())
                .fetch();
    }


    public static boolean isUnAnswered(Record question) {
        return question.get("answer") == null;
    }

    public static boolean isAnswered(Record question) {
        return !isUnAnswered(question);
    }

    public static Record getUser(Record question) {
        return User.getUserById(Integer.parseInt(question.get("user_id").toString()));
    }
}
