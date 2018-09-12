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

    private HashMap<Background, BufferedImage> background_images;
    private BufferedImage selector;

    public TestBoxCreator() throws IOException {

        background_images = new HashMap<>();

        background_images.put(Background.Paper, toCompatibleImage(ImageIO.read(new File("Resources/UI Test/PaperBackground.png"))));
        background_images.put(Background.Wood, toCompatibleImage(ImageIO.read(new File("Resources/UI Test/WoodBackground.png"))));

        selector = toCompatibleImage(ImageIO.read(new File("Resources/UI Test/Selector.png")));

    }

    /**
     * This creates the box. The code is awful... But it will be cleaned once tested.
     */
    public BufferedImage getBox(Position dimensions, Background backgroundID) {

        BufferedImage box = new BufferedImage(dimensions.getX(), dimensions.getY(), BufferedImage.TYPE_INT_RGB);
        Graphics2D bbg = (Graphics2D) box.getGraphics();

        BufferedImage background = background_images.get(backgroundID).getSubimage(0, 0, dimensions.getX(), dimensions.getY());

        bbg.drawImage(background, 0, 0, null);

        // Top left
        bbg.drawImage(selector.getSubimage(0, 0, 8, 8), 0, 0, null);

        // Top right
        bbg.drawImage(selector.getSubimage(128 - 8, 0, 8, 8), dimensions.getX() - 8, 0, null);

        // Bottom left
        bbg.drawImage(selector.getSubimage(0, 115 - 8, 8, 8), 0, dimensions.getY() - 8, null);

        // Bottom right
        bbg.drawImage(selector.getSubimage(128 - 8, 115 - 8, 8, 8), dimensions.getX() - 8, dimensions.getY() - 8, null);

        int x = 8;

        // Top and bottom line
        int width = 84;
        int height = 8;

        while (x + 8 < dimensions.getX()) {
            if (dimensions.getX() < x + width) {
                bbg.drawImage(selector.getSubimage(30, 0, dimensions.getX() - x, height), x, 0, null);
                bbg.drawImage(selector.getSubimage(30, 115 - 8, dimensions.getX() - x, height), x, dimensions.getY() - 8, null);
            } else {
                bbg.drawImage(selector.getSubimage(30, 0, width, height), x, 0, null);
                bbg.drawImage(selector.getSubimage(30, 115 - 8, width, height), x, dimensions.getY() - 8, null);
            }

            x += width;
        }

        // Left and right line
        int y = 8;

        width = 8;
        height = 84;

        while (y + 8 < dimensions.getY()) {
            if (dimensions.getY() < y + height) {
                bbg.drawImage(selector.getSubimage(0, 30, width, dimensions.getY() - y), 0, y, null);
                bbg.drawImage(selector.getSubimage(128 - 8, 30, width, dimensions.getY() - y), dimensions.getX() - 8, y, null);
            } else {
                bbg.drawImage(selector.getSubimage(0, 30, width, height), 0, y, null);
                bbg.drawImage(selector.getSubimage(128 - 8, 30, width, height), dimensions.getX() - 8, y, null);
            }

            y += height;
        }

        bbg.dispose();

        return box;
    }



}
