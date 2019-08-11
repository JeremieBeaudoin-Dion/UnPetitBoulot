package addGameObjectsHere.model.characters.questGivers;

import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.quests.Quest;

/**
 * Template class for QuestGiver
 *
 * @author Jérémie Beaudoin-Dion
 */
public class QuestGiver extends Client {

    private Quest quest;

    public QuestGiver(Client client, Quest quest) {
        super(client);

        this.quest = quest;
    }

    public Quest getQuest() {
        return quest;
    }
}
