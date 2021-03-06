package addGameObjectsHere.view.threadAll;

import jGameFramework.physicalObjects.PlaneDimension;
import jGameFramework.physicalObjects.Position;

/**
 * @author Mia Beaudoin-Dion
 */
public class PlaneDimensionWithMargins extends PlaneDimension {

    private Position margins;

    public PlaneDimensionWithMargins(Position position, Position dimensions, Position margins) {
        super(position, dimensions);

        this.margins = margins;
    }

    public Position getMargins() {
        return margins.clone();
    }

    public Position getPositionWithMargins() {
        return getPosition().add(margins);
    }

    public Position getDimensionsInsideMargins() {
        return getDimensions().add(margins.multiply(2).reverse());
    }

}
