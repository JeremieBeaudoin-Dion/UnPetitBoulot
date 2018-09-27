package addGameObjectsHere.model.characters;

import addGameObjectsHere.model.quests.fight.CharacterDamage;
import addGameObjectsHere.model.quests.fight.CharacterStats;

import java.util.List;

/**
 * An adventurer is a character which has a name and stats.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Adventurer extends Unit {

    private String name;
    private AdventurerClassEnum adventurerClass;
    private List<AdventurerTrait> adventurerTraits;
    private int initialCost;

    /**
     * Constructors
     */
    public Adventurer(AdventurerClassEnum adventurerClass, CharacterStats characterStats,
                      List<AdventurerTrait> adventurerTraits, int cost, double experience, String name) {
        super(characterStats);

        this.initialCost = cost;
        this.name = name;
        this.adventurerClass = adventurerClass;
        this.adventurerTraits = adventurerTraits;
    }

    public AdventurerClassEnum getAdventurerClass() {
        return adventurerClass;
    }

    public String getName() {
        return name + ", the " + getAdjective() + " " + adventurerClass.toString();
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

    //TODO: Change this according to the traits
    @Override
    public void doDamage(CharacterDamage damage) {
        super.doDamage(damage);
    }
}
