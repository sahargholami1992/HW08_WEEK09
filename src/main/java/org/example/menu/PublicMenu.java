package org.example.menu;


import org.example.util.ApplicationContext;


import java.util.Scanner;

public class PublicMenu {

    private final CustomerMenu customerMenu = ApplicationContext.getInstance().getCustomerMenu();
    private final Scanner input = new Scanner(System.in);
    public void publicMenu() {
        System.out.println("***Welcome***");
        System.out.println("1- Sign in");
        System.out.println("2- Sign up");
        System.out.println("3- Exit");
        System.out.println("Enter your select:");
        int select = input.nextInt();
        input.nextLine();
        switch (select) {
            case 1 -> customerMenu.signIn();
            case 2 -> customerMenu.registerCustomer();
            case 3 -> System.exit(-1);
            default -> System.out.println("dose not work!");
        }
    }

}
