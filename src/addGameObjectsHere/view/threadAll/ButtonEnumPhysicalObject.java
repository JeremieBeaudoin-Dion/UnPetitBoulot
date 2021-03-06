package addGameObjectsHere.view.threadAll;

import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.BoundingArea;

import java.awt.*;

/**
 * A type of Button that will return a specific Enum as ActionToReturn
 *
 * @author Mia Beaudoin-Dion
 */
public class ButtonEnumPhysicalObject<E extends Enum> extends ButtonPhysicalObject {

    private E actionToReturn;

    /**
     * Constructor for Button with no margins
     */
    public ButtonEnumPhysicalObject(BoundingArea boundingArea, DisplayableDepth depth, Paint textColor,
                                    E actionToReturn) {
        super(boundingArea, depth, actionToReturn.toString(), textColor);

        this.actionToReturn = actionToReturn;
    }

    /**
     * Constructor for Button with margins
     */
    public ButtonEnumPhysicalObject(PlaneDimensionWithMargins dimensionWithMargins, DisplayableDepth depth,
                                    Paint textColor, E actionToReturn) {
        super(dimensionWithMargins, depth, actionToReturn.toString(), textColor);

        this.actionToReturn = actionToReturn;
    }

    public E getActionToReturn() {
        return actionToReturn;
    }

}
