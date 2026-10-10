public class Product {
    private long id;
    private String name;
    private ProductCategory category;
    private double price;

    public Product(long l, String name, ProductCategory productCategory, double price) {
        this.id = l;
        this.name = name;
        this.category = productCategory;
        this.price = price;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
