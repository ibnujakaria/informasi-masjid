import database.DB;
import database.schemas.UserTableSchema;

/**
 * Created by ibnujakaria on 3/10/17.
 */
public class Main {

    public static void main(String[] args) {
        DB.start();
        System.out.println("Hello gradle!");
    }
}