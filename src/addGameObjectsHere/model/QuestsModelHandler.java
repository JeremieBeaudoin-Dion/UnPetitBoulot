package addGameObjectsHere.model;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.quests.Quest;
import addGameObjectsHere.model.quests.QuestID;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

/**
 * Handles all quests as models.
 *
 * This helps with buying them, keeping the quests history, assigning an Adventurer to a
 * certain quest, etc.
 *
 * @author Mia Beaudoin-Dion
 */
public class QuestsModelHandler implements ImmutableQuestsModelHandler {

    private Map<QuestID, Quest> assignedQuests;

    public QuestsModelHandler() {
        assignedQuests = new EnumMap<>(QuestID.class);
    }

    public void addNewAssignedQuest(Quest quest) {
        assignedQuests.put(quest.getQuestID(), quest);
    }

    public void assignAdventurerToQuest(Adventurer adventurer, QuestID questID) {
        Quest quest = assignedQuests.get(questID);
        quest.addAdventurer(adventurer);
        adventurer.setIsAssignedQuest();
    }

    public Collection<Quest> getAllAssignedQuests() {
        return assignedQuests.values();
    }
}
