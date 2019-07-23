package addGameObjectsHere.model.characters.questGivers;

import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.characters.client.ClientID;

import java.util.List;

/**
 * Template class for QuestGiver
 *
 * @author Jérémie Beaudoin-Dion
 */
public class QuestGiver extends Client {

    public QuestGiver(String name, List<String> description, int alcoholTolerance) {
        super(name, description, alcoholTolerance, ClientID.OldWoman); // TODO: Fix this.
    }

}
