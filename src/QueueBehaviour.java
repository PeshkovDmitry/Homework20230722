public interface QueueBehaviour {

    void addToQueue(Client client, IRichable rich);

    void removeFromQueue(Client client);
}