package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.view.threadAll.images.IconImageID;
import addGameObjectsHere.view.threadInn.characters.ClientImageID;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.TreeSet;

/**
 * An image representation of a client in a box for UI purposes.
 */
public class ClientBoxUI extends ImageBoxUI {

    private Client client;

    /**
     * Constructor
     */
    public ClientBoxUI(BoundingArea boundingArea, boolean isObstacle, DisplayableDepth depth,
                       Client client) {
        super(boundingArea, isObstacle, depth, ClientImageID.getID(client.getClientID()));

        this.client = client;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> setOfImages = super.getImageObjects(cameraPosition, imageLoader);

        Position beerPosition = getPositionAccordingToCamera(cameraPosition).add(
                new Position(getWidth()*7/10, getHeight()*6/10));
        BufferedImage beerImage = imageLoader.getImageFromID(IconImageID.beer);

        setOfImages.add(new DisplayableImage(beerPosition, getDepth().add(5), beerImage));

        Position beerTextPosition = beerPosition.add(new Position(beerImage.getWidth()/2, beerImage.getHeight()/2));

        setOfImages.add(new DisplayableText(beerTextPosition, getDepth().add(6),
                Integer.toString(client.getNumberOfDrinksTonight()), imageLoader.getBaseFont(45), Color.BLACK,
                DisplayableText.Alignment.center));

        return setOfImages;
    }

}
