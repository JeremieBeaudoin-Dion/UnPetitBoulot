package addGameObjectsHere.characters.fight;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class LineCharacter {

    private List<Character> characters;
    private CharacterLine line;

    public LineCharacter (CharacterLine line) {
        this.line = line;
        characters = new LinkedList<>();
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void doDamage(int dmg) {
        int trueDamage = dmg / characters.size();

        for(Character character : characters) {
            character.doDamage(trueDamage);
        }
    }

    public boolean isActive() {
        for(Character character : characters) {
            /*if (!character.isOut()) {
                return true;
            }*/
        }

        return false;
    }

    public boolean isCompatible (Character character) {
        return character.getStats().getLine() == line;
    }

}
