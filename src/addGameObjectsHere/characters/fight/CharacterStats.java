package addGameObjectsHere.characters.fight;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class CharacterStats {

    private int atk;
    private int initiative;
    private int endurance;

    private CharacterLine line;
    private CharacterHP characterHP;

    public CharacterStats(int atk, CharacterLine line, int hp, int initiative, int endurance) {
        this.atk = atk;
        this.initiative = initiative;
        this.endurance = endurance;

        this.line = line;
        this.characterHP = new CharacterHP(hp);
    }

    public CharacterHP getCharacterHP() {
        return characterHP;
    }

    public int getAtk() {
        return atk;
    }

    public CharacterLine getLine() {
        return line;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getEndurance() {
        return endurance;
    }

    public static void main(String[] args) {
        System.out.println("Testing lines: " + testLines());
    }

    private static boolean testLines() {

        CharacterLine line1 = CharacterLine.front;
        CharacterLine line2 = CharacterLine.middle;
        CharacterLine line3 = CharacterLine.back;
        CharacterLine line4 = CharacterLine.middle;

        return line1.compareTo(line2) < 0  && line3.compareTo(line2) > 0 && line2.compareTo(line4) == 0;
    }
}
