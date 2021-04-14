package jGameFramework.display;

import jGameFramework.core.ObjectWithID;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Displayables are objects which can be displayed on screen using the ImageHandler.
 *
 * Their Depth represent the order in which the Displayables will be put on the screen.
 *
 * @author Mia Beaudoin-Dion
 */
public abstract class Displayable extends ObjectWithID {

    private DisplayableDepth depth;
    private Position position;
    private Position widthAndHeight;

    /**
     * Constructors with specific position, width and height
     */
    public Displayable(Position position, Position widthAndHeight, DisplayableDepth depth) {
        this.depth = depth;
        this.position = position;
        this.widthAndHeight = widthAndHeight;
    }

    public Position getPosition(){
        return position;
    }

    /**
     * Positions to be compared must take into account the fact that the height of the object
     * should be taken into account.
     *
     * Example: A higher image should be in front of a lower image if its size is smaller.
     */
    private Position getComparablePosition() {
        return position.add(new Position(0, widthAndHeight.getY()));
    }

    /**
     * Displayables are first compared with their depth values. The bigger the depth, the higher the image.
     *
     * If the depth values are the same, the positions are compared. The bigger the y value, the higher the image.
     * If the ys are the same, the bigger the x value, the higher the image.
     *
     * If their position are the same, the smaller width and height are considered higher.
     */
    @Override
    public int compareTo(ObjectWithID objectWithID) {
        if (!(objectWithID instanceof Displayable)){
            return super.compareTo(objectWithID);
        }

        Displayable other = (Displayable) objectWithID;

        if (depth.equals(other.depth)) {
            if (getComparablePosition().equals(other.getComparablePosition())){
                return super.compareTo(objectWithID);
            }

            return getComparablePosition().compareTo(other.getComparablePosition());
        }

        return depth.compareTo(other.depth);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Displayable)){
            return super.equals(other);
        }

        Displayable displayable = (Displayable) other;

        return position.equals(displayable.getPosition()) && depth.equals(displayable.depth) &&
                widthAndHeight.equals(displayable.widthAndHeight);
    }

}
