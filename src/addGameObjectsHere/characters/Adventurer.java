package addGameObjectsHere.characters;

import addGameObjectsHere.characters.fight.CharacterDamage;
import addGameObjectsHere.characters.fight.CharacterStats;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * An adventurer is a character which has a name and stats.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Adventurer extends Unit {

    private String name;
    private AdventurerClassEnum adventurerClass;
    private List<AdventurerTrait> adventurerTraits;

    private static final Color NAME_TEXT_COLOR = Color.WHITE;
    private static final Font NAME_TEXT_FONT = new Font("Century Schoolbook", Font.PLAIN, 18);

    /**
     * Constructors
     */
    public Adventurer(AdventurerClassEnum adventurerClass, CharacterStats characterStats, List<AdventurerTrait> adventurerTraits, double experience, String name) {
        super(characterStats);

        this.name = name;
        this.adventurerClass = adventurerClass;
        this.adventurerTraits = adventurerTraits;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> setOfImages = new TreeSet<>();

        setOfImages.add(new DisplayableImage(this, cameraPosition, imageLoader.getAdventurerImage(adventurerClass)));

        Position positionToAddToText = new Position(getWidth()/2.5, getHeight()/2 + NAME_TEXT_FONT.getSize()/2);

        setOfImages.add(new DisplayableText(getPositionAccordingToCamera(cameraPosition).add(positionToAddToText),
                getDepth().add(1), name, NAME_TEXT_FONT, NAME_TEXT_COLOR));

        return setOfImages;
    }

    public String getName() {
        return name + ", the " + getAdjective() + " " + adventurerClass.toString();
    }

    private String getAdjective() {
        if (adventurerTraits.size() == 0) {
            return "average";
        }

        return adventurerTraits.get(0).getName().toLowerCase();
    }

    //TODO: Change this according to the traits
    @Override
    public void doDamage(CharacterDamage damage) {
        super.doDamage(damage);
    }
}
