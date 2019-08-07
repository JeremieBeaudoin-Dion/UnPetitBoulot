package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.view.threadAll.images.BoxCreator;
import addGameObjectsHere.view.threadInn.characters.PlayerImageID;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class ConversationChoices extends ConversationInteractionObject {

    private static final Position TEXT_OFFSET = new Position(25, 25);
    private static final int TEXT_SIZE = 30;

    private List<String> choices;
    private int currentChoice;

    /**
     * Constructor
     */
    public ConversationChoices(BoundingArea boundingArea, DisplayableDepth depth, PlayerImageID clientImageID,
                               ImagePosition positionOfImage, List<String> choices) {
        super(boundingArea, depth, clientImageID, positionOfImage);

        this.choices = choices;
        this.currentChoice = 0;
    }

    @Override
    protected TreeSet<Displayable> getAdditionalImages(Position conversationPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        Position maxDimensions = new Position((this.getWidth() -
                ConversationInteractionObject.SIZE_OF_IMAGE_BOX.getX())/ choices.size(), this.getHeight());

        Position boxDimensions = new Position(maxDimensions.getX() * 4/5, maxDimensions.getY()/2);
        Position boxPosition = conversationPosition.add(new Position(boxDimensions.getX()/5, boxDimensions.getY()/2));

        int i = 0;
        BoxCreator.Background background;

        for (String text : choices) {
            if (i == this.currentChoice) {
                background = BoxCreator.Background.LightWood;
            } else {
                background = BoxCreator.Background.Wood;
            }

            images.add(new DisplayableImage(boxPosition.clone(), this.getDepth(),
                    imageLoader.getBox(boxDimensions.clone(), background)));

            images.add(new DisplayableText(boxPosition.add(TEXT_OFFSET), getDepth().add(1), text,
                    imageLoader.getBaseFont(TEXT_SIZE), Color.WHITE));

            boxPosition = boxPosition.add(new Position(maxDimensions.getX(), 0));
            i++;
        }

        return images;
    }

    @Override
    public void decide() {

    }

    @Override
    public void next() {
        this.currentChoice++;

        if (currentChoice >= choices.size()) {
            currentChoice = choices.size() - 1;
        }
    }

    @Override
    public void previous() {
        this.currentChoice--;

        if (currentChoice <= 0) {
            currentChoice = 0;
        }
    }
}
