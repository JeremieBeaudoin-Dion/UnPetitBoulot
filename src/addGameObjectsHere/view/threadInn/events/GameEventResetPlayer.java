package addGameObjectsHere.view.threadInn.events;

import addGameObjectsHere.view.threadInn.characters.PlayerPhysicalObject;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.exceptions.ActionInvocationException;

import java.lang.reflect.Method;

/**
 * A game thread event that will be called when night time is up.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameEventResetPlayer extends GameEvent<PlayerPhysicalObject> {

    public GameEventResetPlayer() {
        super(PlayerPhysicalObject.class, getNightMethod());
    }

    private static Method getNightMethod(){
        try {
            return PlayerPhysicalObject.class.getMethod("resetPlayer");
        } catch (NoSuchMethodException e) {
            throw new ActionInvocationException("resetPlayer", PlayerPhysicalObject.class, e.getMessage());
        }
    }

}
