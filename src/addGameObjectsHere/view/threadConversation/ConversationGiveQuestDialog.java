package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.model.quests.Quest;
import addGameObjectsHere.model.quests.QuestID;
import addGameObjectsHere.view.threadInn.characters.ClientImageID;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.util.*;

/**
 * Physical object that represents a box containing textList
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ConversationGiveQuestDialog extends ConversationInteractionObject {

    private final static int NUMBER_OF_AREAS = 2;

    private ListOfChoices<Enum> listOfChoices;

    private TextBox textBox;
    private Map<QuestID, Quest> quests;

    /**
     * Constructor
     */
    public ConversationGiveQuestDialog(ImageLoader imageLoader, BoundingArea boundingArea, DisplayableDepth depth,
                                       Enum imageID, ImagePosition positionOfImage, Collection<Quest> quests) {
        super(boundingArea, depth, imageID, positionOfImage);

        this.quests = getMapOfQuests(quests);

        List<Enum> choices = new LinkedList<>();

        for (Quest q : quests) {
            choices.add(q.getQuestID());
        }

        choices.add(ClientConversationDialogActionID.Leave);

        listOfChoices = new ListOfChoices<>(getSubAreaWithoutImageBox(NUMBER_OF_AREAS, 0), getDepth(),
                ListOfChoices.Pos.marginsX, choices);

        textBox = new TextBox(imageLoader, getSubAreaWithoutImageBox(NUMBER_OF_AREAS, 1), getDepth(),
                "");

        updateTextBoxWithCurrentChoice(listOfChoices.getCurrentAction());
    }

    private Map<QuestID, Quest> getMapOfQuests(Collection<Quest> quests) {
        Map<QuestID, Quest> questMap = new EnumMap<>(QuestID.class);

        for (Quest q : quests) {
            questMap.put(q.getQuestID(), q);
        }

        return questMap;
    }

    @Override
    protected TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.addAll(textBox.getImageObjects(cameraPosition, imageLoader));
        images.addAll(listOfChoices.getImageObjects(cameraPosition, imageLoader));

        return images;
    }

    @Override
    public ClientConversationDialogActionID getCurrentAction() {
        if (listOfChoices.getCurrentAction() == ClientConversationDialogActionID.Leave) {
            return ClientConversationDialogActionID.Leave;
        }

        return ClientConversationDialogActionID.GiveQuest;
    }

    public QuestID getCurrentQuest() {
        return ((QuestID) listOfChoices.getCurrentAction());
    }

    @Override
    public void next() {
        listOfChoices.next();
        updateTextBoxWithCurrentChoice(listOfChoices.getCurrentAction());
    }

    @Override
    public void previous() {
        listOfChoices.previous();
        updateTextBoxWithCurrentChoice(listOfChoices.getCurrentAction());
    }

    private void updateTextBoxWithCurrentChoice(Enum currentChoice) {
        if (currentChoice == ClientConversationDialogActionID.Leave) {
            textBox.updateText("");
        }

        if (currentChoice instanceof QuestID) {
            textBox.updateText(quests.get(currentChoice).getDescription());
        }
    }


}
