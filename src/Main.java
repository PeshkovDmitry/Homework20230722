import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Client client1 = new Client("Иван", 2500.0);
        client1.addToWishList(new Product("Капуста", 100.0));
        client1.addToWishList(new Product("Говядина", 600.0));
        client1.addToWishList(new Product("Молоко", 70.0));

        Client client2 = new Client("Татьяна", 1500.0);
        client2.addToWishList(new Product("Картофель", 40.0));
        client2.addToWishList(new Product("Свинина", 350.0));
        client2.addToWishList(new Product("Молоко", 70.0));

        Client client3 = new Client("Ольга", 10000.0);
        client3.addToWishList(new Product("Говядина", 600.0));
        client3.addToWishList(new Product("Свинина", 350.0));
        client3.addToWishList(new Product("Картофель", 40.0));

        Client client4 = new Client("Максим", 0.0);
        client3.addToWishList(new Product("Говядина", 600.0));
        client3.addToWishList(new Product("Свинина", 350.0));
        client3.addToWishList(new Product("Картофель", 40.0));

        Market market = new Market();
        market.addProduct(new Product("Капуста", 100.0));
        market.addProduct(new Product("Говядина", 600.0));
        market.addProduct(new Product("Молоко", 70.0));
        market.addProduct(new Product("Картофель", 40.0));
        market.addProduct(new Product("Свинина", 350.0));
        market.addProduct(new Product("Молоко", 70.0));
        market.addProduct(new Product("Говядина", 600.0));
        market.addProduct(new Product("Свинина", 350.0));
        market.addProduct(new Product("Картофель", 40.0));

        // ДЛЯ ЗАДАНИЯ 1 РЕАЛИЗОВАН ФУНКЦИОНАЛЬНЫЙ ИНТЕРФЕЙС IRichable
        // ДОБАВЛЯЕМ КЛИЕНТОВ В ОЧЕРЕДЬ ТОЛЬКО ПРИ НАЛИЧИИ У НИХ ДЕНЕГ

        market.addToQueue(client1, (x) -> x.showMoney() > 0);
        market.addToQueue(client2, (x) -> x.showMoney() > 0);
        market.addToQueue(client3, (x) -> x.showMoney() > 0);
        market.addToQueue(client4, (x) -> x.showMoney() > 0);
        System.out.print(market);

        market.update();
        System.out.print(market);

        // ДЛЯ ЗАДАНИЯ 2 В КЛАССЕ Market РЕАЛИЗОВАН ИНТЕРФЕЙС Iterable<Client>
        // ДЛЯ ПЕРЕБОРА ВСЕХ КЛИЕНТОВ РЫНКА НЕЗАВИСИМО ОТ ТОГО, В ОЧЕРЕДИ ОНИ ИЛИ НЕТ

        System.out.println("\nКТО ГУЛЯЕТ ПО РЫНКУ ПОСЛЕ ПЕРВОГО update()\n");
        for (Client c: market) {
            System.out.println(c);
        }

        market.update();
        System.out.print(market);

        market.update();
        System.out.print(market);

        // ДЛЯ ЗАДАНИЯ 3 ВЫЗЫВАЕМ МЕТОД, СОРТИРУЮЩИЙ СДЕЛАВШИХ ПОКУПКУ КЛИЕНТОВ
        // РЫНКА В ПОРЯДКЕ ВОЗРАСТАНИЯ СОДЕРЖИМОГО ИХ КОШЕЛЬКА

        System.out.println("\nПО РЫНКУ ГУЛЯЮТ...\n");

        List<Client> sortedClients =
                showSortedMarketClients(market.getSatisfiedClients(), (c1, c2) -> (int) (c1.showMoney() - c2.showMoney()));

        for (Client client: sortedClients) {
            System.out.println(client);
        }
    }

    public static List<Client> showSortedMarketClients(List<Client> list, Comparator<Client> comp) {
        List<Client> res = new ArrayList<>(list);
        Collections.sort(res, comp);
        return res;
    }

}