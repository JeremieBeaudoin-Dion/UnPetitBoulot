package addGameObjectsHere.characters;

import java.util.List;

/**
 * A character stat holds different statistics that a character has. They are the following:
 *
 *      Health: Determines the amount of damage a character can endure
 *      Damage: The amount of damage this character inflicts to others when attacking
 *      Agility: The speed of the character (determines the turn order)
 *      Endurance: The amount of damage the character can sustain without being knocked out
 *      Line: The line in which the character stands in combat
 *      Special: Any special ability
 *
 * @author Jérémie Beaudoin-Dion
 */
public class CharacterStat {

    private double health;
    private double damage;
    private double agility;
    private double endurance;
    private int line;
    private List<SpecialStat> special;

    public CharacterStat(double health, double damage, double agility, double endurance, int line, List<SpecialStat> special) {
        this.health = health;
        this.damage = damage;
        this.agility = agility;
        this.endurance = endurance;
        this.line = line;
        this.special = special;
    }

    public double getHealth() {
        return health;
    }

    public double getDamage() {
        return damage;
    }

    public double getAgility() {
        return agility;
    }

    public double getEndurance() {
        return endurance;
    }

    public int getLine() {
        return line;
    }

    public List<SpecialStat> getSpecial() {
        return special;
    }
}
