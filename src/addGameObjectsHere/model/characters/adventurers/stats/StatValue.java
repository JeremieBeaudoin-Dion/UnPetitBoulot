package addGameObjectsHere.model.characters.adventurers.stats;

public class StatValue {

    private BaseStat stat;
    private int value;

    public StatValue(BaseStat stat, int value) {
        this.stat = stat;
        this.value = value;
    }

    public BaseStat getStat() {
        return stat;
    }

    public int getValue() {
        return value;
    }
}
