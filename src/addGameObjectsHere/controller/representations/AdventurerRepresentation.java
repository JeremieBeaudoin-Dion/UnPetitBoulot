package addGameObjectsHere.controller.representations;

import addGameObjectsHere.controller.windows.DraggablePhysicalObject;
import addGameObjectsHere.model.characters.Adventurer;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;
import jGameFramework.physicalObjects.VisionRectangle;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerRepresentation extends DraggablePhysicalObject {

    private static final int WIDTH = 145;
    private static final int HEIGHT = 50;

    private Adventurer adventurer;

    private static final Color NAME_TEXT_COLOR = Color.WHITE;
    private static final Font NAME_TEXT_FONT = new Font("Century Schoolbook", Font.PLAIN, 18);

    public AdventurerRepresentation(Adventurer adventurer, DisplayableDepth baseDepth) {
        super(getBasicArea(), false, new VisionRectangle(WIDTH, HEIGHT), baseDepth);

        this.adventurer = adventurer;
    }

    private static BoundingArea getBasicArea() {
        return new BoundingArea(0, 0, WIDTH, HEIGHT);
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> setOfImages = new TreeSet<>();

        setOfImages.add(new DisplayableImage(this, cameraPosition, imageLoader.getAdventurerImage(adventurer.getAdventurerClass())));

        Position positionToAddToText = new Position(getWidth()/2.5, getHeight()/2 + NAME_TEXT_FONT.getSize()/2);

        setOfImages.add(new DisplayableText(getPositionAccordingToCamera(cameraPosition).add(positionToAddToText),
                getDepth().add(1), adventurer.getName(), NAME_TEXT_FONT, NAME_TEXT_COLOR));

        return setOfImages;
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

    @Override
    protected void hasStoppedMoving() {

    }

    @Override
    protected void hasStartedMoving() {

    }

    @Override
    protected void doUpdate(TreeSet<PhysicalObject> surroundings) {

    }
}
