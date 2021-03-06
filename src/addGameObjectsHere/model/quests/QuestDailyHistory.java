package addGameObjectsHere.model.quests;

import addGameObjectsHere.model.characters.adventurers.AdventurerDailyHistory;
import addGameObjectsHere.model.quests.challenges.Challenge;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

/**
 * A representation of all challenges that the adventurers had to overcome
 * and their states after each challenge.
 *
 * @author Mia Beaudoin-Dion
 */
public class QuestDailyHistory {

    private List<Pair<Challenge, List<AdventurerDailyHistory>>> allEntries;

    public QuestDailyHistory() {
        allEntries = new LinkedList<>();
    }

    public void record(List<AdventurerDailyHistory> adventurers, Challenge challenge) {
        allEntries.add(new Pair<>(challenge, adventurers));
    }

    public List<Pair<Challenge, List<AdventurerDailyHistory>>> getAllEntries() {
        return allEntries;
    }

}
