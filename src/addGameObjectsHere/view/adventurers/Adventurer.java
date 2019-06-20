package addGameObjectsHere.view.adventurers;

import addGameObjectsHere.model.characters.AdventurerClassEnum;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.*;

import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class Adventurer extends PhysicalObjectMoving {

    public static final int SIZE = 144;

    private AdventurerClassEnum adventurerClass;
    private AdventurerImageID adventurerImageID;

    /**
     * Constructor
     */
    public Adventurer(int x, int y, AdventurerClassEnum adventurerClass, DisplayableDepth baseDepth) {
        super(new BoundingArea(x, y, SIZE, SIZE), true, baseDepth,
                new VisionRectangle(SIZE + 50, SIZE + 50), new VelocitySquare(2, 2));

        this.adventurerClass = adventurerClass;
        adventurerImageID = getImageID(this.adventurerClass);
    }

    private AdventurerImageID getImageID(AdventurerClassEnum adventurerClass) {
        return null;  // TODO
    }

    /**
     * Method called every frame
     */
    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        return null;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableImage(this, cameraPosition, imageLoader.getAdventurerImage(adventurerImageID)));

        return images;
    }

    /**
     * Returns any action that the GameThread should handle
     */
    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    /**
     * Returns true if the object should be disposed of
     */
    @Override
    public boolean dispose() {
        return false;
    }

    public AdventurerImageID getAdventurerImageID() {
        return adventurerImageID;
    }
}
