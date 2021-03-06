package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.model.quests.Quest;
import addGameObjectsHere.view.threadInn.characters.ClientImageID;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Physical object that represents a box containing textList
 *
 * @author Mia Beaudoin-Dion
 */
public class ConversationReceiveQuestDialog extends ConversationInteractionObject {

    private final static int NUMBER_OF_AREAS = 3;

    private TextBox textBox;
    private QuestInformation questInformation;
    private ListOfChoices<ClientConversationDialogActionID> listOfChoices;

    /**
     * Constructor
     */
    public ConversationReceiveQuestDialog(ImageLoader imageLoader, BoundingArea boundingArea, DisplayableDepth depth,
                                          ClientImageID clientImageID, ImagePosition positionOfImage, Quest quest) {
        super(boundingArea, depth, clientImageID, positionOfImage);

        textBox = new TextBox(imageLoader, getSubAreaWithoutImageBox(NUMBER_OF_AREAS + 1, 0), getDepth(),
                quest.getDescription());
        questInformation = new QuestInformation(getSubAreaWithoutImageBox(NUMBER_OF_AREAS + 1, 1), getDepth(), quest);

        List<ClientConversationDialogActionID> choices = new LinkedList<>();
        choices.add(ClientConversationDialogActionID.AcceptQuest);
        choices.add(ClientConversationDialogActionID.Leave);

        listOfChoices = new ListOfChoices<>(getSubAreaWithoutImageBox(NUMBER_OF_AREAS - 1, 1),
                getDepth(), ListOfChoices.Pos.center, choices);
    }

    @Override
    protected TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.addAll(textBox.getImageObjects(cameraPosition, imageLoader));
        images.addAll(questInformation.getImageObjects(cameraPosition, imageLoader));
        images.addAll(listOfChoices.getImageObjects(cameraPosition, imageLoader));

        return images;
    }

    @Override
    public ClientConversationDialogActionID getCurrentAction() {
        return listOfChoices.getCurrentAction();
    }

    @Override
    public void next() {
        listOfChoices.next();
    }

    @Override
    public void previous() {
        listOfChoices.previous();
    }

}
