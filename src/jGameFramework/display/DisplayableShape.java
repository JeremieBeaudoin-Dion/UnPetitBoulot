package jGameFramework.display;

import jGameFramework.physicalObjects.Position;

import java.awt.*;

/**
 * A shape that can be displayed on screen. It has a color
 * and a java.awt shape.
 *
 * @author Mia Beaudoin-Dion
 */
public abstract class DisplayableShape extends Displayable {

    private Shape shape;
    private Paint paint;

    /**
     * Constructors
     */
    public DisplayableShape(DisplayableDepth depth, Shape shape, Paint paint) {
        super(new Position(shape.getBounds().x, shape.getBounds().y),
                new Position(shape.getBounds().width, shape.getBounds().height), depth);
        this.shape = shape;
        this.paint = paint;
    }

    public Shape getShape(){
        return shape;
    }

    public Paint getPaint(){
        return paint;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DisplayableShape)){
            return super.equals(other);
        }

        DisplayableShape otherShape = (DisplayableShape) other;

        return super.equals(other) && otherShape.shape.equals(shape) && otherShape.paint.equals(paint);
    }
}
