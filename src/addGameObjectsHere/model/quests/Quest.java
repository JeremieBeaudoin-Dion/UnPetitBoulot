package addGameObjectsHere.model.quests;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import jGameFramework.core.ObjectWithID;

import java.util.List;

/**
 * A quest will have a series of challenges that the adventurers
 * will have to endure.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Quest extends ObjectWithID {

    private List<Challenge> dayChallengeMap;
    private QuestID id;
    private Party party;
    private String description;
    private int currentTimeInDay;
    private int reward;

    public Quest(QuestID id, String description, List<Challenge> dayChallengeMap, int reward) {
        this.dayChallengeMap = dayChallengeMap;
        this.id = id;
        this.reward = reward;
        this.currentTimeInDay = 0;
        this.description = description;
        this.party = new Party();
    }

    public void addAdventurer(Adventurer adventurer) {
        this.party.addUnit(adventurer.getUnit()); // TODO: Make sure this always works
    }

    public void doChallenge() {
        if (isDone()) {
            return;
        }

        party.handleChallenge(dayChallengeMap.get(currentTimeInDay));
        currentTimeInDay++;
    }

    public int getTimeToComplete() {
        return dayChallengeMap.size();
    }

    public int getReward() {
        return reward;
    }

    public String getName() {
        return id.toString();
    }

    public boolean isDone() {
        return currentTimeInDay == dayChallengeMap.size();
    }

    public boolean isFailed() {
        return !party.isActive();
    }

    public String getDescription() {
        return description;
    }

    public QuestID getQuestID() {
        return id;
    }

}
