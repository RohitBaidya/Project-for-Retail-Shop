import java.util.*;

public class RetailShopSimple {

   static class Product {
        int id;
        String name;
        double price;

        Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public String toString() {
            return id + ". " + name + " - $" + price;
        }
    }

    static class User {
        String name;
        List<Product> cart = new ArrayList<>();

        User(String name) {
            this.name = name;
        }

        void addToCart(Product p) {
            cart.add(p);
            System.out.println(p.name + " added to cart.");
        }

        void viewCart() {
            if (cart.isEmpty()) {
                System.out.println("Cart is empty.");
            } else {
                for (Product p : cart) {
                    System.out.println(p);
                }
            }
        }

        double checkout() {
            double total = 0;
            for (Product p : cart) {
                total += p.price;
            }
            cart.clear();
            return total;
        }
    }

  
    static List<Product> products = Arrays.asList(
        new Product(1, "Laptop", 750.00),
        new Product(2, "Shoes", 49.99),
        new Product(3, "Phone", 500.00)
    );

     static Product getProductById(int id) {
        for (Product p : products) {
            if (p.id == id) return p;
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User user = new User("John");

        while (true) {
            System.out.println("\nWelcome, " + user.name + "!");
            System.out.println("1. Show Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (Product p : products) {
                        System.out.println(p);
                    }
                    break;
                case 2:
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    Product p = getProductById(id);
                    if (p != null) user.addToCart(p);
                    else System.out.println("Product not found.");
                    break;
                case 3:
                    user.viewCart();
                    break;
                case 4:
                    double total = user.checkout();
                    System.out.println("Total paid: $" + total);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
