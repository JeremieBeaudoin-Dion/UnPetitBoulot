package addGameObjectsHere.controller.windows;

import addGameObjectsHere.controller.dayNight.DayInteractingObject;
import addGameObjectsHere.controller.representations.AdventurerRepresentation;
import addGameObjectsHere.model.characters.generator.AdventurerGenerator;
import addGameObjectsHere.model.inn.Inn;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * A type of window that acts as a shop of adventurers.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerWindow extends GameWindow implements DayInteractingObject {

    private AdventurerGenerator generator;
    private List<AdventurerRepresentation> adventurerRepresentations;
    private Inn inn;

    /**
     * Constructor
     */
    public AdventurerWindow(Inn inn, Position position, int baseDepth) {
        super(WindowID.Adventurers, position, baseDepth);

        generator = new AdventurerGenerator();
        adventurerRepresentations = new LinkedList<>();
        this.inn = inn;

        isDay();
    }

    @Override
    public TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        for (AdventurerRepresentation adventurer: adventurerRepresentations) {
            images.addAll(adventurer.getImageObjects(cameraPosition, imageLoader));
        }

        return images;
    }

    @Override
    protected TreeSet<PhysicalObject> doUpdate(TreeSet<PhysicalObject> surroundings) {
        return null;
    }

    /*
     * Mouse actions
     */
    @Override
    protected void doLeftMousePressedOnDetails(Position mousePositionCollidingWithObject) {

    }

    @Override
    protected void doLeftMouseReleasedOnDetails(Position mousePositionCollidingWithObject) {

    }

    /*
     * Day and night cycle
     */
    @Override
    public void isDay() {
        generateCurrentAdventurers();
    }

    private void generateCurrentAdventurers() {
        adventurerRepresentations = new LinkedList<>();

        for (int i=0; i<3; i++) {
            adventurerRepresentations.add(new AdventurerRepresentation(generator.generateAdventurer(0),
                                            getDepth().add(1)));
        }

        resetAdventurersPosition();
    }

    @Override
    public void setPositionTo(Position newPosition)  {
        super.setPositionTo(newPosition);

        resetAdventurersPosition();
    }

    private void resetAdventurersPosition() {
        Position offset = super.getImagesOffset();

        Position positionOfAdventurers = this.getPosition().add(offset);

        for (AdventurerRepresentation adventurer : adventurerRepresentations) {
            adventurer.setPositionTo(positionOfAdventurers);

            positionOfAdventurers = positionOfAdventurers.add(
                    new Position(0, adventurer.getBoundingArea().getHeight()));
        }
    }

    @Override
    public void isNight() {
        //do nothing
    }
}
