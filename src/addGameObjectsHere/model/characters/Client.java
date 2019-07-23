package addGameObjectsHere.model.characters;

import java.util.List;

/**
 * A client of the inn have a maximum number of drinks they can drink
 * in a night, will have a description and may also be an Adventurer,
 * QuestGiver, etc.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Client {

    private int alcoholTolerance;
    private String name;
    private List<String> description;

    public Client(String name, List<String> description, int alcoholTolerance) {
        this.alcoholTolerance = alcoholTolerance;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getAlcoholTolerance() {
        return alcoholTolerance;
    }

    public List<String> getDescription() {
        return description;
    }

}
