package addGameObjectsHere.model;

import addGameObjectsHere.view.threadInn.characters.PlayerImageID;

/**
 * This handles everything that has to do with the Player.
 *
 * For now, only the PlayerImageID is kept, but this will increase with time.
 *
 * @author Mia Beaudoin-Dion
 */
public class PlayerModelHandler implements ImmutablePlayerModelHandler {

    private PlayerImageID imageID;

    public PlayerModelHandler() {
        imageID = PlayerImageID.InnKeeperMan;
    }

    public PlayerImageID getImageID() {
        return imageID;
    }
}
