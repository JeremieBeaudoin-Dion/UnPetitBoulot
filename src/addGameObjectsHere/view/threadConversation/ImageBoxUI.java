package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.view.threadAll.images.BoxCreator;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.List;
import java.util.TreeSet;

public class ImageBoxUI extends PhysicalObject {

    private Enum imageID;

    /**
     * Constructor
     */
    public ImageBoxUI(BoundingArea boundingArea, boolean isObstacle, DisplayableDepth depth, Enum imageID) {
        super(boundingArea, isObstacle, depth);

        this.imageID = imageID;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> setOfImages = new TreeSet<>();

        Position actualPosition = this.getPositionAccordingToCamera(cameraPosition);

        setOfImages.add(new DisplayableImage(actualPosition, getDepth().add(1),
                imageLoader.getBox(new Position(getWidth(), getHeight()), BoxCreator.Background.Wood)));

        setOfImages.add(new DisplayableImage(actualPosition, getDepth().add(2),
                imageLoader.getImageFromID(imageID)));

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
}
