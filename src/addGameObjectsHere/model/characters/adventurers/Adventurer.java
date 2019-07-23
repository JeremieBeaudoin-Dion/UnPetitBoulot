package addGameObjectsHere.model.characters.adventurers;

import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.characters.client.ClientID;
import addGameObjectsHere.model.quests.fight.CharacterStats;
import addGameObjectsHere.view.threadInn.characters.ClientImageID;

import java.util.List;

/**
 * An adventurer is a character which has a name and stats.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Adventurer extends Client {

    private Unit unit;

    private AdventurerClassEnum adventurerClass;
    private List<AdventurerTrait> adventurerTraits;
    private int initialCost;

    /**
     * Constructors
     */
    public Adventurer(AdventurerClassEnum adventurerClass, CharacterStats characterStats,
                      List<AdventurerTrait> adventurerTraits, int cost, double experience, String name,
                      int alcoholTolerance) {
        super(name, null, alcoholTolerance, ClientID.getIDFromAdventurer(adventurerClass));

        this.unit = new Unit(characterStats, name);

        this.initialCost = cost;
        this.adventurerClass = adventurerClass;
        this.adventurerTraits = adventurerTraits;
    }

    public AdventurerClassEnum getAdventurerClass() {
        return adventurerClass;
    }

    public Unit getUnit() {
        return unit;
    }

    public String getName() {
        return super.getName() + ", the " + getAdjective() + " " + adventurerClass.toString();
    }

    private String getAdjective() {
        if (adventurerTraits.size() == 0) {
            return "average";
        }

        return adventurerTraits.get(0).getName().toLowerCase();
    }

    public int getCost() {
        return initialCost;
    }

}
