package addGameObjectsHere.model.characters.adventurers.stats;

/**
 * A perk is a specific trait of an adventurer.
 *
 * It can change the way they accomplish a quest, their stats, etc.
 */
public class ClassPerk implements Perk{

    private ClassPerkId classPerkId;

    public ClassPerk(ClassPerkId classPerkId) {
        this.classPerkId = classPerkId;
    }

    public String getName() {
        return classPerkId.toString();
    }

    public String getDescription() {
        return ClassPerkId.getDescription(classPerkId);
    }
}
