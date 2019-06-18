package addGameObjectsHere.model.quests;

import addGameObjectsHere.view.windows.quest.QuestImagesID;
import jGameFramework.core.ObjectWithID;

import java.util.LinkedList;

/**
 * A quest will have a series of challenges that the adventurers
 * will have to endure.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Quest extends ObjectWithID {

    private LinkedList<Challenge> dayChallengeMap;
    private QuestID id;
    private Party party;
    private QuestImagesID questZone;
    private int currentTimeInDay;
    private int reward;

    public Quest(QuestID id, LinkedList<Challenge> dayChallengeMap, QuestImagesID questZone, int reward) {
        this.dayChallengeMap = dayChallengeMap;
        this.id = id;
        this.reward = reward;
        this.currentTimeInDay = 0;
        this.questZone = questZone;
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

    public QuestImagesID getQuestZone() {
        return questZone;
    }
}
