import java.util.*;

public class RetailShop {

    // Product class
    static class Product {
        private int id;
        private String name;
        private String category;
        private double price;

        public Product(int id, String name, String category, double price) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.price = price;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }

        @Override
        public String toString() {
            return id + ". " + name + " - " + category + " - $" + price;
        }
    }

    // User class
    static class User {
        private String name;
        private List<Product> cart = new ArrayList<>();
        private List<Product> wishlist = new ArrayList<>();

        public User(String name) {
            this.name = name;
        }

        public void addToCart(Product p) {
            cart.add(p);
            System.out.println(p.getName() + " has been added to the cart.");
        }

        public void addToWishlist(Product p) {
            wishlist.add(p);
            System.out.println(p.getName() + " has been added to the wishlist.");
        }

        public void viewCart() {
            System.out.println("🛒 Your Cart:");
            if (cart.isEmpty()) {
                System.out.println("Cart is empty.");
            } else {
                for (Product p : cart) {
                    System.out.println(p);
                }
            }
        }

        public void viewWishlist() {
            System.out.println("💖 Your Wishlist:");
            if (wishlist.isEmpty()) {
                System.out.println("Wishlist is empty.");
            } else {
                for (Product p : wishlist) {
                    System.out.println(p);
                }
            }
        }

        public double checkout() {
            double total = 0;
            for (Product p : cart) {
                total += p.getPrice();
            }
            cart.clear(); // Empty cart after checkout
            return total;
        }

        public String getName() {
            return name;
        }
    }

    // List to store products
    static List<Product> products = new ArrayList<>();

    // Load some sample products
    static void loadProducts() {
        products.add(new Product(1, "Laptop", "Electronics", 750.00));
        products.add(new Product(2, "Shoes", "Fashion", 49.99));
        products.add(new Product(3, "Phone", "Electronics", 500.00));
        products.add(new Product(4, "T-Shirt", "Fashion", 15.00));
        products.add(new Product(5, "Watch", "Accessories", 120.00));
    }

    // Display all products
    static void showAllProducts() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    // Search product by keyword
    static void searchProduct(String keyword) {
        boolean found = false;
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No product found matching your search.");
        }
    }

    // Get product by ID
    static Product getProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User("John");
        loadProducts();

        while (true) {
            System.out.println("\n🏪 Welcome to the Retail Shop, " + user.getName() + "!");
            System.out.println("1. View All Products");
            System.out.println("2. Search Product");
            System.out.println("3. Add to Cart");
            System.out.println("4. Add to Wishlist");
            System.out.println("5. View Cart");
            System.out.println("6. View Wishlist");
            System.out.println("7. Checkout");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            switch (choice) {
                case 1:
                    showAllProducts();
                    break;
                case 2:
                    System.out.print("Enter product name to search: ");
                    String keyword = scanner.nextLine();
                    searchProduct(keyword);
                    break;
                case 3:
                    System.out.print("Enter Product ID to add to cart: ");
                    int cartId = scanner.nextInt();
                    Product cartProduct = getProductById(cartId);
                    if (cartProduct != null) {
                        user.addToCart(cartProduct);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Product ID to add to wishlist: ");
                    int wishId = scanner.nextInt();
                    Product wishProduct = getProductById(wishId);
                    if (wishProduct != null) {
                        user.addToWishlist(wishProduct);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 5:
                    user.viewCart();
                    break;
                case 6:
                    user.viewWishlist();
                    break;
                case 7:
                    double total = user.checkout();
                    System.out.println("✅ Checkout successful! Total amount paid: $" + total);
                    break;
                case 8:
                    System.out.println("👋 Thank you for visiting. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid option. Please try again.");
            }
        }
    }
}
