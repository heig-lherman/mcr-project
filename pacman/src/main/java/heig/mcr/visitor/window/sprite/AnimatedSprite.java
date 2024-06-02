package heig.mcr.visitor.window.sprite;

import heig.mcr.visitor.math.RegionOfInterest;

import java.awt.*;

public class AnimatedSprite implements Sprite {

    private static final Sprite ANIMATION_END = new DefaultSprite();

    private final Sprite[] frames;
    private final int delay;

    private int currentFrame;
    private boolean looping;
    private boolean animating;
    private long lastUpdate;


    public AnimatedSprite(Sprite[] frames, int delay, boolean looping) {
        this(frames, delay, looping, true);
    }

    public AnimatedSprite(Sprite[] frames, int delay, boolean looping, boolean animating) {
        this.frames = frames;
        this.delay = delay;
        this.looping = looping;
        this.animating = animating;

        this.currentFrame = 0;
        this.lastUpdate = System.currentTimeMillis();
    }

    public void start() {
        animating = true;
    }

    public void stop() {
        animating = false;
    }

    public void restart() {
        currentFrame = 0;
        lastUpdate = System.currentTimeMillis();
        start();
    }

    private Sprite getCurrentFrame() {
        if (currentFrame < frames.length) {
            return frames[currentFrame];
        } else {
            return ANIMATION_END;
        }
    }

    private void update() {
        long now = System.currentTimeMillis();
        if (animating && now - lastUpdate > delay) {
            currentFrame++;

            if (looping) {
                currentFrame %= frames.length;
            } else if (currentFrame >= frames.length) {
                animating = false;
            }

            lastUpdate = now;
        }
    }

    @Override
    public void draw(Graphics graphics, int x, int y, int width, int height) {
        update();
        getCurrentFrame().draw(graphics, x, y, width, height);
    }

    @Override
    public Sprite slice(RegionOfInterest roi) {
        update();
        return getCurrentFrame().slice(roi);
    }

    @Override
    public int getWidth() {
        return getCurrentFrame().getWidth();
    }

    @Override
    public int getHeight() {
        return getCurrentFrame().getHeight();
    }
}
