package addGameObjectsHere.controller.windows.inn;

import addGameObjectsHere.controller.windows.PhysicalObjectLayout;
import addGameObjectsHere.controller.windows.adventurers.AdventurerRepresentation;
import addGameObjectsHere.controller.windows.GameWindow;
import addGameObjectsHere.controller.windows.WindowID;
import addGameObjectsHere.model.characters.Adventurer;
import addGameObjectsHere.model.inn.Inn;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.Set;
import java.util.TreeSet;

/**
 * The inn window shows pertinent information about the inn.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class InnWindow extends GameWindow {

    private Inn inn;
    private PhysicalObjectLayout<AdventurerRepresentation> objectLayout;

    /**
     * Constructor
     */
    public InnWindow(Inn inn, Position position) {
        super(WindowID.Inn, position);

        this.inn = inn;
        objectLayout = new PhysicalObjectLayout<>(getAdventurersRepresentation(), getDetailsArea(), getDepth());
    }

    private Set<AdventurerRepresentation> getAdventurersRepresentation() {
        TreeSet<AdventurerRepresentation> adventurerRepresentations = new TreeSet<>();

        for (Adventurer adventurer : inn.getAdventurers()) {
            adventurerRepresentations.add(new AdventurerRepresentation(adventurer, getDepth().add(1)));
        }

        return adventurerRepresentations;
    }

    @Override
    protected TreeSet<PhysicalObject> doUpdate(TreeSet<PhysicalObject> surroundings) {
        objectLayout.setPhysicalObjects(getAdventurersRepresentation());

        return null;
    }

    /**
     * Adds necessary images
     */
    @Override
    public TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> images = new TreeSet<>();

        images.addAll(objectLayout.getImageObjects(cameraPosition, imageLoader));

        return images;
    }

    @Override
    protected String getWindowTitle() {
        return super.getWindowTitle() + " $" + inn.getMoney();
    }

    @Override
    protected void doLeftMousePressedOnDetails(Position mousePositionCollidingWithObject) {

    }

    @Override
    protected void doLeftMouseReleasedOnDetails(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void setPositionTo(Position newPosition)  {
        objectLayout.moveBy(getPosition().add(newPosition.reverse()));

        super.setPositionTo(newPosition);
    }

}
