package addGameObjectsHere.view.threadAll;

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
 * A physical object to represent a button.
 *
 * In the context of InnKeeper, this button will have a text inside and respect
 * any margins given to it.
 *
 * The button will return an Enum
 *
 * @author Mia Beaudoin-Dion
 */
public class ButtonPhysicalObject extends PhysicalObject {

    private static final BoxCreator.Background BACKGROUND_SELECTED = BoxCreator.Background.LightWood;
    private static final BoxCreator.Background BACKGROUND_NOT_SELECTED = BoxCreator.Background.Wood;
    private static final int TEXT_SIZE = 30;

    private String text;
    private Paint textColor;
    private PlaneDimensionWithMargins margins;
    private boolean selected;

    /**
     * Constructor for Button with no margins
     */
    public ButtonPhysicalObject(BoundingArea boundingArea, DisplayableDepth depth, String text, Paint textColor) {
        super(boundingArea, false, depth);

        this.text = text;
        this.textColor = textColor;
        selected = false;
    }

    /**
     * Constructor for Button with margins
     */
    public ButtonPhysicalObject(PlaneDimensionWithMargins dimensionWithMargins, DisplayableDepth depth, String text,
                                Paint textColor) {
        super(new BoundingArea(dimensionWithMargins.getX(), dimensionWithMargins.getY(),
                    dimensionWithMargins.getWidth(), dimensionWithMargins.getHeight()), false, depth);

        margins = dimensionWithMargins;
        this.text = text;
        this.textColor = textColor;
        selected = false;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        if (text != null) {
            images.add(new DisplayableText(getBoundingArea().getCenterPosition(), getDepth().add(1), text,
                    imageLoader.getBaseFont(getTextSizeToFitInYDimension((int) getHeight())), textColor,
                    DisplayableText.Alignment.center));
        }

        BoxCreator.Background background = BACKGROUND_NOT_SELECTED;
        if (selected) {
            background = BACKGROUND_SELECTED;
        }

        if (margins == null) {
            images.add(new DisplayableImage(this, cameraPosition,
                    imageLoader.getBox(new Position(getWidth(), getHeight()), background)));
        } else {
            Position positionWithMargins = getPositionAccordingToCamera(cameraPosition).add(margins.getMargins());

            images.add(new DisplayableImage(positionWithMargins, getDepth(),
                    imageLoader.getBox(margins.getDimensionsInsideMargins(), background)));
        }

        return images;
    }

    private int getTextSizeToFitInYDimension(int ySize) {
        int textSize = TEXT_SIZE;

        if (textSize > ySize*2/3) {
            textSize = ySize*2/3;
        }

        return textSize;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return this.selected;
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
