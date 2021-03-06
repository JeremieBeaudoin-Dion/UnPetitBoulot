package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Physical object that represents a box containing textList
 *
 * @author Mia Beaudoin-Dion
 */
public class ConversationSeeAdventurerDialog extends ConversationInteractionObject {

    private final static int NUMBER_OF_AREAS = 3;

    private ListOfChoices<ClientConversationDialogActionID> listOfChoices;

    private TextBox adventurerDescriptionTextBox;
    private AdventurerQuickInformation adventurerInformation;
    private Adventurer adventurer;

    /**
     * Constructor
     */
    public ConversationSeeAdventurerDialog(ImageLoader imageLoader, BoundingArea boundingArea, DisplayableDepth depth,
                                           Enum imageID, ImagePosition positionOfImage, Adventurer adventurer) {
        super(boundingArea, depth, imageID, positionOfImage);

        this.adventurer = adventurer;

        List<ClientConversationDialogActionID> choices = new LinkedList<>();

        choices.add(ClientConversationDialogActionID.Details);
        choices.add(ClientConversationDialogActionID.Hire);
        choices.add(ClientConversationDialogActionID.Leave);

        listOfChoices = new ListOfChoices<>(getSubAreaWithoutImageBox(NUMBER_OF_AREAS, 0), getDepth(),
                ListOfChoices.Pos.marginsX, choices);

        adventurerDescriptionTextBox = new TextBox(getSubAreaWithoutImageBox(NUMBER_OF_AREAS, 1), getDepth(),
                adventurer.getDescription(), null, imageLoader.getBaseFont(Font.BOLD, 25),
                DisplayableText.Alignment.center);

        adventurerInformation = new AdventurerQuickInformation(getSubAreaWithoutImageBox(NUMBER_OF_AREAS, 2), getDepth(),
                adventurer);

        updateTextBoxWithCurrentChoice(listOfChoices.getCurrentAction());
    }

    @Override
    protected TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.addAll(adventurerDescriptionTextBox.getImageObjects(cameraPosition, imageLoader));
        images.addAll(adventurerInformation.getImageObjects(cameraPosition, imageLoader));
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
        updateTextBoxWithCurrentChoice(listOfChoices.getCurrentAction());
    }

    @Override
    public void previous() {
        listOfChoices.previous();
        updateTextBoxWithCurrentChoice(listOfChoices.getCurrentAction());
    }

    private void updateTextBoxWithCurrentChoice(Enum currentChoice) {
        if (currentChoice == ClientConversationDialogActionID.Leave) {
            adventurerDescriptionTextBox.updateText("");
            adventurerInformation.setVisible(false);
        } else {
            adventurerDescriptionTextBox.updateText(adventurer.getDescription());
            adventurerInformation.setVisible(true);
        }
    }

}
