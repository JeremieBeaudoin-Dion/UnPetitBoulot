package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.characters.client.ClientConversationDialogID;
import addGameObjectsHere.model.characters.questGivers.QuestGiver;
import addGameObjectsHere.view.threadAll.images.BoxCreator;
import addGameObjectsHere.view.threadInn.characters.ClientImageID;
import addResourceLoaderHere.DepthHandler;
import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import addResourceLoaderHere.ModelObjectLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.coreActions.GameThreadEventQuit;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
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

    private List<GameEvent> actionsToDo;

    private ImageLoader imageLoader;

    /**
     * Constructor
     */
    public CharacterConversationUI(ImageLoader imageLoader, ModelObjectLoader modelObjectLoader) {
        super(getStartBoundingArea(), false, DepthHandler.UI_BASE_DEPTH);

        this.imageLoader = imageLoader;

        numberOfBoxesCreated = 0;
        this.currentConversationClient = modelObjectLoader.getInn().getCurrentConversationClient();

        allInteractionObjects = new Stack<>();
        allInteractionObjects.push(new ConversationTextBox(imageLoader, getNextBoxArea(),
                getNextDepth(), ClientImageID.getID(this.currentConversationClient.getClientID()),
                ConversationInteractionObject.ImagePosition.left,
                this.currentConversationClient.getDialog(ClientConversationDialogID.description)));

        allInteractionObjects.push(new ConversationChoices(getNextBoxArea(),
                getNextDepth(), modelObjectLoader.getPlayerImageID(),
                ConversationInteractionObject.ImagePosition.right,
                getListOfActionsForClient(currentConversationClient)));

    }

    private static BoundingArea getStartBoundingArea() {
        return new BoundingArea(GameInformation.WINDOW_WIDTH/5, GameInformation.WINDOW_HEIGHT/10,
                GameInformation.WINDOW_WIDTH*3/5, GameInformation.WINDOW_HEIGHT*8/10);
    }

    private List<ClientConversationDialogActionID> getListOfActionsForClient(Client client) {
        List<ClientConversationDialogActionID> listOfChoices = new LinkedList<>();

        if (client instanceof Adventurer) {
            listOfChoices.add(ClientConversationDialogActionID.GiveQuest);
        } else if (client instanceof QuestGiver) {
            listOfChoices.add(ClientConversationDialogActionID.SeeQuest);
        }

        listOfChoices.add(ClientConversationDialogActionID.Leave);

        return listOfChoices;
    }

    private BoundingArea getNextBoxArea() {
        int width = (int) getWidth();
        int height = (int) getHeight();

        BoundingArea area = new BoundingArea(getPosition().getX(), getPosition().getY() + height/TOTAL_NUMBER_OF_BOXES * numberOfBoxesCreated,
                width,height/TOTAL_NUMBER_OF_BOXES);

        numberOfBoxesCreated++;

        return area;
    }

    private DisplayableDepth getNextDepth() {
        return getDepth().add(100);
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
        List<GameEvent> toReturn = actionsToDo;

        actionsToDo = null;

        return toReturn;
    }

    /**
     * Returns true if the object should be disposed of
     */
    @Override
    public boolean dispose() {
        return false;
    }

    public void decide() {
        ClientConversationDialogActionID action = allInteractionObjects.peek().getCurrentAction();

        switch (action) {
            case Leave:
                actionsToDo = new LinkedList<>();
                actionsToDo.add(new GameThreadEventQuit());
                break;

            case SeeQuest:
                QuestGiver questGiver = (QuestGiver) this.currentConversationClient;

                allInteractionObjects.push(new ConversationReceiveQuestDialog(this.imageLoader, getNextBoxArea(),
                        getNextDepth(), ClientImageID.getID(questGiver.getClientID()),
                        ConversationInteractionObject.ImagePosition.left, questGiver.getQuest()));
                break;
        }
    }

    public void next() {
        allInteractionObjects.peek().next();
    }

    public void previous() {
        allInteractionObjects.peek().previous();
    }

}
