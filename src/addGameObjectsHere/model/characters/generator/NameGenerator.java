package addGameObjectsHere.model.characters.generator;

import java.util.Collections;
import java.util.Stack;

/**
 * TODO: This could be a better name gen, but for now it will do
 *
 * @author Mia Beaudoin-Dion
 */
class NameGenerator {

    private Stack<String> allnames;

    NameGenerator() {

        allnames = new Stack<>();
        populate();

    }

    String generate() {
        if (allnames.empty()) {
            populate();
        }

        return allnames.pop();
    }

    private void populate() {
        allnames.add("Baruh");
        allnames.add("Bromwood");
        allnames.add("Ferdi");
        allnames.add("Wirth");
        allnames.add("Terris");
        allnames.add("Nellie");
        allnames.add("Agda");
        allnames.add("Kristen");
        allnames.add("Luwanna");
        allnames.add("Makena");
        allnames.add("Shandy");
        allnames.add("Nolan");
        allnames.add("Uto");
        allnames.add("Efrain");
        allnames.add("Archerd");
        allnames.add("Wagner");
        allnames.add("Kester");

        Collections.shuffle(allnames);
    }

}
