package heig.mcr.visitor.window.sprite;

import java.awt.*;

/**
 * Contract for a drawable object that sometimes requires special rendering.
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 */
public interface SpecialRender {

    void renderSpecial(Graphics2D g, int x, int y, int width, int height);

    boolean hasSpecialRendering();
}
