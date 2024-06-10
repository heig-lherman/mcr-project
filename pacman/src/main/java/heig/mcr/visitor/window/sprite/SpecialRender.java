package heig.mcr.visitor.window.sprite;

import java.awt.Graphics2D;

public interface SpecialRender {
    void renderSpecial(Graphics2D g, int x, int y, int width, int height);
    boolean hasSpecialRendering();
}