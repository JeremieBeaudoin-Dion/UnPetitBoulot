package addGameObjectsHere.model.characters.adventurers;

import addGameObjectsHere.model.characters.adventurers.stats.BaseStat;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.Perk;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.StatBuff;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.Trigger;
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
    private boolean assignedQuest;

    /**
     * Constructors
     */
    public Adventurer(Client client, CharacterStats characterStats) {
        super(client);
        this.unit = new Unit(characterStats);
        this.assignedQuest = false;
        this.unit.getStats().setOwner(this);
        doTrigger(Trigger.CharacterCreation);
    }

    public void doTrigger(Trigger trigger) {
        unit.doTrigger(trigger);
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
            adjectives.append(quirk.getName().toLowerCase()).append(" ");
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
        return unit.getStats().get(BaseStat.Cut);
    }

    @Override
    protected void doDrinkOnce() {
        unit.getStats().addBuff(new StatBuff(BaseStat.Cut, -2, true));
    }
}
