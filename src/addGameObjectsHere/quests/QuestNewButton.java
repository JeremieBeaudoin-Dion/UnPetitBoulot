package addGameObjectsHere.quests;

import addResourceLoaderHere.GameThreadID;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.coreActions.GameThreadEventAddNew;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableShapeFilled;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.MouseInteractingPhysicalObject;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * One of the dayNight in QuestTop menu.
 *
 * This will lead to the new Quest menu.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class QuestNewButton extends PhysicalObject implements MouseInteractingPhysicalObject {

    private static final int STARTING_X = 300;
    private static final int STARTING_Y = 300;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final Color color = Color.CYAN;

    private boolean goToMenu;

    /**
     * Constructor
     */
    public QuestNewButton() {
        super(getStartingBoundingArea(), false);

        goToMenu = false;
    }

    private static BoundingArea getStartingBoundingArea() {
        Shape circle = new Ellipse2D.Double(STARTING_X, STARTING_Y, WIDTH, HEIGHT);

        return new BoundingArea(circle);
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableShapeFilled(1000, getBoundingArea(), color));

        return images;
    }

    @Override
    public List<GameEvent> getAction() {

        List<GameEvent> actionsToDo = new LinkedList<>();

        if (goToMenu) {
            actionsToDo.add(new GameThreadEventAddNew(GameThreadID.QuestsNew));
            goToMenu = false;
        }

        return actionsToDo;
    }

    @Override
    public boolean dispose() {
        return false;
    }

    @Override
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        goToMenu = true;
    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }
}
