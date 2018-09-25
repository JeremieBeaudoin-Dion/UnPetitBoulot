package addGameObjectsHere.controller.windows;

import addGameObjectsHere.model.characters.Adventurer;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.TreeSet;

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

        Position myPosition = getPositionAccordingToCamera(cameraPosition);
        Position positionOfText = myPosition.add(new Position(10, 10 + TITLE_FONT.getSize()));

        images.add(new DisplayableText(positionOfText, getDepth().add(1), adventurer.getName(), TITLE_FONT, Color.BLACK));

        return images;
    }

    public Adventurer getAdventurer() {
        return adventurer;
    }
}
