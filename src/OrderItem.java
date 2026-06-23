public class OrderItem {
    private final Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        quantity += amount;
    }

    public int getSubtotal() {
        return product.getPrice() * quantity;
    }

    public String toReceiptLine() {
        return String.format("%-12s %,6d원 x %d = %,7d원",
                product.getName(), product.getPrice(), quantity, getSubtotal());
    }
}
