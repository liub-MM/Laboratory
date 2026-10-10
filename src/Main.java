import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        Customer c1 = new Customer(1, "Іван Петренко", "ivan@gmail.com");
        Customer c2 = new Customer(2, "Олена Коваленко", "olena@gmail.com");

        Product p1 = new Product(101L, "Ноутбук", ProductCategory.ELECTRONICS, 35000.0);
        Product p2 = new Product(102L, "Мишка", ProductCategory.ELECTRONICS, 1500.0);
        Product p3 = new Product(103L, "Футболка", ProductCategory.CLOTHING, 500.0);
        Product p4 = new Product(104L, "Книга Java", ProductCategory.BOOKS, 800.0);


        List<Product> list1 = new ArrayList<>();
        list1.add(p1);
        list1.add(p2);

        List<Product> list2 = new ArrayList<>();
        list2.add(p4);

        List<Product> list3 = new ArrayList<>();
        list3.add(p3);
        list3.add(p4);

        Order order1 = new Order(1001L, c1, list1, OrderStatus.NEW);
        Order order2 = new Order(1002L, c2, list2, OrderStatus.PROCESSING);
        Order order3 = new Order(1003L, c1, list3, OrderStatus.NEW);


        OrderService service = new OrderService();

        System.out.println(" 1. Додавання замовлень");
        service.addOrder(order1);
        service.addOrder(order2);
        service.addOrder(order3);
        System.out.println("Всі замовлення успішно додані. Всього замовлень: " + service.getOrders().size());
        System.out.println();

        System.out.println("2. Пошук замовлення за ID 1002 (findOrderById)");
        System.out.println(service.findOrderById(1002L));
        System.out.println();

        System.out.println("3. Замовлення клієнта з ID 1 (findOrdersByCustomer)");
        List<Order> ivansOrders = service.findOrdersByCustomer(1L);
        for (Order o : ivansOrders) {
            System.out.println(o);
        }
        System.out.println();

        System.out.println(" 4. Пошук за статусом NEW (findOrdersByStatus)");
        for (Order o : service.findOrdersByStatus(OrderStatus.NEW)) {
            System.out.println("Замовлення ID: " + o.getId());
        }
        System.out.println();

        System.out.println("5. Замовлення, що містять товар 'Книга Java' ID 104 (findOrdersContainingProduct) ");
        for (Order o : service.findOrdersContainingProduct(104L)) {
            System.out.println("Замовлення ID: " + o.getId());
        }
        System.out.println();

        System.out.println(" 6. Розрахунок вартості замовлення 1001 (calculateOrderTotal) ");
        System.out.println("Сума: " + service.calculateOrderTotal(1001L) + " грн");
        System.out.println();

        System.out.println(" 7. Унікальні клієнти, які зробили замовлення (findCustomersWithOrders) ");
        Set<Customer> customers = service.findCustomersWithOrders();
        for (Customer c : customers) {
            System.out.println(c.getName());
        }
        System.out.println();

        System.out.println(" 8. Сортування за загальною вартістю (sortOrdersByTotalPrice) ");
        for (Order o : service.sortOrdersByTotalPrice()) {
            System.out.println("ID: " + o.getId() + " | Вартість: " + service.calculateOrderTotal(o.getId()));
        }
        System.out.println();

        System.out.println(" 9. Підрахунок замовлень за статусом (countOrdersByStatus) ");
        Map<OrderStatus, Long> statusCount = service.countOrdersByStatus();
        for (Map.Entry<OrderStatus, Long> entry : statusCount.entrySet()) {
            System.out.println("Статус " + entry.getKey() + ": " + entry.getValue() + " шт.");
        }
        System.out.println();

        System.out.println(" 10. Видалення замовлення 1003 (removeOrder) ");
        service.removeOrder(1003L);
        System.out.println("Залишилось замовлень: " + service.getOrders().size());
    }
}