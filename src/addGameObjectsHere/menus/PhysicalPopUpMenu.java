package addGameObjectsHere.menus;

import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableShapeFilled;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class PhysicalPopUpMenu extends PhysicalObject {

    /**
     * Constructor
     */
    public PhysicalPopUpMenu() {
        super(getBasicArea(), false);
    }

    private static BoundingArea getBasicArea() {
        int width = GameInformation.WINDOW_WIDTH / 4;
        double height = GameInformation.WINDOW_HEIGHT / 2.5;

        return new BoundingArea(new RoundRectangle2D.Double(70, 100, width, height, 10, 10));
    }

    /**
     * Returns the image representation of this physical object
     * <p>
     * The ImageObject will have a relative position depending
     * on the position of the CameraWithEdges.
     *
     * @param cameraPosition
     * @param imageLoader
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> allImages = new TreeSet<>();

        allImages.addAll(getBackgroundImages(cameraPosition));

        return allImages;
    }

    private List<Displayable> getBackgroundImages(Position cameraPosition) {
        List<Displayable> allDisplayables = new LinkedList<>();

        allDisplayables.add(new DisplayableShapeFilled(0, getAreaAccordingToCamera(cameraPosition), Color.darkGray));

        return allDisplayables;
    }

    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    @Override
    public boolean dispose() {
        return false;
    }
}
