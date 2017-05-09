package database.models.jooq;

/**
 * Created by ibnujakaria on 04/04/17.
 */
public interface SyncronizationInterface {

    public abstract void insertNewRows();
    public abstract void updateRowsFromServer();
}
