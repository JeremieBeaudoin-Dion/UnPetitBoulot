package addGameObjectsHere.characters.generator;

import addGameObjectsHere.characters.Adventurer;
import addGameObjectsHere.characters.AdventurerClassEnum;
import addResourceLoaderHere.EnumHelper;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class UnitGenerator {

    private NameGenerator nameGenerator;
    private UnitStatGenerator unitStatGenerator;
    private AdventurerTraitGenerator adventurerTraitGenerator;

    public UnitGenerator() {

        this.nameGenerator = new NameGenerator();
        this.unitStatGenerator = new UnitStatGenerator();
        this.adventurerTraitGenerator = new AdventurerTraitGenerator();

    }

    public Adventurer generateAdventurer(int fame) {

        AdventurerClassEnum adventurerClassEnum = EnumHelper.randomEnum(AdventurerClassEnum.class);

        return new Adventurer(adventurerClassEnum, unitStatGenerator.generate(adventurerClassEnum), adventurerTraitGenerator.generate(0, 2), 0, nameGenerator.generate());

    }


    /*
     *****************************************************************************************************************
     * TEST
     *****************************************************************************************************************
     */
    public static void main(String[] args) {

        UnitGenerator unitGenerator = new UnitGenerator();

        for (int i=0; i< 20; i++) {
            Adventurer test = unitGenerator.generateAdventurer(0);
            System.out.println(test.getName());
        }

    }

}
