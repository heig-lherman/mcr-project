package heig.mcr.visitor.window.sprite;

import heig.mcr.visitor.math.RegionOfInterest;

import java.awt.*;

/**
 * A sprite is a visual representation of an entity.
 */
public interface Sprite {

    /**
     * Draw the sprite at the given position.
     *
     * @param graphics the graphics context
     * @param x        the x position
     * @param y        the y position
     * @param width    the width
     * @param height   the height
     */
    void draw(Graphics graphics, int x, int y, int width, int height);

    /**
     * Slice a sub-sprite of the sprite.
     *
     * @param roi the region of interest
     * @return the sliced sprite
     */
    Sprite slice(RegionOfInterest roi);

    /**
     * Get the width and height of the sprite.
     *
     * @return the width and height of the sprite
     */
    int getWidth();

    /**
     * Get the height of the sprite.
     *
     * @return the height of the sprite
     */
    int getHeight();

     void startBlinking();
     void stopBlinking();
}
