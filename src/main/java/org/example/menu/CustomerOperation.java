package org.example.menu;

import org.example.domain.*;
import org.example.service.*;
import org.example.util.ApplicationContext;


import java.util.List;
import java.util.Scanner;

public class CustomerOperation {
    PublicMenu publicMenu = ApplicationContext.getInstance().getPublicMenu();
    private Customer customer ;
    private final TypeService typeService = ApplicationContext.getInstance().getTypeService();
    private final ProductService productService = ApplicationContext.getInstance().getProductService();
    private final OrderService orderService = ApplicationContext.getInstance().getOrderService();
    private final OrderProductService orderProductService = ApplicationContext.getInstance().getOrderProductService();
    private final ReceiptSheetService receiptSheetService = ApplicationContext.getInstance().getReceiptSheetService();
    private final Scanner input = new Scanner(System.in);

    public CustomerOperation(Customer customer) {
        this.customer = customer;
    }

    public CustomerOperation() {
    }


    public void customerOperation() {
        System.out.println("1-Add product");
        System.out.println("2-Delete product");
        System.out.println("3-Show list");
        System.out.println("4-Show total Price");
        System.out.println("5-Sign out");
        int number = input.nextInt();
        input.nextLine();
        switch (number) {
            case 1 -> typeMenu();
            case 2 -> deleteOfList();
            case 3 -> showReceiptSheet();
            case 4 -> showTotalPrice();
            case 5 -> publicMenu.publicMenu();
            default -> System.out.println("invalid number");
        }
    }
    public void typeMenu() {
        List<Type> typeList = typeService.findAll();
        for (Type type : typeList) {
            System.out.println(type.getId() + "-" + type.getName());
        }
        System.out.println("Choose a category or 0 to quit shopping ");
        int number = input.nextInt();
        input.nextLine();
        if (number == 0) {
            customerOperation();
        }
        showProducts(number);
    }
    public void showProducts(int typeId) {
        List<Product> products = productService.findByType(typeId);
        if (!products.isEmpty()) {
            for (Product product : products) {
                System.out.println("id: " + product.getId() + " >>> " + product.getName() + " >>> " + product.getPrice() + " $ ");
            }
            addProductToReceipt();
        } else {
            System.out.println("No products in this category");
            typeMenu();
        }
    }
    public void deleteOfList() {
        Order order = orderService.findByCustomerAndPaymentStatus("not_payed",customer.getId());
        if (order != null) {
            System.out.println("Product id:");
            int productId = input.nextInt();
            OrderProduct orderProduct = orderProductService.findByOrderAndProductId(order.getId(), productId);
            if (orderProduct != null) {
                orderProductService.deleteByOrderAndProductId(order.getId(), productId);
                System.out.println("Product successfully deleted");
            }
        } else {
            System.out.println("Your cart is empty");
        }
        customerOperation();
    }
    public void showReceiptSheet() {
        List<ReceiptSheet> receiptSheetList = receiptSheetService.showReceiptSheet(customer.getId());
        if (!receiptSheetList.isEmpty()) {
                for (ReceiptSheet receiptSheet : receiptSheetList) {
                System.out.println(receiptSheet.getName() + " - " + receiptSheet.getPrice() + " - " + receiptSheet.getQuantity());
            }
        } else {
            System.out.println("Your receipt sheet is null");
        }
        customerOperation();
    }
    public void addProductToReceipt() {
        System.out.println("Product Id: ");
        int productId = input.nextInt();
        Product product = productService.findById((long) productId);
        if (product != null) {
            int quantity = product.getNumbers();
            System.out.println("Number: ");
            int productNumber = input.nextInt();
            if (productNumber > quantity) {
                System.out.println("we dont have enough product");
                typeMenu();
            } else {
                Order order = orderService.findByCustomerAndPaymentStatus("not_payed", customer.getId());
                if (order != null) {
                    OrderProduct orderProduct = orderProductService.findByOrderAndProductId(order.getId(), productId);
                    if (orderProduct != null) {
                        orderProduct.setQuantity(orderProduct.getQuantity() + productNumber);
                        orderProductService.update(orderProduct);
                    } else {
                        OrderProduct newOrderProduct = new OrderProduct(order, product, productNumber);
                        orderProductService.save(newOrderProduct);
                    }
                } else {
                    order = new Order(customer, java.time.LocalDate.now().toString(), "not_payed");
                    orderService.save(order);
                    OrderProduct orderProduct = new OrderProduct(order, product, productNumber);
                    orderProductService.save(orderProduct);
                }
                product.setNumbers(product.getNumbers() - productNumber);
                productService.update(product);
                System.out.println("product added");
            }
        } else {
            System.out.println("Invalid product id");
        }
        typeMenu();
    }
    public void showTotalPrice() {
        List<ReceiptSheet> receiptSheetList = receiptSheetService.showReceiptSheet(customer.getId());
        double totalPrice = 0;
        for (ReceiptSheet receiptSheet : receiptSheetList) {
            totalPrice += receiptSheet.getQuantity() * receiptSheet.getPrice();
        }
        System.out.println("Total price: " + totalPrice );
        customerOperation();
    }


}
