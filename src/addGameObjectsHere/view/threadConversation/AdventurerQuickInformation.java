package addGameObjectsHere.view.threadConversation;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.characters.adventurers.stats.BaseStat;
import addGameObjectsHere.model.characters.adventurers.stats.CharacterStats;
import addGameObjectsHere.model.quests.Quest;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * A physical object used by conversation UIs to give information on a certain quest
 * when giving it to an adventurer.
 *
 * @author Mia Beaudoin-Dion
 */
public class AdventurerQuickInformation extends PhysicalObject {

    private final static int TEXT_SIZE = 25;

    private Adventurer adventurer;
    private boolean visible;

    /**
     * Constructor
     */
    public AdventurerQuickInformation(BoundingArea boundingArea, DisplayableDepth depth, Adventurer adventurer) {
        super(boundingArea, false, depth);

        this.adventurer = adventurer;
        visible = true;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        if (!isVisible()) {
            return images;
        }

        Color fontColor = Color.BLACK;

        CharacterStats stats = adventurer.getUnit().getStats();

        images.add(new DisplayableText(getPositionAccordingToCamera(cameraPosition).add(new Position(getWidth()/7, TEXT_SIZE/2)),
                getDepth(), "Hp: " + stats.getMaxHealth(),
                imageLoader.getBaseFont(TEXT_SIZE), getStatColor(BaseStat.Hp), DisplayableText.Alignment.center));

        images.add(new DisplayableText(getPositionAccordingToCamera(cameraPosition).add(new Position(getWidth()*2/7, TEXT_SIZE/2)),
                getDepth(), "Att: " + stats.get(BaseStat.Att),
                imageLoader.getBaseFont(TEXT_SIZE), getStatColor(BaseStat.Att), DisplayableText.Alignment.center));

        images.add(new DisplayableText(getPositionAccordingToCamera(cameraPosition).add(new Position(getWidth()*3/7, TEXT_SIZE/2)),
                getDepth(), "Dex: " + stats.get(BaseStat.Dex),
                imageLoader.getBaseFont(TEXT_SIZE), getStatColor(BaseStat.Dex), DisplayableText.Alignment.center));

        images.add(new DisplayableText(getPositionAccordingToCamera(cameraPosition).add(new Position(getWidth()*4/7, TEXT_SIZE/2)),
                getDepth(), "Con: " + stats.get(BaseStat.Con),
                imageLoader.getBaseFont(TEXT_SIZE), getStatColor(BaseStat.Con), DisplayableText.Alignment.center));

        images.add(new DisplayableText(getPositionAccordingToCamera(cameraPosition).add(new Position(getWidth()*5/7, TEXT_SIZE/2)),
                getDepth(), "Hon: " + stats.get(BaseStat.Hon),
                imageLoader.getBaseFont(TEXT_SIZE), getStatColor(BaseStat.Hon), DisplayableText.Alignment.center));

        images.add(new DisplayableText(getPositionAccordingToCamera(cameraPosition).add(new Position(getWidth()*6/7, TEXT_SIZE/2)),
                getDepth(), "Cut: " + adventurer.getCost() + "%",
                imageLoader.getBaseFont(TEXT_SIZE), getStatColor(BaseStat.Cut), DisplayableText.Alignment.center));

        return images;
    }

    protected Paint getStatColor(BaseStat stat) {
        CharacterStats.StatIsBuffed statIsBuffed = adventurer.getUnit().getStats().compareStatToOriginal(stat);

        if (statIsBuffed == CharacterStats.StatIsBuffed.buffed) {
            return new Color(0, 60, 0);
        } else if (statIsBuffed == CharacterStats.StatIsBuffed.nerfed) {
            return new Color(60, 0, 0);
        } else {
            return new Color(0, 0, 0);
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean isVisible) {
        visible = isVisible;
    }

    /**
     * Returns any action that the GameThread should handle
     */
    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    /**
     * Returns true if the object should be disposed of
     */
    @Override
    public boolean dispose() {
        return false;
    }
}
