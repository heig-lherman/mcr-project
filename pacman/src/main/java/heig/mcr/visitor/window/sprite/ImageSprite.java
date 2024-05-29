package heig.mcr.visitor.window.sprite;

import heig.mcr.visitor.math.RegionOfInterest;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * An image sprite displays an entity using a given image or subset of image.
 */
public class ImageSprite implements Sprite {

    private final Image image;

    public ImageSprite(Image image) {
        this.image = image;
    }

    @Override
    public void draw(Graphics graphics, int x, int y, int width, int height) {
        graphics.drawImage(
                image, x, y, x + width, x + height, 0, 0,
                image.getWidth(null), image.getHeight(null), null
        );
    }

    @Override
    public Sprite slice(RegionOfInterest roi) {
        BufferedImage img = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(
                        roi.w(),
                        roi.h(),
                        Transparency.BITMASK
                );

        Graphics g = img.createGraphics();
        g.drawImage(
                image,
                0, 0,
                roi.w(), roi.h(),
                roi.x(), roi.y(),
                roi.x() + roi.w(), roi.y() + roi.h(),
                null
        );
        g.dispose();

        return new ImageSprite(img);
    }

    @Override
    public int getWidth() {
        return image.getWidth(null);
    }

    @Override
    public int getHeight() {
        return image.getHeight(null);
    }

    private boolean withinBounds(int x, int y) {
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }
}
