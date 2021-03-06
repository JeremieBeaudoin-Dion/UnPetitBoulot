package addGameObjectsHere.view.threadInn.other;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Mia Beaudoin-Dion
 */
public class LoadingBar extends PhysicalObject {

    private static final Position LOADING_BACKGROUND_OFFSET = new Position(17, 0);

    private static final Position SIZE = new Position(144,18);

    private boolean interrupted;

    private long timeMillisAtStart;
    private int timeMillisLength;

    /**
     * Constructor
     */
    public LoadingBar(Position position, DisplayableDepth depth, int timeInMillis) {
        super(new BoundingArea(position.getX(), position.getY(), SIZE.getX(), SIZE.getY()), false, depth);

        interrupted = false;
        timeMillisLength = timeInMillis;

        timeMillisAtStart = System.currentTimeMillis();
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> imagesToReturn = new TreeSet<>();

        imagesToReturn.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition).add(LOADING_BACKGROUND_OFFSET),
                getDepth(), getLoadingBackgroundImage(imageLoader)));

        imagesToReturn.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition), getDepth().add(1),
                imageLoader.getImageFromID(GenericImagesID.loadingForeground)));

        return imagesToReturn;
    }

    private BufferedImage getLoadingBackgroundImage(ImageLoader imageLoader) {
        BufferedImage backgroundImage = imageLoader.getImageFromID(GenericImagesID.loadingBackgroundBeer);

        int realWidth = (int) ((double) backgroundImage.getWidth() * getCompletionPercentage());

        if (realWidth == 0) {
            realWidth = 1;
        }

        return backgroundImage.getSubimage(0, 0, realWidth, backgroundImage.getHeight());
    }

    private double getCompletionPercentage() {
        long currentTime = System.currentTimeMillis();

        if (timeMillisLength > currentTime - timeMillisAtStart) {
            return (currentTime - timeMillisAtStart) / (double) timeMillisLength;
        }

        // Else - If more than complete, return complete
        return 1.0;
    }

    /**
     * Returns any action that the GameThread should handle
     */
    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    public boolean isDone() {
        return getCompletionPercentage() == 1.0;
    }

    /**
     * Returns true if the object should be disposed of
     */
    @Override
    public boolean dispose() {
        return interrupted || isDone();
    }

    public void interrupt() {
        this.interrupted = true;
    }

}
