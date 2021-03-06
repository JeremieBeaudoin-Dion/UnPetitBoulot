package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.view.threadInn.characters.ClientPhysicalObject;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableShapeFilled;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * Physical object that represents a box containing textList
 *
 * @author Mia Beaudoin-Dion
 */
public abstract class ConversationInteractionObject extends DecisionUI {

    private static final boolean TEST = false;
    protected static final Position SIZE_OF_IMAGE_BOX = ClientPhysicalObject.SHADOW_SIZE.clone();

    public enum ImagePosition {
        left, right
    }

    private ImagePosition positionOfImage;
    private Enum imageID;
    private ImageBoxUI imageBoxUI;

    /**
     * Constructor
     */
    public ConversationInteractionObject(BoundingArea boundingArea, DisplayableDepth depth, Enum imageID,
                                         ImagePosition positionOfImage) {
        super(boundingArea, false, depth);

        this.positionOfImage = positionOfImage;
        this.imageID = imageID;

        BoundingArea boxImageArea;

        switch (positionOfImage) {
            case left:
                Position boxPosition = new Position(boundingArea.getX(), boundingArea.getY());
                boxImageArea = new BoundingArea(boxPosition.getX(), boxPosition.getY(),
                        SIZE_OF_IMAGE_BOX.getX(), SIZE_OF_IMAGE_BOX.getY());
                break;

            default:
                Position rightPosition = new Position(boundingArea.getX() + getWidth() - SIZE_OF_IMAGE_BOX.getX(),
                        boundingArea.getY());

                boxImageArea = new BoundingArea(rightPosition.getX(), rightPosition.getY(),
                        SIZE_OF_IMAGE_BOX.getX(), SIZE_OF_IMAGE_BOX.getY());
                break;
        }

        imageBoxUI = new ImageBoxUI(boxImageArea, false, getDepth().add(10), imageID);
    }

    /**
     * Returns area without the box with the image
     */
    protected BoundingArea getAreaWithoutBox() {

        switch (positionOfImage) {
            case left:
                return new BoundingArea(getPosition().getX() + SIZE_OF_IMAGE_BOX.getX(), getPosition().getY(),
                        (int) (getWidth() - SIZE_OF_IMAGE_BOX.getX()), (int) getHeight());

            default:
                return new BoundingArea(getPosition().getX(), getPosition().getY(),
                        (int) (getWidth() - SIZE_OF_IMAGE_BOX.getX()), (int) getHeight());
        }
    }

    protected BoundingArea getSubAreaWithoutImageBox(int numberOfAreas, int index) {
        BoundingArea baseArea = getAreaWithoutBox();

        int width = (int) baseArea.getWidth();
        int height = (int) baseArea.getHeight()/numberOfAreas;

        return new BoundingArea(baseArea.getX(), baseArea.getY() + height * index, width, height);
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> setOfImages = new TreeSet<>();

        if (TEST) {
            setOfImages.add(new DisplayableShapeFilled(this, cameraPosition, Color.CYAN));
        }

        Position actualPosition = this.getPositionAccordingToCamera(cameraPosition);
        setOfImages.addAll(imageBoxUI.getImageObjects(cameraPosition, imageLoader));

        setOfImages.addAll(getAdditionalImages(cameraPosition, imageLoader));

        return setOfImages;
    }

    protected abstract TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader);

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
