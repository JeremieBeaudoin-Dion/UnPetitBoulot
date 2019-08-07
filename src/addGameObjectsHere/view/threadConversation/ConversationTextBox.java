package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.view.threadInn.characters.ClientImageID;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * Physical object that represents a box containing textList
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ConversationTextBox extends ConversationInteractionObject {

    private static final int TEXT_OFFSET_X = 10;
    private static final int TEXT_OFFSET_Y = 30;
    private static final float FONT_SIZE = 23;

    private List<String> textList;

    /**
     * Constructor
     */
    public ConversationTextBox(BoundingArea boundingArea, DisplayableDepth depth, ClientImageID clientImageID,
                               ImagePosition positionOfImage, List<String> text) {
        super(boundingArea, depth, clientImageID, positionOfImage);

        this.textList = text;
    }

    @Override
    protected TreeSet<Displayable> getAdditionalImages(Position myPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> setOfImages = new TreeSet<>();

        int y = myPosition.getY() + TEXT_OFFSET_Y;
        int x = myPosition.getX() + TEXT_OFFSET_X;

        for (String text : textList) {
            setOfImages.add(new DisplayableText(new Position(x, y), getDepth(), text,
                    imageLoader.getBaseFont(FONT_SIZE), Color.BLACK));

            y += FONT_SIZE * 1.2;
        }

        return setOfImages;
    }

    @Override
    public void decide() {

    }

    @Override
    public void next() {

    }

    @Override
    public void previous() {

    }

}
