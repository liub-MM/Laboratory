import java.util.List;

public class Order {
    private long id;
    private Customer customer;
    private List<Product> products;
    private OrderStatus status;

    public Order(long l, Customer c, List<Product> list, OrderStatus orderStatus) {
        this.id = l;
        this.customer = c;
        this.products = list;
        this.status = orderStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
