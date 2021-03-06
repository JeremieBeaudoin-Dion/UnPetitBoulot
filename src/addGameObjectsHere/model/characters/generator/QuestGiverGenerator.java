package addGameObjectsHere.model.characters.generator;

import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.characters.questGivers.QuestGiver;
import addGameObjectsHere.model.quests.Quest;
import addGameObjectsHere.model.quests.QuestID;

import java.util.LinkedList;

/**
 * @author Mia Beaudoin-Dion
 */
public class QuestGiverGenerator {

    public QuestGiver generateQuestGiver(Client client) {
        return new QuestGiver(client, getQuest());
    }

    private Quest getQuest() {
        return new Quest(QuestID.KoboldsProblem, "Kobolds have infiltrated my garden! Please take " +
                "care of them...", new LinkedList<>(), 150, 20);
    }

}
