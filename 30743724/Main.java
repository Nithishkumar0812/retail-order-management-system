package com.retail;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManagement productManagement = new ProductManagement();
        OrderManagement orderManagement = new OrderManagement();
        CustomerManagement customerManagement = new CustomerManagement();

        while (true) {
            System.out.println("===== Online Retail Order Management System =====");
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Orders");
            System.out.println("3. Manage Customers");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manageProducts(productManagement);
                    break;
                case 2:
                    manageOrders(orderManagement);
                    break;
                case 3:
                    manageCustomers(customerManagement);
                    break;
                case 4:
                    System.out.println("Exiting the application!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageProducts(ProductManagement productManagement) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Product Management =====");
            System.out.println("1. Add New Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Remove Product");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    productManagement.add();
                    break;
                case 2:
                    productManagement.viewAll();
                    break;
                case 3:
                    System.out.print("Enter the product ID to update: ");
                    int productId = scanner.nextInt();
                    productManagement.update(productId);
                    break;
                case 4:
                    System.out.print("Enter the product ID to remove: ");
                    int removeProductId = scanner.nextInt();
                    productManagement.remove(removeProductId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageOrders(OrderManagement orderManagement) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Order Management =====");
            System.out.println("1. Place New Order");
            System.out.println("2. View Orders");
            System.out.println("3. Update Order Status");
            System.out.println("4. Cancel Order");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    orderManagement.add();
                    break;
                case 2:
                    orderManagement.viewAll();
                    break;
                case 3:
                    System.out.print("Enter the order ID to update status: ");
                    int orderId = scanner.nextInt();
                    orderManagement.update(orderId);
                    break;
                case 4:
                    System.out.print("Enter the order ID to cancel: ");
                    int cancelOrderId = scanner.nextInt();
                    orderManagement.remove(cancelOrderId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageCustomers(CustomerManagement customerManagement) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Customer Management =====");
            System.out.println("1. Add New Customer");
            System.out.println("2. View Customer Details");
            System.out.println("3. Update Customer");
            System.out.println("4. Remove Customer");
            System.out.println("5. View customer ID");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    customerManagement.addCustomer();
                    break;
                case 2:
                    customerManagement.viewCustomer();
                    break;
                case 3:
                    customerManagement.updateCustomer();
                    break;
                case 4:
                    customerManagement.removeCustomer();
                    break;
                case 5:
                    customerManagement.viewID();
                    break;
                case 6:
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
