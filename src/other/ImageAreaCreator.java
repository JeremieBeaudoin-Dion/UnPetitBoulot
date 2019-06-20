package other;

import jGameFramework.physicalObjects.BoundingArea;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Reads an image file and returns the desired area.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ImageAreaCreator {

    private static final Color outlineColor = new Color(255, 50, 255);

    public static BoundingArea getOutline(BufferedImage image) {
        Area area = new Area();
        for (int x=0; x<image.getWidth(); x++) {
            for (int y=0; y<image.getHeight(); y++) {
                Color pixel = new Color(image.getRGB(x,y));

                if (pixel.equals(outlineColor)) {
                    Rectangle r = new Rectangle(x,y,1,1);
                    area.add(new Area(r));
                }
            }
        }

        return new BoundingArea(area);
    }

    public static void main(String[] args) {

        String inPath = "Resources/WallMaps/Inn_InvisibleWalls.png";
        String outPath = "Resources/WallMaps/InvisibleWall.oos";

        try {
            BoundingArea area = ImageAreaCreator.getOutline(ImageIO.read(new File(inPath)));

            FileOutputStream fileOut = new FileOutputStream(outPath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(area);
            objectOut.close();
            System.out.println("The Object  was successfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
