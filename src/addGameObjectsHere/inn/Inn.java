package addGameObjectsHere.inn;


import addGameObjectsHere.characters.Adventurer;
import jGameFramework.physicalObjects.Position;

import java.util.Set;
import java.util.TreeSet;

/**
 * A holder class for the information of the player
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Inn {

    private int money;

    private Set<Adventurer> adventurers;

    /**
     * Constructor
     */
    public Inn() {
        money = 100;

        adventurers = new TreeSet<>();
    }

    /**
     * Adds the adventurer to the inn
     */
    public void addAdventurer(Adventurer adventurer) {
        adventurers.add(adventurer);

        orderAdventurers();
    }

    /**
     * Resets the adventurers to the desired position.
     */
    private void orderAdventurers() {

        int x = 0;
        int y = 5;

        for (Adventurer adventurer : adventurers) {
            adventurer.setPositionTo(new Position(x, y));

            y += adventurer.getBoundingArea().getHeight();
            y += 5;
        }
    }

    public int getMoney() {
        return money;
    }

    public Set<Adventurer> getAdventurers() {
        return adventurers;
    }
}
