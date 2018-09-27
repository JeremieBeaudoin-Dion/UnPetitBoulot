package addGameObjectsHere.controller.windows;

import addGameObjectsHere.model.characters.Adventurer;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.*;

/**
 * A type of form which takes an adventurer and shows information about that adventurer.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerForm extends Form {

    private Adventurer adventurer;

    private static final Font TITLE_FONT = new Font("Century Schoolbook", Font.BOLD, 20);
    private static final Font BODY_FONT = new Font("Century Schoolbook", Font.PLAIN, 15);

    /**
     * Constructor
     */
    public AdventurerForm(Adventurer adventurer, BoundingArea boundingArea, DisplayableDepth depth, boolean canBuyAdventurer) {
        super(boundingArea, depth, canBuyAdventurer);

        this.adventurer = adventurer;
    }

    @Override
    protected TreeSet<Displayable> getInfoImages(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> images = new TreeSet<>();

        images.addAll(getTextDisplayables(getTextStartPosition(cameraPosition)));

        return images;
    }

    private Position getTextStartPosition(Position cameraPosition) {
        Position myPosition = getPositionAccordingToCamera(cameraPosition);

        return myPosition.add(new Position(10, 10 + TITLE_FONT.getSize()));
    }

    private TreeSet<Displayable> getTextDisplayables(Position positionOfText) {
        TreeSet<Displayable> images = new TreeSet<>();

        for (Map.Entry<String, Font> toDisplay : getAllInformationToShowFromAdventurer(adventurer).entrySet()) {
            images.add(new DisplayableText(positionOfText, getDepth().add(1), toDisplay.getKey(),
                    toDisplay.getValue(), Color.BLACK));

            positionOfText = positionOfText.add(new Position(0, toDisplay.getValue().getSize()));
        }

        return images;
    }

    private Map<String, Font> getAllInformationToShowFromAdventurer(Adventurer adventurer) {
        Map<String, Font> map = new LinkedHashMap<>();

        map.put(adventurer.getName(), TITLE_FONT);
        map.put("Cost: " + adventurer.getCost(), BODY_FONT);
        map.put("Class: " + adventurer.getAdventurerClass(), BODY_FONT);
        map.put("Line: " + adventurer.getLine().toString(), BODY_FONT);
        map.put("Health: " + adventurer.getStats().getHealth().getMaxHp(), BODY_FONT);
        map.put("Endurance: " + adventurer.getStats().getHealth().getEndurance(), BODY_FONT);
        map.put("Damage: " + adventurer.getDamage().getAmount(), BODY_FONT);
        map.put("Initiative: " + adventurer.getDamage().getInitiative(), BODY_FONT);

        return map;
    }

    public Adventurer getAdventurer() {
        return adventurer;
    }
}
