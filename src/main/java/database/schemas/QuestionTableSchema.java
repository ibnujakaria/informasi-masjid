package database.schemas;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class QuestionTableSchema extends Schema {
    @Override
    protected int getVersion() {
        return 1;
    }

    @Override
    protected void prepare() {
        UP_QUERIES[0] = "create table questions (id integer primary key autoincrement, " +
                "user_id integer, ustadz_id integer default 0, title varchar(200), " +
                "description longtext null, answer longtext null, is_anonim integer default 0, " +
                "created_at text, updated_at text)";
    }
}
