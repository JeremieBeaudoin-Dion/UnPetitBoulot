package other;

import addGameObjectsHere.view.threadInn.inn.ServingTablePhysicalObject;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Reads an image file and returns the desired area.
 *
 * @author Mia Beaudoin-Dion
 */
public class ImageTableCreator {

    private static final Color outlineColor = new Color(0, 0, 255);

    public static List<Area> getAreas(BufferedImage image) {

        List<Area> allAreas = new LinkedList<>();

        boolean needToCreateNewArea;

        for (int x=0; x<image.getWidth(); x++) {
            System.out.println("Processing column: " + x);
            for (int y=0; y<image.getHeight(); y++) {
                Color pixel = new Color(image.getRGB(x,y));

                if (pixel.equals(outlineColor)) {
                    Position myPosition = new Position(x, y);
                    needToCreateNewArea = true;

                    for (Area currArea: allAreas) {
                        if (getClosestDistance(myPosition, currArea) <= 3.0) {
                            currArea.add(new Area(new Rectangle(x, y, 1, 1)));
                            needToCreateNewArea = false;
                            break;
                        }
                    }

                    if (needToCreateNewArea) {
                        allAreas.add(new Area(new Rectangle(x, y, 1, 1)));
                    }
                }

            }
        }

        return allAreas;
    }

    public static double getClosestDistance(Position comparedPoint, Area area) {
        final PathIterator pathIterator = area.getPathIterator(null);

        double smallestDistance = Double.MAX_VALUE;

        while (!pathIterator.isDone()) {
            final double[] segment = new double[6];
            int type = pathIterator.currentSegment(segment);

            Position currentPosition = new Position(segment[0], segment[1]);

            if (type != PathIterator.SEG_CLOSE) {
                if (currentPosition.getDistance(comparedPoint) < smallestDistance) {
                    smallestDistance = currentPosition.getDistance(comparedPoint);
                }
            }


            pathIterator.next();
        }

        return smallestDistance;
    }

    public static void main(String[] args) {

        String inPath = "Build/WallMaps/Floor1_Tables.png";
        String outPath = "Resources/WallMaps/Floor1Tables.oos";

        try {
            List<Area>areaList = ImageTableCreator.getAreas(ImageIO.read(new File(inPath)));

            List<ServingTablePhysicalObject> allTables = new LinkedList<>();

            int i = 0;

            for (Area area : areaList) {
                allTables.add(new ServingTablePhysicalObject(new BoundingArea(area)));
                System.out.println("The Object " + i + " was successfully add to the list");
                i++;
            }

            FileOutputStream fileOut = new FileOutputStream(outPath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(allTables);
            objectOut.close();

            System.out.println("List successfully written to file.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
