package addGameObjectsHere.view.threadInn.characters;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.characters.questGivers.QuestGiver;
import addGameObjectsHere.view.threadAll.images.IconImageID;
import addGameObjectsHere.view.threadInn.inn.ServingTablePhysicalObject;
import addResourceLoaderHere.DepthHandler;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.collections.SetHelper;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.TreeSet;

/**
 * Clients roaming around in the inn. They have a MODEL object which represent
 * their name, price, etc.
 *
 * @author Mia Beaudoin-Dion
 */
public class ClientPhysicalObject extends CharacterObjectMovingWithShadow {

    private enum CurrentAction {
        findingASeat, idle
    }

    private Client client;

    private CurrentAction currentAction;

    private boolean isServed;

    private boolean dispose;

    /**
     * Constructor
     */
    public ClientPhysicalObject(int x, int y, DisplayableDepth depth, Client client, Enum imageID) {
        super(x, y, depth, imageID);

        currentAction = CurrentAction.findingASeat;
        isServed = false;
        this.client = client;

        dispose = false; // For testing only
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> setToReturn = new TreeSet<>();

        setToReturn.addAll(super.getImageObjects(cameraPosition, imageLoader));

        if (isServed) {
            Position beerPosition = getPositionAccordingToCamera(cameraPosition).add(
                    new Position(getWidth(), -getHeight()+10));
            BufferedImage beerImage = imageLoader.getImageFromID(IconImageID.beer);

            setToReturn.add(new DisplayableImage(beerPosition, DepthHandler.ADVENTURER_SHADOW_DEPTH, beerImage));

            Position beerTextPosition = beerPosition.add(new Position(beerImage.getWidth()/2, beerImage.getHeight()/2));

            setToReturn.add(new DisplayableText(beerTextPosition, DepthHandler.ADVENTURER_SHADOW_DEPTH,
                    Integer.toString(client.getNumberOfDrinksTonight()), imageLoader.getBaseFont(45), Color.BLACK,
                    DisplayableText.Alignment.center));
        }

        return setToReturn;
    }

    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        TreeSet<PhysicalObject> updateObjects = new TreeSet<>();
        SetHelper.addAllIfNotNull(updateObjects, super.update(surroundings));

        switch (currentAction) {
            case idle:
                return updateObjects;

            case findingASeat:
                //System.out.println("didn't find a seat..." + getPosition());
                findASeat(surroundings);
        }

        return updateObjects;
    }

    private void findASeat(TreeSet<PhysicalObject> surroundings) {
        for (PhysicalObject obj: surroundings) {
            if (obj instanceof ServingTablePhysicalObject) {
                ((ServingTablePhysicalObject) obj).sit(this);
                currentAction = CurrentAction.idle;
                return;
            }
        }

        // Move around and find a seat -> Todo: have a client helper to get next direction to table.
    }

    public boolean isServed() {
        return isServed;
    }

    public void giveDrink() {
        isServed = true;
        client.drinkOnce();

        if (client.hasDrankOverTolerance()) {
            dispose = true;
            // TODO: Add GOTO outside... (see client helper)
        }

    }

    public void resetDrink() {
        isServed = false;
    }

    @Override
    public boolean dispose() {
        if (client instanceof QuestGiver) {
            if (((QuestGiver) client).hasGivenQuest()) {
                return true;  // TODO: Temporary
            }
        }

        if (client instanceof Adventurer) {
            if (((Adventurer) client).isAssignedQuest()) {
                return true;  // TODO: Temporary
            }
        }

        return dispose;
    }

    public void setDispose() {
        dispose = true;
    }

    /**
     * Returns the client object for conversations
     */
    public Client getClient() {
        return client;
    }

    @Override
    public List<GameEvent> getAction() {
        return super.getAction();
    }
}
