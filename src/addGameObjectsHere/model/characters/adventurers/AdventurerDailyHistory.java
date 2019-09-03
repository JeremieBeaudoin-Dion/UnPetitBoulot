package addGameObjectsHere.model.characters.adventurers;

import addGameObjectsHere.model.characters.client.ClientID;

/**
 * A representation of the adventurer for history purposes during the night.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerDailyHistory {

    private String name;
    private ClientID clientID;
    private int maxHealth;

    private int currentHealth;

    public AdventurerDailyHistory(Adventurer adventurer) {
        name = adventurer.getName();
        clientID = adventurer.getClientID();
        currentHealth = adventurer.getUnit().getStats().getHealth().getCurrentHP();
        maxHealth = adventurer.getUnit().getStats().getHealth().getMaxHp();
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
