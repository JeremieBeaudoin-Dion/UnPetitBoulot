package addGameObjectsHere.view.windows.adventurers;

import addGameObjectsHere.view.dayNight.DayNightInteractingObject;
import addGameObjectsHere.view.windows.GameWindow;
import addGameObjectsHere.view.windows.PhysicalObjectLayout;
import addGameObjectsHere.view.windows.WindowID;
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
public class AdventurerWindow extends GameWindow implements DayNightInteractingObject {

    private AdventurerGenerator generator;
    private PhysicalObjectLayout<AdventurerWindowRepresentation> objectsLayout;
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
        AdventurerWindowRepresentation adventurer = objectsLayout.doMouseClick(mousePositionCollidingWithObject);

        if (adventurer != null) {
            windowToAdd = new AdventurerRecruitWindow(inn, adventurer.getAdventurer(), getPosition().add(new Position(20, 20)));
            objectsLayout.removeObject(adventurer);
        }
    }

    @Override
    public void setPositionTo(Position newPosition)  {
        objectsLayout.moveBy(getPosition().add(newPosition.reverse()));

        super.setPositionTo(newPosition);
    }

    @Override
    public void changeCycle() {
        objectsLayout.setPhysicalObjects(generateCurrentAdventurers());
    }

    private Set<AdventurerWindowRepresentation> generateCurrentAdventurers() {
        Set<AdventurerWindowRepresentation> adventurerWindowRepresentations = new TreeSet<>();

        for (int i=0; i<4; i++) {
            adventurerWindowRepresentations.add(new AdventurerWindowRepresentation(generator.generateAdventurer(0),
                    getDepth().add(1)));
        }

        return adventurerWindowRepresentations;
    }
}
