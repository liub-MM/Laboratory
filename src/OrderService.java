import java.util.*;

public class OrderService {
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    public OrderService() {
        this.orders = new ArrayList<>();
    }

    void addOrder(Order order) {
        orders.add(order);
    }

    void removeOrder(long orderId) {
        Order o = findOrderById(orderId);
        orders.remove(o);
    }

    Order findOrderById(long orderId) {
        return orders.stream()
                .filter(order -> order.getId() == orderId)
                .findFirst()
                .orElse(null);
    }

    List<Order> findOrdersByCustomer(long customerId) {
        return orders.stream()
                .filter(
                        order -> order.getCustomer().getId() == customerId
                ).toList();
    }

    List<Order> findOrdersByStatus(OrderStatus status) {
        return orders.stream()
                .filter(order -> order.getStatus() == status)
                .toList();
    }

    List<Order> findOrdersContainingProduct(long productId) {
        return orders.stream()
                .filter(
                        order -> order.getProducts()
                                .stream()
                                .anyMatch(product -> product.getId() == productId)
                ).toList();
    }

    double calculateOrderTotal(long orderId) {
        Order o = findOrderById(orderId);
        double total = 0;
        for (Product p : o.getProducts()) {
            total += p.getPrice();
        }
        return total;
    }

    Set<Customer> findCustomersWithOrders() {
        Set<Customer> customers = new HashSet<>();
        for (Order o : orders) {
            customers.add(o.getCustomer());
        }
        return customers;
    }

    public List<Order> sortOrdersByTotalPrice() {
        List<Order> sortedOrders = new ArrayList<>(orders);
        int n = sortedOrders.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Order currentOrder = sortedOrders.get(j);
                Order nextOrder = sortedOrders.get(j + 1);

                double currentTotal = calculateOrderTotal(currentOrder.getId());
                double nextTotal = calculateOrderTotal(nextOrder.getId());

                if (currentTotal > nextTotal) {
                    sortedOrders.set(j, nextOrder);
                    sortedOrders.set(j + 1, currentOrder);
                }
            }
        }
        return sortedOrders;
    }

    public Map<OrderStatus, Long> countOrdersByStatus() {
        Map<OrderStatus, Long> statusCount = new HashMap<>();

        for (Order order : orders) {
            OrderStatus status = order.getStatus();
            if (statusCount.containsKey(status)) {
                long oldCount = statusCount.get(status);
                statusCount.put(status, oldCount + 1);
            } else {
                statusCount.put(status, 1L);
            }
        }

        return statusCount;

    }


}
