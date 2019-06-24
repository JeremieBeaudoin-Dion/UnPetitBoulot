package addResourceLoaderHere;

import addGameObjectsHere.view.adventurers.Player;
import jGameFramework.collections.InputActionKeyMap;
import jGameFramework.core.Loader;

/**
 * An instance of Loader that returns InputActionKeyMap,
 * a Map necessary to interpret input from the user.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ActionLoader implements Loader<InputActionKeyMap> {

    /**
     * For now, the inputs are going to be independent of GameThreadID
     */
    public InputActionKeyMap get(GameThreadID gameThreadID){
        if (gameThreadID == GameThreadID.Floor1) {
            return getFloor1KeyMap();
        }

        return getMenuKeyMap();
    }

    private InputActionKeyMap getMenuKeyMap(){
        return new InputActionKeyMap(null, null,
                ActionMapHelper.getAllMousePressedActions(),
                ActionMapHelper.getAllMouseReleaseActions(),
                ActionMapHelper.getAllMouseWheelActions());
    }

    private InputActionKeyMap getFloor1KeyMap(){
        return new InputActionKeyMap(
                ActionMapHelper.getAllMovePushActions(Player.class),
                ActionMapHelper.getAllMoveReleaseActions(Player.class),
                null, null, null);
    }

}

