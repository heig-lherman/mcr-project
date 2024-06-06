package heig.mcr.visitor.window.sprite;

import heig.mcr.visitor.math.RegionOfInterest;

import java.awt.*;

public class ColorSprite implements Sprite {

    private final Color color;

    public ColorSprite(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics graphics, int x, int y, int width, int height) {
        graphics.setColor(color);
        graphics.fillRect(x, y, width, height);
    }

    @Override
    public Sprite slice(RegionOfInterest roi) {
        return new ColorSprite(color);
    }

    @Override
    public int getWidth() {
        return 16;
    }

    @Override
    public int getHeight() {
        return 16;
    }

    @Override
    public void startBlinking() {

    }

    @Override
    public void stopBlinking() {

    }
}
