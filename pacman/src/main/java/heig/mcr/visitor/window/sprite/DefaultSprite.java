package heig.mcr.visitor.window.sprite;

import heig.mcr.visitor.math.RegionOfInterest;

import java.awt.*;

/**
 * A default sprite, draws nothing.
 */
public class DefaultSprite implements Sprite {

    @Override
    public void draw(Graphics graphics, int x, int y, int width, int height) {
        // Do nothing
    }

    @Override
    public Sprite slice(RegionOfInterest roi) {
        return new DefaultSprite();
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
