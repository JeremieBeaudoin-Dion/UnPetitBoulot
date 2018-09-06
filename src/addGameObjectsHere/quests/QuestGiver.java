package addGameObjectsHere.quests;

import addGameObjectsHere.inn.Reputation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.List;
import java.util.TreeSet;

/**
 * The object in charge of quests.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class QuestGiver extends PhysicalObject {


    /**
     * Constructor
     */
    public QuestGiver(BoundingArea boundingArea) {
        super(boundingArea, false);
    }

    /**
     * Must be called before every night.
     */
    public void doNightCycle() {

    }

    /**
     * Generates quests according to the current reputation of the player.
     * The bigger the reputation,
     */
    private void generateQuests(Reputation reputation) {

    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        return null;
    }

    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    @Override
    public boolean dispose() {
        return false;
    }
}
