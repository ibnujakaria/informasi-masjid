package app;

import database.DB;
import database.models.User;

import java.util.Scanner;

/**
 * Created by ibnujakaria on 3/16/17.
 */
public class CreateAdmin {

    public static void main(String[] args) {
        DB.start();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama admin: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan email: ");
        String email = scanner.nextLine();
        System.out.print("Masukkan city: ");
        String city = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        User.createUser(name, username, email, city, password, false, true);
        System.out.println("Sekarang wes bisa login!");
    }
}
