package org.example.menu;


import org.example.domain.Customer;
import org.example.service.CustomerService;
import org.example.util.ApplicationContext;
import org.example.util.Validation;

import java.sql.SQLException;
import java.util.Scanner;

public class CustomerMenu {
    private final CustomerService customerService = ApplicationContext.getInstance().getCustomerService();
//    PublicMenu publicMenu = ApplicationContext.getInstance().getPublicMenu();
//    CustomerOperation customerOperation = ApplicationContext.getInstance().getCustomerOperation();
    private final Scanner input = new Scanner(System.in);


    public void registerCustomer() {
        System.out.println("Enter your name:");
        String name = input.nextLine();

        System.out.println("Enter your userName:");
        String userName = input.nextLine();
        System.out.println("Enter your password:");
        String password = null;
        boolean flg = true;
        while (flg) {
            password = input.nextLine();
            if (Validation.isValidPasswordWithRegex(password))
                flg = false;
            else
                System.out.println("please enter a valid password");
        }
        System.out.println("Enter your address:");
        String address = input.nextLine();

        Customer customer = new Customer(name,userName,password,address);
        customerService.save(customer);
        System.out.println("you successfully added !");
        new PublicMenu().publicMenu();
    }

    public void signIn() {
        System.out.println("Enter your username:");
        String username = input.nextLine();

        System.out.println("Enter your password:");
        String password = input.nextLine();

        if (customerService.login(username,password)) {
            Customer customer = customerService.findByUsername(username);
            System.out.println("you successfully logged in ");
            CustomerOperation customerOperation = new CustomerOperation(customer);
            customerOperation.customerOperation();

        } else {
            System.out.println("you enter a username and password incorrect");
            new PublicMenu().publicMenu();

        }
    }
}
