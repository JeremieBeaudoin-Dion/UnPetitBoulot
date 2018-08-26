package addGameObjectsHere.characters;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.Position;

import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class Adventurer extends Unit {

    private String name;
    private AdventurerClassEnum adventurerClassEnum;
    private List<AdventurerTrait> adventurerTraits;

    /**
     * Constructors
     */
    public Adventurer(AdventurerClassEnum adventurerClassEnum, UnitStat unitStat, List<AdventurerTrait> adventurerTraits, double experience, String name) {
        super(unitStat);

        this.name = name;
        this.adventurerClassEnum = adventurerClassEnum;
        this.adventurerTraits = adventurerTraits;
    }

    /**
     * Returns the image representation of this physical object
     * <p>
     * The ImageObject will have a relative position depending
     * on the position of the CameraWithEdges.
     *
     * @param cameraPosition
     * @param imageLoader
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        return null;
    }

    public String getName() {
        return name + ", the " + getAdjective() + " " + adventurerClassEnum.toString();
    }

    private String getAdjective() {
        if (adventurerTraits.size() == 0) {
            return "average";
        }

        return adventurerTraits.get(0).getName().toLowerCase();
    }

}
