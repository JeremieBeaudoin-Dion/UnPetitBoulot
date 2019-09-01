package addGameObjectsHere.model.characters.adventurers;

import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.quests.Quest;
import addGameObjectsHere.model.quests.fight.CharacterStats;

import java.util.List;

/**
 * An adventurer is a character which has a name and stats for adventuring.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Adventurer extends Client {

    private Unit unit;

    private List<AdventurerTrait> adventurerTraits;
    private int initialCost;

    private boolean assignedQuest;

    /**
     * Constructors
     */
    public Adventurer(Client client, CharacterStats characterStats,
                      List<AdventurerTrait> adventurerTraits, int cost, double experience) {
        super(client);

        this.unit = new Unit(characterStats);

        this.initialCost = cost;
        this.adventurerTraits = adventurerTraits;

        this.assignedQuest = false;
    }

    public Unit getUnit() {
        return unit;
    }

    public String getName() {
        return super.getName() + ", the " + getAdjective();
    }

    private String getAdjective() {
        if (adventurerTraits.size() == 0) {
            return "average";
        }

        return adventurerTraits.get(0).getName().toLowerCase();
    }

    public void setIsAssignedQuest() {
        assignedQuest = true;
    }

    public boolean isAssignedQuest() {
        return assignedQuest;
    }

    public int getCost(Quest quest) {
        // TODO: Costs depends on quest length, etc.
        return initialCost;
    }
}
