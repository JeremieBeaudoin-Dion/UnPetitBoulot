package addResourceLoaderHere;

import addGameObjectsHere.model.inn.Inn;

/**
 * Creates and holds necessary model objects.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ModelObjectLoader {

    private Inn inn;

    public ModelObjectLoader() {
        this.inn = new Inn();
    }

    public Inn getInn() {
        return inn;
    }
}
