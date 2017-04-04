package database.models;

import app.Main;
import database.DB;
import database.Synchronization;
import org.joda.time.Instant;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.Time;
import java.sql.Timestamp;

import static org.jooq.impl.DSL.table;
import static org.jooq.impl.DSL.*;

/**
 * Created by ibnujakaria on 04/04/17.
 */
public class Model {

    protected static DSLContext db = DSL.using(DB.mysql_conn, SQLDialect.MARIADB);
    protected static DSLContext db_secondary= DSL.using(DB.conn, SQLDialect.SQLITE);

    protected static void synchronizeWithServer (String table) {
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

        Timestamp serverTimestamp = (Timestamp) last_server_transaction_insert.get("last_executed");
        Instant serverTimeInstant = new Instant(serverTimestamp.getTime());

        if (last_server_transaction_insert != null) {
            System.out.println(table + " nggak sinkron dengan server! Ada row baru");
            synchronizeInsert(table,
                    last_local_transaction_insert != null ?
                            (String) last_local_transaction_insert.get("last_executed") : null);
        }
    }

    private static void synchronizeInsert (String table, String last_local_executed) {
        SelectJoinStep newUsersFromServerJoinStep = db.select().from(table(table));

        Result<Record> newUsersFromServer;

        if (last_local_executed != null) {
            newUsersFromServer = newUsersFromServerJoinStep
                    .where(field("created_at").greaterThan(last_local_executed)).fetch();
        } else {
            newUsersFromServer = newUsersFromServerJoinStep.fetch();
        }

        System.out.println("insert this data:");
        System.out.println(newUsersFromServer);
        for (Record user : newUsersFromServer) {
            User.createUsersWithIdLocal(
                    ((Integer) user.get("id")) + "",
                    (String) user.get("name"),
                    (String) user.get("username"),
                    (String) user.get("email"),
                    (String) user.get("address"),
                    User.isUstadz(user),
                    User.isAdmin(user)
            );
        }

        Synchronization.upadateLastTransactionLocal(table, "insert");
    }

    private static void synchronizeUpdate (String table) {

    }
}
