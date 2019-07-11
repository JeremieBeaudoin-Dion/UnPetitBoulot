package addResourceLoaderHere;

import addGameObjectsHere.view.threadInn.adventurers.PlayerPhysicalObject;
import addGameObjectsHere.view.threadNight.NightUI;
import jGameFramework.collections.InputActionKeyMap;
import jGameFramework.core.Loader;
import jGameFramework.coreActions.Action;
import jGameFramework.coreActions.GameEvent;

import java.awt.event.KeyEvent;
import java.util.HashMap;

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

        if (gameThreadID == GameThreadID.Night) {
            return getNightKeyMap();
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

        // Get all move actions from ActionMapHelper
        HashMap<Integer, Action> allPushActions = ActionMapHelper.getAllMovePushActions(PlayerPhysicalObject.class);
        HashMap<Integer, Action> allReleaseActions = ActionMapHelper.getAllMoveReleaseActions(PlayerPhysicalObject.class);

        // Set player interact action.
        try {
            Action playerNotInteracting = new Action(new GameEvent<>(PlayerPhysicalObject.class,
                    PlayerPhysicalObject.class.getMethod("setInteract")));
            allReleaseActions.put(KeyEvent.VK_SPACE, playerNotInteracting);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return new InputActionKeyMap(allPushActions, allReleaseActions,
                null, null, null);
    }

    private InputActionKeyMap getNightKeyMap(){

        // Get all move actions from ActionMapHelper
        HashMap<Integer, Action> allReleaseActions = new HashMap<>();

        // Set player interact action.
        try {
            Action pressSpaceBar = new Action(new GameEvent<>(NightUI.class,
                    NightUI.class.getMethod("endNight")));

            allReleaseActions.put(KeyEvent.VK_SPACE, pressSpaceBar);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return new InputActionKeyMap(null, allReleaseActions,
                null, null, null);
    }

}

