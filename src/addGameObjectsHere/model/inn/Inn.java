package addGameObjectsHere.model.inn;


import addGameObjectsHere.model.characters.Adventurer;

import java.util.Set;
import java.util.TreeSet;

/**
 * This class represents the inn of the user. It holds adventurers,
 * upgrades, etc.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Inn {

    private int money;
    private int fame;
    private Set<Adventurer> adventurers;

    /**
     * Constructor
     */
    public Inn() {
        money = 220;
        fame = 0;
        adventurers = new TreeSet<>();
    }

    /**
     * Adds the adventurer to the inn
     */
    public void addAdventurer(Adventurer adventurer) {
        adventurers.add(adventurer);
    }

    /**
     * Adds an adventurer at their cost
     */
    public void buyAdventurer(Adventurer adventurer) {
        if (!canBuyAdventurer(adventurer)) {
            throw new IllegalStateException("Cannot buy adventurer if you don't have enough money!!!");
        }

        money -= adventurer.getCost();
        addAdventurer(adventurer);
    }

    public boolean canBuyAdventurer(Adventurer adventurer) {
        return money > adventurer.getCost();
    }

    public void removeAdventurer(Adventurer adventurer) {
        adventurers.remove(adventurer);
    }

    public int getMoney() {
        return money;
    }

    public int getFame() {
        return fame;
    }

    public Set<Adventurer> getAdventurers() {
        return adventurers;
    }
}
