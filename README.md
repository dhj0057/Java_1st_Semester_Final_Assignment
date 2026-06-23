# Java 1학기 기말 과제

## 카페 키오스크 주문 관리 프로그램

Java Swing으로 구현한 카페 키오스크 프로그램입니다. 사용자는 고객명과 수량을 입력하고 메뉴 버튼을 눌러 주문을 추가할 수 있습니다. 결제하기 버튼을 누르면 총 주문 금액, 할인 금액, 최종 결제 금액이 포함된 영수증이 출력됩니다.

## 주요 기능

- 커피와 디저트 메뉴 선택
- 수량 입력 및 같은 메뉴 주문 시 수량 합산
- 주문 내역 실시간 표시
- 20,000원 이상 주문 시 10% 할인 적용
- 고객명 포함 영수증 출력
- 주문 초기화

## 객체지향 프로그래밍 요소

| 개념 | 적용 내용 |
| --- | --- |
| 클래스 | `Product`, `Coffee`, `Dessert`, `OrderItem`, `Order`, `KioskFrame`, `Main` |
| 생성자 | 상품, 주문 항목, UI 객체 생성 시 초기값 설정 |
| 캡슐화 | 필드를 `private`으로 선언하고 getter 메서드로 접근 |
| 상속 | `Coffee`, `Dessert`가 추상 클래스 `Product`를 상속 |
| 오버라이딩 | `Coffee`, `Dessert`가 `getDescription()` 메서드를 각각 재정의 |
| 접근 제한 | 외부에서 직접 수정하면 안 되는 데이터는 `private`으로 보호 |
| 다형성 | `List<Product>`에 `Coffee`, `Dessert` 객체를 함께 저장하고 처리 |
| 컬렉션 | `ArrayList`로 메뉴 목록과 주문 내역 관리 |

## 클래스 구조

```text
src
├── Main.java
├── KioskFrame.java
├── Product.java
├── Coffee.java
├── Dessert.java
├── Order.java
└── OrderItem.java
```

## 실행 방법

### IntelliJ IDEA

1. 프로젝트를 IntelliJ IDEA로 엽니다.
2. `src/Main.java` 파일을 실행합니다.

### 터미널

```bash
javac -encoding UTF-8 -d out/production/Java_project01 src/*.java
java -cp out/production/Java_project01 Main
```

## 입력과 출력

### 입력

- 고객명
- 주문 수량
- 메뉴 선택

### 출력

- 주문 상품 목록
- 총 주문 금액
- 할인 금액
- 최종 결제 금액
- 주문 영수증

## 보고서 참고 파일

`REPORT_DRAFT.md` 파일에는 PDF 보고서 작성에 사용할 수 있는 초안이 포함되어 있습니다. 제출용 PDF에는 프로그램 UI 캡처 화면을 추가하면 됩니다.
