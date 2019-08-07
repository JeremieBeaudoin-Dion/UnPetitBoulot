package addGameObjectsHere.model.characters.generator;

import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.characters.client.ClientID;
import addGameObjectsHere.model.characters.client.ClientTypeEnum;
import addGameObjectsHere.model.inn.Inn;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class ClientIDGenerator {

    private Inn inn;

    private EnumMap<ClientTypeEnum, List<ClientID>> lowLevel;

    public ClientIDGenerator(Inn inn) {
        this.inn = inn;

        lowLevel = generateLowLevel();
    }

    private EnumMap<ClientTypeEnum, List<ClientID>> generateLowLevel() {
        EnumMap<ClientTypeEnum, List<ClientID>> lowLevel = new EnumMap<>(ClientTypeEnum.class);

        List<ClientID> adventurers = new LinkedList<>();
        adventurers.add(ClientID.Rogue);
        adventurers.add(ClientID.Warrior);
        adventurers.add(ClientID.Ranger);
        lowLevel.put(ClientTypeEnum.Adventurer, adventurers);

        List<ClientID> questGivers = new LinkedList<>();
        questGivers.add(ClientID.OldWoman);
        lowLevel.put(ClientTypeEnum.QuestGiver, questGivers);

        List<ClientID> villagers = new LinkedList<>();
        villagers.add(ClientID.Man);
        lowLevel.put(ClientTypeEnum.Villager, villagers);

        return lowLevel;
    }


    public ClientID generateClientID(ClientTypeEnum clientType) {
        List<ClientID> currentList = lowLevel.get(clientType);

        Collections.shuffle(currentList);

        return currentList.get(0);
    }

}
