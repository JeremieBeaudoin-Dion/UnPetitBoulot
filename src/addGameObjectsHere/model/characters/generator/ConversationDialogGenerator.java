package addGameObjectsHere.model.characters.generator;

import addGameObjectsHere.model.characters.client.ClientConversationDialogID;
import addGameObjectsHere.model.characters.client.ClientID;

import java.util.EnumMap;

/**
 * @author Mia Beaudoin-Dion
 */
public class ConversationDialogGenerator {

    public EnumMap<ClientConversationDialogID, String> generateConversationDialog(ClientID clientID) {

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

    private EnumMap<ClientConversationDialogID, String> getMan() {
        EnumMap<ClientConversationDialogID, String> map = new EnumMap<>(ClientConversationDialogID.class);

        String description = "The man looks like someone who works with his hands and smells like someone who bathes " +
                "once a week. He likes complaining about the weather, telling bad jokes and spending copper on beer.";
        map.put(ClientConversationDialogID.description, description);

        return map;
    }

    private EnumMap<ClientConversationDialogID, String> getRanger() {
        EnumMap<ClientConversationDialogID, String> map = new EnumMap<>(ClientConversationDialogID.class);

        String description = "The ranger waits for her drink silently, smoking her pipe. Her boots are caked with mud " +
                "and her hair is full of grass leaves and twigs. She's polite, but she looks like she'd be more " +
                "comfortable drinking with bears and pixies than the townsfolk.";

        map.put(ClientConversationDialogID.description, description);

        String quest = "'Why not? At this point I'll do anything to get out of this stinking town!'";
        map.put(ClientConversationDialogID.hire, quest);

        return map;
    }

    private EnumMap<ClientConversationDialogID, String> getRogue() {
        EnumMap<ClientConversationDialogID, String> map = new EnumMap<>(ClientConversationDialogID.class);

        String description = "The cheery rogue spins coins on the table and tries to invite passersby to a game " +
                "of dice. Something tells you they brought their own dice for a reason. They seem to have a lot of money " +
                "to spend tonight. Let's hope their coins are not ALL counterfeit.";
        map.put(ClientConversationDialogID.description, description);

        String quest = "'For that price, I'll take the job.'";
        map.put(ClientConversationDialogID.hire, quest);

        return map;
    }

    private EnumMap<ClientConversationDialogID, String> getWarrior() {
        EnumMap<ClientConversationDialogID, String> map = new EnumMap<>(ClientConversationDialogID.class);

        String description = "With arms this large and a jawbone this square, the warrior would make a few eyes turn... " +
                "if it weren't for his missing teeth, cauliflower ears and crooked nose. He sits there, sharpening " +
                "his sword. He never asks for a drink, but you feel safer getting him something nonetheless.";
        map.put(ClientConversationDialogID.description, description);

        String quest = "'Sure. That sounds easy.'";
        map.put(ClientConversationDialogID.hire, quest);

        return map;
    }

    private EnumMap<ClientConversationDialogID, String> getOldWoman() {
        EnumMap<ClientConversationDialogID, String> map = new EnumMap<>(ClientConversationDialogID.class);

        String description = "The old lady is frail and trembles a bit, but you know the type. She might outdrink the " +
                "whole room. She asks: 'Would you know some upstanding folk who would be willing to do me a favor by " +
                "any chance? For some coin of course.'";
        map.put(ClientConversationDialogID.description, description);

        String accept = "'Good lad.'";
        map.put(ClientConversationDialogID.acceptQuest, accept);

        return map;
    }

}
