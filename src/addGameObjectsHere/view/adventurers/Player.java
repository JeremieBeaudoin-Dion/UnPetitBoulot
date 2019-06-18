package addGameObjectsHere.view.adventurers;

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
public class Player extends PhysicalObjectMoving {

    private AdventurerImageID imageID;

    /**
     * Constructor
     */
    public Player(int x, int y) {
        super(new BoundingArea(x, y, Adventurer.SIZE, Adventurer.SIZE), true, new DisplayableDepth(500),
                new VisionRectangle(300, 300), new VelocitySquare(2, 2));

        imageID = AdventurerImageID.InnKeeperMan;
    }

    /**
     * Method called every frame
     */
    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        this.updateAndMove(surroundings);

        return null;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableImage(this, cameraPosition, imageLoader.getAdventurerImage(imageID)));

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
}
