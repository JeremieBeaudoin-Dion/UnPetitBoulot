package addGameObjectsHere.view.threadConversation;

import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.util.LinkedList;
import java.util.List;

/**
 * A helper class to divide a string into a BoundingArea to fit into it.
 *
 * If the string is longer than the width of the BoundingArea, it is divided into
 * a list of stings which does fit.
 *
 * @author Mia Beaudoin-Dion
 */
public class TextLengthHelper {

    public static List<String> getTextListToFitIntoBox(String text, BoundingArea area, Position buffer, Font font) {
        FontRenderContext frc = new FontRenderContext(font.getTransform(), true, true);

        List<String> finalList = new LinkedList<>();

        String[] wordsInText = text.split(" ");
        StringBuilder currentText = new StringBuilder();

        for (int i=0; i<wordsInText.length; i++) {
            double textWidth = font.getStringBounds(currentText + wordsInText[i], frc).getWidth();

            if (textWidth >= area.getWidth() - (buffer.getX() * 2)) {
                finalList.add(currentText.toString());
                currentText = new StringBuilder(wordsInText[i] + " ");
            } else {
                currentText.append(wordsInText[i]);
                currentText.append(" ");
            }
        }

        finalList.add(currentText.toString());

        return finalList;
    }

}
