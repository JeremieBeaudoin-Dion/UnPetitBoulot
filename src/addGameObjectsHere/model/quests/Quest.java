package addGameObjectsHere.model.quests;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.quests.challenges.Challenge;
import jGameFramework.core.ObjectWithID;

import java.util.List;

/**
 * A quest will have a series of challenges that the adventurers
 * will have to overcome.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Quest extends ObjectWithID {

    private List<Challenge> dayChallengeMap;
    private QuestID id;
    private Party party;
    private String description;
    private int currentTimeInDay;
    private int amountToAddToRewardPerDrink;
    private int reward;

    private QuestDailyHistory questDailyHistory;

    public Quest(QuestID id, String description, List<Challenge> dayChallengeMap, int baseReward,
                 int amountToAddToRewardPerDrink) {
        this.dayChallengeMap = dayChallengeMap;
        this.id = id;
        this.amountToAddToRewardPerDrink = amountToAddToRewardPerDrink;
        this.currentTimeInDay = 0;
        this.description = description;
        this.party = new Party();

        reward = baseReward;
        questDailyHistory = new QuestDailyHistory();
    }

    public void addAdventurer(Adventurer adventurer) {
        this.party.add(adventurer); // TODO: Make sure this always works
    }

    public void doChallenge() {
        if (isCompleted() || isFailed()) {
            return;
        }

        party.handleChallenge(dayChallengeMap.get(currentTimeInDay));
        questDailyHistory.record(party.getCurrentHistory(), dayChallengeMap.get(currentTimeInDay));
        currentTimeInDay++;
    }

    public int getTimeToComplete() {
        return dayChallengeMap.size();
    }

    /**
     * Every time the QuestGiver drinks, the reward goes up by that quest's number.
     */
    public void drinkUp() {
        reward += amountToAddToRewardPerDrink;
    }

    public int getTotalReward() {
        return reward;
    }

    public String getName() {
        return id.toString();
    }

    public boolean isCompleted() {
        return !isFailed() && currentTimeInDay == dayChallengeMap.size();
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
