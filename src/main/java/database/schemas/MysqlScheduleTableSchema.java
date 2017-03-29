package database.schemas;

/**
 * Created by ibnujakaria on 19/03/17.
 */
public class MysqlScheduleTableSchema extends MysqlSchema {
    @Override
    protected int getVersion() {
        return 5;
    }

    @Override
    protected void prepare() {
        UP_QUERIES[0] = "create table schedules (" +
                "id int primary key auto_increment," +
                "title varchar(200) not null," +
                "ustadz_id int default 0," +
                "ustadz varchar(200) null," +
                "description varchar(3000) null," +
                "periodic varchar(200)," + // once, weekly, monthly, monthly, yearly,
                "interval int default 0," + // ist not ngaruh for daily and once
                "created_at datetime," +
                "updated_at datetime" +
                ")";

        UP_QUERIES[1] = "alter table schedules add column interval_by varchar(100) null";

        UP_QUERIES[2] = "alter table schedules add column sub_interval_by varchar(100) null";

        UP_QUERIES[3] = "alter table schedules add column start_at varchar (100) null";

        UP_QUERIES[4] = "alter table schedules add column exact_date varchar (100) null";
    }
}
