import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final List<OrderItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        for (OrderItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                item.addQuantity(quantity);
                return;
            }
        }
        items.add(new OrderItem(product, quantity));
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public int getTotalPrice() {
        int total = 0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public int getDiscount() {
        int total = getTotalPrice();
        if (total >= 20000) {
            return (int) (total * 0.1);
        }
        return 0;
    }

    public int getPaymentPrice() {
        return getTotalPrice() - getDiscount();
    }

    public String createReceipt(String customerName) {
        StringBuilder builder = new StringBuilder();
        builder.append("========== 주문 영수증 ==========\n");
        builder.append("고객명: ").append(customerName).append("\n\n");

        for (OrderItem item : items) {
            builder.append(item.toReceiptLine()).append("\n");
        }

        builder.append("\n총 주문 금액: ").append(String.format("%,d원", getTotalPrice())).append("\n");
        builder.append("할인 금액   : ").append(String.format("%,d원", getDiscount())).append("\n");
        builder.append("결제 금액   : ").append(String.format("%,d원", getPaymentPrice())).append("\n");
        builder.append("=================================");
        return builder.toString();
    }
}
