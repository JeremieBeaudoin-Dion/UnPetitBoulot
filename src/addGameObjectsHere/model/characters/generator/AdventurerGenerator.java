package addGameObjectsHere.model.characters.generator;

import addGameObjectsHere.model.characters.Adventurer;
import addGameObjectsHere.model.characters.AdventurerClassEnum;
import addGameObjectsHere.model.inn.Inn;
import addResourceLoaderHere.EnumHelper;

/**
 * A generator that creates adventurers
 *
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerGenerator {

    private NameGenerator nameGenerator;
    private UnitStatGenerator unitStatGenerator;
    private AdventurerTraitGenerator adventurerTraitGenerator;

    private Inn inn;

    public AdventurerGenerator(Inn inn) {
        this.nameGenerator = new NameGenerator();
        this.unitStatGenerator = new UnitStatGenerator();
        this.adventurerTraitGenerator = new AdventurerTraitGenerator();

        this.inn = inn;
    }

    /**
     * TODO: Make the fame relevant
     */
    public Adventurer generateAdventurer() {

        AdventurerClassEnum adventurerClassEnum = EnumHelper.randomEnum(AdventurerClassEnum.class);

        return new Adventurer(adventurerClassEnum, unitStatGenerator.generate(adventurerClassEnum),
                adventurerTraitGenerator.generate(0, 2), 100, 0, nameGenerator.generate());
    }

    /*
     *****************************************************************************************************************
     * TEST
     *****************************************************************************************************************
     */
    public static void main(String[] args) {

        AdventurerGenerator adventurerGenerator = new AdventurerGenerator(new Inn());

        for (int i=0; i< 20; i++) {
            Adventurer test = adventurerGenerator.generateAdventurer();
            System.out.println(test.getName());
        }

    }

}
