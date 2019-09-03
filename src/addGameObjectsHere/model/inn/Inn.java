package addGameObjectsHere.model.inn;


import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.quests.Quest;
import addGameObjectsHere.model.quests.QuestID;

import java.util.*;

/**
 * This class represents the inn of the user. It holds adventurers,
 * upgrades, etc.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Inn {

    public static final int COST_OF_DRINK = 4;

    private int money;
    private int reputation;
    private int comfort;

    private Client currentConversationClient;

    private Map<QuestID, Quest> assignedQuests;

    private InnDailyHistory innDailyHistory;

    /**
     * Constructor
     */
    public Inn() {
        money = 100;
        reputation = 0;
        comfort = 10; // Temporary for testing
        assignedQuests = new HashMap<>();
        innDailyHistory = new InnDailyHistory();
    }

    public int getNumberOfAdventurersToCreate() {
        return this.comfort; // This will change...
    }

    /**
     * Adds an adventurer at their cost
     */
    public void assignAdventurerToQuest(Adventurer adventurer, QuestID questID) {
        Quest quest = assignedQuests.get(questID);

        if (!canBuyAdventurer(adventurer, quest)) {
            throw new IllegalStateException("Cannot buy adventurer if you don't have enough money!!!");
        }

        money -= adventurer.getCost(quest);
        quest.addAdventurer(adventurer);
        adventurer.setIsAssignedQuest();
    }

    public boolean canBuyAdventurer(Adventurer adventurer, Quest quest) {
        return money > adventurer.getCost(quest);
    }

    public void addQuest(Quest quest) {
        assignedQuests.put(quest.getQuestID(), quest);
    }

    /**
     * By giving alcohol to an adventurer, gets money.
     */
    public void serveAdventurer(int numberOfAdventurers) {
        money += numberOfAdventurers * COST_OF_DRINK;
        innDailyHistory.sellDrink(numberOfAdventurers * COST_OF_DRINK);
    }

    public int getMoney() {
        return money;
    }

    public int getReputation() {
        return reputation;
    }

    public void setCurrentConversationClient(Client client) {
        this.currentConversationClient = client;
    }

    public Client getCurrentConversationClient() {
        return currentConversationClient;
    }

    public Collection<Quest> getAssignedQuests() {
        return assignedQuests.values();
    }

    public InnDailyHistory getHistoryOfTransactions() {
        return innDailyHistory;
    }

    /**
     * Handles quests and adventurers.
     */
    public void doNightCycle() {
        for (Quest quest : assignedQuests.values()) {
            quest.doChallenge();

            if (quest.isFailed() || quest.isCompleted()) {
                innDailyHistory.addQuest(quest);
                assignedQuests.remove(quest.getQuestID());
            }

        }
    }

}
