package addGameObjectsHere.controller.windows.quest;

import addGameObjectsHere.controller.dayNight.DayInteractingObject;
import addGameObjectsHere.controller.windows.GameWindow;
import addGameObjectsHere.controller.windows.WindowID;
import addGameObjectsHere.model.quests.Quest;
import addGameObjectsHere.model.quests.QuestGenerator;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * The window containing all the available quests.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class QuestWindow extends GameWindow implements DayInteractingObject {

    private QuestGenerator questGenerator;
    private List<QuestRepresentation> questRepresentations;

    /**
     * Constructor
     */
    public QuestWindow(Position position) {
        super(WindowID.Quests, position);

        questGenerator = new QuestGenerator();
        questRepresentations = new LinkedList<>();
    }

    @Override
    protected TreeSet<PhysicalObject> doUpdate(TreeSet<PhysicalObject> surroundings) {
        return null;
    }

    @Override
    public TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        for (QuestRepresentation questRepresentation : questRepresentations) {
            images.addAll(questRepresentation.getImageObjects(cameraPosition, imageLoader));
        }

        return images;
    }

    @Override
    protected void doLeftMousePressedOnDetails(Position mousePositionCollidingWithObject) {

    }

    @Override
    protected void doLeftMouseReleasedOnDetails(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void isDay() {
        Quest nextQuest = questGenerator.getNextQuest();

        if (nextQuest != null) {
            questRepresentations.add(new QuestRepresentation(nextQuest, getPosition(), getDepth().add(1)));
        }
    }

    @Override
    public void isNight() {

    }
}
