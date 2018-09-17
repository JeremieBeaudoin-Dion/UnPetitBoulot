package addGameObjectsHere.gameButtons;

import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.GameThreadID;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.coreActions.GameThreadEventNew;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class DayButton extends GameButton {

    private static final int START_WIDTH = 192;
    private static final int START_HEIGHT = 108;

    /**
     * Constructor
     */
    public DayButton() {
        super(ButtonID.Day, new BoundingArea(GameInformation.WINDOW_WIDTH - 20 - START_WIDTH,
                GameInformation.WINDOW_HEIGHT - 20 - START_HEIGHT, START_WIDTH, START_HEIGHT), getMenuAction());
    }

    private static List<GameEvent> getMenuAction() {
        List<GameEvent> listOfEvents = new LinkedList<>();

        listOfEvents.add(new GameThreadEventNew(GameThreadID.Night));

        return listOfEvents;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition),
                200, imageLoader.getButtonImage(ButtonID.Day)));

        return images;
    }
}
