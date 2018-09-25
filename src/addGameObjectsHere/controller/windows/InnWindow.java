package addGameObjectsHere.controller.windows;

import addGameObjectsHere.controller.representations.AdventurerRepresentation;
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

    private Set<AdventurerRepresentation> adventurerRepresentations;
    private Inn inn;

    /**
     * Constructor
     */
    public InnWindow(Inn inn, Position position, int baseDepth) {
        super(WindowID.Inn, position, baseDepth);

        this.inn = inn;
        adventurerRepresentations = new TreeSet<>();
        resetAdventurers();
        resetAdventurersPosition();
    }

    @Override
    protected TreeSet<PhysicalObject> doUpdate(TreeSet<PhysicalObject> surroundings) {

        resetAdventurers();
        resetAdventurersPosition();

        return null;
    }

    /**
     * Adds necessary images
     */
    @Override
    public TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> images = new TreeSet<>();

        for (AdventurerRepresentation adventurer: adventurerRepresentations) {
            images.addAll(adventurer.getImageObjects(cameraPosition, imageLoader));
        }

        return images;
    }

    @Override
    protected void doLeftMousePressedOnDetails(Position mousePositionCollidingWithObject) {

    }

    @Override
    protected void doLeftMouseReleasedOnDetails(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void setPositionTo(Position newPosition)  {
        super.setPositionTo(newPosition);

        resetAdventurersPosition();
    }

    private void resetAdventurers() {
        adventurerRepresentations = new TreeSet<>();

        for (Adventurer adventurer : inn.getAdventurers()) {
            adventurerRepresentations.add(new AdventurerRepresentation(adventurer, getDepth().add(1)));
        }
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

}
