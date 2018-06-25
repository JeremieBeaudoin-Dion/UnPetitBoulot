package addGameObjectsHere.characters.generator;

import addGameObjectsHere.characters.AdventurerTrait;
import addGameObjectsHere.characters.AdventurerTraitEnum;
import addResourceLoaderHere.EnumHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerTraitGenerator {

    private Random random;

    public AdventurerTraitGenerator() {
        random = new Random();
    }

    public List<AdventurerTrait> generate(int min, int max) {

        List<AdventurerTrait> generatedList = new LinkedList<>();

        int numberOfTraits = random.nextInt((max - min) + 1) + min;

        for (int i=0; i<numberOfTraits; i++) {
            generatedList.add(new AdventurerTrait(EnumHelper.randomEnum(AdventurerTraitEnum.class)));
        }

        return generatedList;
    }

}
