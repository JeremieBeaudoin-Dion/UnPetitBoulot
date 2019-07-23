package addResourceLoaderHere;

import addGameObjectsHere.model.inn.Inn;

import java.io.Serializable;

/**
 * Creates and holds necessary model objects.
 *
 * The model objects keep necessary game information between threads. This
 * can contain, for example, how much money the player will have.
 *
 * GameEvents to SAVE the game will only save ModelObjectLoader and current PhysicalObjects on screen.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ModelObjectLoader implements Serializable {

    private Inn inn;

    public ModelObjectLoader() {
        this.inn = new Inn();
    }

    public Inn getInn() {
        return inn;
    }
}
