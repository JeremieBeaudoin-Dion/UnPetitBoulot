package addGameObjectsHere.view.threadInn.characters;

import addGameObjectsHere.model.characters.client.ClientID;

/**
 * Image representation of clients
 *
 * @author Mia Beaudoin-Dion
 */
public enum ClientImageID {

    Ranger, Rogue, Warrior, Man, OldWoman;

    public static ClientImageID getID(ClientID clientID) {
        switch (clientID) {
            case Rogue:
                return ClientImageID.Rogue;
            case Ranger:
                return ClientImageID.Ranger;
            case Warrior:
                return ClientImageID.Warrior;
            case Man:
                return ClientImageID.Man;
            case OldWoman:
                return ClientImageID.OldWoman;
        }

        throw new UnsupportedOperationException("The adventurer class: " + clientID.name() + " is not implemented yet.");
    }

}
