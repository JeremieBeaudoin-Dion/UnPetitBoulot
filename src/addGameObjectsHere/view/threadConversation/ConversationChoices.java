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

    // private static final Position TEXT_OFFSET = new Position(25, 25);
    private static final int TEXT_SIZE = 30;

    private List<ClientConversationDialogActionID> choices;
    private int currentChoice;

    /**
     * Constructor
     */
    public ConversationChoices(BoundingArea boundingArea, DisplayableDepth depth, PlayerImageID clientImageID,
                               ImagePosition positionOfImage, List<ClientConversationDialogActionID> choices) {
        super(boundingArea, depth, clientImageID, positionOfImage);

        this.choices = choices;
        this.currentChoice = 0;
    }

    @Override
    protected TreeSet<Displayable> getAdditionalImages(Position conversationPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        Position maxDimensions = new Position((this.getWidth() -
                ConversationInteractionObject.SIZE_OF_IMAGE_BOX.getX())/ choices.size(), this.getHeight());

        Position boxDimensions = new Position(maxDimensions.getX() * 5/7, maxDimensions.getY()/2);
        Position boxPosition = conversationPosition.add(new Position(maxDimensions.getX()/7, maxDimensions.getY()/4));

        int i = 0;
        BoxCreator.Background background;

        for (ClientConversationDialogActionID text : choices) {
            if (i == this.currentChoice) {
                background = BoxCreator.Background.LightWood;
            } else {
                background = BoxCreator.Background.Wood;
            }

            images.add(new DisplayableImage(boxPosition.clone(), this.getDepth(),
                    imageLoader.getBox(boxDimensions.clone(), background)));

            images.add(new DisplayableText(boxPosition.add(new Position(boxDimensions.getX()/2, boxDimensions.getY()/2)),
                    getDepth().add(1), text.toString(), imageLoader.getBaseFont(TEXT_SIZE), Color.WHITE, DisplayableText.Alignment.center));

            boxPosition = boxPosition.add(new Position(maxDimensions.getX() , 0));
            i++;
        }

        return images;
    }

    @Override
    public ClientConversationDialogActionID getCurrentAction() {
        return choices.get(currentChoice);
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
