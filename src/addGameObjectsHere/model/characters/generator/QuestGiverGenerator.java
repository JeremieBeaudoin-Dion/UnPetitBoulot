package addGameObjectsHere.model.characters.generator;

import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.characters.questGivers.QuestGiver;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class QuestGiverGenerator {

    public QuestGiver generateQuestGiver(Client client) {
        return new QuestGiver(client);
    }

}
