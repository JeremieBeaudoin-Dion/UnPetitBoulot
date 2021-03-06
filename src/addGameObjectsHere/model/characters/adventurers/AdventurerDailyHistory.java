package addGameObjectsHere.model.characters.adventurers;

import addGameObjectsHere.model.characters.client.ClientID;

/**
 * A representation of the adventurer for history purposes during the night.
 *
 * @author Mia Beaudoin-Dion
 */
public class AdventurerDailyHistory {

    private String name;
    private ClientID clientID;
    private int maxHealth;

    private int currentHealth;

    public AdventurerDailyHistory(Adventurer adventurer) {
        name = adventurer.getName();
        clientID = adventurer.getClientID();
        currentHealth = adventurer.getUnit().getStats().getCurrentHealth();
        maxHealth = adventurer.getUnit().getStats().getMaxHealth();
    }

    public String getName() {
        return name;
    }

    public ClientID getClientID() {
        return clientID;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

}
