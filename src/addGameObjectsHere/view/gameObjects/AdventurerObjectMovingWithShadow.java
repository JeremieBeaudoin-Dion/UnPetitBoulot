package addGameObjectsHere.view.gameObjects;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableShapeFilled;
import jGameFramework.physicalObjects.*;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public abstract class AdventurerObjectMovingWithShadow extends PhysicalObjectMoving {

    private static final int VELOCITY = 2;
    private static final Position ADV_SIZE = new Position(68, 26);

    public static final Position SHADOW_SIZE = new Position(144, 144); // Setting position and size as final for now
    public static final Position SHADOW_OFFSET = new Position(-40, -118);

    private AdventurerShadowImage shadow;

    private Enum imageID;

    /**
     * Constructor
     */
    public AdventurerObjectMovingWithShadow(int x, int y, DisplayableDepth depth, Enum imageID) {
        super(getBoundingArea(x, y), true, depth,
                new VisionRectangle(SHADOW_SIZE.getX() * 2, SHADOW_SIZE.getY() * 2),
                new VelocitySquare(VELOCITY, VELOCITY));

        shadow = new AdventurerShadowImage(this, SHADOW_SIZE, SHADOW_OFFSET);
        this.imageID = imageID;
    }

    private static BoundingArea getBoundingArea(int x, int y) {
        Ellipse2D ellipse = new Ellipse2D.Double((double) x, (double) y,
                (double) ADV_SIZE.getX(), (double) ADV_SIZE.getY());

        return new BoundingArea(ellipse);
    }

    /**
     * Method called every frame
     */
    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        this.updateAndMove(surroundings);

        shadow.update();

        return null;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableShapeFilled(this, cameraPosition, Color.BLACK));

        images.addAll(shadow.getImageObjects(cameraPosition, imageLoader));

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

    public Enum getImageID(){
        return imageID;
    }

}
