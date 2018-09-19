package addResourceLoaderHere;

import addGameObjectsHere.windows.GameWindow;
import addGameObjectsHere.gameButtons.DayButton;
import addGameObjectsHere.gameButtons.NightButton;
import addGameObjectsHere.camera.CameraStill;
import addGameObjectsHere.characters.generator.AdventurerGenerator;
import addGameObjectsHere.inn.Inn;
import addGameObjectsHere.inn.InnAdventurerPanel;
import addGameObjectsHere.dayNight.DayUI;
import addGameObjectsHere.dayNight.NightUI;
import addGameObjectsHere.windows.WindowID;
import jGameFramework.core.Loader;
import jGameFramework.physicalObjects.Camera;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.TreeSet;

/**
 * Loads all game objects to create GameThreads.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class PhysicalObjectLoader implements Loader<TreeSet<PhysicalObject>> {

    private ImageLoader imageLoader;

    /**
     * Constructor
     */
    public PhysicalObjectLoader(ImageLoader imageLoader){
        this.imageLoader = imageLoader;
    }

    /**
     * Returns game object to handle according to gameThreadID
     */
    public TreeSet<PhysicalObject> get(GameThreadID gameThreadID){
        switch (gameThreadID){
            case Menu:
                return getMenuObjects();

            case Day:
                return getDayObjects();

            case Night:
                return getNightObjects();

            case QuestsTop:
                return getQuestsTopObjects();

            case QuestsNew:
                return getQuestsNewObjects();

            case TestWindow:
                return getTestWindowObjects();
        }

        throw new IllegalArgumentException("The GameThreadID: " + gameThreadID + " is not implemented.");
    }

    private TreeSet<PhysicalObject> getMenuObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        return objects;
    }

    private TreeSet<PhysicalObject> getDayObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        Inn testInn = createTestInn();

        objects.add(new InnAdventurerPanel(testInn));

        objects.add(new DayButton());
        objects.add(new DayUI());

        return objects;
    }

    private Inn createTestInn() {
        Inn inn = new Inn();

        AdventurerGenerator adventurerGenerator = new AdventurerGenerator();

        inn.addAdventurer(adventurerGenerator.generateAdventurer(0));
        inn.addAdventurer(adventurerGenerator.generateAdventurer(0));
        inn.addAdventurer(adventurerGenerator.generateAdventurer(0));

        return inn;
    }

    private TreeSet<PhysicalObject> getNightObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new NightButton());

        objects.add(new NightUI());

        return objects;
    }

    private TreeSet<PhysicalObject> getQuestsTopObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();


        return objects;
    }

    private TreeSet<PhysicalObject> getQuestsNewObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        return objects;
    }

    private TreeSet<PhysicalObject> getTestWindowObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new DayUI());
        objects.add(new GameWindow(WindowID.Adventurers, new Position(100, 100), 100));
        objects.add(new GameWindow(WindowID.Quests, new Position(200, 100), 200));

        return objects;
    }


    /**
     * The camera will be static for now, independently of gameThread
     */
    public Camera getCamera(GameThreadID gameThreadID){
        return new CameraStill();
    }

}
