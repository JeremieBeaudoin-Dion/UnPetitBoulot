package addGameObjectsHere.controller.gameButtons;

import jGameFramework.coreActions.GameEvent;
import jGameFramework.coreActions.GameThreadEventQuit;
import jGameFramework.display.DisplayableDepth;
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

    public GameButtonQuit(ButtonID buttonID, Position startingPosition, DisplayableDepth depth) {
        super(buttonID, startingPosition, getMenuAction(), depth);
    }

    public GameButtonQuit(ButtonID buttonID, BoundingArea startBoundingArea, DisplayableDepth depth) {
        super(buttonID, startBoundingArea, getMenuAction(), depth);
    }

    private static List<GameEvent> getMenuAction() {
        List<GameEvent> events = new LinkedList<>();

        events.add(new GameThreadEventQuit());

        return events;
    }
}
