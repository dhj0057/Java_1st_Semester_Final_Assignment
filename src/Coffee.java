public class Coffee extends Product {
    private final boolean decaf;

    public Coffee(String name, int price, boolean decaf) {
        super(name, price, "커피");
        this.decaf = decaf;
    }

    public boolean isDecaf() {
        return decaf;
    }

    @Override
    public String getDescription() {
        if (decaf) {
            return "카페인을 줄인 부드러운 커피 메뉴";
        }
        return "진한 향과 풍미가 있는 커피 메뉴";
    }
}
