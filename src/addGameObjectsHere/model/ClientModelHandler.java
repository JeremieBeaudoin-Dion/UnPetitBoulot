package addGameObjectsHere.model;

import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.characters.generator.ClientGenerator;

import java.util.LinkedList;
import java.util.List;

/**
 * Handles client model objects in general.
 *
 * This helps with conversations, getting all clients for the day, etc.
 *
 * @author Mia Beaudoin-Dion
 */
public class ClientModelHandler implements ImmutableClientModelHandler {

    private ClientGenerator clientGenerator;
    private Client currentConversationClient;

    public ClientModelHandler() {
        clientGenerator = new ClientGenerator();
    }

    public void setCurrentConversationClient(Client client) {
        currentConversationClient = client;
    }

    public Client getCurrentConversationClient() {
        return currentConversationClient;
    }

    public List<Client> generateDayClients(int numberOfClientsToCreate) {
        // Depends on the InnID
        List<Client> listToReturn = new LinkedList<>();

        for (int i=0; i<numberOfClientsToCreate; i++) {
            listToReturn.add(clientGenerator.getNextClient());
        }

        return listToReturn;
    }

}
