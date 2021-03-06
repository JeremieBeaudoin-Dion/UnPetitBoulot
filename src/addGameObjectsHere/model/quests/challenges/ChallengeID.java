package addGameObjectsHere.model.quests.challenges;

public enum ChallengeID {
    quiet, treasure, kobold, dragon;

    public static ChallengeType getChallengeType(ChallengeID challengeID) {
        switch (challengeID) {
            case quiet:
                return ChallengeType.QuietDay;
            case treasure:
                return ChallengeType.Treasure;
            case dragon:
            case kobold:
                return ChallengeType.Fight;
        }
        throw new IllegalArgumentException("The challenge :" + challengeID.toString() + " is not implemented.");
    }

    public static String getDescription(ChallengeID challengeID) {
        switch (challengeID) {

        }
        throw new IllegalArgumentException("The challenge :" + challengeID.toString() + " is not implemented.");
    }
}
