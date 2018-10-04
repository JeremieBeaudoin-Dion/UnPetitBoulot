package addGameObjectsHere.controller.windows.adventurers;

import addGameObjectsHere.controller.dayNight.DayInteractingObject;
import addGameObjectsHere.controller.windows.GameWindow;
import addGameObjectsHere.controller.windows.PhysicalObjectLayout;
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
    private PhysicalObjectLayout<AdventurerRepresentation> objectsLayout;
    private Inn inn;

    private GameWindow windowToAdd;

    /**
     * Constructor
     */
    public AdventurerWindow(Inn inn, Position position) {
        super(WindowID.Adventurers, position);

        generator = new AdventurerGenerator();
        this.inn = inn;

        objectsLayout = new PhysicalObjectLayout<>(generateCurrentAdventurers(), getDetailsArea(), getDepth());
    }

    @Override
    public TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.addAll(objectsLayout.getImageObjects(cameraPosition, imageLoader));

        return images;
    }

    @Override
    protected TreeSet<PhysicalObject> doUpdate(TreeSet<PhysicalObject> surroundings) {
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
        AdventurerRepresentation adventurer = objectsLayout.doMouseClick(mousePositionCollidingWithObject);

        if (adventurer != null) {
            windowToAdd = new AdventurerRecruitWindow(inn, adventurer.getAdventurer(), getPosition().add(new Position(20, 20)));
            objectsLayout.removeObject(adventurer);
        }
    }

    /*
     * Day and night cycle
     */
    @Override
    public void isDay() {
        objectsLayout.setPhysicalObjects(generateCurrentAdventurers());
    }

    private Set<AdventurerRepresentation> generateCurrentAdventurers() {
        Set<AdventurerRepresentation> adventurerRepresentations = new TreeSet<>();

        for (int i=0; i<4; i++) {
            adventurerRepresentations.add(new AdventurerRepresentation(generator.generateAdventurer(0),
                                            getDepth().add(1)));
        }

        return adventurerRepresentations;
    }

    @Override
    public void setPositionTo(Position newPosition)  {
        objectsLayout.moveBy(getPosition().add(newPosition.reverse()));

        super.setPositionTo(newPosition);
    }

    @Override
    public void isNight() {
        //do nothing
    }
}
