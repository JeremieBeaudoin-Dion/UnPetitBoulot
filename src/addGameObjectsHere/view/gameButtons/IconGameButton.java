package addGameObjectsHere.view.gameButtons;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.util.List;
import java.util.TreeSet;

/**
 * A type of GameButton that will show an icon.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class IconGameButton extends GameButton {

    public IconGameButton(ButtonID buttonID, Position startingPosition, List<GameEvent> eventActions, DisplayableDepth depth) {
        super(buttonID, startingPosition, eventActions, depth);
    }

    public IconGameButton(ButtonID buttonID, BoundingArea startBoundingArea, List<GameEvent> eventActions, DisplayableDepth depth) {
        super(buttonID, startBoundingArea, eventActions, depth);
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition),
                getDepth(), imageLoader.getImageFromID(getButtonID())));

        return images;
    }
}
