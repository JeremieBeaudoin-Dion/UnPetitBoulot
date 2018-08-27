package addGameObjectsHere.characters.fight;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class Character implements Comparable<Character> {

    private static int ID = 0;

    private CharacterStats characterStats;
    //private CharacterFightHPManager currentCharacterFightHPManager;
    private int characterID;

    public Character (CharacterStats characterStats) {
        this.characterStats = characterStats;

        characterID = ID++;
    }

    public void startFight() {
        //currentCharacterFightHPManager = new CharacterFightHPManager(characterStats.getMaxHp(), characterStats.getEndurance());
    }

    public void doDamage(int dmg) {
        //currentCharacterFightHPManager.doDamage(dmg);
        //characterStats.doDamage(dmg);
    }

    public CharacterStats getStats() {
        return characterStats;
    }

    //public boolean isOut() {
        //return currentCharacterFightHPManager.isOut();
    //}

    @Override
    public int compareTo(Character character) {

        if (this.characterStats.getInitiative() == character.characterStats.getInitiative()) {
            return characterID - character.characterID;
        }

        return characterStats.getInitiative() - character.characterStats.getInitiative();
    }

}
