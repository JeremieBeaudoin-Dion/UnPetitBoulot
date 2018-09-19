package addGameObjectsHere.inn;

import addGameObjectsHere.characters.Adventurer;
import addGameObjectsHere.images.BoxCreator;
import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.MouseInteractingPhysicalObject;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.List;
import java.util.TreeSet;

/**
 * The panel that shows the different adventurers.
 *
 * When an adventurer is clicked, it is set to be given to the
 * current AdventurerHandler.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class InnAdventurerPanel extends PhysicalObject implements MouseInteractingPhysicalObject {

    private static final int STARTING_X = 0;
    private static final int STARTING_Y = 0;
    public static final int WIDTH = GameInformation.WINDOW_WIDTH/7;
    private static final int HEIGHT = GameInformation.WINDOW_HEIGHT;

    private Inn inn;
    private AdventurerHandler adventurerHandler;

    /**
     * Constructor
     */
    public InnAdventurerPanel(Inn inn) {
        super(new BoundingArea(STARTING_X, STARTING_Y, WIDTH, HEIGHT), false, new DisplayableDepth(100));

        adventurerHandler = null;
        this.inn = inn;
    }

    void setAdventurerHandler(AdventurerHandler adventurerHandler) {
        this.adventurerHandler = adventurerHandler;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> setOfImages = new TreeSet<>();

        setOfImages.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition), getDepth(),
                imageLoader.getBox(new Position(getWidth(), getHeight()), BoxCreator.Background.Wood)));

        for (Adventurer adventurer : inn.getAdventurers()) {
            setOfImages.addAll(adventurer.getImageObjects(cameraPosition, imageLoader));
        }

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
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        if (adventurerHandler == null) {
            return;
        }

        for (Adventurer adventurer : inn.getAdventurers()) {

            if (adventurer.isColliding(mousePositionCollidingWithObject)) {
                addAdventurer(adventurer);
                break;
            }
        }
    }

    private void addAdventurer(Adventurer adventurer) {
        try {
            adventurerHandler.AddAdventurer(adventurer);

            inn.removeAdventurer(adventurer);
        } catch (AdventurerHandleException e) {
            // TODO: Print error info in Inspector
        }
    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }
}
