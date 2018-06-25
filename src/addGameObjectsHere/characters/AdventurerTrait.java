package addGameObjectsHere.characters;

import addResourceLoaderHere.EnumHelper;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerTrait {

    private AdventurerTraitEnum type;
    private String name;

    public AdventurerTrait(AdventurerTraitEnum type) {
        this.type = type;
        this.name = EnumHelper.toName(type.toString());
    }

    public String getName() {
        return name;
    }

}
