package database.models.jooq;

import core.auth.Auth;
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
    private  static DSLContext db = DSL.using(DB.mysql_conn, SQLDialect.MARIADB);
    private  static DSLContext db_secondary = DSL.using(DB.conn, SQLDialect.SQLITE);

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

    public static Record getQuestionById (int question_id) {
        return db.select().from(table("questions"))
                .where(field("id").equal(question_id))
                .fetchOne();
    }

    public static void answerQuestion (int question_id, String answer) {
        db.update(table("questions"))
                .set(field("ustadz_id"), Auth.getUserId())
                .set(field("answer"), answer)
                .where(field("id").equal(question_id))
                .execute();
    }

    public static Result<Record> getUnAnsweredQuestions() {
        return db.select().from(table("questions"))
                .where(field("answer").isNull())
                .orderBy(field("id").desc())
                .fetch();
    }

    public static Result<Record> getAnsweredQuestion () {
        return db.select().from(table("questions"))
                .where(field("answer").isNotNull())
                .orderBy(field("id").desc())
                .fetch();
    }

    public static boolean isAnonim (Record question) {
        return question.get("is_anonim").equals(1);
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

    public static Record getUstadzWhoAnswer (Record question) {
        return db.select().from("users")
                .where(field("id").equal(Integer.parseInt(question.get("ustadz_id").toString())))
                .and(field("is_ustadz").equal(1))
                .fetchOne();
    }
}
