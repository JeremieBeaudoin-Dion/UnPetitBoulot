package addResourceLoaderHere;

import addGameObjectsHere.model.*;
import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.characters.questGivers.QuestGiver;
import addGameObjectsHere.model.quests.Quest;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Creates and holds necessary model objects.
 *
 * The model objects keep necessary game information between threads. This
 * can contain, for example, how much money the player will have.
 *
 * GameEvents to SAVE the game will save ModelObjectLoader and current PhysicalObjects on screen.
 *
 * INNKEEPER
 *      For this game, the ModelObjectLoader will act as the main class to send to PhysicalObjects
 *      when interacting with model objects.
 *
 *      This will also return the ModelHandlers themselves. These are to be used to get specific
 *      attributes concerning the ModelHandlers.
 *
 * @author Mia Beaudoin-Dion
 */
public class ModelObjectLoader implements Serializable {

    private ClientModelHandler clientModelHandler;
    private HistoryModelHandler historyModelHandler;
    private InnModelHandler innModelHandler;
    private MoneyModelHandler moneyModelHandler;
    private PlayerModelHandler playerModelHandler;
    private QuestsModelHandler questsModelHandler;

    public ModelObjectLoader() {
        clientModelHandler = new ClientModelHandler();
        historyModelHandler = new HistoryModelHandler();
        innModelHandler = new InnModelHandler();
        moneyModelHandler = new MoneyModelHandler();
        playerModelHandler = new PlayerModelHandler();
        questsModelHandler = new QuestsModelHandler();
    }

    /*
     * Actions to do
     */
    public List<Client> generateDayClients() {
        return clientModelHandler.generateDayClients(innModelHandler.getNumberOfAdventurersToCreate());
    }

    public void commitToQuest(QuestGiver questGiver) {
        questsModelHandler.addNewAssignedQuest(questGiver.getQuest());
        questGiver.giveQuest();
    }

    public void assignClientToQuest(Adventurer adventurer, Quest quest) {
        //moneyModelHandler.buy(adventurer.getCost()); TODO!!!
        questsModelHandler.assignAdventurerToQuest(adventurer, quest.getQuestID());
    }

    public void sellDrinkToClient() {
        moneyModelHandler.sell(innModelHandler.getCostOfDrinks());
    }

    public void setCurrentConversationClient(Client client) {
        clientModelHandler.setCurrentConversationClient(client);
    }

    /*
     * Getters
     */
    public ImmutableMoneyModelHandler getMoneyHandler() {
        return moneyModelHandler;
    }

    public ImmutableInnModelHandler getInnHandler() {
        return innModelHandler;
    }

    public ImmutableClientModelHandler getClientHandler() {
        return clientModelHandler;
    }

    public ImmutableQuestsModelHandler getQuestsHandler() {
        return questsModelHandler;
    }

    public ImmutablePlayerModelHandler getPlayerHandler() {
        return playerModelHandler;
    }

    public ImmutableHistoryModelHandler getHistoryHandler() {
        return historyModelHandler;
    }

}
