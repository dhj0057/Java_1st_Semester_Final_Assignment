import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;

public class KioskFrame extends JFrame {
    private final List<Product> menu = new ArrayList<>();
    private final Order order = new Order();
    private final DefaultListModel<String> orderListModel = new DefaultListModel<>();

    private final JList<String> orderList = new JList<>(orderListModel);
    private final JTextArea receiptArea = new JTextArea();
    private final JTextField customerNameField = new JTextField("홍길동");
    private final JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
    private final JLabel totalLabel = new JLabel("결제 금액: 0원");

    public KioskFrame() {
        setTitle("카페 키오스크 주문 관리 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(920, 620);
        setMinimumSize(new Dimension(820, 560));
        setLocationRelativeTo(null);

        initializeMenu();
        buildLayout();
    }

    private void initializeMenu() {
        menu.add(new Coffee("아메리카노", 3500, false));
        menu.add(new Coffee("카페라떼", 4500, false));
        menu.add(new Coffee("디카페인 콜드브루", 5000, true));
        menu.add(new Dessert("치즈케이크", 6200, true));
        menu.add(new Dessert("초코 머핀", 3800, false));
        menu.add(new Dessert("허니 브레드", 5900, false));
    }

    private void buildLayout() {
        JPanel root = new JPanel(new BorderLayout(16, 16));
        root.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        root.setBackground(new Color(245, 247, 250));

        root.add(createHeaderPanel(), BorderLayout.NORTH);
        root.add(createMenuPanel(), BorderLayout.CENTER);
        root.add(createOrderPanel(), BorderLayout.EAST);

        add(root);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout(12, 8));
        panel.setOpaque(false);

        JLabel title = new JLabel("카페 키오스크");
        title.setFont(new Font("Dialog", Font.BOLD, 28));

        JLabel info = new JLabel("2만원 이상 주문 시 10% 할인 적용");
        info.setFont(new Font("Dialog", Font.PLAIN, 14));

        JPanel customerPanel = new JPanel(new BorderLayout(8, 0));
        customerPanel.setOpaque(false);
        customerPanel.add(new JLabel("고객명"), BorderLayout.WEST);
        customerPanel.add(customerNameField, BorderLayout.CENTER);
        customerPanel.setPreferredSize(new Dimension(220, 32));

        panel.add(title, BorderLayout.WEST);
        panel.add(info, BorderLayout.CENTER);
        panel.add(customerPanel, BorderLayout.EAST);
        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 12, 12));
        panel.setOpaque(false);

        for (Product product : menu) {
            panel.add(createProductButton(product));
        }

        JPanel wrapper = new JPanel(new BorderLayout(0, 10));
        wrapper.setOpaque(false);
        JLabel label = new JLabel("메뉴 선택");
        label.setFont(new Font("Dialog", Font.BOLD, 18));
        wrapper.add(label, BorderLayout.NORTH);
        wrapper.add(panel, BorderLayout.CENTER);
        return wrapper;
    }

    private JButton createProductButton(Product product) {
        JButton button = new JButton("<html><b>" + product.getDisplayText()
                + "</b><br>" + product.getDescription() + "</html>");
        button.setHorizontalAlignment(JButton.LEFT);
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 216, 224)),
                BorderFactory.createEmptyBorder(12, 14, 12, 14)
        ));
        button.addActionListener(event -> addProductToOrder(product));
        return button;
    }

    private JPanel createOrderPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setPreferredSize(new Dimension(330, 0));
        panel.setOpaque(false);

        JLabel label = new JLabel("주문 내역");
        label.setFont(new Font("Dialog", Font.BOLD, 18));

        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane orderScroll = new JScrollPane(orderList);
        orderScroll.setBorder(BorderFactory.createLineBorder(new Color(210, 216, 224)));

        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane receiptScroll = new JScrollPane(receiptArea);
        receiptScroll.setPreferredSize(new Dimension(330, 170));

        JPanel controlPanel = createControlPanel();
        JPanel bottomPanel = new JPanel(new BorderLayout(0, 10));
        bottomPanel.setOpaque(false);
        bottomPanel.add(receiptScroll, BorderLayout.CENTER);
        bottomPanel.add(controlPanel, BorderLayout.SOUTH);

        panel.add(label, BorderLayout.NORTH);
        panel.add(orderScroll, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 8, 8));
        panel.setOpaque(false);

        JPanel quantityPanel = new JPanel(new BorderLayout(8, 0));
        quantityPanel.setOpaque(false);
        quantityPanel.add(new JLabel("수량"), BorderLayout.WEST);
        quantityPanel.add(quantitySpinner, BorderLayout.CENTER);

        JButton receiptButton = new JButton("결제하기");
        receiptButton.addActionListener(event -> printReceipt());

        JButton clearButton = new JButton("주문 초기화");
        clearButton.addActionListener(event -> clearOrder());

        totalLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        totalLabel.setHorizontalAlignment(JLabel.RIGHT);

        panel.add(quantityPanel);
        panel.add(totalLabel);
        panel.add(receiptButton);
        panel.add(clearButton);
        return panel;
    }

    private void addProductToOrder(Product product) {
        int quantity = (Integer) quantitySpinner.getValue();
        order.addProduct(product, quantity);
        refreshOrderList();
    }

    private void refreshOrderList() {
        orderListModel.clear();
        for (OrderItem item : order.getItems()) {
            orderListModel.addElement(item.toReceiptLine());
        }
        totalLabel.setText("결제 금액: " + String.format("%,d원", order.getPaymentPrice()));
    }

    private void printReceipt() {
        if (order.isEmpty()) {
            JOptionPane.showMessageDialog(this, "메뉴를 먼저 선택하세요.");
            return;
        }

        String customerName = customerNameField.getText().trim();
        if (customerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "고객명을 입력하세요.");
            return;
        }

        receiptArea.setText(order.createReceipt(customerName));
    }

    private void clearOrder() {
        order.clear();
        receiptArea.setText("");
        refreshOrderList();
    }
}
