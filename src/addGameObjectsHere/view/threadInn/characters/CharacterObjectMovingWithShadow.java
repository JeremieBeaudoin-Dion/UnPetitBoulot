package addGameObjectsHere.view.threadInn.characters;

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
 * @author Mia Beaudoin-Dion
 */
public abstract class CharacterObjectMovingWithShadow extends PhysicalObjectMoving {

    public static final int VELOCITY = 4;
    private static final Position ADV_SIZE = new Position(68, 26);

    public static final Position SHADOW_SIZE = new Position(144, 144); // Setting position and size as final for now
    public static final Position SHADOW_OFFSET = new Position(-40, -118);

    private CharacterShadowImage shadow;

    private Enum imageID;

    protected boolean createdShadow;

    /**
     * Constructor
     */
    public CharacterObjectMovingWithShadow(int x, int y, DisplayableDepth depth, Enum imageID) {
        super(getBoundingArea(x, y), true, depth,
                new VisionRectangle(ADV_SIZE.getX() * 2, ADV_SIZE.getY() * 4),
                new VelocitySquare(VELOCITY, VELOCITY));

        shadow = new CharacterShadowImage(this, SHADOW_SIZE, SHADOW_OFFSET);
        this.imageID = imageID;

        createdShadow = false;
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

        if (!createdShadow) {
            createdShadow = true;
            TreeSet<PhysicalObject> toReturn = new TreeSet<>();
            toReturn.add(shadow);
            return toReturn;
        }

        return null;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableShapeFilled(this, cameraPosition, Color.BLACK));

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
