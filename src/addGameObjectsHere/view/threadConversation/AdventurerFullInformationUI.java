package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.characters.adventurers.stats.CharacterStats;
import addGameObjectsHere.model.characters.adventurers.stats.ClassPerk;
import addGameObjectsHere.model.characters.adventurers.stats.Perk;
import addGameObjectsHere.model.characters.adventurers.stats.Quirk;
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
    private final int PERK_DESCRIPTION_SIZE = 25;

    private Adventurer adventurer;

    private ButtonPhysicalObject returnButton;

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
                getDepth().add(2), "Return", Color.BLACK);
        returnButton.setSelected(true);

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
                imageLoader.getBaseFont(25), DisplayableText.Alignment.center));

        CharacterStats stats = adventurer.getUnit().getStats();
        Position statsBoxDim = new Position(clientBoxDim.getX()/1.5, clientBoxDim.getY()/4.0);
        Position statsPos = new Position(getPosition().getX() + clientBoxDim.getX(), getPosition().getY());
        Position statsDescDim = new Position(getWidth()*6/10, statsBoxDim.getY());

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                statsPos.getX(), statsPos.getY(), statsBoxDim.getX(), statsBoxDim.getY()),
                getDepth().add(3),"Att : " + stats.getAttack(), BoxCreator.Background.DarkPaper,
                imageLoader.getBaseFont(22), DisplayableText.Alignment.center));

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                statsPos.getX() + statsBoxDim.getX(), statsPos.getY(), statsDescDim.getX(), statsDescDim.getY()),
                getDepth().add(3),"Used for fighting encounters.", BoxCreator.Background.FadedPaper,
                imageLoader.getBaseFont(20), DisplayableText.Alignment.left));

        statsPos = statsPos.add(new Position(0, statsBoxDim.getY()));

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                statsPos.getX(), statsPos.getY(), statsBoxDim.getX(), statsBoxDim.getY()),
                getDepth().add(3),"Dex : " + stats.getDexterity(), BoxCreator.Background.DarkPaper,
                imageLoader.getBaseFont(22), DisplayableText.Alignment.center));

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                statsPos.getX() + statsBoxDim.getX(), statsPos.getY(), statsDescDim.getX(), statsDescDim.getY()),
                getDepth().add(3),"Used for trap encounters.", BoxCreator.Background.FadedPaper,
                imageLoader.getBaseFont(20), DisplayableText.Alignment.left));

        statsPos = statsPos.add(new Position(0, statsBoxDim.getY()));

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                statsPos.getX(), statsPos.getY(), statsBoxDim.getX(), statsBoxDim.getY()),
                getDepth().add(3),"Con : " + stats.getConstitution(), BoxCreator.Background.DarkPaper,
                imageLoader.getBaseFont(22), DisplayableText.Alignment.center));

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                statsPos.getX() + statsBoxDim.getX(), statsPos.getY(), statsDescDim.getX(), statsDescDim.getY()),
                getDepth().add(3),"Used for weather encounters.", BoxCreator.Background.FadedPaper,
                imageLoader.getBaseFont(20), DisplayableText.Alignment.left));

        statsPos = statsPos.add(new Position(0, statsBoxDim.getY()));

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                statsPos.getX(), statsPos.getY(), statsBoxDim.getX(), statsBoxDim.getY()),
                getDepth().add(3),"Hp : " + stats.getMaxHealth(), BoxCreator.Background.DarkPaper,
                imageLoader.getBaseFont(22), DisplayableText.Alignment.center));

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                statsPos.getX() + statsBoxDim.getX(), statsPos.getY(), statsDescDim.getX(), statsDescDim.getY()),
                getDepth().add(3),"Don't let it get to zero.", BoxCreator.Background.FadedPaper,
                imageLoader.getBaseFont(20), DisplayableText.Alignment.left));

        statsPos = statsPos.add(new Position(0, statsBoxDim.getY()));

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                statsPos.getX(), statsPos.getY(), statsBoxDim.getX(), statsBoxDim.getY()),
                getDepth().add(3),"Hon : " + stats.getHonour(), BoxCreator.Background.DarkPaper,
                imageLoader.getBaseFont(22), DisplayableText.Alignment.center));

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                statsPos.getX() + statsBoxDim.getX(), statsPos.getY(), statsDescDim.getX(), statsDescDim.getY()),
                getDepth().add(3),"Determines how trustworthy they are.", BoxCreator.Background.FadedPaper,
                imageLoader.getBaseFont(20), DisplayableText.Alignment.left));


        Position perksTitleDividerDim = new Position(getWidth()*4/5, getHeight()/10);
        Position perksTitleDividerPos = new Position(getPosition().getX(), cutPosition.getY() + statsBoxDim.getY());

        allUnmovingObjects.add(new TextBox( new BoundingArea(
                perksTitleDividerPos.getX(), perksTitleDividerPos.getY(), perksTitleDividerDim.getX(), perksTitleDividerDim.getY()),
                getDepth(), adventurer.getClassId() + "Perks", BoxCreator.Background.Wood,
                imageLoader.getBaseFont(Font.BOLD, PERK_TITLE_SIZE),
                DisplayableText.Alignment.left));

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                perksTitleDividerPos.getX(), perksTitleDividerPos.getY() + perksTitleDividerDim.getY(),
                perksTitleDividerDim.getX(), (int) (perksTitleDividerDim.getY()*0.75)),
                getDepth(), getPerkDescriptionLines(adventurer.getUnit().getStats().getClassPerks()),
                BoxCreator.Background.FadedPaper, imageLoader.getBaseFont(PERK_DESCRIPTION_SIZE),
                DisplayableText.Alignment.left));

        Position quirksTitleDividerDim = new Position(perksTitleDividerDim.getX(), perksTitleDividerDim.getY());
        Position quirksTitleDividerPos = new Position(getPosition().getX(), perksTitleDividerPos.getY() +
                perksTitleDividerDim.getY()*3);

        allUnmovingObjects.add(new TextBox( new BoundingArea(
                quirksTitleDividerPos.getX(), quirksTitleDividerPos.getY(), quirksTitleDividerDim.getX(), quirksTitleDividerDim.getY()),
                getDepth(), "Quirks", BoxCreator.Background.Wood,
                imageLoader.getBaseFont(Font.BOLD, PERK_TITLE_SIZE),
                DisplayableText.Alignment.left));

        allUnmovingObjects.add(new TextBox(new BoundingArea(
                quirksTitleDividerPos.getX(), quirksTitleDividerPos.getY() + quirksTitleDividerDim.getY(),
                quirksTitleDividerDim.getX(), quirksTitleDividerDim.getY()*2),
                getDepth(), getPerkDescriptionLines(adventurer.getUnit().getStats().getQuirks()),
                BoxCreator.Background.FadedPaper, imageLoader.getBaseFont(PERK_DESCRIPTION_SIZE),
                DisplayableText.Alignment.left));

    }

    public List<String> getPerkDescriptionLines(List<Perk> perks) {
        List<String> perkStr = new LinkedList<>();

        for (Perk perk : perks) {
            perkStr.add(perk.getName() + " : " + perk.getDescription());
        }
        return perkStr;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> setToReturn = new TreeSet<>();

        for (PhysicalObject obj : allUnmovingObjects) {
            setToReturn.addAll(obj.getImageObjects(cameraPosition, imageLoader));
        }
        setToReturn.addAll(returnButton.getImageObjects(cameraPosition, imageLoader));

        //setToReturn.add(new DisplayableShapeFilled(getDepth().add(90), new Rectangle(returnButtonPos.getX(),
        //        returnButtonPos.getY(),returnButtonDim.getX(), returnButtonDim.getY()), Color.CYAN));

        return setToReturn;
    }

    /**
     * Always returns "return" so far.
     */
    public ClientConversationDialogActionID getCurrentAction() {
        return ClientConversationDialogActionID.Return;
    }

    @Override
    public void next() {

    }

    @Override
    public void previous() {

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
