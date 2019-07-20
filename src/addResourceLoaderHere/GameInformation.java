package addResourceLoaderHere;

import jGameFramework.physicalObjects.Position;

import javax.sound.sampled.Clip;

/**
 * Contains all the useful game information, such as the name,
 * the pixels of the window, the FPS and the GameThreadID to
 * use at start of game.
 *
 * These variables should not be removed as they are necessary
 * for the game to run.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameInformation {

    public final static String GAME_NAME = "The Innkeeper";

    public static int WINDOW_WIDTH = 1280;
    public static int WINDOW_HEIGHT = 720;

    public static final long FRAMES_PER_SECOND = 60;

    public static final GameThreadID START_GAME_ID = GameThreadID.Conversation;

    /*
     * This is a hint for the ImageHandler.
     *
     * Basically, this should be FALSE if a lot of images will be used,
     * or TRUE if a lot of shapes will be used.
     */
    public static final boolean ANTIALIASING = false;

    /*
     * CAUTION: This will make the window fully resizable, and every
     *          Physical object will be automatically resized by the
     *          JGE.
     *
     * Another way to resize the game is with a new GameThreadEventResize(newScreenSize).
     * The JGE will still resize all PhysicalObjects. For any custom ways to handle resizing,
     * override the resize() method in desired PhysicalObjects.
     */
    public static final boolean RESIZABLE = false;

    // To simplify a lot of the code, all the music clips share the same LoopValue.
    public static final int musicLoopValue = Clip.LOOP_CONTINUOUSLY;

    /**
     * Called automatically every time the JFrame is resized.
     */
    public static void resize(Position newWidthAndHeight) {
        WINDOW_WIDTH = newWidthAndHeight.getX();
        WINDOW_HEIGHT = newWidthAndHeight.getY();
    }

}
