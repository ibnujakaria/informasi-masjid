package database.schemas;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class MysqlQuestionTableSchema extends MysqlSchema {
    @Override
    protected int getVersion() {
        return 1;
    }

    @Override
    protected void prepare() {
        UP_QUERIES[0] = "CREATE TABLE questions (id int PRIMARY KEY AUTO_INCREMENT, user_id int, " +
                "ustadz_id int DEFAULT 0, title varchar(200), description longtext null, answer longtext null, " +
                "is_anonim int default 0, created_at datetime, updated_at datetime)";
    }
}
