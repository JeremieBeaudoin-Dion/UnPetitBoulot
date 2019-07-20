package addResourceLoaderHere;

import addGameObjectsHere.view.threadInn.adventurers.AdventurerImageID;
import addGameObjectsHere.view.threadInn.adventurers.PlayerImageID;
import addGameObjectsHere.view.threadInn.characters.CharacterImageID;
import addGameObjectsHere.view.threadNight.gameButtons.ButtonID;
import addGameObjectsHere.view.threadInn.other.GenericImagesID;
import addGameObjectsHere.view.threadAll.images.BoxCreator;
import addGameObjectsHere.view.threadAll.images.IconImageID;
import addGameObjectsHere.view.threadInn.inn.InnImagesID;
import addGameObjectsHere.view.threadConversation.windows.WindowID;
import addGameObjectsHere.view.threadConversation.windows.quest.QuestImagesID;
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
    private EnumMap<CharacterImageID, BufferedImage> characterImages;
    private EnumMap<WindowID, BufferedImage> windowIdImages;
    private EnumMap<QuestImagesID, BufferedImage> questIdImages;
    private EnumMap<PlayerImageID, BufferedImage> playerImages;

    private EnumMap<GenericImagesID, BufferedImage> genericImages;
    private EnumMap<IconImageID, BufferedImage> iconImages;

    private BoxCreator boxCreator;

    private Font baseFont;

    /**
     * Basic constructor
     * @throws IOException if an image is missing
     */
    public ImageLoader() throws IOException {

        innUIImages = new EnumMap<>(InnImagesID.class);
        adventurerImages = new EnumMap<>(AdventurerImageID.class);
        characterImages = new EnumMap<>(CharacterImageID.class);
        innButtonImages = new EnumMap<>(ButtonID.class);
        windowIdImages = new EnumMap<>(WindowID.class);
        questIdImages = new EnumMap<>(QuestImagesID.class);
        playerImages = new EnumMap<>(PlayerImageID.class);
        genericImages = new EnumMap<>(GenericImagesID.class);
        iconImages = new EnumMap<>(IconImageID.class);

        boxCreator = new BoxCreator();

        loadAllImages();

        try {
            loadFonts();
        } catch (FontFormatException e) {
            throw new RuntimeException("Fonts could not be loaded properly: " + e.getMessage());
        }

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
        innUIImages.put(InnImagesID.bed, toCompatibleImage(ImageIO.read(new File("Resources/Inn/bed1.png"))));

        innButtonImages.put(ButtonID.Day , toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/buttonDay.png"))));
        innButtonImages.put(ButtonID.Night, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/buttonNight.png"))));

        windowIdImages.put(WindowID.Quests, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/map.png"))));
        windowIdImages.put(WindowID.Adventurers, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/backpack.png"))));
        windowIdImages.put(WindowID.Recruit, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/backpack.png"))));
        windowIdImages.put(WindowID.Shop, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/coin.png"))));
        windowIdImages.put(WindowID.Inn, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/inn.png"))));

        loadAllImagesFromEnum(QuestImagesID.class, questIdImages, "Resources/Quests/");
        loadAllImagesFromEnum(IconImageID.class, iconImages, "Resources/Icons/");

        loadAllImagesFromEnum(AdventurerImageID.class, adventurerImages, "Resources/Characters/Adventurers/");
        loadAllImagesFromEnum(CharacterImageID.class, characterImages, "Resources/Characters/");
        loadAllImagesFromEnum(PlayerImageID.class, playerImages, "Resources/Characters/InnKeeper/");
        loadAllImagesFromEnum(GenericImagesID.class, genericImages, "Resources/Generic/");

    }

    /**
     * Helper method to load all images into an EnumMap from the Enum class.
     */
    private <E extends Enum<E>> void loadAllImagesFromEnum(Class<E> anEnum, EnumMap<E, BufferedImage> map, String path) throws IOException {
        for (E imageID: anEnum.getEnumConstants()) {
            map.put(imageID, toCompatibleImage(ImageIO.read(new File(path + imageID.toString() + ".png"))));
        }
    }

    private void loadFonts() throws IOException, FontFormatException {
        baseFont = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/Font/Cup and Talon.ttf"));
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

        if (imageId instanceof GenericImagesID) {
            return genericImages.get(imageId);
        }

        if (imageId instanceof IconImageID) {
            return iconImages.get(imageId);
        }

        throw new UnsupportedOperationException("The following imageID is not supported: " + imageId.name());
    }

    public BufferedImage getBox(Position dimensions, BoxCreator.Background background) {
        return boxCreator.getBox(dimensions, background);
    }

    public Font getBaseFont(float size) {
        return baseFont.deriveFont(size);
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
