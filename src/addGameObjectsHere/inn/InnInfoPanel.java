package addGameObjectsHere.inn;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.MouseInteractingPhysicalObject;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

import static addGameObjectsHere.inn.InnImagesID.panelBottom;

/**
 * The InnInfoPanel shows on the screen the information of the Inn
 * such as money, fame, etc.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class InnInfoPanel extends PhysicalObject implements MouseInteractingPhysicalObject {

    private static final int STARTING_X = 0;
    private static final int STARTING_Y = 565;
    private static final int WIDTH = 1080;
    private static final int HEIGHT = 155;

    private static final Color MONEY_TEXT_COLOR = Color.WHITE;
    private static final Font MONEY_TEXT_FONT = new Font("Century Schoolbook", Font.PLAIN, 25);

    private Inn inn;

    /**
     * Constructor
     */
    public InnInfoPanel(Inn inn) {
        super(new BoundingArea(STARTING_X, STARTING_Y, WIDTH, HEIGHT), false);

        this.inn = inn;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> setOfImages = new TreeSet<>();

        setOfImages.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition), 10, imageLoader.getInnUiImage(panelBottom)));

        setOfImages.add(new DisplayableText(getPositionAccordingToCamera(cameraPosition).add(new Position(getWidth()/10, getHeight()/3)),
                20, "Money: " + inn.getMoney(), MONEY_TEXT_FONT, MONEY_TEXT_COLOR));

        return setOfImages;
    }

    @Override
    public List<GameEvent> getAction() {
        return null;
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

    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }
}
