import java.util.Objects;

public class Product {

    private Double price;

    private String title;

    public Product(String title, Double price) {
        this.price = price;
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getPrice(), product.getPrice()) && Objects.equals(getTitle(), product.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getTitle());
    }

    @Override
    public String toString() {
        return String.format("%s (%.0f руб.)", title, price);
    }
}
