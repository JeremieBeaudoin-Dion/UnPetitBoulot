package addGameObjectsHere.gameButtons;

import jGameFramework.coreActions.GameEvent;
import jGameFramework.coreActions.GameThreadEventQuit;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;

/**
 * A type of button that will quit the current thread.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameButtonQuit extends GameButton {

    public GameButtonQuit(ButtonID buttonID, Position startingPosition) {
        super(buttonID, startingPosition);
    }

    public GameButtonQuit(ButtonID buttonID, BoundingArea startBoundingArea) {
        super(buttonID, startBoundingArea);
    }

    @Override
    protected List<GameEvent> getMenuAction() {
        List<GameEvent> events = new LinkedList<>();

        events.add(new GameThreadEventQuit());

        return events;
    }
}
