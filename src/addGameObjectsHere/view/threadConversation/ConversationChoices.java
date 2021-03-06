package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.view.threadInn.characters.PlayerImageID;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.util.List;
import java.util.TreeSet;

/**
 * A conversation item having only choices
 *
 * @author Mia Beaudoin-Dion
 */
public class ConversationChoices<E extends Enum> extends ConversationInteractionObject {

    private ListOfChoices<E> listOfChoices;

    /**
     * Constructor
     */
    public ConversationChoices(BoundingArea boundingArea, DisplayableDepth depth, PlayerImageID clientImageID,
                               ImagePosition positionOfImage, List<E> choices) {
        super(boundingArea, depth, clientImageID, positionOfImage);

        listOfChoices = new ListOfChoices<>(getAreaWithoutBox(), getDepth(), ListOfChoices.Pos.center, choices);
    }

    @Override
    protected TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader) {
        return listOfChoices.getImageObjects(cameraPosition, imageLoader);
    }

    @Override
    public E getCurrentAction() {
        return listOfChoices.getCurrentAction();
    }

    @Override
    public void next() {
        listOfChoices.next();
    }

    @Override
    public void previous() {
        listOfChoices.previous();
    }
}
