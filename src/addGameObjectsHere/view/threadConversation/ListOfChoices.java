package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.view.threadAll.images.BoxCreator;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class ListOfChoices extends PhysicalObject {

    private static final int TEXT_SIZE = 30;
    private List<ClientConversationDialogActionID> choices;
    private int currentChoice;

    /**
     * Constructor
     */
    public ListOfChoices(BoundingArea boundingArea, DisplayableDepth depth,
                         List<ClientConversationDialogActionID> choices) {
        super(boundingArea, false, depth);

        this.choices = choices;
        this.currentChoice = 0;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        Position maxDimensions = new Position(getWidth()/choices.size(), this.getHeight());

        Position boxDimensions = new Position(maxDimensions.getX() * 5/7, maxDimensions.getY()/2);
        Position boxPosition = getPositionAccordingToCamera(cameraPosition).add(
                new Position(maxDimensions.getX()/7, maxDimensions.getY()/4));

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

    public ClientConversationDialogActionID getCurrentAction() {
        return choices.get(currentChoice);
    }

    public void next() {
        this.currentChoice++;

        if (currentChoice >= choices.size()) {
            currentChoice = choices.size() - 1;
        }
    }

    public void previous() {
        this.currentChoice--;

        if (currentChoice <= 0) {
            currentChoice = 0;
        }
    }
}
