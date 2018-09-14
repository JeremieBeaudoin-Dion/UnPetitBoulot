package addGameObjectsHere.images;

import jGameFramework.physicalObjects.Position;

/**
 * Represents the position, width and height of an image.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ImageDimensions {

    private Position position;
    private Position dimensions;

    public ImageDimensions(Position position, Position dimensions) {
        this.position = position;
        this.dimensions = dimensions;
    }

    public Position getPosition() {
        return position;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public int getWidth() {
        return dimensions.getX();
    }

    public int getHeight() {
        return dimensions.getY();
    }
}
