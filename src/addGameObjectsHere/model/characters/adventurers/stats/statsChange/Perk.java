package addGameObjectsHere.model.characters.adventurers.stats.statsChange;

/**
 * A perk is a modifier for an adventurer that has triggers.
 */
public abstract class Perk {

    private boolean silenced;
    private boolean activated;
    private Trigger startTrigger;
    private Trigger endTrigger;
    private PerkType perkType;
    private PerkId id;

    public Perk(PerkId id) {
        this.id = id;
        this.perkType = PerkId.getPerkType(id);
    }

    public Perk(PerkId id, Trigger startTrigger, Trigger endTrigger) {
        silenced = false;
        activated = false;
        this.startTrigger = startTrigger;
        this.endTrigger = endTrigger;
        this.id = id;
        this.perkType = PerkId.getPerkType(id);
    }

    public void doTrigger(Trigger trigger) {
        if (silenced) {
            return;
        }

        if (trigger == startTrigger) {
            activated = true;
            doStartTrigger();
        }

        if (trigger == endTrigger) {
            activated = false;
            doEndTrigger();
        }
    }

    public void silence() {
        silenced = true;

        if (activated) {
            doEndTrigger();
        }
    }

    public void unSilence() {
        silenced = false;

        if (activated) {
            doStartTrigger();
        }
    }

    public PerkType getPerkType() {
        return perkType;
    }

    public String getName() {
        return id.toString();
    }

    public String getDescription() {
        return PerkId.getDescription(id);
    }

    protected abstract void doStartTrigger();
    protected abstract void doEndTrigger();

}
