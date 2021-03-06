package addGameObjectsHere.model.characters.questGivers;

import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.quests.Quest;

/**
 * Template class for QuestGiver
 *
 * @author Mia Beaudoin-Dion
 */
public class QuestGiver extends Client {

    private Quest quest;
    private boolean givenQuest;

    public QuestGiver(Client client, Quest quest) {
        super(client);

        this.quest = quest;
        givenQuest = false;
    }

    /**
     * Returns quest object and sets quest as "Given"
     */
    public void giveQuest() {
        givenQuest = true;
    }

    /**
     * Returns the quest object
     */
    public Quest getQuest() {
        return quest;
    }


    public boolean hasGivenQuest() {
        return givenQuest;
    }

    @Override
    public void drinkOnce() {
        quest.drinkUp();
        super.drinkOnce();
    }
}
