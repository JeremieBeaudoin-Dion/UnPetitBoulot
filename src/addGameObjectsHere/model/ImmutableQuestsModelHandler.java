package addGameObjectsHere.model;

import addGameObjectsHere.model.quests.Quest;

import java.util.Collection;

/**
 * A QuestModelHandler that cannot be changed.
 *
 * @author Jérémie Beaudoin-Dion
 */
public interface ImmutableQuestsModelHandler {

    Collection<Quest> getAllAssignedQuests();

}
