package addGameObjectsHere.model.characters.adventurers.stats.statsChange;

import addGameObjectsHere.model.characters.adventurers.stats.BaseStat;

/**
 * A change (negative or positive) to any adventurer statistics.
 */
public class StatBuff {

    private boolean isPermanent;
    private BaseStat statBuffed;
    private double amount;
    private boolean isAddition;
    private Trigger endTrigger;
    private Perk creator;
    private boolean delete;

    public StatBuff(BaseStat statBuffed, double amount, boolean isAddition) {
        isPermanent = true;
        this.statBuffed = statBuffed;
        this.amount = amount;
        this.isAddition = isAddition;
    }

    public StatBuff(BaseStat statBuffed, double amount, boolean isAddition, Trigger endTrigger) {
        isPermanent = false;
        this.statBuffed = statBuffed;
        this.amount = amount;
        this.isAddition = isAddition;
        this.endTrigger = endTrigger;
    }

    public boolean isTriggered(Trigger trigger) {
        return endTrigger == trigger;
    }

    public boolean isChangeStat(BaseStat stat) {
        return statBuffed == stat;
    }

    public int getAmount(int baseAmount) {
        if (isAddition) {
            return baseAmount + (int) amount;
        } else {
            return (int) (baseAmount * amount);
        }
    }

    public double getAmount(double baseAmount) {
        if (isAddition) {
            return baseAmount + amount;
        } else {
            return baseAmount * amount;
        }
    }

    public void setDelete(boolean isDelete) {
        this.delete = isDelete;
    }

    public boolean isDelete() {
        return delete;
    }
}
