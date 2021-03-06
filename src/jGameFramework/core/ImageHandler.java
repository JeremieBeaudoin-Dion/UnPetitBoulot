package jGameFramework.core;

import addResourceLoaderHere.GameInformation;
import jGameFramework.display.*;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.awt.Dimension;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

import javax.swing.*;

/**
 * This class creates a JFrame instance.
 *
 * It draws the graphics on the frame and handles
 * double buffering.
 *
 * @author Mia Beaudoin-Dion
 *
 */
public class ImageHandler extends JFrame implements Runnable {

    public static final int TICK_PER_SEC = GameInformation.IMAGE_FRAMES_PER_SECOND;
    public static final boolean IS_FRAME_CAPPED = false;

    private Thread thread;
    private boolean running = false;

    public int frames;
    public int lastFrames;
    public int ticks;

    // Assuming the INSETS will not change during the game
    static Insets INSETS;

    static Position POSITION_ON_SCREEN = new Position(0, 0);

    private ThreadSafeImageSet imageSet;

    /**
     * Constructor; creates the window and such
     */
    ImageHandler (ThreadSafeImageSet imageSet){

        this.imageSet = imageSet;

        // Sets the parameter for the window
        setTitle(GameInformation.GAME_NAME);
        setPreferredSize(new Dimension(GameInformation.WINDOW_WIDTH, GameInformation.WINDOW_HEIGHT));
        setResizable(GameInformation.RESIZABLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        pack();

        // Makes sure the graphics don't get drawn on the border of the window
        INSETS = getInsets();
        setSize(INSETS.left + GameInformation.WINDOW_WIDTH + INSETS.right,
                INSETS.top + GameInformation.WINDOW_HEIGHT + INSETS.bottom);
    }

    /**
     * Called every frame, this method handles graphics
     */
    void update() {
        frames++;
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){
            createBufferStrategy(2);
            return;
        }

        updateLocationOnScreen();

        // Sets the double buffer
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        // Sets the quality of the image to high
        setRenderingQuality(g);

        // Sets default background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, super.getWidth(), super.getHeight());

        // Draws the physical objects on the frame
        drawFrame(g);

        // Clears the cache
        g.dispose();
        bs.show();
    }

    private void updateLocationOnScreen(){
        POSITION_ON_SCREEN = new Position(getLocationOnScreen().getX(), getLocationOnScreen().getY());
    }

    /**
     * Sets the desired rendering quality of the image
     */
    private void setRenderingQuality(Graphics2D bbg) {
        bbg.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        bbg.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        bbg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (Game.ANTIALIASING) {
            bbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        } else {
            bbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }
    }

    /**
     * Displays all Displayable on screen
     */
    private void drawFrame(Graphics2D bbg) {

        imageSet.startReading();
        for(Displayable object : imageSet.read()) {
            printDisplayableOnBuffer(bbg, object);
        }
        imageSet.stopReading();
    }

    /**
     * Sends the displayable to the correct method in order to be put on screen
     */
    private void printDisplayableOnBuffer(Graphics2D bbg, Displayable displayable) {
        if (displayable instanceof DisplayableImage) {
            printImage(bbg, (DisplayableImage) displayable);

        } else if (displayable instanceof DisplayableShapeOutline) {
            printShapeOutline(bbg, (DisplayableShapeOutline) displayable);

        } else if (displayable instanceof DisplayableShapeFilled){
            printShapeFilled(bbg, (DisplayableShapeFilled) displayable);

        } else if (displayable instanceof DisplayableText){
            printText(bbg, (DisplayableText) displayable);
        }
    }

    private void printImage(Graphics2D bbg, DisplayableImage displayableImage) {
        bbg.drawImage(displayableImage.getImage(),
                displayableImage.getPosition().getX() + INSETS.left,
                displayableImage.getPosition().getY() + INSETS.top, null);
    }

    private void printShapeOutline(Graphics2D bbg, DisplayableShapeOutline displayableShapeOutline) {
        bbg.setPaint(displayableShapeOutline.getPaint());
        bbg.setStroke(displayableShapeOutline.getStroke());

        Shape displayShape = displayableShapeOutline.getShape();

        AffineTransform tx = new AffineTransform();
        tx.translate(INSETS.left, INSETS.top);

        bbg.draw(tx.createTransformedShape(displayShape));
    }

    private void printShapeFilled(Graphics2D bbg, DisplayableShapeFilled displayableShapeFilled) {
        bbg.setPaint(displayableShapeFilled.getPaint());

        Shape displayShape = displayableShapeFilled.getShape();

        AffineTransform tx = new AffineTransform();
        tx.translate(INSETS.left, INSETS.top);

        bbg.fill(tx.createTransformedShape(displayShape));
    }

    private void printText(Graphics2D bbg, DisplayableText displayableText) {
        bbg.setPaint(displayableText.getPaint());
        bbg.setFont(displayableText.getFont());

        int x = displayableText.getPosition().getX();
        int y = displayableText.getPosition().getY();

        FontMetrics fontMetrics = bbg.getFontMetrics(displayableText.getFont());

        if (displayableText.getAlignment() == DisplayableText.Alignment.center) {
            x = x - fontMetrics.stringWidth(displayableText.getMessage())/2;
            y = y - fontMetrics.getHeight()/2 + fontMetrics.getAscent();
        }
        if (displayableText.getAlignment() == DisplayableText.Alignment.right) {
            x = x - fontMetrics.stringWidth(displayableText.getMessage());
        }

        bbg.drawString(displayableText.getMessage(), x + INSETS.left, y + INSETS.top);
    }

    @Override
    public void paint (Graphics g) {}

    @Override
    public void repaint () {}

    void resizeIfNotNull(Position nextScreenSize) {
        if (nextScreenSize != null) {
            super.setSize(nextScreenSize.getX(), nextScreenSize.getY());
        }

    }

    public void run() {
        //Tick counter variable
        long lastTime = System.nanoTime();
        //Nanoseconds per Tick
        double nsPerTick = 1000000000D/ TICK_PER_SEC;
        frames = 0;
        ticks = 0;
        long fpsTimer = System.currentTimeMillis();
        double delta = 0;
        boolean shouldRender;
        while(running){
            shouldRender = !IS_FRAME_CAPPED;
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            //if it should tick it does this
            while(delta >= 1 ){
                ticks++;
                //tick();
                delta -= 1;
                shouldRender = true;
            }
            if (shouldRender){
                update();
            }
            if (fpsTimer < System.currentTimeMillis() - 1000){
                System.out.println(ticks +" ticks, "+ frames+ " frames");
                ticks = 0;
                lastFrames = frames;
                frames = 0;
                fpsTimer = System.currentTimeMillis();
            }
        }
    }

    public synchronized void start(){
        if(running) return;
        running = true;
        thread = new Thread(this, "Thread");
        thread.start();
    }

}

