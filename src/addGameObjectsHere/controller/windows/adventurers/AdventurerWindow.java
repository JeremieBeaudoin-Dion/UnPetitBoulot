package addGameObjectsHere.controller.windows.adventurers;

import addGameObjectsHere.controller.dayNight.DayInteractingObject;
import addGameObjectsHere.controller.windows.GameWindow;
import addGameObjectsHere.controller.windows.WindowID;
import addGameObjectsHere.model.characters.generator.AdventurerGenerator;
import addGameObjectsHere.model.inn.Inn;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.Set;
import java.util.TreeSet;

/**
 * A type of window that acts as a shop of adventurers.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerWindow extends GameWindow implements DayInteractingObject {

    private AdventurerGenerator generator;
    private Set<AdventurerRepresentation> adventurerRepresentations;
    private Inn inn;

    private GameWindow windowToAdd;

    /**
     * Constructor
     */
    public AdventurerWindow(Inn inn, Position position) {
        super(WindowID.Adventurers, position);

        generator = new AdventurerGenerator();
        adventurerRepresentations = new TreeSet<>();
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
        resetAdventurersPosition();

        if (windowToAdd != null) {
            TreeSet<PhysicalObject> objectsToAdd = new TreeSet<>();

            objectsToAdd.add(windowToAdd);
            windowToAdd = null;

            return objectsToAdd;
        }

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
        for (AdventurerRepresentation adventurerRepresentation : adventurerRepresentations) {
            if (adventurerRepresentation.isColliding(mousePositionCollidingWithObject)) {
                windowToAdd = new AdventurerRecruitWindow(inn, adventurerRepresentation.getAdventurer(), getPosition());
                adventurerRepresentations.remove(adventurerRepresentation);
                return;
            }
        }
    }

    /*
     * Day and night cycle
     */
    @Override
    public void isDay() {
        generateCurrentAdventurers();
    }

    private void generateCurrentAdventurers() {
        adventurerRepresentations = new TreeSet<>();

        for (int i=0; i<4; i++) {
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
