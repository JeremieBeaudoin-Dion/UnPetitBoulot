package addResourceLoaderHere;

import addGameObjectsHere.characters.AdventurerClassEnum;
import addGameObjectsHere.gameButtons.ButtonID;
import addGameObjectsHere.images.BoxCreator;
import addGameObjectsHere.inn.InnImagesID;
import addGameObjectsHere.windows.WindowID;
import jGameFramework.core.LoaderOfImages;
import jGameFramework.physicalObjects.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Loads images in order to be displayed on screen
 *
 * This class is necessary for the JGame Framework to work.
 * It can load as many images as needed.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ImageLoader extends LoaderOfImages {

    private HashMap<InnImagesID, BufferedImage> innUIImages;
    private HashMap<ButtonID, BufferedImage> innButtonImages;
    private HashMap<AdventurerClassEnum, BufferedImage> adventurerImages;
    private HashMap<WindowID, BufferedImage> windowIdImages;

    private BoxCreator boxCreator;

    /**
     * Basic constructor
     * @throws IOException if an image is missing
     */
    public ImageLoader() throws IOException {

        innUIImages = new HashMap<>();
        adventurerImages = new HashMap<>();
        innButtonImages = new HashMap<>();
        windowIdImages = new HashMap<>();

        boxCreator = new BoxCreator();

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

        innUIImages.put(InnImagesID.backgroundDay, toCompatibleImage(ImageIO.read(new File("Resources/pub.png"))));
        innUIImages.put(InnImagesID.backgroundNight, toCompatibleImage(ImageIO.read(new File("Resources/innNight.png"))));

        innButtonImages.put(ButtonID.Day , toCompatibleImage(ImageIO.read(new File("Resources/buttonDay.png"))));
        innButtonImages.put(ButtonID.Night, toCompatibleImage(ImageIO.read(new File("Resources/buttonNight.png"))));

        windowIdImages.put(WindowID.Quests, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/map.png"))));
        windowIdImages.put(WindowID.Adventurers, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/backpack.png"))));
        windowIdImages.put(WindowID.Shop, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/coin.png"))));

        adventurerImages.put(AdventurerClassEnum.Fighter, toCompatibleImage(ImageIO.read(new File("Resources/Adventurers/fighter_small.png"))));
        adventurerImages.put(AdventurerClassEnum.Rogue, toCompatibleImage(ImageIO.read(new File("Resources/Adventurers/rogue_small.png"))));
        adventurerImages.put(AdventurerClassEnum.Ranger, toCompatibleImage(ImageIO.read(new File("Resources/Adventurers/ranger_small.png"))));

    }

    public BufferedImage getInnUiImage(InnImagesID id) {
        return innUIImages.get(id);
    }

    public BufferedImage getWindowImage(WindowID id) {
        return windowIdImages.get(id);
    }

    public BufferedImage getAdventurerImage(AdventurerClassEnum id) {
        return adventurerImages.get(id);
    }

    public BufferedImage getButtonImage(ButtonID id) {
        return innButtonImages.get(id);
    }

    public BufferedImage getBox(Position dimensions, BoxCreator.Background background) {
        return boxCreator.getBox(dimensions, background);
    }

    /**
     * Helper method.
     *
     * // Example of use
     *  imageExample = toCompatibleImage(ImageIO.read(new File("ImageExample.png")));
     *  Image img = imageExample.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
     *  imageExampleScaled = toBufferedImage(img);
     */
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

}
