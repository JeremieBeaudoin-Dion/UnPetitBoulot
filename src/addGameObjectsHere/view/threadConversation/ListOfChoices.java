package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.view.threadAll.ButtonEnumPhysicalObject;
import addGameObjectsHere.all.ListIteratorOnObject;
import addGameObjectsHere.view.threadAll.PlaneDimensionWithMargins;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Physical object representing a series of choices in a conversation.
 *
 * @author Mia Beaudoin-Dion
 */
public class ListOfChoices<E extends Enum> extends PhysicalObject {

    public enum Pos {
        center, marginsX, full
    }

    private List<ButtonEnumPhysicalObject<E>> buttonList;
    private ListIteratorOnObject<ButtonEnumPhysicalObject<E>> iterator;

    /**
     * Constructor
     */
    public ListOfChoices(BoundingArea boundingArea, DisplayableDepth depth, Pos pos, List<E> choices) {
        super(boundingArea, false, depth);

        buttonList = prepareButtons(choices, pos);
        iterator = new ListIteratorOnObject<>(buttonList);
        iterator.get().setSelected(true);
    }

    private List<ButtonEnumPhysicalObject<E>> prepareButtons(List<E> choices, Pos pos) {
        List<ButtonEnumPhysicalObject<E>> allObjects = new LinkedList<>();

        int i = 0;
        for (E choice: choices) {
            allObjects.add(new ButtonEnumPhysicalObject<>(getDimensionsForButton(pos, i, choices.size()), getDepth(),
                    Color.WHITE, choice));
            i++;
        }

        return allObjects;
    }

    private PlaneDimensionWithMargins getDimensionsForButton(Pos pos, int index, int numberOfButtons) {
        PlaneDimensionWithMargins boxDimension;

        Position maxDimensions = new Position(getWidth()/numberOfButtons, this.getHeight());
        Position positionOfButton = new Position(getPosition().getX() + maxDimensions.getX() * index,
                getPosition().getY());

        if (pos == Pos.center) {
            boxDimension = new PlaneDimensionWithMargins(positionOfButton, maxDimensions,
                    new Position(maxDimensions.getX()/7, maxDimensions.getY()/4));

        } else if (pos == Pos.marginsX) {
            boxDimension = new PlaneDimensionWithMargins(positionOfButton, maxDimensions,
                    new Position(maxDimensions.getX()/7, 0));
        } else {
            boxDimension = new PlaneDimensionWithMargins(positionOfButton, maxDimensions,
                    new Position(0, 0));
        }

        return boxDimension;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        for (PhysicalObject button : buttonList) {
            images.addAll(button.getImageObjects(cameraPosition, imageLoader));
        }

        return images;
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
        return iterator.get().getActionToReturn();
    }

    public void next() {
        if (iterator.hasNext()) {
            iterator.get().setSelected(false);
            iterator.next().setSelected(true);
        }
    }

    public void previous() {
        if (iterator.hasPrevious()) {
            iterator.get().setSelected(false);
            iterator.previous().setSelected(true);
        }
    }
}
