package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.view.threadAll.images.BoxCreator;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * A text that fits within a BoundingArea. If specified, this will show a specific box as background.
 *
 * If the string send is too long to fit the width, it is divided into lines.
 *
 * @author Mia Beaudoin-Dion
 */
public class TextBox extends PhysicalObject {

    private static final float FONT_SIZE = 23;

    private List<String> textList;

    private Font currentFont;
    private DisplayableText.Alignment alignment;

    private BoxCreator.Background boxBackground;
    private Position textOffset;

    private Paint paint;

    /**
     * Constructor
     */
    public TextBox(ImageLoader imageLoader, BoundingArea boundingArea, DisplayableDepth depth, String text) {
        super(boundingArea, false, depth);

        currentFont = imageLoader.getBaseFont(FONT_SIZE);
        this.alignment = DisplayableText.Alignment.left;

        textOffset = new Position(FONT_SIZE/2, FONT_SIZE);

        this.textList = TextLengthHelper.getTextListToFitIntoBox(text, boundingArea, textOffset.clone(),
                imageLoader.getBaseFont(FONT_SIZE));

        this.paint = Color.BLACK;
        boxBackground = null;
    }

    public TextBox(ImageLoader imageLoader, BoundingArea boundingArea, DisplayableDepth depth, String text,
                   BoxCreator.Background boxBackground) {
        super(boundingArea, false, depth);

        currentFont = imageLoader.getBaseFont(FONT_SIZE);
        this.alignment = DisplayableText.Alignment.left;

        textOffset = new Position(FONT_SIZE/2, FONT_SIZE);
        this.textList = TextLengthHelper.getTextListToFitIntoBox(text, boundingArea, textOffset.clone(),
                imageLoader.getBaseFont(FONT_SIZE));

        this.paint = Color.BLACK;
        this.boxBackground = boxBackground;
    }

    public TextBox(BoundingArea boundingArea, DisplayableDepth depth, String text, BoxCreator.Background boxBackground,
                   Font desiredFont, DisplayableText.Alignment alignment) {
        super(boundingArea, false, depth);

        currentFont = desiredFont;
        this.alignment = alignment;

        textOffset = new Position(desiredFont.getSize()/2.0, desiredFont.getSize());
        this.textList = TextLengthHelper.getTextListToFitIntoBox(text, boundingArea, textOffset.clone(),
                desiredFont);

        this.paint = Color.BLACK;
        this.boxBackground = boxBackground;
    }

    public TextBox(BoundingArea boundingArea, DisplayableDepth depth, String text, BoxCreator.Background boxBackground,
                   Font desiredFont, DisplayableText.Alignment alignment, Paint paint) {
        super(boundingArea, false, depth);

        currentFont = desiredFont;
        this.alignment = alignment;

        textOffset = new Position(desiredFont.getSize()/2.0, desiredFont.getSize());
        this.textList = TextLengthHelper.getTextListToFitIntoBox(text, boundingArea, textOffset.clone(),
                desiredFont);

        this.paint = paint;
        this.boxBackground = boxBackground;
    }

    public TextBox(BoundingArea boundingArea, DisplayableDepth depth, List<String> text, BoxCreator.Background boxBackground,
                   Font desiredFont, DisplayableText.Alignment alignment) {
        super(boundingArea, false, depth);

        currentFont = desiredFont;
        this.alignment = alignment;

        textOffset = new Position(desiredFont.getSize()/2.0, desiredFont.getSize());
        this.textList = new LinkedList<>();

        for (String textLine : text) {
            textList.addAll(TextLengthHelper.getTextListToFitIntoBox(textLine, boundingArea, textOffset.clone(),
                    desiredFont));
        }

        this.paint = paint;
        this.boxBackground = boxBackground;
    }

    public void updateText(String newText) {
        this.textList = TextLengthHelper.getTextListToFitIntoBox(newText, getBoundingArea(), textOffset.clone(),
                currentFont);
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> setOfImages = new TreeSet<>();

        Position textPosition = getPositionAccordingToCamera(cameraPosition);

        // Fixes an issue with displaying a single line of text.
        if (alignment == DisplayableText.Alignment.left && textList.size() == 1) {
            textPosition = textPosition.add(new Position(textOffset.getX(), getHeight()/2 + 2));
        } else if (alignment == DisplayableText.Alignment.left) {
            textPosition = textPosition.add(textOffset);
        } else if (alignment == DisplayableText.Alignment.right) {
            textPosition = textPosition.add(new Position(getWidth(), 0));
            textPosition = textPosition.add(textOffset.reverse());
        } else {
            textPosition = textPosition.add(new Position(getWidth()/2, getHeight()/2));
        }

        for (String text : textList) {
            setOfImages.add(new DisplayableText(textPosition, getDepth().add(1), text,
                    currentFont, this.paint, this.alignment));

            textPosition = textPosition.add(new Position(0, FONT_SIZE * 1.15));
        }

        if (boxBackground != null) {
            setOfImages.add(new DisplayableImage(this, cameraPosition,
                    imageLoader.getBox(new Position(getWidth(), getHeight()), boxBackground)));
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
