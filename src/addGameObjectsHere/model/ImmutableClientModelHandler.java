package addGameObjectsHere.model;

import addGameObjectsHere.model.characters.client.Client;

/**
 * A ClientModelHandler that cannot be modified.
 *
 * @author Jérémie Beaudoin-Dion
 */
public interface ImmutableClientModelHandler {

    Client getCurrentConversationClient();

}
