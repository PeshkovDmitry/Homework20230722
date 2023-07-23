import java.util.*;

public class Market implements MarketBehaviour, QueueBehaviour, Iterable<Client> {

    private List<Product> products = new ArrayList<>();

    private Queue<Client> queue = new LinkedList<>();


    private List<Client> satisfiedClients = new ArrayList<>();

    public void update() {
        Client currentClient = queue.poll();
        if (currentClient != null) {
            Iterator<Product> iterator = products.iterator();
            while (iterator.hasNext()) {
                Product p = iterator.next();
                if ((currentClient.getWishList().contains(p)) && currentClient.canBuy(p)) {
                    currentClient.Buy(p);
                    iterator.remove();
                }
            }
            satisfiedClients.add(currentClient);
        }
    }

    public List<Client> getSatisfiedClients() {
        return satisfiedClients;
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void sellProduct(Product product) {
        products.remove(product);
    }

    @Override
    public void addToQueue(Client client, IRichable rich) {
        if (rich.hasMoney(client)) {
            queue.add(client);
        }
    }

    @Override
    public void removeFromQueue(Client client) { queue.remove(client); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n### РЫНОК ### \n");
        sb.append("ПРОДУКТЫ В ПРОДАЖЕ:\n");
        for (Product p: products) {
            sb.append("\t").append(p).append("\n");
        }
        sb.append("КЛИЕНТЫ В ОЧЕРЕДИ: \n");
        for (Client c: queue) {
            sb.append("\t").append(c).append("\n");
        }
        sb.append("ДОВОЛЬНЫЕ КЛИЕНТЫ: \n");
        for (Client c: satisfiedClients) {
            sb.append("\t").append(c).append("\n");
        }
        return sb.toString();
    }

    // ДЛЯ ЗАДАНИЯ 2 РЕАЛИЗОВАН ИНТЕРФЕЙС Iterator<Client>,
    // ПЕРЕБИРАЮЩИЙ ВСЕХ КЛИЕНТОВ НА РЫНКЕ НЕЗАВИСИМО
    // ОТ ТОГО, В ОЧЕРЕДИ ОНИ ИЛИ НЕТ

    @Override
    public Iterator<Client> iterator() {
        return new Iterator<Client>() {

            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < queue.size() + satisfiedClients.size();
            }

            @Override
            public Client next() {
                if (count < queue.size()) {
                    return queue.stream().toList().get(count++);
                }
                else {
                    return satisfiedClients.get(count++ - queue.size());
                }
            }
        };
    }
}
