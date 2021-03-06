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
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Most of the UI for character conversations is handled by the CharacterConversationUI
 *
 * @author Mia Beaudoin-Dion
 */
public class CharacterConversationUI extends PhysicalObject {

    private static final int TOTAL_NUMBER_OF_BOXES = 4;

    private ModelObjectLoader modelObjectLoader;
    private int numberOfBoxesCreated;
    private Stack<ConversationInteractionObject> allInteractionObjects;

    private List<GameEvent> actionsToDo;

    private ImageLoader imageLoader;

    private TextBox clientNameTextBox;

    private DecisionUI detailsWindow;

    /**
     * Constructor
     */
    public CharacterConversationUI(ImageLoader imageLoader, ModelObjectLoader modelObjectLoader) {
        super(getStartBoundingArea(), false, DepthHandler.UI_BASE_DEPTH);

        this.imageLoader = imageLoader;

        detailsWindow = null;

        numberOfBoxesCreated = 0;
        this.modelObjectLoader = modelObjectLoader;

        Client currentConversationClient = modelObjectLoader.getClientHandler().getCurrentConversationClient();

        clientNameTextBox = new TextBox(getClientNameBoundingArea(), getDepth(),
                currentConversationClient.getName(), BoxCreator.Background.FadedPaper,
                imageLoader.getBaseFont(Font.BOLD, 32), DisplayableText.Alignment.left);

        allInteractionObjects = new Stack<>();
        allInteractionObjects.push(new ConversationTextBox(imageLoader, getNextBoxArea(),
                getNextDepth(), ClientImageID.getID(currentConversationClient.getClientID()),
                ConversationInteractionObject.ImagePosition.left,
                currentConversationClient.getDialog(ClientConversationDialogID.description)));

        allInteractionObjects.push(new ConversationChoices<>(getNextBoxArea(),
                getNextDepth(), modelObjectLoader.getPlayerHandler().getImageID(),
                ConversationInteractionObject.ImagePosition.right,
                getListOfActionsForClient(currentConversationClient)));
    }

    private static BoundingArea getStartBoundingArea() {
        return new BoundingArea(GameInformation.WINDOW_WIDTH/5, GameInformation.WINDOW_HEIGHT/10,
                GameInformation.WINDOW_WIDTH*3/5, GameInformation.WINDOW_HEIGHT*8/10);
    }

    private BoundingArea getClientNameBoundingArea() {
        int width = (int) this.getWidth()*2/3;
        int height = (int) this.getHeight()/10;

        int x = getPosition().getX();
        int y = getPosition().getY() - height;

        return new BoundingArea(x, y, width, height);
    }

    private List<ClientConversationDialogActionID> getListOfActionsForClient(Client client) {
        List<ClientConversationDialogActionID> listOfChoices = new LinkedList<>();

        if (client instanceof Adventurer) {
            listOfChoices.add(ClientConversationDialogActionID.ProposeQuest);
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

        // Name of client
        setOfImages.addAll(clientNameTextBox.getImageObjects(cameraPosition, imageLoader));

        if (detailsWindow != null) {
            setOfImages.addAll(detailsWindow.getImageObjects(cameraPosition, imageLoader));
        } else {
            for (ConversationInteractionObject conversationObject : allInteractionObjects) {
                setOfImages.addAll(conversationObject.getImageObjects(cameraPosition, imageLoader));
            }
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
        ClientConversationDialogActionID action;

        if (detailsWindow == null) {
            action = (ClientConversationDialogActionID) allInteractionObjects.peek().getCurrentAction();
        } else {
            action = (ClientConversationDialogActionID) detailsWindow.getCurrentAction();
        }

        if (action == null) {
            action = ClientConversationDialogActionID.Leave;
        }

        Client currentClient = modelObjectLoader.getClientHandler().getCurrentConversationClient();

        switch (action) {
            case Leave:
                actionsToDo = new LinkedList<>();
                actionsToDo.add(new GameThreadEventQuit());
                break;

            case SeeQuest:
                if (currentClient instanceof  QuestGiver) {
                    QuestGiver questGiver = (QuestGiver) currentClient;

                    allInteractionObjects.push(new ConversationReceiveQuestDialog(this.imageLoader, getNextBoxArea(),
                            getNextDepth(), ClientImageID.getID(questGiver.getClientID()),
                            ConversationInteractionObject.ImagePosition.left, questGiver.getQuest()));
                }
                break;

            case ProposeQuest:
                if (currentClient instanceof  Adventurer) {
                    allInteractionObjects.push(new ConversationSeeAdventurerDialog(this.imageLoader, getNextBoxArea(),
                            getNextDepth(), modelObjectLoader.getPlayerHandler().getImageID(),
                            ConversationInteractionObject.ImagePosition.right,
                            (Adventurer) currentClient));
                }
                break;

            case AcceptQuest:
                QuestGiver questGiver = (QuestGiver) currentClient;
                modelObjectLoader.commitToQuest(questGiver);

                allInteractionObjects.push(new ConversationTextBox(imageLoader, getNextBoxArea(),
                        getNextDepth(), ClientImageID.getID(questGiver.getClientID()),
                        ConversationInteractionObject.ImagePosition.left,
                        questGiver.getDialog(ClientConversationDialogID.acceptQuest)));
                break;

            case Hire:
                detailsWindow = null;
                allInteractionObjects.peek().next();
                Adventurer adventurer = (Adventurer) currentClient;
                //modelObjectLoader.assignClientToQuest(adventurer, TODO!!!
                 //((ConversationSeeAdventurerDialog) allInteractionObjects.peek()).getCurrentQuest());

                allInteractionObjects.push(new ConversationTextBox(imageLoader, getNextBoxArea(),
                        getNextDepth(), ClientImageID.getID(adventurer.getClientID()),
                        ConversationInteractionObject.ImagePosition.left,
                        adventurer.getDialog(ClientConversationDialogID.hire)));
                break;

            case Details:
                Adventurer adventurer2 = (Adventurer) currentClient;

                detailsWindow = new AdventurerFullInformationUI(getBoundingArea(), false, getDepth().add(20),
                        adventurer2, this.imageLoader);
                break;

            case Return:
                detailsWindow = null;
                break;

        }
    }

    public void next() {
        if (detailsWindow == null) {
            allInteractionObjects.peek().next();
        } else {
            detailsWindow.next();
        }
    }

    public void previous() {
        if (detailsWindow == null) {
            allInteractionObjects.peek().previous();
        } else {
            detailsWindow.previous();
        }
    }

}
