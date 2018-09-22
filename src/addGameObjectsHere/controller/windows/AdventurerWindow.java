package addGameObjectsHere.controller.windows;

import addGameObjectsHere.controller.representations.AdventurerRepresentation;
import addGameObjectsHere.model.characters.generator.AdventurerGenerator;
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

    private List<AdventurerRepresentation> adventurers;

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
            adventurers.add(new AdventurerRepresentation(generator.generateAdventurer(0), getDepth().add(i)));
        }
    }

    @Override
    protected void updatePosition() {
        Position offset = super.getImagesOffset();

        Position positionOfAdventurers = this.getPosition().add(offset);

        for (AdventurerRepresentation adventurer : adventurers) {
            adventurer.setPositionTo(positionOfAdventurers);

            positionOfAdventurers = positionOfAdventurers.add(
                    new Position(0, adventurer.getBoundingArea().getHeight()));
        }
    }

    @Override
    public TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        for (AdventurerRepresentation adventurer: adventurers) {
            images.addAll(adventurer.getImageObjects(cameraPosition, imageLoader));
        }

        return images;
    }

}
