package database.models;

import database.DB;
import database.Synchronization;
import org.joda.time.Instant;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.Timestamp;

import static org.jooq.impl.DSL.table;
import static org.jooq.impl.DSL.*;

/**
 * Created by ibnujakaria on 04/04/17.
 */
public class Model {

    protected static DSLContext db = DSL.using(DB.mysql_conn, SQLDialect.MARIADB);
    protected static DSLContext db_secondary= DSL.using(DB.conn, SQLDialect.SQLITE);
    protected static SelectJoinStep newRowsFromServerJoinStep;
    protected static Result<Record> newRowsFromServer;

    protected static void synchronizeWithServer (String table, SyncronizationInterface callback) {
        Record last_local_transaction_insert = db_secondary.select().from(table("last_transactions"))
                .where(field("table_name").equal(table))
                .and(field("action").equal("insert"))
                .fetchOne();

        SelectConditionStep last_server_transaction_insert_sql = db.select().from(table("last_transactions"))
                .where(field("table_name").equal(table))
                .and(field("action").equal("insert"));

        if (last_local_transaction_insert != null) {
            last_server_transaction_insert_sql = last_server_transaction_insert_sql
                    .and(field("last_executed").greaterThan((String) last_local_transaction_insert.get("last_executed")));
        }

        Record last_server_transaction_insert = last_server_transaction_insert_sql.fetchOne();

        if (last_server_transaction_insert != null) {
            System.out.println(table + " nggak sinkron dengan server! Ada row baru");
            synchronizeInsert(table,
                    last_local_transaction_insert != null ?
                            (String) last_local_transaction_insert.get("last_executed") : null,
                    callback);
        }
    }

    public static void synchronizeInsert (String table, String last_local_executed, SyncronizationInterface callback) {
        newRowsFromServerJoinStep = db.select().from(table(table));

        if (last_local_executed != null) {
            newRowsFromServer = newRowsFromServerJoinStep
                    .where(field("created_at").greaterThan(last_local_executed)).fetch();
        } else {
            newRowsFromServer = newRowsFromServerJoinStep.fetch();
        }

        callback.insertNewRows();
    }

    public static void synchronizeUpdate (String table) {

    }
}
