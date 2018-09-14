package addResourceLoaderHere.imageHelper;

import jGameFramework.core.LoaderOfImages;
import jGameFramework.physicalObjects.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * This is a test.
 *
 * The idea is to make a class that will return a box for UI.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class TestBoxCreator extends LoaderOfImages {

    public enum Background{Paper, Wood}

    private enum SelectorLineDirections{Top, Down, Left, Right;
        int getIsX() {

            if (this == Top || this == Down) {
                return 1;
            }

            return 0;
        }

        int getIsY() {
            if (this == Left || this == Right) {
                return 1;
            }

            return 0;
        }
    }

    private enum SelectorCornerDirection {

        TopLeft, TopRight, BottomLeft, BottomRight;

        int getIsRight() {
            if (this == TopLeft || this == BottomLeft) {
                return 1;
            }

            return 0;
        }

        int getIsBottom() {
            if (this == BottomLeft || this == BottomRight) {
                return 1;
            }
            return 0;
        }
    }

    private static final int SELECTOR_CORNER_WIDTH_AND_HEIGHT = 8;
    private static final int SELECTOR_WIDTH = 128;
    private static final int SELECTOR_HEIGHT = 115;

    private HashMap<Background, BufferedImage> background_images;
    private HashMap<SelectorLineDirections, BufferedImage> selectorLines;
    private HashMap<SelectorCornerDirection, BufferedImage> selectorCorners;
    private BufferedImage selector;

    public TestBoxCreator() throws IOException {

        background_images = new HashMap<>();

        background_images.put(Background.Paper, toCompatibleImage(ImageIO.read(new File("Resources/UI Test/PaperBackground.png"))));
        background_images.put(Background.Wood, toCompatibleImage(ImageIO.read(new File("Resources/UI Test/WoodBackground.png"))));

        selector = toCompatibleImage(ImageIO.read(new File("Resources/UI Test/Selector.png")));

        selectorLines = new HashMap<>();

        /*
        selectorLines.put(SelectorLineDirections.Top,
                selector.getSubimage(SELECTOR_CORNER_WIDTH_AND_HEIGHT, 0,
                        SELECTOR_WIDTH - SELECTOR_CORNER_WIDTH_AND_HEIGHT*2, SELECTOR_CORNER_WIDTH_AND_HEIGHT));
        selectorLines.put(SelectorLineDirections.Down,
                selector.getSubimage(SELECTOR_CORNER_WIDTH_AND_HEIGHT, SELECTOR_HEIGHT - SELECTOR_CORNER_WIDTH_AND_HEIGHT,
                        SELECTOR_WIDTH - SELECTOR_CORNER_WIDTH_AND_HEIGHT*2, SELECTOR_CORNER_WIDTH_AND_HEIGHT));
        selectorLines.put(SelectorLineDirections.Left,
                selector.getSubimage(0, SELECTOR_CORNER_WIDTH_AND_HEIGHT,
                        SELECTOR_CORNER_WIDTH_AND_HEIGHT, SELECTOR_HEIGHT - SELECTOR_CORNER_WIDTH_AND_HEIGHT*2));
        selectorLines.put(SelectorLineDirections.Right,
                selector.getSubimage(SELECTOR_WIDTH - SELECTOR_CORNER_WIDTH_AND_HEIGHT, SELECTOR_CORNER_WIDTH_AND_HEIGHT,
                        SELECTOR_WIDTH - SELECTOR_CORNER_WIDTH_AND_HEIGHT*2, SELECTOR_CORNER_WIDTH_AND_HEIGHT));*/


        selectorCorners = new HashMap<>();

        for (SelectorCornerDirection direction : SelectorCornerDirection.values()) {
            selectorCorners.put(direction, selector.getSubimage((SELECTOR_WIDTH - SELECTOR_CORNER_WIDTH_AND_HEIGHT) * direction.getIsRight(),
                    (SELECTOR_HEIGHT - SELECTOR_CORNER_WIDTH_AND_HEIGHT) * direction.getIsBottom(),
                    SELECTOR_CORNER_WIDTH_AND_HEIGHT, SELECTOR_CORNER_WIDTH_AND_HEIGHT));
        }

    }

    /**
     * This creates the box. The code is awful... But it will be cleaned once tested.
     */
    public BufferedImage getBox(Position dimensions, Background backgroundID) {

        BufferedImage box = new BufferedImage(dimensions.getX(), dimensions.getY(), BufferedImage.TYPE_INT_RGB);
        Graphics2D bbg = (Graphics2D) box.getGraphics();

        drawBackground(dimensions, bbg, backgroundID);

        //drawSelector(dimensions, bbg); TODO

        bbg.dispose();

        return box;
    }

    private void drawBackground(Position dimensions, Graphics2D bbg, Background backgroundID) {
        bbg.drawImage(getBackground(dimensions, backgroundID), 0, 0, null);
    }

    private BufferedImage getBackground(Position dimensions, Background backgroundID) {
        return background_images.get(backgroundID).getSubimage(0, 0, dimensions.getX(), dimensions.getY());
    }

    private void drawSelector(Position dimensions, Graphics2D bbg) {
        drawAllSelectorCorners(dimensions, bbg);
        drawAllSelectorLines(dimensions, bbg);
    }

    private void drawAllSelectorCorners(Position dimensions, Graphics2D bbg) {
        for (SelectorCornerDirection direction : SelectorCornerDirection.values()) {
            bbg.drawImage(selectorCorners.get(direction),
                    (dimensions.getX() - SELECTOR_CORNER_WIDTH_AND_HEIGHT) * direction.getIsRight(),
                    (dimensions.getY() - SELECTOR_CORNER_WIDTH_AND_HEIGHT) * direction.getIsBottom(), null);
        }
    }

    private void drawAllSelectorLines(Position dimensions, Graphics2D bbg) {

        Position dimensionOfLines = dimensions.add(
                new Position(-(SELECTOR_CORNER_WIDTH_AND_HEIGHT * 2), -(SELECTOR_CORNER_WIDTH_AND_HEIGHT * 2)));
    }

        /*
        for (SelectorLineDirections direction : SelectorLineDirections.values()) {
            bbg.drawImage(getSelectorLine(direction, dimensionOfLines),
                    dimensionOfLines.getX() * direction.getIsRight() + SELECTOR_CORNER_WIDTH_AND_HEIGHT,
                    dimensionOfLines.getY() * direction.getIsBottom()  + SELECTOR_CORNER_WIDTH_AND_HEIGHT, null);
        }
    }

    private BufferedImage getSelectorLine(SelectorLineDirections direction, Position dimensionOfLines) {
        return selectorLines.get(direction).getSubimage((SELECTOR_WIDTH - SELECTOR_CORNER_WIDTH_AND_HEIGHT) * direction.getIsRight(),
                (SELECTOR_HEIGHT - SELECTOR_CORNER_WIDTH_AND_HEIGHT) * direction.getIsBottom(),
                dimensionOfLines.getX()  * direction.getIsRight() + SELECTOR_CORNER_WIDTH_AND_HEIGHT,
                dimensionOfLines.getY()  * direction.getIsBottom() + SELECTOR_CORNER_WIDTH_AND_HEIGHT);
    }*/
}
