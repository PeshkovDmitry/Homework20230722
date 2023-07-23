import java.util.ArrayList;
import java.util.List;

public class Client implements DoShopping, UseWishList {

    private String name;

    private Double money;

    private List<Product> purchases = new ArrayList<>();

    private List<Product> wishList = new ArrayList<>();

    public Client(String name, Double money) {
        this.name = name;
        this.money = money;
    }

    public Double showMoney() {
        return money;
    }

    @Override
    public void Buy(Product product) {
        if (canBuy(product)) {
            money -= product.getPrice();
            purchases.add(product);
            removeFromWishList(product);
        }
    }

    @Override
    public boolean canBuy(Product product) {
        return money >= product.getPrice() && product.getPrice() > 0;
    }


    @Override
    public void addToWishList(Product product) {
        wishList.add(product);
    }

    @Override
    public void removeFromWishList(Product product) {
        wishList.remove(product);
    }

    public List<Product> getWishList() {
        return wishList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s (%.0f руб.) \n",name.toUpperCase(),money));
        sb.append("\tКупленные продукты: ");
        for (Product p:purchases) {
            sb.append(p).append(";");
        }
        sb.append("\n\tПродукты, которые нужно купить: ");
        for (Product p:wishList) {
            sb.append(p).append(";");
        }
        return sb.toString();
    }

}
