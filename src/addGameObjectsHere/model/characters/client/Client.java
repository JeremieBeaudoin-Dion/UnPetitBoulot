package addGameObjectsHere.model.characters.client;

import java.util.EnumMap;

/**
 * A client of the inn have a maximum number of drinks they can drink
 * in a night, will have a description and may also be an Adventurer,
 * QuestGiver, etc.
 *
 * @author Mia Beaudoin-Dion
 */
public class Client {

    private static final int BASE_ALCOHOL_TOLERANCE = 3;

    private String name;
    private EnumMap<ClientConversationDialogID, String> conversationDialog;
    private ClientID clientID;
    private int drinksTonight;
    private int alcoholTolerance;

    public Client(ClientID clientID, String name, EnumMap<ClientConversationDialogID,
            String> conversationDialog, int alcoholTolerance) {
        this.name = name;
        this.conversationDialog = conversationDialog;
        this.clientID = clientID;
        this.alcoholTolerance = alcoholTolerance;
        drinksTonight = 0;
    }

    public Client(Client client) {
        this.name = client.name;
        this.conversationDialog = client.conversationDialog.clone();
        this.clientID = client.getClientID();
        this.alcoholTolerance = client.alcoholTolerance;
        drinksTonight = 0;
    }

    /**
     * Returns the maximum number of drinks a client can take before leaving.
     */
    public int getAlcoholTolerance() {
        if (alcoholTolerance == 0) {
            return BASE_ALCOHOL_TOLERANCE;
        }

        return this.alcoholTolerance;
    }

    public void drinkOnce() {
        drinksTonight += 1;
        doDrinkOnce();
    }

    protected void doDrinkOnce() {
        // do nothing.
    }

    public boolean hasDrankOverTolerance() {
        return drinksTonight >= getAlcoholTolerance();
    }

    public int getNumberOfDrinksTonight() {
        return drinksTonight;
    }

    public String getName() {
        return name;
    }

    public String getDialog(ClientConversationDialogID dialogID) {
        return conversationDialog.get(dialogID);
    }

    public ClientID getClientID() {
        return clientID;
    }

}
