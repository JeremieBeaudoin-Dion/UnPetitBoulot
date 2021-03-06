package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.model.quests.Quest;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Mia Beaudoin-Dion
 */
public class QuestInformation extends PhysicalObject {

    private final static int TEXT_SIZE = 30;

    private Quest quest;

    /**
     * Constructor
     */
    public QuestInformation(BoundingArea boundingArea, DisplayableDepth depth, Quest quest) {
        super(boundingArea, false, depth);

        this.quest = quest;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableText(getPositionAccordingToCamera(cameraPosition).add(new Position(getWidth()/3, TEXT_SIZE)),
                getDepth(), "Time: " + this.quest.getTimeToComplete(),
                imageLoader.getBaseFont(TEXT_SIZE), Color.BLACK, DisplayableText.Alignment.center));

        images.add(new DisplayableText(getPositionAccordingToCamera(cameraPosition).add(new Position(getWidth()*2/3, TEXT_SIZE)),
                getDepth(), "Reward: " + this.quest.getTotalReward(),
                imageLoader.getBaseFont(TEXT_SIZE), Color.BLACK, DisplayableText.Alignment.center));


        /*Position timePosition = getPositionAccordingToCamera(cameraPosition).add(new Position(getWidth()/3, 0));

        images.add(new DisplayableImage(timePosition, getDepth(), imageLoader.getImageFromID(IconImageID.map_small)));

        int imageWidth = imageLoader.getImageFromID(IconImageID.map_small).getWidth();

        timePosition = timePosition.add(new Position(imageWidth + 5, 0));

        images.add(new DisplayableText(timePosition, getDepth(), String.valueOf(this.quest.getTimeToComplete()),
                imageLoader.getBaseFont(TEXT_SIZE), Color.BLACK, DisplayableText.Alignment.left));


        // Other thing.
        Position moneyPosition = getPositionAccordingToCamera(cameraPosition).add(new Position((getWidth()*2)/3, 0));

        images.add(new DisplayableImage(timePosition, getDepth(), imageLoader.getImageFromID(IconImageID.coin_small)));

        moneyPosition = moneyPosition.add(new Position(imageWidth + 5, 0));

        images.add(new DisplayableText(moneyPosition, getDepth(), String.valueOf(this.quest.getTotalReward()),
                imageLoader.getBaseFont(TEXT_SIZE), Color.BLACK, DisplayableText.Alignment.left));*/

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
}
