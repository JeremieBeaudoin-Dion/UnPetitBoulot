package addGameObjectsHere.view.threadInn.events;

import addGameObjectsHere.view.threadInn.handler.ClientHandlerPhysicalObject;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.exceptions.ActionInvocationException;

import java.lang.reflect.Method;

/**
 * A game thread event that will be called when night time is up.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameEventResetAdventurers extends GameEvent<ClientHandlerPhysicalObject> {

    public GameEventResetAdventurers() {
        super(ClientHandlerPhysicalObject.class, getNightMethod());
    }

    private static Method getNightMethod(){
        try {
            return ClientHandlerPhysicalObject.class.getMethod("isNight");
        } catch (NoSuchMethodException e) {
            throw new ActionInvocationException("isNight", ClientHandlerPhysicalObject.class, e.getMessage());
        }
    }

}
