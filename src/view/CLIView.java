package view;

import controllers.UserController;
import controllers.OrderController;
import models.Pizza;
import models.User;
import models.Order;
import patterns.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CLIView provides a simple Command Line Interface for the system.
 */
public class CLIView {
    private OrderController controller = new OrderController();
    private UserController userController = new UserController();
    private int orderCounter = 1; // To generate unique order IDs

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Pizza Ordering System!");
        System.out.println("1. Customize and Order Pizza");
        System.out.println("2. Save Pizza to Favorites");
        System.out.println("3. View Favorites and Reorder");
        System.out.println("4. Redeem Loyalty Points");
        System.out.println("5. Track Order");
        System.out.println("6. View All Orders");
        System.out.println("7. View Highly-Rated Pizza Combinations");
        System.out.println("8. Add Feedback");
        System.out.println("9. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                placeOrder(scanner);
                Order order = null;
                Command placeOrderCommand = new PlaceOrderCommand(controller, order);
                placeOrderCommand.execute();
                break;
            case 2:
                saveToFavorites(scanner);
                break;
            case 3:
                reorderFavorite(scanner);
                break;
            case 4:
                redeemLoyaltyPoints(scanner);
                break;
            case 5:
                trackOrder(scanner);
                break;
            case 6:
                viewOrderDetails(scanner);
                break;
            case 7:
                viewHighlyRatedPizzas();
                break;
            case 8:
                addFeedback(scanner);
                break;
            case 9:
                System.out.println("Goodbye!");
                System.exit(0);
        }

        showMenu();
    }

    private void viewOrderDetails(Scanner scanner) {
        System.out.println("Enter the Order ID to view details:");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Retrieve the order by ID
        Order order = controller.getOrderById(orderId);
        if (order == null) { // Check if order is null
            System.out.println("Order not found!");
            return;
        }

        // Display detailed order information
        System.out.println("Order Details:");
        System.out.println(formatOrder(order));
    }

    private void addFeedback(Scanner scanner) {
        System.out.println("Enter the Order ID to provide feedback:");
        int orderId;

        // Validate input to ensure it is an integer
        while (true) {
            if (scanner.hasNextInt()) {
                orderId = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid Order ID:");
                scanner.next(); // Clear the invalid input
            }
        }

        // Retrieve the order by ID
        Order order = controller.getOrderById(orderId);
        if (order == null) {
            System.out.println("Order not found!");
            return;
        }

        System.out.println("Order Details:");
        System.out.println(formatOrder(order));

        System.out.println("Please provide your feedback:");
        String feedback = scanner.nextLine();
        order.setFeedback(feedback);

        System.out.println("Please rate your order (1 to 5):");
        int rating;
        while (true) {
            if (scanner.hasNextInt()) {
                rating = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                if (rating >= 1 && rating <= 5) {
                    break;
                } else {
                    System.out.println("Invalid rating. Please enter a value between 1 and 5:");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 5:");
                scanner.next(); // Clear the invalid input
            }
        }
        order.setRating(rating);

        System.out.println("Thank you for your feedback!");
    }


    private void viewHighlyRatedPizzas() {
        List<Pizza> popularPizzas = controller.getHighlyRatedCombinations();
        if (popularPizzas.isEmpty()) {
            System.out.println("No highly-rated combinations found.");
        } else {
            System.out.println("Highly-Rated Pizza Combinations:");
            for (Pizza pizza : popularPizzas) {
                System.out.println(pizza.getDescription());
            }
        }
    }

    private void placeOrder(Scanner scanner) {
        System.out.println("Enter your name for the order:");
        String customerName = scanner.nextLine();

        User user = userController.getOrCreateUser(customerName);

        List<Pizza> pizzas = new ArrayList<>();
        double totalPrice = 0.0;

        while (true) {
            System.out.println("Customizing your pizza...");

            Pizza pizza = new Pizza();
            // Customize crust, sauce, and toppings using handlers
            CustomizationHandler crustHandler = new CrustHandler();
            CustomizationHandler sauceHandler = new SauceHandler();
            CustomizationHandler toppingsHandler = new ToppingsHandler();
            crustHandler.setNextHandler(sauceHandler);
            sauceHandler.setNextHandler(toppingsHandler);
            crustHandler.handle(pizza);

            System.out.println("Choose size (Small, Medium, Large):");
            String size = scanner.nextLine();
            pizza.setSize(size);

            System.out.println("Would you like to add extra cheese? (yes/no):");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                pizza = new ExtraCheeseDecorator(pizza);
            }

            System.out.println("Would you like premium packaging? (yes/no):");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                pizza = new PremiumPackagingDecorator(pizza);
            }

            System.out.println("Pizza description: " + pizza.getDescription());
            double price = pizza.getPrice();
            System.out.println("Pizza price: Rs." + price);

            System.out.println("Enter the quantity for this pizza:");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            totalPrice += price * quantity;

            pizzas.add(pizza);

            System.out.println("Do you want to add another pizza? (yes/no):");
            if (scanner.nextLine().equalsIgnoreCase("no")) {
                break;
            }
        }

        System.out.println("Do you want this order for Pickup or Delivery? (pickup/delivery):");
        String orderType = scanner.nextLine();

        int estimatedDeliveryTime = 0;
        if (orderType.equalsIgnoreCase("delivery")) {
            System.out.println("Enter the delivery distance in kilometers:");
            double distanceInKm = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            estimatedDeliveryTime = estimateDeliveryTime(distanceInKm);
            System.out.println("Estimated delivery time: " + estimatedDeliveryTime + " minutes");
        }

        Order order = new Order(orderCounter++, customerName, pizzas, "Placed", orderType);
        controller.addOrder(order);

        System.out.println("Order Summary:");
        System.out.println(formatOrder(order));

        System.out.println("Total price: Rs." + totalPrice);
        System.out.println("Choose payment method (1: Credit Card, 2: Digital Wallet):");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        PaymentStrategy paymentMethod;
        if (paymentChoice == 1) {
            paymentMethod = new CreditCardPayment();
        } else {
            paymentMethod = new DigitalWalletPayment();
        }

        paymentMethod.pay(totalPrice);

        System.out.println("Order placed successfully:");
        System.out.println(order); // This will now use the overridden toString() method
    }

    private void saveToFavorites(Scanner scanner) {
        System.out.println("Enter your name:");
        String customerName = scanner.nextLine();
        User user = userController.getOrCreateUser(customerName);

        Pizza pizza = customizePizza(scanner);
        user.addFavorite(pizza);
    }

    private void reorderFavorite(Scanner scanner) {
        System.out.println("Enter your name:");
        String customerName = scanner.nextLine();
        User user = userController.getUser(customerName);

        if (user == null || user.getFavorites().isEmpty()) {
            System.out.println("No favorites found for this user.");
            return;
        }

        System.out.println("Your Favorites:");
        List<Pizza> favorites = user.getFavorites();
        for (int i = 0; i < favorites.size(); i++) {
            System.out.println((i + 1) + ". " + favorites.get(i));
        }

        System.out.println("Enter the number of the favorite to reorder:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > favorites.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        Pizza selectedPizza = favorites.get(choice - 1);
        System.out.println("Reordered: " + selectedPizza);
    }

    private Pizza customizePizza(Scanner scanner) {
        PizzaBuilder builder = new PizzaBuilder();

        System.out.println("Choose crust (Thin, Thick, Stuffed):");
        builder.setCrust(scanner.nextLine());

        System.out.println("Choose sauce (Tomato, Barbecue, Pesto):");
        builder.setSauce(scanner.nextLine());

        List<String> toppings = new ArrayList<>();
        while (true) {
            System.out.println("Add a topping (Cheese, Pepperoni, Mushrooms, etc.):");
            toppings.add(scanner.nextLine());

            System.out.println("Do you want to add another topping? (yes/no):");
            if (scanner.nextLine().equalsIgnoreCase("no")) {
                break;
            }
        }
        builder.setToppings(toppings);

        System.out.println("Choose size (Small, Medium, Large):");
        builder.setSize(scanner.nextLine());

        return builder.build();
    }

    /**
     * Estimate delivery time based on distance in kilometers.
     *
     * @param distanceInKm The distance to the delivery location in kilometers.
     * @return The estimated delivery time in minutes.
     */
    private int estimateDeliveryTime(double distanceInKm) {
        int baseTime = 10; // Base preparation time in minutes
        int timePerKm = 2; // Additional time per kilometer
        return baseTime + (int) (distanceInKm * timePerKm);
    }

    /**
     * Format an order for clear appearance.
     *
     * @param order The order to format.
     * @return A string representation of the order.
     */
    private String formatOrder(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(order.getOrderId()).append("\n");
        sb.append("Customer: ").append(order.getCustomerName()).append("\n");
        sb.append("Order Type: ").append(order.getOrderType()).append("\n");
        sb.append("Status: ").append(order.getStatus()).append("\n");
        sb.append("Pizzas: \n");

        int count = 1;
        for (Pizza pizza : order.getPizzas()) {
            sb.append("  Pizza ").append(count++).append(":\n");
            sb.append("    Crust: ").append(pizza.getCrust()).append("\n");
            sb.append("    Sauce: ").append(pizza.getSauce()).append("\n");
            sb.append("    Toppings: ").append(String.join(", ", pizza.getToppings())).append("\n");
            sb.append("    Size: ").append(pizza.getSize()).append("\n");
        }

        sb.append("Feedback: ").append(order.getFeedback()).append("\n");
        sb.append("Rating: ").append(order.getRating()).append("\n");

        return sb.toString();
    }

    private void trackOrder(Scanner scanner) {
        System.out.println("Enter your Order ID:");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Retrieve the order by ID
        Order order = controller.getOrderById(orderId); // Ensure getOrderById is implemented in OrderController
        if (order == null) {
            System.out.println("Order not found!");
        } else {
            System.out.println("Order Details:");
            System.out.println(formatOrder(order));
        }
    }

    private void redeemLoyaltyPoints(Scanner scanner) {
        System.out.println("Enter your name:");
        String customerName = scanner.nextLine();
        User user = userController.getUser(customerName);

        if (user == null) { // Check if the user exists
            System.out.println("User not found!");
            return;
        }

        System.out.println("You have " + user.getLoyaltyPoints() + " points.");
        System.out.println("Enter points to redeem:");
        int pointsToRedeem = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (pointsToRedeem > user.getLoyaltyPoints()) {
            System.out.println("You do not have enough points to redeem.");
        } else {
            user.redeemPoints(pointsToRedeem);
            System.out.println("Redeemed " + pointsToRedeem + " points. Remaining points: " + user.getLoyaltyPoints());
        }
    }


}
