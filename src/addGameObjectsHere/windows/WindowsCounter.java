package addGameObjectsHere.windows;

/**
 * A counter class that will implement every time update() is called.
 *
 * This is protected by a flag. If the flag is false, the counter will not implement.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class WindowsCounter {

    private int max_value;
    private boolean flag;
    private int currentValue;

    /**
     * Constructor
     */
    public WindowsCounter(int max_value) {
        if (max_value < 0) {
            throw new IllegalArgumentException("The counter's value must be bigger than 0");
        }

        this.max_value = max_value;
        currentValue = 0;
        flag = false;
    }

    public void start() {
        flag = true;
    }

    public void stop() {
        flag = false;
        currentValue = 0;
    }

    public void update() {
        if (flag) {
            currentValue++;
        }
    }

    public boolean isDone() {
        return currentValue >= max_value;
    }

}
