public abstract class Product {
    private final String name;
    private final int price;
    private final String category;

    public Product(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public abstract String getDescription();

    public String getDisplayText() {
        return String.format("[%s] %s - %,d원", category, name, price);
    }
}
