package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
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
 * A physical object used by conversation UIs to give information on a certain quest
 * when giving it to an adventurer.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class QuestGivingInformation extends PhysicalObject {

    private final static int TEXT_SIZE = 30;

    private int currentMoney;
    private Adventurer adventurer;
    private Quest quest;

    /**
     * Constructor
     */
    public QuestGivingInformation(BoundingArea boundingArea, DisplayableDepth depth, int currentMoney, Quest quest,
                                  Adventurer adventurer) {
        super(boundingArea, false, depth);

        this.currentMoney = currentMoney;
        this.adventurer = adventurer;
        this.quest = quest;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        if (quest == null) {
            return images;
        }

        Color fontColor = Color.BLACK;

        if (currentMoney < this.adventurer.getCost(quest)) {
            fontColor = Color.GRAY;
        }

        images.add(new DisplayableText(getPositionAccordingToCamera(cameraPosition).add(new Position(getWidth()/3, TEXT_SIZE/2)),
                getDepth(), "Time: " + this.quest.getTimeToComplete(),
                imageLoader.getBaseFont(TEXT_SIZE), fontColor, DisplayableText.Alignment.center));

        images.add(new DisplayableText(getPositionAccordingToCamera(cameraPosition).add(new Position(getWidth()*2/3, TEXT_SIZE/2)),
                getDepth(), "Cost: " + this.adventurer.getCost(quest),
                imageLoader.getBaseFont(TEXT_SIZE), fontColor, DisplayableText.Alignment.center));

        return images;
    }

    public void updateQuest(Quest quest) {
        this.quest = quest;
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
