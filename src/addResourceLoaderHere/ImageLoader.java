package addResourceLoaderHere;

import addGameObjectsHere.view.threadConversation.ConversationImagesID;
import addGameObjectsHere.view.threadInn.characters.AdventurerImageID;
import addGameObjectsHere.view.threadInn.characters.PlayerImageID;
import addGameObjectsHere.view.threadInn.characters.BasicClientImageID;
import addGameObjectsHere.view.threadInn.characters.QuestGiverImageID;
import addGameObjectsHere.view.threadInn.other.GenericImagesID;
import addGameObjectsHere.view.threadAll.images.BoxCreator;
import addGameObjectsHere.view.threadAll.images.IconImageID;
import addGameObjectsHere.view.threadInn.inn.InnImagesID;
import jGameFramework.core.LoaderOfImages;
import jGameFramework.physicalObjects.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Loads images in order to be displayed on screen
 *
 * This class is necessary for the JGame Framework to work.
 * It can load as many images as needed.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ImageLoader extends LoaderOfImages {

    private Map<Class, EnumMap> allImages;

    private BoxCreator boxCreator;

    private Font baseFont;

    /**
     * Basic constructor
     * @throws IOException if an image is missing
     */
    public ImageLoader() throws IOException {

        allImages = new HashMap<>();

        allImages.put(InnImagesID.class, new EnumMap<>(InnImagesID.class));
        allImages.put(AdventurerImageID.class, new EnumMap<>(AdventurerImageID.class));
        allImages.put(BasicClientImageID.class, new EnumMap<>(BasicClientImageID.class));
        allImages.put(QuestGiverImageID.class, new EnumMap<>(QuestGiverImageID.class));
        allImages.put(PlayerImageID.class, new EnumMap<>(PlayerImageID.class));
        allImages.put(GenericImagesID.class, new EnumMap<>(GenericImagesID.class));
        allImages.put(IconImageID.class, new EnumMap<>(IconImageID.class));
        allImages.put(ConversationImagesID.class, new EnumMap<>(ConversationImagesID.class));

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
    @SuppressWarnings("unchecked")
    private void loadAllImages() throws IOException {
        loadAllImagesFromEnum(IconImageID.class, allImages.get(IconImageID.class), "Resources/Icons/");
        loadAllImagesFromEnum(AdventurerImageID.class, allImages.get(AdventurerImageID.class), "Resources/Characters/Adventurers/");
        loadAllImagesFromEnum(InnImagesID.class, allImages.get(InnImagesID.class), "Resources/Inn/");
        loadAllImagesFromEnum(BasicClientImageID.class, allImages.get(BasicClientImageID.class), "Resources/Characters/");
        loadAllImagesFromEnum(QuestGiverImageID.class, allImages.get(QuestGiverImageID.class), "Resources/Characters/QuestGiver/");
        loadAllImagesFromEnum(PlayerImageID.class, allImages.get(PlayerImageID.class), "Resources/Characters/InnKeeper/");
        loadAllImagesFromEnum(GenericImagesID.class, allImages.get(GenericImagesID.class), "Resources/Generic/");
        loadAllImagesFromEnum(ConversationImagesID.class, allImages.get(ConversationImagesID.class), "Resources/");
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
        return (BufferedImage) allImages.get(imageId.getClass()).get(imageId);
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
