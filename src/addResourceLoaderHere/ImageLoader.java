package addResourceLoaderHere;

import addGameObjectsHere.view.adventurers.AdventurerImageID;
import addGameObjectsHere.view.adventurers.PlayerImageID;
import addGameObjectsHere.view.gameButtons.ButtonID;
import addGameObjectsHere.view.images.BoxCreator;
import addGameObjectsHere.view.inn.InnImagesID;
import addGameObjectsHere.view.windows.WindowID;
import addGameObjectsHere.view.windows.quest.QuestImagesID;
import jGameFramework.core.LoaderOfImages;
import jGameFramework.physicalObjects.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;

/**
 * Loads images in order to be displayed on screen
 *
 * This class is necessary for the JGame Framework to work.
 * It can load as many images as needed.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ImageLoader extends LoaderOfImages {

    private EnumMap<InnImagesID, BufferedImage> innUIImages;
    private EnumMap<ButtonID, BufferedImage> innButtonImages;
    private EnumMap<AdventurerImageID, BufferedImage> adventurerImages;
    private EnumMap<WindowID, BufferedImage> windowIdImages;
    private EnumMap<QuestImagesID, BufferedImage> questIdImages;
    private EnumMap<PlayerImageID, BufferedImage> playerImages;

    private BoxCreator boxCreator;

    /**
     * Basic constructor
     * @throws IOException if an image is missing
     */
    public ImageLoader() throws IOException {

        innUIImages = new EnumMap<>(InnImagesID.class);
        adventurerImages = new EnumMap<>(AdventurerImageID.class);
        innButtonImages = new EnumMap<>(ButtonID.class);
        windowIdImages = new EnumMap<>(WindowID.class);
        questIdImages = new EnumMap<>(QuestImagesID.class);
        playerImages = new EnumMap<>(PlayerImageID.class);

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

        innUIImages.put(InnImagesID.floor1, toCompatibleImage(ImageIO.read(new File("Resources/Inn/Floor1.png"))));
        innUIImages.put(InnImagesID.backgroundDay, toCompatibleImage(ImageIO.read(new File("Resources/pub.png"))));
        innUIImages.put(InnImagesID.backgroundNight, toCompatibleImage(ImageIO.read(new File("Resources/innNight.png"))));

        innButtonImages.put(ButtonID.Day , toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/buttonDay.png"))));
        innButtonImages.put(ButtonID.Night, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/buttonNight.png"))));

        windowIdImages.put(WindowID.Quests, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/map.png"))));
        windowIdImages.put(WindowID.Adventurers, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/backpack.png"))));
        windowIdImages.put(WindowID.Recruit, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/backpack.png"))));
        windowIdImages.put(WindowID.Shop, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/coin.png"))));
        windowIdImages.put(WindowID.Inn, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/inn.png"))));

        for (QuestImagesID imageId : QuestImagesID.values()) {
            questIdImages.put(imageId, toCompatibleImage(ImageIO.read(new File("Resources/Quests/" + imageId.toString() + "_small.png"))));
        }

        for (AdventurerImageID imageId : AdventurerImageID.values()) {
            adventurerImages.put(imageId, toCompatibleImage(ImageIO.read(new File("Resources/Adventurers/" + imageId.toString() + ".png"))));
        }

        for (PlayerImageID imageId : PlayerImageID.values()) {
            playerImages.put(imageId, toCompatibleImage(ImageIO.read(new File("Resources/InnKeeper/" + imageId.toString() + ".png"))));
        }

    }

    public BufferedImage getImageFromID(Enum imageId) {
        if (imageId instanceof InnImagesID) {
            return innUIImages.get(imageId);
        }

        if (imageId instanceof QuestImagesID) {
            return questIdImages.get(imageId);
        }

        if (imageId instanceof WindowID) {
            return windowIdImages.get(imageId);
        }

        if (imageId instanceof AdventurerImageID) {
            return adventurerImages.get(imageId);
        }

        if (imageId instanceof PlayerImageID) {
            return playerImages.get(imageId);
        }

        if (imageId instanceof ButtonID) {
            return innButtonImages.get(imageId);
        }

        throw new UnsupportedOperationException("The following imageID is not supported: " + imageId.name());
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
