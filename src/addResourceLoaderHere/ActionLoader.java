package addResourceLoaderHere;

import addGameObjectsHere.camera.CameraMovingInBox;
import jGameFramework.collections.InputActionKeyMap;
import jGameFramework.core.Loader;

/**
 * An instance of Loader that returns InputActionKeyMap,
 * a Map necessary to interpret input from the user.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ActionLoader implements Loader<InputActionKeyMap> {

    public InputActionKeyMap get(GameThreadID gameThreadID){

        switch (gameThreadID){
            case Menu:
                return getMenuKeyMap();
        }

        throw new IllegalArgumentException("The GameThreadID: " + gameThreadID + " is not implemented.");
    }

    private InputActionKeyMap getMenuKeyMap(){
        return new InputActionKeyMap(ActionMapHelper.getAllMovePushActions(CameraMovingInBox.class),
                ActionMapHelper.getAllMoveReleaseActions(CameraMovingInBox.class),
                ActionMapHelper.getAllMousePressedActions(), ActionMapHelper.getAllMouseReleaseActions());
    }
}

