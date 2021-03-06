package addGameObjectsHere.view.threadNight;

import addGameObjectsHere.view.threadInn.inn.InnImagesID;
import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.GameThreadID;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.coreActions.GameThreadEventSetNew;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Will handle the UI of the night
 *
 * @author Mia Beaudoin-Dion
 */
public class NightUI extends PhysicalObject {

    private boolean nightIsEnded;

    /**
     * Constructor
     */
    public NightUI() {
        super(new BoundingArea(0, 0, GameInformation.WINDOW_WIDTH, GameInformation.WINDOW_HEIGHT), false,
                new DisplayableDepth(DisplayableDepth.BACKGROUND));

        nightIsEnded = false;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> setOfImages = new TreeSet<>();

        setOfImages.add(new DisplayableImage(this, cameraPosition, imageLoader.getImageFromID(InnImagesID.backgroundNight)));

        setOfImages.add(new DisplayableText(new Position(500, 500), new DisplayableDepth(500), "Press space",
                imageLoader.getBaseFont(35), Color.WHITE, DisplayableText.Alignment.left));

        return setOfImages;
    }

    public void endNight() {
        nightIsEnded = true;
    }

    @Override
    public List<GameEvent> getAction() {
        if (nightIsEnded) {
            List<GameEvent> list = new LinkedList<>();

            list.add(new GameThreadEventSetNew(GameThreadID.Inn));

            return list;
        }

        return null;
    }

    @Override
    public boolean dispose() {
        return false;
    }
}
