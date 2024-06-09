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
        var img = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        graphics.drawImage(img, x, y, null);
    }

    @Override
    public Sprite slice(RegionOfInterest roi) {
        int width = Math.min(getWidth(), roi.w());
        int height = Math.min(getHeight(), roi.h());

        BufferedImage img = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(
                        width,
                        height,
                        Transparency.TRANSLUCENT
                );

        Graphics2D g = img.createGraphics();
        g.drawImage(
                image,
                0, 0,
                roi.w(), roi.h(),
                roi.x(), roi.y(),
                roi.x() + roi.w(), roi.y() + roi.h(),
                null
        );

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
}
