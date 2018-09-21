package addGameObjectsHere.windows;

import addGameObjectsHere.characters.Adventurer;
import addGameObjectsHere.characters.generator.AdventurerGenerator;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerWindow extends GameWindow {

    private AdventurerGenerator generator;

    private List<Adventurer> adventurers;

    /**
     * Constructor
     */
    public AdventurerWindow(Position position, int baseDepth) {
        super(WindowID.Adventurers, position, baseDepth);

        generator = new AdventurerGenerator();
        adventurers = new LinkedList<>();

        // Basic method for now
        populateRandomAdventurers(3);
        updatePosition();
    }

    private void populateRandomAdventurers(int amount) {
        for (int i=0; i<amount; i++) {
            adventurers.add(generator.generateAdventurer(0));
        }
    }

    @Override
    protected void updatePosition() {
        Position offset = super.getImagesOffset();

        Position positionOfAdventurers = this.getPosition().add(offset);

        for (Adventurer adventurer : adventurers) {
            adventurer.setPositionTo(positionOfAdventurers);

            positionOfAdventurers = positionOfAdventurers.add(
                    new Position(0, adventurer.getBoundingArea().getHeight()));
        }
    }

    @Override
    public TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        for (Adventurer adventurer: adventurers) {
            images.addAll(adventurer.getImageObjects(cameraPosition, imageLoader));
        }

        return images;
    }

}
