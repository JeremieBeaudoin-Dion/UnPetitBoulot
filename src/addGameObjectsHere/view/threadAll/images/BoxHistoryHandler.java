package addGameObjectsHere.view.threadAll.images;

import addGameObjectsHere.view.threadAll.HistoryQueue;
import jGameFramework.physicalObjects.Position;
import javafx.util.Pair;

import java.awt.image.BufferedImage;

/**
 * For performances purposes, keeps the last 10 boxes created.
 *
 * @author Mia Beaudoin-Dion
 */
class BoxHistoryHandler {

    private HistoryQueue<Pair<BoxCreator.Background, Position>, BufferedImage> historyOfBoxes;

    BoxHistoryHandler() {
        historyOfBoxes = new HistoryQueue<>(10);
    }

    void set(BoxCreator.Background bck, Position dm, BufferedImage box) {
        historyOfBoxes.add(new Pair<>(bck, dm), box);
    }

    boolean exists(BoxCreator.Background bck, Position dm) {
        return historyOfBoxes.exists(new Pair<>(bck, dm));
    }

    BufferedImage getBox(BoxCreator.Background bck, Position dm) {
        return historyOfBoxes.get(new Pair<>(bck, dm));
    }

}
