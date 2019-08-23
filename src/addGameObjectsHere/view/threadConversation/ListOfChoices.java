package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.view.threadAll.PlaneDimensionWithMargins;
import addGameObjectsHere.view.threadAll.images.BoxCreator;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * Physical object representing a series of choices in a conversation.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ListOfChoices<E extends Enum> extends PhysicalObject {

    public enum Pos {
        center, full
    }

    private static final int TEXT_SIZE = 30;
    private List<E> choices;
    private int currentChoice;

    private Pos pos;

    /**
     * Constructor
     */
    public ListOfChoices(BoundingArea boundingArea, DisplayableDepth depth, Pos pos, List<E> choices) {
        super(boundingArea, false, depth);

        this.choices = choices;
        this.currentChoice = 0;
        this.pos = pos;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        PlaneDimensionWithMargins boxDimension = getDimensionsForBox(cameraPosition);

        int i = 0;
        BoxCreator.Background background;

        for (E text : choices) {
            if (i == this.currentChoice) {
                background = BoxCreator.Background.LightWood;
            } else {
                background = BoxCreator.Background.Wood;
            }

            images.add(new DisplayableImage(boxDimension.getPositionWithMargins(), this.getDepth(),
                    imageLoader.getBox(boxDimension.getDimensionsInsideMargins(), background)));

            images.add(new DisplayableText(boxDimension.getCenter(), getDepth().add(1), text.toString(),
                    imageLoader.getBaseFont(getTextSizeToFitInYDimension(boxDimension.getDimensionsInsideMargins().getY())),
                    Color.WHITE, DisplayableText.Alignment.center));

            boxDimension = new PlaneDimensionWithMargins(boxDimension.getPosition().add(
                    new Position(boxDimension.getDimensions().getX() , 0)), boxDimension.getDimensions(),
                    boxDimension.getMargins());

            i++;
        }

        return images;
    }

    private PlaneDimensionWithMargins getDimensionsForBox(Position cameraPosition) {
        PlaneDimensionWithMargins boxDimension;

        Position maxDimensions = new Position(getWidth()/choices.size(), this.getHeight());

        if (this.pos == Pos.center) {
            boxDimension = new PlaneDimensionWithMargins(getPositionAccordingToCamera(cameraPosition), maxDimensions,
                    new Position(maxDimensions.getX()/7, maxDimensions.getY()/4));

        } else {
            boxDimension = new PlaneDimensionWithMargins(maxDimensions, getPositionAccordingToCamera(cameraPosition),
                    new Position(0, 0));
        }

        return boxDimension;
    }

    private int getTextSizeToFitInYDimension(int ySize) {
        int textSize = TEXT_SIZE;

        if (textSize > ySize*2/3) {
            textSize = ySize*2/3;
        }

        return textSize;
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

    public E getCurrentAction() {
        return choices.get(currentChoice);
    }

    public void next() {
        this.currentChoice++;

        if (currentChoice >= choices.size()) {
            currentChoice = choices.size() - 1;
        }
    }

    public void previous() {
        this.currentChoice--;

        if (currentChoice <= 0) {
            currentChoice = 0;
        }
    }
}
