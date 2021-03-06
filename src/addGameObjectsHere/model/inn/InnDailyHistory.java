package addGameObjectsHere.model.inn;

import addGameObjectsHere.model.quests.Quest;

import java.util.LinkedList;
import java.util.List;

/**
 * A model class to keep history of all daily transactions in the inn.
 *
 * @author Mia Beaudoin-Dion
 */
public class InnDailyHistory {

    private List<Quest> allQuests;
    private int moneyWonInDrinks;
    private int moneyWonInQuests;

    public InnDailyHistory() {
        reset();
    }

    public void reset() {
        allQuests = new LinkedList<>();
        moneyWonInDrinks = 0;
        moneyWonInQuests = 0;
    }

    public void addQuest(Quest quest) {
        allQuests.add(quest);

        if (quest.isCompleted()) {
            moneyWonInQuests += quest.getTotalReward();
        }
    }

    public void sellDrink(int money) {
        moneyWonInDrinks += money;
    }


}
