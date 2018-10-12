package addGameObjectsHere.model.quests;

import addResourceLoaderHere.EnumHelper;

import java.util.LinkedList;

/**
 * An object which helps create Quests.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class QuestGenerator {

    private QuestID[] allIDs;
    private int iterator;

    public QuestGenerator() {
        allIDs = QuestID.values();
        iterator = 0;
    }

    /**
     * Assuming each quest only gets returned one time
     */
    public Quest getNextQuest() {
        if (iterator >= allIDs.length) {
            return null;
        }

        QuestID questID = allIDs[iterator];
        iterator++;
        return getQuest(questID);
    }

    private Quest getQuest(QuestID id) {
        return new Quest(id, getChallengePerDay(id), EnumHelper.randomEnum(QuestImagesID.class), getReward(id));
    }

    /**
     * Using linked list to make sure challenges are ordered.
     */
    private static LinkedList<Challenge> getChallengePerDay(QuestID id) {
        switch (id) {
            case Test1:
                return getTest1Challenges();
        }

        throw new IllegalArgumentException("The questID: " + id + " is not implemented yet");
    }

    private static LinkedList<Challenge> getTest1Challenges() {
        return new LinkedList<>();
    }

    private static int getReward(QuestID id) {
        switch (id) {
            case Test1:
                return 200;
        }

        throw new IllegalArgumentException("The questID: " + id + " is not implemented yet");
    }
}
