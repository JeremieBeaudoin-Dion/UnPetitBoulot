package addGameObjectsHere.view.innObjects;

import addGameObjectsHere.view.adventurers.AdventurerPhysicalObject;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableShapeFilled;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * A table has adventurers near it. When it is served, it also serves the adventurers that are near.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ServingTablePhysicalObject extends PhysicalObject {

    private final boolean TESTING = false;

    private List<AdventurerPhysicalObject> seatedAdventurers;

    /**
     * Constructor
     */
    public ServingTablePhysicalObject(BoundingArea area) {
        super(area, true, new DisplayableDepth(DisplayableDepth.BACKGROUND));

        seatedAdventurers = new LinkedList<>();
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        if (!TESTING) {
            return null;
        }

        // For testing purposes only
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableShapeFilled(this, cameraPosition, new Color(0, 0, 255)));

        images.add(new DisplayableText(this, cameraPosition, Integer.toString(getId()),
                new Font("Times new roman", Font.PLAIN, 35), Color.BLACK));

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

    /**
     * Returns adventurers that are seating at the table.
     */
    public List<AdventurerPhysicalObject> getSeatedAdventurers() {
        return seatedAdventurers;
    }

    /**
     * Adds the adventurer to the table.
     */
    public void sit(AdventurerPhysicalObject obj) {
        seatedAdventurers.add(obj);
    }

    /**
     * Removes the adventurer to the table.
     */
    public void leave(AdventurerPhysicalObject obj) {
        seatedAdventurers.remove(obj);
    }
}
