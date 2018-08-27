package addGameObjectsHere.characters.fight;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * During an adventure, a party consists of a set of characters
 * who will endure the following threats:
 *      - Fights
 *      - Traps
 *      - Etc.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Party {

    private List<Character> characters;

    public Party (List<Character> characters) {
        this.characters = characters;
    }

    private static Stack<LineCharacter> createLines() {
        Stack<LineCharacter> lines = new Stack<>();


        return lines;
    }

    public void fight() {
        for (Character character : characters) {
            character.startFight();

        }
    }


}
