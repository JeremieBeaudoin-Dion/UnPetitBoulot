package addResourceLoaderHere;

import addGameObjectsHere.view.threadAll.PhysicalObjectBackground;
import addGameObjectsHere.view.threadConversation.CharacterConversationUI;
import addGameObjectsHere.view.threadConversation.ConversationImagesID;
import addGameObjectsHere.view.threadInn.characters.PlayerPhysicalObject;
import addGameObjectsHere.view.threadAll.camera.CameraFollowingPlayer;
import addGameObjectsHere.view.threadAll.camera.CameraStill;
import addGameObjectsHere.view.threadInn.ui.MoneyUI;
import addGameObjectsHere.view.threadNight.NightUI;
import addResourceLoaderHere.physicalObjectsHelper.InnLoader;
import jGameFramework.core.Loader;
import jGameFramework.physicalObjects.Camera;
import jGameFramework.physicalObjects.PhysicalObject;

import java.util.TreeSet;

/**
 * Loads all game objects to create GameThreads.
 *
 * This in a way is the representation of the VIEW of the game. PhysicalObjects have a position and
 * images as well as simple interactions to be made within a certain thread.
 *
 * This should only load new PhysicalObjects to be handled by the ObjectHandler for each game threads.
 * The objects will not interact between threads.
 *
 * All objects that are to interact between threads or that must keep important game information should
 * be in the ModelObjectLoader.
 *
 * GameEvents to SAVE the game will only save ModelObjectLoader and current PhysicalObjects on screen.
 *
 * @author Mia Beaudoin-Dion
 */
public class PhysicalObjectLoader implements Loader<TreeSet<PhysicalObject>> {

    private ImageLoader imageLoader;

    private PlayerPhysicalObject playerPhysicalObject;

    private ModelObjectLoader modelObjectLoader;

    private InnLoader innLoader;

    /**
     * Constructor
     */
    public PhysicalObjectLoader(ImageLoader imageLoader, ModelObjectLoader modelObjectLoader){
        this.imageLoader = imageLoader;
        this.modelObjectLoader = modelObjectLoader;
        this.innLoader = new InnLoader(modelObjectLoader);

        playerPhysicalObject = new PlayerPhysicalObject(modelObjectLoader,
                320, 300, DepthHandler.ADVENTURER_DEPTH);  // TODO: Setup player starting position
    }

    /**
     * Returns game object to handle according to gameThreadID
     */
    public TreeSet<PhysicalObject> get(GameThreadID gameThreadID){
        switch (gameThreadID){
            case Inn:
                return getInnObjects();

            case Night:
                return getNightObjects();

            case Conversation:
                return getConversationObjects();
        }

        throw new IllegalArgumentException("The GameThreadID: " + gameThreadID + " is not implemented.");
    }

    private TreeSet<PhysicalObject> getInnObjects() {
        return new TreeSet<>(innLoader.getPhysicalObjects(playerPhysicalObject));
    }

    private TreeSet<PhysicalObject> getNightObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new NightUI());

        return objects;
    }

    private TreeSet<PhysicalObject> getConversationObjects(){
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new PhysicalObjectBackground(ConversationImagesID.InnDay2_fixed, GameInformation.WINDOW_WIDTH,
                GameInformation.WINDOW_HEIGHT));

        objects.add(new CharacterConversationUI(imageLoader, modelObjectLoader));

        objects.add(new MoneyUI(modelObjectLoader));

        return objects;
    }

    /**
     * Depending on gameThread, returns the correct camera.
     */
    public Camera getCamera(GameThreadID gameThreadID){
        if (gameThreadID == GameThreadID.Inn) {
            return new CameraFollowingPlayer(playerPhysicalObject);
        }

        return new CameraStill();
    }

    /**
     * Method needed to update the Model when loading a saved game.
     */
    public void setModel(ModelObjectLoader modelObjectLoader){
        this.modelObjectLoader = modelObjectLoader;
    }

}
