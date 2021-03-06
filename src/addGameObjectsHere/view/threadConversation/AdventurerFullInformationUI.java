package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.characters.adventurers.stats.*;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.Perk;
import addGameObjectsHere.view.threadAll.ButtonPhysicalObject;
import addGameObjectsHere.view.threadAll.images.BoxCreator;
import addGameObjectsHere.view.threadInn.characters.ClientPhysicalObject;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class AdventurerFullInformationUI extends DecisionUI {

    private final int PERK_TITLE_SIZE = 32;
    private final int PERK_NAME_SIZE = 24;
    private final int PERK_DESCRIPTION_SIZE = 20;

    private Adventurer adventurer;

    private ButtonPhysicalObject returnButton;
    private ButtonPhysicalObject hireButton;

    private List<PhysicalObject> allUnmovingObjects;

    /**
     * Constructor
     */
    public AdventurerFullInformationUI(BoundingArea boundingArea, boolean isObstacle, DisplayableDepth depth,
                                       Adventurer adventurer, ImageLoader imageLoader) {
        super(boundingArea, isObstacle, depth);

        this.adventurer = adventurer;

        Position returnButtonDim = new Position(getWidth()/4, getHeight()/8);
        Position returnButtonPos = new Position(getPosition().getX() + getWidth() - returnButtonDim.getX() - 10,
                getPosition().getY() + getHeight() - returnButtonDim.getY() - 10);

        returnButton = new ButtonPhysicalObject(new BoundingArea(returnButtonPos.getX(), returnButtonPos.getY(),
                returnButtonDim.getX(), returnButtonDim.getY()),
                getDepth().add(2), "Return", Color.WHITE);
        returnButton.setSelected(true);

        hireButton = new ButtonPhysicalObject(new BoundingArea(returnButtonPos.getX() - returnButtonDim.getX() - 5,
                returnButtonPos.getY(),
                returnButtonDim.getX(), returnButtonDim.getY()),
                getDepth().add(2), "Hire", Color.WHITE);
        hireButton.setSelected(false);

        setUIObjects(imageLoader);
    }

    private void setUIObjects(ImageLoader imageLoader) {
        allUnmovingObjects = new LinkedList<>();

        Position clientBoxDim = new Position(ClientPhysicalObject.SHADOW_SIZE.clone().getX(),
                ClientPhysicalObject.SHADOW_SIZE.clone().getY());

        allUnmovingObjects.add(new ClientBoxUI(new BoundingArea(getPosition().getX(), getPosition().getY(),
                clientBoxDim.getX(), clientBoxDim.getY()),false, getDepth().add(1), adventurer));

        Position cutPosition = new Position(getPosition().getX(), getPosition().add(clientBoxDim).getY());

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                cutPosition.getX(), cutPosition.getY(), clientBoxDim.getX(), clientBoxDim.getY()/4),
                getDepth().add(2),"Cut : " + adventurer.getCost() + "%", BoxCreator.Background.DarkPaper,
                imageLoader.getBaseFont(25), DisplayableText.Alignment.center, getStatColor(BaseStat.Cut)));

        CharacterStats adventurerStats = adventurer.getUnit().getStats();
        Position statsBoxDim = new Position(clientBoxDim.getX()/1.5, clientBoxDim.getY()/4.0);
        Position statsPos = new Position(getPosition().getX() + clientBoxDim.getX(), getPosition().getY());
        Position statsDescDim = new Position(getWidth()*6/10, statsBoxDim.getY());

        for (BaseStat stat : BaseStat.values()) {
            if (stat != BaseStat.Cut && stat != BaseStat.Desp) {
                allUnmovingObjects.add(new TextBox(new BoundingArea(
                    statsPos.getX(), statsPos.getY(), statsBoxDim.getX(), statsBoxDim.getY()),
                    getDepth().add(3),stat.toString() + " : " + adventurerStats.get(stat), BoxCreator.Background.DarkPaper,
                    imageLoader.getBaseFont(22), DisplayableText.Alignment.center, getStatColor(stat)));

                allUnmovingObjects.add(new TextBox(new BoundingArea(
                        statsPos.getX() + statsBoxDim.getX(), statsPos.getY(), statsDescDim.getX(), statsDescDim.getY()),
                        getDepth().add(4),BaseStat.getDescription(stat), BoxCreator.Background.FadedPaper,
                        imageLoader.getBaseFont(20), DisplayableText.Alignment.left));

                statsPos = statsPos.add(new Position(0, statsBoxDim.getY()));
            }
        }

        Position perksTitleDividerDim = new Position(getWidth()*5/6, getHeight()/11);
        Position perksTitleDividerPos = new Position(getPosition().getX(), cutPosition.getY() + statsBoxDim.getY());

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                perksTitleDividerPos.getX(), perksTitleDividerPos.getY(), perksTitleDividerDim.getX(), perksTitleDividerDim.getY()),
                getDepth(), adventurer.getClassId() + " Perks", BoxCreator.Background.Wood,
                imageLoader.getBaseFont(Font.BOLD, PERK_TITLE_SIZE),
                DisplayableText.Alignment.left));

        Position perkPosition = new Position(perksTitleDividerPos.getX(),
                perksTitleDividerPos.getY() + perksTitleDividerDim.getY());

        for (Perk perk : adventurer.getUnit().getStats().getClassPerks()) {
            addPerkTextBox(imageLoader, perk, perkPosition, perksTitleDividerDim);
            perkPosition = perkPosition.add(new Position(0, perksTitleDividerDim.getY()));
        }

        allUnmovingObjects.add(new TextBox( new BoundingArea(
                perkPosition.getX(), perkPosition.getY(),
                perksTitleDividerDim.getX(), perksTitleDividerDim.getY()),
                getDepth(), "Quirks", BoxCreator.Background.Wood,
                imageLoader.getBaseFont(Font.BOLD, PERK_TITLE_SIZE),
                DisplayableText.Alignment.left));

        perkPosition = perkPosition.add(new Position(0, perksTitleDividerDim.getY()));

        for (Perk perk : adventurer.getUnit().getStats().getQuirks()) {
            addPerkTextBox(imageLoader, perk, perkPosition, perksTitleDividerDim);
            perkPosition = perkPosition.add(new Position(0, perksTitleDividerDim.getY()));
        }

    }

    protected Paint getStatColor(BaseStat stat) {
        CharacterStats.StatIsBuffed statIsBuffed = adventurer.getUnit().getStats().compareStatToOriginal(stat);

        if (statIsBuffed == CharacterStats.StatIsBuffed.buffed) {
            return new Color(0, 60, 0);
        } else if (statIsBuffed == CharacterStats.StatIsBuffed.nerfed) {
            return new Color(60, 0, 0);
        } else {
            return new Color(0, 0, 0);
        }
    }

    public void addPerkTextBox(ImageLoader imageLoader, Perk perk, Position currentPosition, Position perkDim) {
        Position nameDim = new Position(perkDim.getX() * 0.3, perkDim.getY());

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                currentPosition.getX(), currentPosition.getY(),
                nameDim.getX(), nameDim.getY()),
                getDepth(), perk.getName(), BoxCreator.Background.DarkPaper,
                imageLoader.getBaseFont(Font.BOLD, PERK_NAME_SIZE),
                DisplayableText.Alignment.center));

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                currentPosition.getX() + nameDim.getX(), currentPosition.getY(),
                perkDim.getX() - nameDim.getX(), perkDim.getY()),
                getDepth(), perk.getDescription(), BoxCreator.Background.FadedPaper,
                imageLoader.getBaseFont(Font.PLAIN, PERK_DESCRIPTION_SIZE),
                DisplayableText.Alignment.left));
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> setToReturn = new TreeSet<>();

        for (PhysicalObject obj : allUnmovingObjects) {
            setToReturn.addAll(obj.getImageObjects(cameraPosition, imageLoader));
        }
        setToReturn.addAll(returnButton.getImageObjects(cameraPosition, imageLoader));
        setToReturn.addAll(hireButton.getImageObjects(cameraPosition, imageLoader));

        return setToReturn;
    }

    /**
     * Always returns "return" so far.
     */
    public ClientConversationDialogActionID getCurrentAction() {
        if (returnButton.isSelected()) {
            return ClientConversationDialogActionID.Return;
        } else {
            return ClientConversationDialogActionID.Hire;
        }
    }

    @Override
    public void next() {
        if (returnButton.isSelected()) {
            hireButton.setSelected(true);
            returnButton.setSelected(false);
        } else {
            hireButton.setSelected(false);
            returnButton.setSelected(true);
        }
    }

    @Override
    public void previous() {
        if (returnButton.isSelected()) {
            hireButton.setSelected(true);
            returnButton.setSelected(false);
        } else {
            hireButton.setSelected(false);
            returnButton.setSelected(true);
        }
    }

    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    @Override
    public boolean dispose() {
        return false;
    }
}
