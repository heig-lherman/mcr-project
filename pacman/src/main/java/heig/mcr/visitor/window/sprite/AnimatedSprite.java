package heig.mcr.visitor.window.sprite;

import heig.mcr.visitor.math.RegionOfInterest;
import java.awt.*;

public class AnimatedSprite implements Sprite {
    private static final Sprite ANIMATION_END = new DefaultSprite();
    private static final int DEFAULT_BLINK_DELAY = 20;

    private final Sprite[] frames;
    private final int delay;

    private int currentFrame;
    private boolean looping;
    private boolean animating;
    private long lastUpdate;

    // Variables for blinking
    private boolean blinking;
    private int blinkDelay;
    private long lastBlink;
    private boolean visible = true;

    // Constructor with blink delay specified
    private AnimatedSprite(Sprite[] frames, int delay, boolean looping, boolean animating, int blinkDelay) {
        this.frames = frames;
        this.delay = delay;
        this.looping = looping;
        this.animating = animating;
        this.blinkDelay = blinkDelay;

        this.currentFrame = 0;
        this.lastUpdate = System.currentTimeMillis();
        this.lastBlink = System.currentTimeMillis();
    }

    public AnimatedSprite(Sprite[] frames, int delay, boolean looping) {
        this(frames, delay, looping, true);
    }

    public AnimatedSprite(Sprite[] frames, int delay, boolean looping, boolean animating) {
        this(frames, delay, looping, animating, DEFAULT_BLINK_DELAY);
    }

    public void restart() {
        currentFrame = 0;
        lastUpdate = System.currentTimeMillis();
        start();
    }

    public void start() {
        animating = true;
    }

    public void stop() {
        animating = false;
    }


    @Override
    public void startBlinking() {
        blinking = true;
        lastBlink = System.currentTimeMillis();
    }

    @Override
    public void stopBlinking() {
        blinking = false;
        visible = true;
    }

    private void updateBlinking() {
        if (blinking && System.currentTimeMillis() - lastBlink > blinkDelay) {
            visible = !visible;
            lastBlink = System.currentTimeMillis();
        }
    }

    private void updateAnimation() {
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
        updateAnimation();
        updateBlinking();
        if (visible) {
            getCurrentFrame().draw(graphics, x, y, width, height);
        }
    }

    @Override
    public Sprite slice(RegionOfInterest roi) {
        updateAnimation();
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

    private Sprite getCurrentFrame() {
        if (currentFrame < frames.length) {
            return frames[currentFrame];
        } else {
            return ANIMATION_END;
        }
    }
}
