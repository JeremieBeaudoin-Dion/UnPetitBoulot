package jGameFramework.core.threadObjects;

import addResourceLoaderHere.*;
import jGameFramework.core.ThreadSafeImageSet;
import jGameFramework.display.Displayable;
import jGameFramework.exceptions.EmptyThreadException;
import jGameFramework.physicalObjects.Position;
import javafx.util.Pair;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

/**
 * The GameThreadHandler uses the ResourceLoaders to create and put
 * all current GameThreads in a Stack. Only one GameThread runs at
 * a time (the one on top of the stack) so when a game thread is added
 * to the stack, the others are paused until the game returns to that
 * thread.
 *
 * This class also handles saveGame() and loadGame(), storing the
 * current stack of GameThreads in a file or deSerializing it to
 * come back to the desired state.
 *
 * @author Mia Beaudoin-Dion
 */
public class GameThreadHandler {

    private Position nextScreenSize;

    // Threads
    private Stack<GameThread> allThreads;

    // Resource loaders
    private ImageLoader imageLoader;
    private ActionLoader actionLoader;
    private MusicLoader musicLoader;
    private SoundLoader soundLoader;
    private ModelObjectLoader modelObjectLoader;
    private PhysicalObjectLoader physicalObjectLoader;
    private ThreadSafeImageSet imageSet;

    /**
     * Constructor
     */
    public GameThreadHandler(ThreadSafeImageSet imageSet) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        this.imageSet = imageSet;
        loadResources();

        setNewGameState(GameInformation.START_GAME_ID);

    }

    private void loadResources() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        imageLoader = new ImageLoader();
        actionLoader = new ActionLoader();
        musicLoader = new MusicLoader();
        soundLoader = new SoundLoader();
        modelObjectLoader = new ModelObjectLoader();
        physicalObjectLoader = new PhysicalObjectLoader(imageLoader, modelObjectLoader);
    }

    /**
     * Getter for the current thread's InputHandler
     *
     * This is used by the KeyHandler and MouseHandler to get
     * the proper coreAction.Action to do according to the input.
     */
    public InputHandler getCurrentInputHandler(){
        return allThreads.peek().getInputHandler();
    }

    /*
     * Basic methods called every frame
     */
    public void update(double deltaValue){
        allThreads.peek().update(deltaValue);
    }

    /**
     * Updates the threadsafe image holder with the correct images to change.
     */
    public void updateImages(){
        imageSet.startReading();
        SortedSet<Displayable> newSetOfImages = allThreads.peek().getImages(imageLoader);
        SortedSet<Displayable> currentSetOfImages = imageSet.read();

        if (!areSetImagesEqual(newSetOfImages, currentSetOfImages)) {
            imageSet.stopReading();
            imageSet.write(newSetOfImages);
        } else {
            imageSet.stopReading();
        }
    }

    private boolean areSetImagesEqual(SortedSet<Displayable> newSetOfImages, SortedSet<Displayable> currentSetOfImages) {
        if (newSetOfImages.size() != currentSetOfImages.size()) {
            return false;
        }

        Iterator<Displayable> newSetIter = newSetOfImages.iterator();
        Iterator<Displayable> currentSetIter = currentSetOfImages.iterator();

        while (newSetIter.hasNext() && currentSetIter.hasNext()) {
            Displayable newSetElement = newSetIter.next();
            Displayable currentSetElement = currentSetIter.next();

            if (!newSetElement.equals(currentSetElement)) {
                return false;
            }
        }
        return true;
    }

    public void resize(Position lastSize, Position newScreenSize) {
        allThreads.peek().resize(lastSize, newScreenSize);
    }

    public void setNextResize(Position newScreenSize) {
        nextScreenSize = newScreenSize.clone();
    }

    public Position getResizeAndSetNull() {
        if (nextScreenSize != null) {
            Position temp = nextScreenSize;
            nextScreenSize = null;

            return temp;
        }

        return null;
    }

    /*
     * Plays music and sounds
     */
    public void playSound(String id){
        allThreads.peek().playSound(id);
    }

    public void playMusic(String id){
        allThreads.peek().playMusic(id);
    }

    public void stopMusic(String id){
        allThreads.peek().stopMusic(id);
    }

    /**
     * Pops the current thread. If the stack of threads is
     * empty, quits the game.
     */
    public void quitCurrentState() {
        if (!allThreads.isEmpty()) {
            allThreads.peek().clear();
            allThreads.pop();
        }

        if (allThreads.isEmpty()) {
            throw new EmptyThreadException();
        }
    }

    /**
     * Adds a new GameThread on top of current thread's stack
     */
    public void addNewGameState(GameThreadID gameThreadID) {
        allThreads.push(createGameThread(gameThreadID));
    }

    /**
     * Sets the top gameThread and empties stack
     */
    public void setNewGameState(GameThreadID gameThreadID) {
        allThreads = new Stack<>();

        allThreads.push(createGameThread(gameThreadID));
    }

    private GameThread createGameThread(GameThreadID gameThreadID){
        return new GameThread(this, gameThreadID, physicalObjectLoader.getCamera(gameThreadID),
                physicalObjectLoader.get(gameThreadID), actionLoader.get(gameThreadID),
                musicLoader.get(gameThreadID), soundLoader.get(gameThreadID));
    }

    /*
     * Save and load games
     */
    public void saveGame(String saveFilePath) {

        try {
            FileOutputStream fileOut = new FileOutputStream(saveFilePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(getSerial());

            out.close();
            fileOut.close();

        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    private Pair<ModelObjectLoader, Stack<GameThreadSerialized>> getSerial() {
        Stack<GameThreadSerialized> serialThreads = new Stack<>();
        Stack<GameThread> copiedStack = (Stack<GameThread>) allThreads.clone();

        while (!copiedStack.isEmpty()) {
            serialThreads.push(copiedStack.pop().getSerial());
        }

        return new Pair<>(modelObjectLoader, serialThreads);
    }

    @SuppressWarnings("unchecked")
    public void loadGame(String saveFilePath) {
        try {
            FileInputStream file = new FileInputStream(saveFilePath);
            ObjectInputStream saved = new ObjectInputStream(file);

            deSerialize((Pair<ModelObjectLoader, Stack<GameThreadSerialized>>) saved.readObject());

            saved.close();
            file.close();

        } catch (IOException | ClassNotFoundException e) {
            setNewGameState(GameInformation.START_GAME_ID);
            e.printStackTrace();
        }

    }

    private void deSerialize(Pair<ModelObjectLoader, Stack<GameThreadSerialized>> deserialized) {
        // Deserialize model
        this.modelObjectLoader = deserialized.getKey();
        this.physicalObjectLoader.setModel(modelObjectLoader);

        // Deserialize threads
        Stack<GameThreadSerialized> serialThreads = deserialized.getValue();
        allThreads = new Stack<>();

        while (!serialThreads.isEmpty()) {
            allThreads.push(serialThreads.pop().getThread(this, actionLoader, musicLoader, soundLoader));
        }
    }

}
