package addGameObjectsHere.view.threadInn.inn;

/**
 * The current possible ids for images
 *
 * @author Mia Beaudoin-Dion
 */
public enum InnImagesID {

    backgroundNight, Floor1, Bed;

    public static InnImagesID getImageID(InnId innId) {
        switch (innId) {
            case floor1:
                return InnImagesID.Floor1;
        }

        throw new UnsupportedOperationException("The innID : " + innId.name() + " is not supported.");
    }

}
