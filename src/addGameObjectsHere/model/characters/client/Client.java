package addGameObjectsHere.model.characters.client;

import java.util.EnumMap;

/**
 * A client of the inn have a maximum number of drinks they can drink
 * in a night, will have a description and may also be an Adventurer,
 * QuestGiver, etc.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Client {

    private int alcoholTolerance;
    private String name;
    private EnumMap<ClientConversationDialogID, String> conversationDialog;
    private ClientID clientID;

    public Client(ClientID clientID, String name, EnumMap<ClientConversationDialogID,
            String> conversationDialog, int alcoholTolerance) {
        this.alcoholTolerance = alcoholTolerance;
        this.name = name;
        this.conversationDialog = conversationDialog;
        this.clientID = clientID;
    }

    public Client(Client client) {
        this.alcoholTolerance = client.alcoholTolerance;
        this.name = client.name;
        this.conversationDialog = client.conversationDialog.clone();
        this.clientID = client.getClientID();
    }

    public String getName() {
        return name;
    }

    public int getAlcoholTolerance() {
        return alcoholTolerance;
    }

    public String getDialog(ClientConversationDialogID dialogID) {
        return conversationDialog.get(dialogID);
    }

    public ClientID getClientID() {
        return clientID;
    }

}
