package addGameObjectsHere.view.adventurers;

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
public class Player extends PhysicalObjectMoving {

    private PlayerImageID imageID;
    private static final int VELOCITY = 2;
    private static final Position SHADOWSIZE = new Position(68, 26);

    private PlayerShadowImage shadow;

    /**
     * Constructor
     */
    public Player(int x, int y, DisplayableDepth depth) {
        super(getBoundingArea(x, y), true, depth,
                new VisionRectangle(PlayerShadowImage.SIZE.getX() * 2, PlayerShadowImage.SIZE.getY() * 2),
                new VelocitySquare(VELOCITY, VELOCITY));

        imageID = PlayerImageID.InnKeeperMan;
        shadow = new PlayerShadowImage(this);
    }

    private static BoundingArea getBoundingArea(int x, int y) {
        Ellipse2D ellipse = new Ellipse2D.Double((double) x, (double) y,
                (double) SHADOWSIZE.getX(), (double) SHADOWSIZE.getY());

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

    public PlayerImageID getImageID() {
        return imageID;
    }
}
