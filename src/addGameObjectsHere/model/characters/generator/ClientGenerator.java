package addGameObjectsHere.model.characters.generator;

import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.characters.client.ClientID;
import addGameObjectsHere.model.characters.client.ClientTypeEnum;
import addGameObjectsHere.model.inn.Inn;
import addResourceLoaderHere.EnumHelper;

import java.util.Random;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class ClientGenerator {

    private Inn inn; // TODO: Eventually keep some clients and shuffle between old and new.

    private AdventurerGenerator adventurerGenerator;
    private QuestGiverGenerator questGiverGenerator;
    private VillagerGenerator villagerGenerator;
    private NameGenerator nameGenerator;
    private ClientIDGenerator clientIDGenerator;
    private ConversationDialogGenerator conversationDialogGenerator;
    private AlcoholToleranceGenerator alcoholToleranceGenerator;

    public ClientGenerator(Inn inn) {
        this.inn = inn;

        adventurerGenerator = new AdventurerGenerator();
        questGiverGenerator = new QuestGiverGenerator();
        villagerGenerator = new VillagerGenerator();

        clientIDGenerator = new ClientIDGenerator(inn);
        nameGenerator = new NameGenerator();
        conversationDialogGenerator = new ConversationDialogGenerator();
        alcoholToleranceGenerator = new AlcoholToleranceGenerator();
    }

    public Client getNextClient() {
        // Equal chances for adventurer, Questgiver and villager. - For now
        ClientTypeEnum clientType = EnumHelper.randomEnum(ClientTypeEnum.class);
        ClientID clientID = clientIDGenerator.generateClientID(clientType);

        Client client = new Client(clientID, nameGenerator.generate(),
                conversationDialogGenerator.generateConversationDialog(clientID),
                alcoholToleranceGenerator.getTolerance(clientID));

        switch(clientType) {
            case Villager:
                return villagerGenerator.generateVillager(client);
            case Adventurer:
                return adventurerGenerator.generateAdventurer(client);
            case QuestGiver:
                return questGiverGenerator.generateQuestGiver(client);
        }

        return null;
    }

}
