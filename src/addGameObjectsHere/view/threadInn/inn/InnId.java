package addGameObjectsHere.view.threadInn.inn;

/**
 * Represents different
 *
 * @author Jérémie Beaudoin-Dion
 */
public enum InnId {

    floor1;

    public InnImagesID getImageID(InnId innId) {
        switch (innId) {
            case floor1:
                return InnImagesID.Floor1;
        }

        throw new UnsupportedOperationException("The innID : " + innId.name() + " is not supported.");
    }

}
