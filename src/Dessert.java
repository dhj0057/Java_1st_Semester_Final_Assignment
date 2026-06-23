public class Dessert extends Product {
    private final boolean refrigerated;

    public Dessert(String name, int price, boolean refrigerated) {
        super(name, price, "디저트");
        this.refrigerated = refrigerated;
    }

    public boolean isRefrigerated() {
        return refrigerated;
    }

    @Override
    public String getDescription() {
        if (refrigerated) {
            return "신선도를 위해 냉장 보관하는 디저트";
        }
        return "음료와 함께 먹기 좋은 베이커리 디저트";
    }
}
