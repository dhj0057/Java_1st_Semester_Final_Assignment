# 카페 키오스크 주문 관리 프로그램

학과명: __________  
학년: __________  
성명: __________

## 주제

카페 키오스크 주문 관리 프로그램

## 주제 선택 배경

카페 키오스크는 일상생활에서 쉽게 접할 수 있는 주문 시스템이다. 사용자는 메뉴를 선택하고 수량을 입력한 뒤 주문 내역과 결제 금액을 확인한다. 이 과정은 상품, 주문 항목, 주문 관리 객체로 나누어 설계하기 좋기 때문에 Java 객체지향프로그래밍 개념을 표현하기에 적합하다고 판단했다.

## 사용된 주요 기술 및 설명

이 프로그램은 Java Swing을 사용하여 GUI 화면을 구성했다. 사용자는 버튼으로 메뉴를 선택하고, 수량 입력 스피너를 통해 주문 수량을 지정할 수 있다. 주문 금액이 20,000원 이상이면 10% 할인이 적용되며, 결제하기 버튼을 누르면 영수증이 출력된다.

객체지향 개념으로는 클래스, 생성자, 캡슐화, 상속, 오버라이딩, 다형성을 사용했다. `Product`는 공통 상품 정보를 가진 추상 클래스이고, `Coffee`와 `Dessert`는 `Product`를 상속받아 각각의 설명 메서드를 오버라이딩한다. `KioskFrame`에서는 `List<Product>`에 커피와 디저트 객체를 함께 저장하여 부모 타입으로 자식 객체를 처리하는 다형성을 사용했다.

## 처리 과정에 사용된 주요 소스 코드와 설명

### 1. 상품 부모 클래스

```java
public abstract class Product {
    private final String name;
    private final int price;
    private final String category;

    public Product(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public abstract String getDescription();
}
```

`Product` 클래스는 모든 상품이 공통으로 가지는 이름, 가격, 분류 정보를 관리한다. 필드를 `private`으로 선언하여 캡슐화를 적용했고, 생성자를 통해 객체 생성 시 초기값을 설정한다.

### 2. 상속과 오버라이딩

```java
public class Coffee extends Product {
    private final boolean decaf;

    public Coffee(String name, int price, boolean decaf) {
        super(name, price, "커피");
        this.decaf = decaf;
    }

    @Override
    public String getDescription() {
        if (decaf) {
            return "카페인을 줄인 부드러운 커피 메뉴";
        }
        return "진한 향과 풍미가 있는 커피 메뉴";
    }
}
```

`Coffee` 클래스는 `Product`를 상속받는다. 부모 생성자인 `super()`를 호출하여 공통 정보를 저장하고, `getDescription()` 메서드를 커피 메뉴에 맞게 재정의했다.

### 3. 주문 처리

```java
public void addProduct(Product product, int quantity) {
    for (OrderItem item : items) {
        if (item.getProduct().getName().equals(product.getName())) {
            item.addQuantity(quantity);
            return;
        }
    }
    items.add(new OrderItem(product, quantity));
}
```

`Order` 클래스는 주문 항목을 관리한다. 이미 선택한 상품이면 수량을 추가하고, 새로운 상품이면 주문 항목을 새로 생성한다. 매개변수 타입을 `Product`로 선언했기 때문에 `Coffee`, `Dessert` 객체를 모두 처리할 수 있다.

## UI 전체 캡처화면

아래 위치에 프로그램 실행 화면 캡처를 삽입한다.

- 프로그램 첫 화면
- 메뉴 선택 후 주문 내역 화면
- 결제하기 버튼 클릭 후 영수증 출력 화면

## 입력

- 고객명 입력
- 수량 선택
- 메뉴 버튼 클릭

## 출력

- 주문한 상품 목록
- 총 주문 금액
- 할인 금액
- 최종 결제 금액
- 영수증

## Github 주소

Repository 주소: ______________________________
