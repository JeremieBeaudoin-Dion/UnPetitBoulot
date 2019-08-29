package addGameObjectsHere.view.threadConversation;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * A text within a box.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class TextBox extends PhysicalObject {

    private static final Position TEXT_OFFSET = new Position(10, 30);
    private static final float FONT_SIZE = 23;

    private List<String> textList;

    private Font currentFont;

    /**
     * Constructor
     */
    public TextBox(ImageLoader imageLoader, BoundingArea boundingArea, DisplayableDepth depth, String text) {
        super(boundingArea, false, depth);

        currentFont = imageLoader.getBaseFont(FONT_SIZE);

        this.textList = TextLengthHelper.getTextListToFitIntoBox(text, boundingArea, TEXT_OFFSET.clone(),
                imageLoader.getBaseFont(FONT_SIZE));
    }

    public void updateText(String newText) {
        this.textList = TextLengthHelper.getTextListToFitIntoBox(newText, getBoundingArea(), TEXT_OFFSET.clone(),
                currentFont);
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> setOfImages = new TreeSet<>();

        Position textPosition = getPositionAccordingToCamera(cameraPosition).add(TEXT_OFFSET);

        currentFont = imageLoader.getBaseFont(FONT_SIZE);

        for (String text : textList) {
            setOfImages.add(new DisplayableText(textPosition, getDepth(), text,
                    currentFont, Color.BLACK, DisplayableText.Alignment.left));

            textPosition = textPosition.add(new Position(0, FONT_SIZE * 1.2));
        }

        return setOfImages;
    }

    /**
     * Returns any action that the GameThread should handle
     */
    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    /**
     * Returns true if the object should be disposed of
     */
    @Override
    public boolean dispose() {
        return false;
    }
}
