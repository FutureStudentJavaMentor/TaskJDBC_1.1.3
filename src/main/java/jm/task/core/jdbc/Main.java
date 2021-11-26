package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Alex", "Pierson", (byte) 25);
        System.out.println("User with name " + userService.getAllUsers().get(0).getName() + " add in database");

        userService.saveUser("Micky", "Freaky", (byte) 35);
        System.out.println("User with name " + userService.getAllUsers().get(1).getName() + " add in database");

        userService.saveUser("Iceboats", "Grain", (byte) 39);
        System.out.println("User with name " + userService.getAllUsers().get(2).getName() + " add in database");

        userService.saveUser("Fillip", "Plain", (byte) 44);
        System.out.println("User with name " + userService.getAllUsers().get(3).getName() + " add in database");

        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
