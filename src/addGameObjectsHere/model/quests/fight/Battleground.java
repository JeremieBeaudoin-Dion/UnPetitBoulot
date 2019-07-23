package addGameObjectsHere.model.quests.fight;

import addGameObjectsHere.model.characters.adventurers.Unit;

import java.util.*;

/**
 * The Battleground orders adventurers and enemies in lines, distributing
 * damage evenly in the lines.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Battleground {

    private Stack<LineOfCharacters> friendlyCharacters;
    private Stack<LineOfCharacters> enemyCharacters;

    public Battleground(List<Unit> friendlyCharacters, List<Unit> enemyCharacters) {

        this.friendlyCharacters = createLines(friendlyCharacters);
        this.enemyCharacters = createLines(enemyCharacters);

    }

    private Stack<LineOfCharacters> createLines(List<Unit> characterList) {
        return toStack(createList(characterList));
    }

    private Stack<LineOfCharacters> toStack(List<LineOfCharacters> lines) {
        Collections.reverse(lines);

        Stack<LineOfCharacters> stackOfLines = new Stack<>();

        for (LineOfCharacters line : lines) {
            stackOfLines.push(line);
        }

        return stackOfLines;
    }

    private List<LineOfCharacters> createList(List<Unit> characterList) {
        List<LineOfCharacters> allLines = new LinkedList<>();

        for (Unit character : characterList) {
            boolean added = false;

            for (LineOfCharacters line : allLines) {
                if (line.isCompatible(character)) {
                    line.addCharacter(character);
                    added = true;
                    break;
                }
            }

            if (!added) {
                allLines.add(new LineOfCharacters(character));
            }
        }

        return allLines;
    }

    public boolean isActive() {
        return !friendlyCharacters.empty() && !enemyCharacters.empty();
    }

    public void doDamage(CharacterDamage dmg) {

        if (dmg.isFriendly()) {
            doDamageTo(dmg, friendlyCharacters);
        } else {
            doDamageTo(dmg, enemyCharacters);
        }

    }

    private void doDamageTo(CharacterDamage dmg, Stack<LineOfCharacters> line) {
        line.peek().doDamage(dmg);

        if (!line.peek().isActive()) {
            line.pop();
        }
    }

}
