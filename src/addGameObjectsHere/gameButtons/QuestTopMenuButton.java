package addGameObjectsHere.gameButtons;

import addResourceLoaderHere.GameThreadID;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.coreActions.GameThreadEventNew;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;

/**
 * The button to go to the Quest Top Menu
 *
 * @author Jérémie Beaudoin-Dion
 */
public class QuestTopMenuButton extends GameButton {

    private static final int STARTING_X = 300;
    private static final int STARTING_Y = 300;

    public QuestTopMenuButton() {
        super(ButtonID.QuestTop, new Position(STARTING_X, STARTING_Y));
    }

    @Override
    protected List<GameEvent> getMenuAction() {
        List<GameEvent> actionsToDo = new LinkedList<>();

        actionsToDo.add(new GameThreadEventNew(GameThreadID.QuestsTop));

        return actionsToDo;
    }
}