package addGameObjectsHere.gameButtons;

import jGameFramework.coreActions.GameEvent;
import jGameFramework.physicalObjects.Position;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * This is to reduce the number of button class to create
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ButtonCreator {

    private HashMap<ButtonID, Position> allButtonPositions;
    private HashMap<ButtonID, List<GameEvent>> allButtonEvents;

    public ButtonCreator() {
        allButtonPositions = createAllPositions();
        allButtonEvents = createAllGameEvents();
    }

    private static HashMap<ButtonID, Position> createAllPositions() {
        HashMap<ButtonID, Position> buttonPositions = new HashMap<>();

        /*
        buttonPositions.put(ButtonID.AdventurerTop, new Position(600, 300));
        buttonPositions.put(ButtonID.QuestTop, new Position(300, 300));
        buttonPositions.put(ButtonID.InnUpgrades, new Position(300, 600));*/

        buttonPositions.put(ButtonID.AdventurerTop, new Position(200, 0));
        buttonPositions.put(ButtonID.QuestTop, new Position(300, 0));
        buttonPositions.put(ButtonID.InnUpgrades, new Position(400, 0));

        return buttonPositions;
    }

    private static HashMap<ButtonID, List<GameEvent>> createAllGameEvents() {
        HashMap<ButtonID, List<GameEvent>> buttonPositions = new HashMap<>();

        List<GameEvent> gameEventList = new LinkedList<>();
        buttonPositions.put(ButtonID.QuestTop, gameEventList);
        buttonPositions.put(ButtonID.InnUpgrades, gameEventList);
        buttonPositions.put(ButtonID.AdventurerTop, gameEventList);

        /*
        gameEventList.add(new GameThreadEventNew(GameThreadID.QuestsTop));
        buttonPositions.put(ButtonID.QuestTop, gameEventList);

        gameEventList = new LinkedList<>();
        gameEventList.add(new GameThreadEventNew(GameThreadID.InnUpgradeMenu));
        buttonPositions.put(ButtonID.InnUpgrades, gameEventList);

        gameEventList = new LinkedList<>();
        gameEventList.add(new GameThreadEventNew(GameThreadID.AdventurerMenu));
        buttonPositions.put(ButtonID.AdventurerTop, gameEventList);*/

        return buttonPositions;
    }

    public GameButton getButton(ButtonID buttonID) {
        return new GameButton(buttonID, allButtonPositions.get(buttonID), allButtonEvents.get(buttonID));
    }

    /**
     * Returns a button a the specified position
     */
    public GameButton getButton(ButtonID buttonID, Position position) {
        return new GameButton(buttonID, position, allButtonEvents.get(buttonID));
    }

}
