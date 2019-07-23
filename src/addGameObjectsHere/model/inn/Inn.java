package addGameObjectsHere.model.inn;


import addGameObjectsHere.model.characters.adventurers.Adventurer;

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
    private int reputation;
    private Set<Adventurer> adventurers;
    private int comfort;

    /**
     * Constructor
     */
    public Inn() {
        money = 100;
        reputation = 0;
        comfort = 10; // Temporary for testing
        adventurers = new TreeSet<>();
    }

    /**
     * Adds the adventurer to the inn
     */
    public void addAdventurer(Adventurer adventurer) {
        adventurers.add(adventurer);
    }

    public int getNumberOfAdventurersToCreate() {
        return this.comfort; // This will change...
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

    /**
     * By giving alcohol to an adventurer, gets money.
     */
    public void serveAdventurer(int numberOfAdventurers) {
        money += numberOfAdventurers * 4;
    }

    public void removeAdventurer(Adventurer adventurer) {
        adventurers.remove(adventurer);
    }

    public int getMoney() {
        return money;
    }

    public int getReputation() {
        return reputation;
    }

    public Set<Adventurer> getAdventurers() {
        return adventurers;
    }
}
