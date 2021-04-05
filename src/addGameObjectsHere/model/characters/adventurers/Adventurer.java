package addGameObjectsHere.model.characters.adventurers;

import addGameObjectsHere.model.characters.adventurers.stats.Perk;
import addGameObjectsHere.model.characters.adventurers.stats.PositiveQuirkId;
import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.characters.adventurers.stats.CharacterStats;

import java.util.List;

/**
 * An adventurer is a character which has a name and stats for adventuring.
 *
 * @author Mia Beaudoin-Dion
 */
public class Adventurer extends Client {

    private Unit unit;

    private int initialCost;

    private boolean assignedQuest;

    /**
     * Constructors
     */
    public Adventurer(Client client, CharacterStats characterStats, int cost) {
        super(client);

        this.unit = new Unit(characterStats);

        this.initialCost = cost;

        this.assignedQuest = false;
    }

    public Unit getUnit() {
        return unit;
    }

    public String getName() {
        return super.getName();// + ", the " + getAdjective();
    }

    public String getClassId() {
        return getClientID().toString();
    }

    public String getDescription() {
        String name = super.getName();

        List<Perk> quirks = getUnit().getStats().getQuirks();
        StringBuilder adjectives = new StringBuilder();
        for (Perk quirk : quirks) {
            adjectives.append(quirk.getName().toLowerCase() + " ");
        }

        return name + ", the " + adjectives.toString() + getClassId();
    }

    public void setIsAssignedQuest() {
        assignedQuest = true;
    }

    public boolean isAssignedQuest() {
        return assignedQuest;
    }

    /**
     * Cost to hire adventurer depends on their base cost
     * and the number of drinks they have drank tonight.
     */
    public int getCost() {
        return initialCost - 2 * getNumberOfDrinksTonight();
    }
}
