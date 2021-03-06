package addGameObjectsHere.model;

import addGameObjectsHere.model.quests.Quest;

import java.util.Collection;

/**
 * A QuestModelHandler that cannot be changed.
 *
 * @author Mia Beaudoin-Dion
 */
public interface ImmutableQuestsModelHandler {

    Collection<Quest> getAllAssignedQuests();

}
