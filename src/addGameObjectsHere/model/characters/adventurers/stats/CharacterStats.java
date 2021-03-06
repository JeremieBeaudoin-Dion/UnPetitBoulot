package addGameObjectsHere.model.characters.adventurers.stats;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.Perk;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.PerkType;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.StatBuff;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.Trigger;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.perks.BaseStatCreationPerk;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Container class for statistics about an adventurer.
 *
 * @author Mia Beaudoin-Dion
 */
public class CharacterStats {

    public enum StatIsBuffed {
        nerfed, normal, buffed
    }

    private CharacterHealth characterHealth;
    private List<Perk> allPerks;
    private EnumMap<BaseStat, Integer> allBaseStats;
    private List<StatBuff> activeBuffs;

    public CharacterStats(EnumMap<BaseStat, Integer> allBaseStats, List<Perk> allPerks) {
        this.allBaseStats = allBaseStats;
        this.allPerks = allPerks;

        this.characterHealth = new CharacterHealth(allBaseStats.get(BaseStat.Hp));
        activeBuffs = new LinkedList<>();
    }

    public void setOwner(Adventurer adventurer) {
        for (Perk perk : allPerks) {
            if (perk instanceof BaseStatCreationPerk) {
                ((BaseStatCreationPerk) perk).setOwner(adventurer);
            }
        }
    }

    public void doDamage(int damage) {
        characterHealth.doDamage(damage);
    }

    public void startFight() {
        characterHealth.startFight();
    }

    public boolean isDead() {
        return characterHealth.isDead();
    }

    public void addBuff(StatBuff buff) {
        activeBuffs.add(buff);
    }

    /*
     * Display
     */
    public int get(BaseStat stat) {
        updateStats();

        int baseAmount = allBaseStats.get(stat);

        for (StatBuff buff : activeBuffs) {
            if (buff.isChangeStat(stat)) {
                baseAmount = buff.getAmount(baseAmount);
            }
        }

        return Math.max(baseAmount, 0);
    }

    protected void updateStats() {
        activeBuffs.removeIf(StatBuff::isDelete);
    }

    /**
     * Returns the status of a stat. If it is affected by a Buff.
     */
    public StatIsBuffed compareStatToOriginal(BaseStat stat) {
        int baseAmount = allBaseStats.get(stat);
        int actualAmount = get(stat);

        if (stat == BaseStat.Cut) {
            if (actualAmount > baseAmount) {
                return StatIsBuffed.nerfed;
            } else if (actualAmount < baseAmount) {
                return StatIsBuffed.buffed;
            }
        } else {
            if (actualAmount > baseAmount) {
                return StatIsBuffed.buffed;
            } else if (actualAmount < baseAmount) {
                return StatIsBuffed.nerfed;
            }
        }

        return StatIsBuffed.normal;
    }

    public int getMaxHealth() {
        return allBaseStats.get(BaseStat.Hp);
    }
    public int getCurrentHealth() {
        return get(BaseStat.Hp);
    }
    public List<Perk> getClassPerks() {
        List<Perk> classPerks = new LinkedList<>();

        for (Perk perk : allPerks) {
            if (perk.getPerkType() == PerkType.classPerk) {
                classPerks.add(perk);
            }
        }

        return classPerks;
    }
    public List<Perk> getQuirks() {
        List<Perk> quirks = new LinkedList<>();

        for (Perk perk : allPerks) {
            if (perk.getPerkType() != PerkType.classPerk) {
                quirks.add(perk);
            }
        }

        return quirks;
    }
    public List<Perk> getAllPerks() {
        return allPerks;
    }

}
