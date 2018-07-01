package addResourceLoaderHere;

import jGameFramework.core.LoaderOfImages;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Loads images in order to be displayed on screen
 *
 * This class is necessary for the JGame Framework to work.
 * It can load as many images as needed.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ImageLoader extends LoaderOfImages {

    private BufferedImage imageExample;
    private BufferedImage imageExampleScaled;

    private BufferedImage gameDayBackground;
    private BufferedImage gameNightBackground;

    private BufferedImage gameDayButton;
    private BufferedImage gameNightButton;

    /**
     * Basic constructor
     * @throws IOException if an image is missing
     */
    public ImageLoader() throws IOException {

        loadAllImages();

    }

    /**
     * Loads all image from file
     *
     * Do remember to use toCompatibleImage() which greatly increases the performance
     * of jGameFramework.display.
     *
     * @throws IOException : if the image is missing
     */
    private void loadAllImages() throws IOException {

        gameDayBackground = toCompatibleImage(ImageIO.read(new File("InnUI/pub.png")));
        gameNightBackground = toCompatibleImage(ImageIO.read(new File("InnUI/innNight.png")));

        gameDayButton = toCompatibleImage(ImageIO.read(new File("InnUI/buttonDay.png")));
        gameNightButton = toCompatibleImage(ImageIO.read(new File("InnUI/buttonNight.png")));

        // Example of use
        imageExample = toCompatibleImage(ImageIO.read(new File("ImageExample.png")));

        Image img = imageExample.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        imageExampleScaled = toBufferedImage(img);
    }

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public BufferedImage getImageExample() {
        return imageExampleScaled;
    }

    public BufferedImage getGameDayBackground() {
        return gameDayBackground;
    }

    public BufferedImage getGameNightBackground() {
        return gameNightBackground;
    }

    public BufferedImage getDayButton() {
        return gameDayButton;
    }

    public BufferedImage getNightButton() {
        return gameNightButton;
    }

}
