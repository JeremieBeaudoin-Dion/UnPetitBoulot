package addGameObjectsHere.model.quests;

import java.util.LinkedList;

/**
 * A quest will have a series of challenges that the adventurers
 * will have to endure.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Quest {

    private LinkedList<Challenge> dayChallengeMap;
    private QuestID id;
    private Party party;
    private int currentTimeInDay;
    private int reward;

    public Quest(QuestID id, LinkedList<Challenge> dayChallengeMap, int reward) {
        this.dayChallengeMap = dayChallengeMap;
        this.id = id;
        this.reward = reward;
        this.currentTimeInDay = 0;
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

    public void assignParty(Party party) {
        this.party = party;
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

}
