package addGameObjectsHere.controller.gameButtons;

import addGameObjectsHere.model.images.BoxCreator;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class BoxedGameButton extends GameButton {

    private Font font = new Font("Century Schoolbook", Font.PLAIN, 30);

    public BoxedGameButton(ButtonID buttonID, Position startingPosition,
                           List<GameEvent> eventActions, DisplayableDepth depth) {
        super(buttonID, startingPosition, eventActions, depth);
    }

    public BoxedGameButton(ButtonID buttonID, BoundingArea startBoundingArea,
                           List<GameEvent> eventActions, DisplayableDepth depth) {
        super(buttonID, startBoundingArea, eventActions, depth);
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition), getDepth(),
                imageLoader.getBox(new Position(getWidth(), getHeight()), BoxCreator.Background.Wood)));

        images.add(new DisplayableText(this, cameraPosition, getButtonID().toString(), font, Color.WHITE));

        return images;
    }
}
