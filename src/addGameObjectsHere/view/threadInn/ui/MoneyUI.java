package addGameObjectsHere.view.threadInn.ui;

import addGameObjectsHere.view.threadAll.images.BoxCreator;
import addGameObjectsHere.view.threadAll.images.IconImageID;
import addResourceLoaderHere.DepthHandler;
import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import addResourceLoaderHere.ModelObjectLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.PhysicalObjectUI;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * Ui element showing the amount of money the user has
 *
 * @author Mia Beaudoin-Dion
 */
public class MoneyUI extends PhysicalObject implements PhysicalObjectUI {

    private ModelObjectLoader modelObjectLoader;

    /**
     * Constructor
     */
    public MoneyUI(ModelObjectLoader modelObjectLoader) {
        super(getBasicArea(), false, DepthHandler.UI_BASE_DEPTH);

        this.modelObjectLoader = modelObjectLoader;
    }

    /**
     * Helper method to create BoundingArea
     */
    private static BoundingArea getBasicArea() {
        int width = GameInformation.WINDOW_WIDTH / 8;
        int height = GameInformation.WINDOW_HEIGHT / 10;

        int x = GameInformation.WINDOW_WIDTH - width;
        int y = 0;

        return new BoundingArea(x, y, width, height);
    }

    /**
     * Returns the image representation of this physical object
     *
     * As this is a UI element, it does not factor in the cameraPosition.
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> setToReturn = new TreeSet<>();

        setToReturn.add(new DisplayableImage(getPosition(), getDepth(),
                imageLoader.getBox(new Position(getWidth(), getHeight()), BoxCreator.Background.Paper)));

        setToReturn.add(new DisplayableImage(getPosition(), getDepth().add(1), imageLoader.getImageFromID(IconImageID.coin)));

        setToReturn.add(new DisplayableText(getPosition().add(new Position(70, 45)), getDepth().add(1),
                Integer.toString(modelObjectLoader.getMoneyHandler().getTotalMoney()),
                imageLoader.getBaseFont(35f), Color.BLACK, DisplayableText.Alignment.left));

        return setToReturn;
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
