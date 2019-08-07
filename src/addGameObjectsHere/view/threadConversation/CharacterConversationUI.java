package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.characters.client.ClientConversationDialogID;
import addGameObjectsHere.view.threadAll.images.BoxCreator;
import addGameObjectsHere.view.threadInn.characters.ClientImageID;
import addGameObjectsHere.view.threadInn.characters.PlayerImageID;
import addResourceLoaderHere.DepthHandler;
import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Most of the UI for character conversations is handled by the CharacterConversationUI
 *
 * @author Jérémie Beaudoin-Dion
 */
public class CharacterConversationUI extends PhysicalObject {

    private static final int TOTAL_NUMBER_OF_BOXES = 4;

    private Client currentConversationClient;
    private int numberOfBoxesCreated;
    private Stack<ConversationInteractionObject> allInteractionObjects;


    /**
     * Constructor
     */
    public CharacterConversationUI(Client currentConversationClient) {
        super(getStartBoundingArea(), false, DepthHandler.UI_BASE_DEPTH);

        numberOfBoxesCreated = 0;
        this.currentConversationClient = currentConversationClient;

        allInteractionObjects = new Stack<>();
        allInteractionObjects.push(new ConversationTextBox(getNextBoxArea(),
                getDepth().add(100), ClientImageID.getID(this.currentConversationClient.getClientID()),
                ConversationInteractionObject.ImagePosition.left,
                this.currentConversationClient.getDialog(ClientConversationDialogID.description)));

        List<String> listOfChoices = new LinkedList<>();
        listOfChoices.add("Talk");
        listOfChoices.add("Leave");
        listOfChoices.add("Quit");

        allInteractionObjects.push(new ConversationChoices(getNextBoxArea(),
                getDepth().add(100), PlayerImageID.InnKeeperMan,
                ConversationInteractionObject.ImagePosition.right, listOfChoices)); // TODO: FIX PLAYER IMAGE

    }

    private static BoundingArea getStartBoundingArea() {
        return new BoundingArea(GameInformation.WINDOW_WIDTH/5, GameInformation.WINDOW_HEIGHT/10,
                GameInformation.WINDOW_WIDTH*3/5, GameInformation.WINDOW_HEIGHT*8/10);
    }

    private BoundingArea getNextBoxArea() {
        int width = (int) getWidth();
        int height = (int) getHeight();

        BoundingArea area = new BoundingArea(getPosition().getX(), getPosition().getY() + height/TOTAL_NUMBER_OF_BOXES * numberOfBoxesCreated,
                width,height/TOTAL_NUMBER_OF_BOXES);

        numberOfBoxesCreated++;

        return area;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> setOfImages = new TreeSet<>();

        // Background
        setOfImages.add(new DisplayableImage(this, cameraPosition,
                imageLoader.getBox(new Position(getWidth(), getHeight()), BoxCreator.Background.Paper)));

        for (ConversationInteractionObject conversationObject : allInteractionObjects) {
            setOfImages.addAll(conversationObject.getImageObjects(cameraPosition, imageLoader));
        }

        return setOfImages;
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

    public void decide() {
        //allInteractionObjects.peek().
    }

    public void next() {
        allInteractionObjects.peek().next();
    }

    public void previous() {
        allInteractionObjects.peek().previous();
    }

}
