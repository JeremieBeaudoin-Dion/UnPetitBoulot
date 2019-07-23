package addGameObjectsHere.model.quests.fight;

import addGameObjectsHere.model.characters.adventurers.Unit;

import java.util.LinkedList;
import java.util.List;

/**
 * Characters are ordered in lines to distribute damage evenly
 *
 * @author Jérémie Beaudoin-Dion
 */
public class LineOfCharacters implements Comparable<LineOfCharacters> {

    private List<Unit> characters;
    private CharacterLine line;

    public LineOfCharacters(Unit character) {
        this.line = character.getLine();
        characters = new LinkedList<>();

        characters.add(character);
    }

    public void addCharacter(Unit character) {
        characters.add(character);
    }

    public void doDamage(CharacterDamage dmg) {
        dmg.setNumberOfAffectedCharacters(characters.size());

        for (Unit character : characters) {
            character.doDamage(dmg);
        }
    }

    public boolean isActive() {
        for(Unit character : characters) {
            if (!character.isOut()) {
                return true;
            }
        }

        return false;
    }

    public boolean isCompatible (Unit character) {
        return character.getLine() == line;
    }

    @Override
    public int compareTo(LineOfCharacters lineOfCharacters) {
        return line.compareTo(lineOfCharacters.line);
    }

}
