package addGameObjectsHere.model;

/**
 * Handles money at a model level.
 *
 * @author Mia Beaudoin-Dion
 */
public class MoneyModelHandler implements ImmutableMoneyModelHandler {

    public static final int STARTING_AMOUNT = 100;

    private int totalMoney;

    public MoneyModelHandler() {
        totalMoney = STARTING_AMOUNT;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void buy(int amount) {
        if (totalMoney < amount) {
            throw new IllegalStateException("You can't buy that. You don't have enough money.");
        }

        totalMoney -= amount;
    }

    public void sell(int amount) {
        totalMoney += amount;
    }

}
