package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.view.threadInn.characters.ClientImageID;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.util.TreeSet;

/**
 * Physical object that represents a box containing textList
 *
 * @author Mia Beaudoin-Dion
 */
public class ConversationTextBox extends ConversationInteractionObject {

    private TextBox textBox;

    /**
     * Constructor
     */
    public ConversationTextBox(ImageLoader imageLoader, BoundingArea boundingArea, DisplayableDepth depth,
                               ClientImageID clientImageID, ImagePosition positionOfImage, String text) {
        super(boundingArea, depth, clientImageID, positionOfImage);

        this.textBox = new TextBox(imageLoader, getAreaWithoutBox(), getDepth(), text);
    }

    @Override
    protected TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader) {
        return textBox.getImageObjects(cameraPosition, imageLoader);
    }

    @Override
    public ClientConversationDialogActionID getCurrentAction() {
        return null;
    }

    @Override
    public void next() {

    }

    @Override
    public void previous() {

    }

}
