package addGameObjectsHere.view.windows.quest;

import addGameObjectsHere.model.quests.Quest;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.core.ObjectWithID;
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
 * The physical object holding a quest in the game.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class QuestRepresentation extends PhysicalObject {

    private static final int WIDTH = 145;
    private static final int HEIGHT = 50;

    private Quest quest;

    private static final Color NAME_TEXT_COLOR = Color.WHITE;
    private static final Font NAME_TEXT_FONT = new Font("Century Schoolbook", Font.PLAIN, 18);

    /**
     * Constructor
     */
    public QuestRepresentation(Quest quest, Position position, DisplayableDepth depth) {
        super(new BoundingArea(position.getX(), position.getY(), WIDTH, HEIGHT), false, depth);

        this.quest = quest;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> setOfImages = new TreeSet<>();

        setOfImages.add(new DisplayableImage(this, cameraPosition, imageLoader.getImageFromID(quest.getQuestZone())));

        Position positionToAddToText = new Position(getWidth()/2.5, getHeight()/2 + NAME_TEXT_FONT.getSize()/2);

        setOfImages.add(new DisplayableText(getPositionAccordingToCamera(cameraPosition).add(positionToAddToText),
                getDepth().add(1), quest.getName(), NAME_TEXT_FONT, NAME_TEXT_COLOR));

        return setOfImages;
    }

    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    @Override
    public boolean dispose() {
        return false;
    }

    @Override
    public int compareTo(ObjectWithID objectWithID) {
        if (objectWithID instanceof QuestRepresentation) {
            return quest.compareTo(((QuestRepresentation) objectWithID).quest);
        }

        return super.compareTo(objectWithID);
    }
}
