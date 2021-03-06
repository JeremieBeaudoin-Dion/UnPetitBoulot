package addGameObjectsHere.model.quests.challenges;

import addGameObjectsHere.model.characters.adventurers.stats.BaseStat;
import addGameObjectsHere.model.characters.adventurers.stats.StatValue;

/**
 * A challenge is an encounter that the Party will have to overcome.
 *
 * @author Mia Beaudoin-Dion
 */
public class Challenge extends DiceRoller {

    private ChallengeID challengeID;
    private ChallengeType challengeType;
    private StatValue challenge;
    private StatValue damage;

    public Challenge(ChallengeID id, StatValue challenge, StatValue damage) {
        challengeID = id;
        challengeType = ChallengeID.getChallengeType(id);
        this.challenge = challenge;
        this.damage = damage;
    }

    public String getChallengeName() {
        return challengeID.toString();
    }

    public String getChallengeDescription() {
        return ChallengeID.getDescription(challengeID);
    }

    public ChallengeType getChallengeType() {
        return challengeType;
    }

    public StatValue getChallenge() {
        return challenge;
    }

    public StatValue getDamage() {
        return damage;
    }

    public int rollChallenge() {
        return rollDice(challenge.getValue());
    }

    public int rollDamage() {
        return rollDice(damage.getValue());
    }
}
