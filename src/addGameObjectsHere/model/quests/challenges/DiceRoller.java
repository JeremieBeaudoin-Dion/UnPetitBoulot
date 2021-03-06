package addGameObjectsHere.model.quests.challenges;

import addGameObjectsHere.model.characters.generator.RandomGenerator;

public class DiceRoller extends RandomGenerator {

    public DiceRoller() {
        super();
    }

    public int rollDice(int numberOfDice) {
        int total = 0;

        for (int i = 0; i < numberOfDice; i++) {
            total += getRndInt(0, 2);
        }

        return total;
    }
}
