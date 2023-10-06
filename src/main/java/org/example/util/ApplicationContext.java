package org.example.util;


import org.example.menu.CustomerMenu;
import org.example.menu.CustomerOperation;
import org.example.menu.PublicMenu;
import org.example.repository.*;
import org.example.repository.impl.*;
import org.example.service.*;
import org.example.service.impl.*;

@SuppressWarnings("unused")
public class ApplicationContext {

    private static final ApplicationContext applicationContext = new ApplicationContext();

    private OrderRepository orderRepository;

    private CustomerRepository customerRepository;

    private ProductRepository productRepository;

    private OrderService orderService;

    private CustomerService customerService;

    private ProductService productService;
    private OrderProductRepository orderProductRepository;
    private OrderProductService orderProductService;
    private TypeRepository typeRepository;
    private TypeService typeService;
    private ReceiptSheetRepository receiptSheetRepository;
    private ReceiptSheetService receiptSheetService;
    private PublicMenu publicMenu;
    private CustomerMenu customerMenu;
    private CustomerOperation customerOperation;


    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        return applicationContext;
    }

    public CustomerRepository getCustomerRepository() {
        if (customerRepository == null) {
            customerRepository = new CustomerRepositoryImpl(
                    DataSource.getConnection()
            );
        }
        return customerRepository;
    }

    public CustomerService getCustomerService() {
        if (customerService == null) {
            customerService = new CustomerServiceImpl(
                    getCustomerRepository()
            );
        }
        return customerService;
    }

    public OrderRepository getOrderRepository() {
        if (orderRepository == null) {
            orderRepository = new OrderRepositoryImpl(
                    DataSource.getConnection()
            );
        }
        return orderRepository;
    }

    public OrderService getOrderService() {
        if (orderService == null) {
            orderService = new OrderServiceImpl(
                    getOrderRepository()
            );
        }
        return orderService;
    }

    public ProductRepository getProductRepository() {
        if (productRepository == null) {
            productRepository = new ProductRepositoryImpl(
                    DataSource.getConnection()
            );
        }
        return productRepository;
    }

    public ProductService getProductService() {
        if (productService == null) {
            productService = new ProductServiceImpl(
                    getProductRepository()

            );
        }
        return productService;
    }
    public OrderProductRepository getOrderProductRepository() {
        if (orderProductRepository == null) {
                orderProductRepository = new OrderProductRepositoryImpl(
                    DataSource.getConnection()
            );
        }
        return orderProductRepository;
    }

    public OrderProductService getOrderProductService() {
        if (orderProductService == null) {
            orderProductService = new OrderProductServiceImpl(
                    getOrderProductRepository()

            );
        }
        return orderProductService;
    }
    public TypeRepository getTypeRepository() {
        if (typeRepository == null) {
            typeRepository = new TypeRepositoryImpl(
                    DataSource.getConnection()
            );
        }
        return typeRepository;
    }

    public TypeService getTypeService() {
        if (typeService == null) {
            typeService = new TypeServiceImpl(
                    getTypeRepository()

            );
        }
        return typeService;
    }
    public ReceiptSheetRepository getReceiptSheetRepository() {
        if (receiptSheetRepository == null) {
            receiptSheetRepository = new ReceiptSheetRepositoryImpl(
                    DataSource.getConnection()
            );
        }
        return receiptSheetRepository;
    }

    public ReceiptSheetService getReceiptSheetService() {
        if (receiptSheetService == null) {
            receiptSheetService = new ReceiptSheetServiceImpl(
                    getReceiptSheetRepository()

            );
        }
        return receiptSheetService;
    }
    public PublicMenu getPublicMenu() {
        if (publicMenu == null) {
            publicMenu = new PublicMenu();
        }
        return publicMenu;
    }
    public CustomerMenu getCustomerMenu() {
        if (customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }
    public CustomerOperation getCustomerOperation() {
        if (customerOperation == null) {
            customerOperation = new CustomerOperation();
        }
        return customerOperation;
    }


}
