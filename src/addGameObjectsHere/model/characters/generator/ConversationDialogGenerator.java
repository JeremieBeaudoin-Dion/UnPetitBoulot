package addGameObjectsHere.model.characters.generator;

import addGameObjectsHere.model.characters.client.ClientConversationDialogID;
import addGameObjectsHere.model.characters.client.ClientID;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class ConversationDialogGenerator {

    public EnumMap<ClientConversationDialogID, List<String>> generateConversationDialog(ClientID clientID) {

        switch (clientID) {
            case OldWoman:
                return getOldWoman();
            case Man:
                return getMan();
            case Ranger:
                return getRanger();
            case Rogue:
                return getRogue();
            case Warrior:
                return getWarrior();
        }

        return null;
    }

    private EnumMap<ClientConversationDialogID, List<String>> getMan() {
        EnumMap<ClientConversationDialogID, List<String>> map = new EnumMap<>(ClientConversationDialogID.class);

        List<String> description = new LinkedList<>();
        description.add("The man looks like someone who works with his hands and");
        description.add("smells like someone who bathes once a week. He likes");
        description.add("complaining about the weather, telling bad jokes and");
        description.add("spending copper on beer.");
        map.put(ClientConversationDialogID.description, description);

        return map;
    }

    private EnumMap<ClientConversationDialogID, List<String>> getRanger() {
        EnumMap<ClientConversationDialogID, List<String>> map = new EnumMap<>(ClientConversationDialogID.class);

        List<String> description = new LinkedList<>();
        description.add("The ranger waits for his drink silently, smoking his pipe. His");
        description.add("boots are caked with mud and his hair is full of grass leaves");
        description.add("and twigs. He's polite, but he looks like he'd be more");
        description.add("comfortable drinking with bears and pixies than the townsfolk");
        map.put(ClientConversationDialogID.description, description);

        List<String> quest = new LinkedList<>();
        quest.add("'Why not? At this point I'll do anything to get out of this");
        quest.add("stinking town!'");
        map.put(ClientConversationDialogID.giveQuest, quest);

        return map;
    }

    private EnumMap<ClientConversationDialogID, List<String>> getRogue() {
        EnumMap<ClientConversationDialogID, List<String>> map = new EnumMap<>(ClientConversationDialogID.class);

        List<String> description = new LinkedList<>();
        description.add("The cheery rogue spins coins on the table and tries to invite");
        description.add("passersby to a game of dice. Something tells you he brought");
        description.add("his own dice for a reason. He seems to have a lot of money");
        description.add("to spend tonight. Let's hope his coins are not ALL counterfeit.");
        map.put(ClientConversationDialogID.description, description);

        List<String> quest = new LinkedList<>();
        quest.add("'For that price, I'll take the job.'");
        map.put(ClientConversationDialogID.giveQuest, quest);

        return map;
    }

    private EnumMap<ClientConversationDialogID, List<String>> getWarrior() {
        EnumMap<ClientConversationDialogID, List<String>> map = new EnumMap<>(ClientConversationDialogID.class);

        List<String> description = new LinkedList<>();
        description.add("With arms this large and a jawbone this square, the warrior");
        description.add("would make a few eyes turn... if it weren't for his missing");
        description.add("teeth, cauliflower ears and crooked nose. He sits there,");
        description.add("sharpening his sword. He never asks for a drink, but you feel");
        description.add("safer getting him something nonetheless.");
        map.put(ClientConversationDialogID.description, description);

        List<String> quest = new LinkedList<>();
        quest.add("'Sure. That sounds easy.'");
        map.put(ClientConversationDialogID.giveQuest, quest);

        return map;
    }

    private EnumMap<ClientConversationDialogID, List<String>> getOldWoman() {
        EnumMap<ClientConversationDialogID, List<String>> map = new EnumMap<>(ClientConversationDialogID.class);

        List<String> description = new LinkedList<>();
        description.add("The old lady is frail and trembles a bit, but you know the type.");
        description.add("She might outdrink the whole room.");
        description.add("'Would you know some upstanding fold who would be willing");
        description.add("to do me a favor by any chance? For some coin of course.'");
        map.put(ClientConversationDialogID.description, description);

        List<String> accept = new LinkedList<>();
        accept.add("'Good lad.'");
        map.put(ClientConversationDialogID.acceptQuest, accept);

        return map;
    }

}
