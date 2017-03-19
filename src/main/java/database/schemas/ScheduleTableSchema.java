package database.schemas;

/**
 * Created by ibnujakaria on 19/03/17.
 */
public class ScheduleTableSchema extends Schema {
    @Override
    protected int getVersion() {
        return 1;
    }

    @Override
    protected void prepare() {
        UP_QUERIES[0] = "create table schedules (" +
                "id integer primary key autoincrement," +
                "title varchar (200) not null," +
                "ustadz_id integer default 0," +
                "ustadz varchar (200) null," +
                "description varchar (3000) null," +
                "periodic varchar (200)," + // once, weekly, monthly_by_day, monthly_by_week, yearly,
                "interval integer default 0," + // ist not ngaruh for daily and once
                "created_at text," +
                "updated_at text" +
                ")";
    }
}
