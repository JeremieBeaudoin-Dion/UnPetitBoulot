package addGameObjectsHere.model.quests;

import java.util.HashMap;
import java.util.Map;

/**
 * An object which helps create Quests.
 *
 * @author Jérémie Beaudoin-Dion
 */
class QuestCreator {

    static Map<Integer, Challenge> getChallengePerDay(QuestID id) {
        switch (id) {
            case Test1:
                return getTest1Challenges();
        }

        throw new IllegalArgumentException("The questID: " + id + " is not implemented yet");
    }

    private static Map<Integer, Challenge> getTest1Challenges() {
        return new HashMap<>();
    }

    static int getReward(QuestID id) {
        switch (id) {
            case Test1:
                return 200;
        }

        throw new IllegalArgumentException("The questID: " + id + " is not implemented yet");
    }
}
