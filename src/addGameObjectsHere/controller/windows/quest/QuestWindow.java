package addGameObjectsHere.controller.windows.quest;

import addGameObjectsHere.controller.dayNight.DayNightInteractingObject;
import addGameObjectsHere.controller.windows.GameWindow;
import addGameObjectsHere.controller.windows.PhysicalObjectLayout;
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
public class QuestWindow extends GameWindow implements DayNightInteractingObject {

    private QuestGenerator questGenerator;
    private PhysicalObjectLayout<QuestRepresentation> questRepresentations;

    /**
     * Constructor
     */
    public QuestWindow(Position position) {
        super(WindowID.Quests, position);

        questGenerator = new QuestGenerator();
        questRepresentations = new PhysicalObjectLayout<>(new TreeSet<>(), getDetailsArea(), getDepth().add(1));
    }

    @Override
    protected TreeSet<PhysicalObject> doUpdate(TreeSet<PhysicalObject> surroundings) {
        return null;
    }

    @Override
    public TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.addAll(questRepresentations.getImageObjects(cameraPosition, imageLoader));

        return images;
    }

    @Override
    public void setPositionTo(Position newPosition)  {
        questRepresentations.moveBy(getPosition().add(newPosition.reverse()));

        super.setPositionTo(newPosition);
    }

    @Override
    protected void doLeftMousePressedOnDetails(Position mousePositionCollidingWithObject) {

    }

    @Override
    protected void doLeftMouseReleasedOnDetails(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void changeCycle() {
        Quest nextQuest = questGenerator.getNextQuest();

        if (nextQuest != null) {
            questRepresentations.addPhysicalObject(new QuestRepresentation(nextQuest, getPosition(), getDepth().add(1)));
        }
    }
}
