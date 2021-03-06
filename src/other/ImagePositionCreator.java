package other;

import jGameFramework.physicalObjects.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Reads an image file and returns the desired positions.
 *
 * TODO: There is a lot of duplicate code. This needs to be refactored.
 *
 * @author Mia Beaudoin-Dion
 */
public class ImagePositionCreator {

    private static final Color positionColor = new Color(0, 255, 0);

    public static List<Position> getPositions(BufferedImage image) {
        List<Position> listToReturn = new LinkedList<>();

        for (int x=0; x<image.getWidth(); x++) {
            System.out.println("Processing column: " + x);
            for (int y=0; y<image.getHeight(); y++) {
                Color pixel = new Color(image.getRGB(x, y));

                if (pixel.equals(positionColor)) {
                    listToReturn.add(new Position(x, y));
                }

            }
        }

        return listToReturn;
    }



    public static void main(String[] args) {

        String inPath = "Build/WallMaps/Floor1_AdventurerPositions.png";
        String outPath = "Resources/WallMaps/Floor1Positions.oos";

        try {
            List<Position> list = ImagePositionCreator.getPositions(ImageIO.read(new File(inPath)));

            FileOutputStream fileOut = new FileOutputStream(outPath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(list);
            objectOut.close();
            System.out.println("The Object  was successfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
