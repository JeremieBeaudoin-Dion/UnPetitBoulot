package addResourceLoaderHere;

import addGameObjectsHere.view.threadConversation.CharacterConversationUI;
import addGameObjectsHere.view.threadInn.characters.PlayerPhysicalObject;
import addGameObjectsHere.view.threadNight.NightUI;
import jGameFramework.collections.InputActionKeyMap;
import jGameFramework.core.Loader;
import jGameFramework.coreActions.*;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * An instance of Loader that returns InputActionKeyMap,
 * a Map necessary to interpret input from the user.
 *
 * @author Mia Beaudoin-Dion
 */
public class ActionLoader implements Loader<InputActionKeyMap> {

    /**
     * For now, the inputs are going to be independent of GameThreadID
     */
    public InputActionKeyMap get(GameThreadID gameThreadID){
        if (gameThreadID == GameThreadID.Inn) {
            return getFloor1KeyMap();
        }

        if (gameThreadID == GameThreadID.Night) {
            return getNightKeyMap();
        }

        if (gameThreadID == GameThreadID.Conversation) {
            return getConversationKeyMap();
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
            Action playerInteracting = new ActionTimed(new GameEvent<>(PlayerPhysicalObject.class,
                    PlayerPhysicalObject.class.getMethod("setInteract")), 200);
            allReleaseActions.put(KeyEvent.VK_SPACE, playerInteracting);

            allPushActions.put(KeyEvent.VK_SPACE, new ActionNone());

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return new InputActionKeyMap(allPushActions, allReleaseActions,
                null, null, null);
    }

    private InputActionKeyMap getNightKeyMap(){

        HashMap<Integer, Action> allReleaseActions = new HashMap<>();

        // Set player interact action.
        try {
            Action pressSpaceBar = new ActionTimed(new GameEvent<>(NightUI.class,
                    NightUI.class.getMethod("endNight")), 200);

            allReleaseActions.put(KeyEvent.VK_SPACE, pressSpaceBar);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return new InputActionKeyMap(ActionMapHelper.getEquivalentMapWithoutActions(allReleaseActions),
                allReleaseActions,null, null, null);
    }

    private InputActionKeyMap getConversationKeyMap(){
        // Get all move actions from ActionMapHelper
        HashMap<Integer, Action> allReleaseActions = new HashMap<>();

        // Set player interact action.
        try {
            Action releaseSpaceBar = new Action(new GameEvent<>(CharacterConversationUI.class,
                    CharacterConversationUI.class.getMethod("decide")));
            allReleaseActions.put(KeyEvent.VK_SPACE, releaseSpaceBar);

            Action escapeKey = new Action(new GameThreadEventQuit());
            allReleaseActions.put(KeyEvent.VK_ESCAPE, escapeKey);

            Action releaseLeft = new Action(new GameEvent<>(CharacterConversationUI.class,
                    CharacterConversationUI.class.getMethod("previous")));
            allReleaseActions.put(KeyEvent.VK_LEFT, releaseLeft);

            Action releaseA = new Action(new GameEvent<>(CharacterConversationUI.class,
                    CharacterConversationUI.class.getMethod("previous")));
            allReleaseActions.put(KeyEvent.VK_A, releaseA);

            Action releaseRight = new Action(new GameEvent<>(CharacterConversationUI.class,
                    CharacterConversationUI.class.getMethod("next")));
            allReleaseActions.put(KeyEvent.VK_RIGHT, releaseRight);

            Action releaseD = new Action(new GameEvent<>(CharacterConversationUI.class,
                    CharacterConversationUI.class.getMethod("next")));
            allReleaseActions.put(KeyEvent.VK_D, releaseD);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return new InputActionKeyMap(ActionMapHelper.getEquivalentMapWithoutActions(allReleaseActions), allReleaseActions,
                null, null, null);
    }

}

