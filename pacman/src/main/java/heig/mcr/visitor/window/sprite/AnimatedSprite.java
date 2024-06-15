package heig.mcr.visitor.window.sprite;

import heig.mcr.visitor.math.RegionOfInterest;

import java.awt.*;

/**
 * An animated sprite displays an entity using a sequence of images.
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 */
public class AnimatedSprite implements Sprite {
    private static final Sprite ANIMATION_END = new DefaultSprite();
    private static final int DEFAULT_BLINK_DELAY = 80;

    private boolean visible = true;
    private final Sprite[] frames;
    private final int delay;

    private int currentFrame = 0;
    private boolean looping;
    private boolean animating;

    // Variables for blinking
    private boolean blinking;
    private int blinkDelay;

    private long lastUpdate = System.currentTimeMillis();
    private long lastBlink = System.currentTimeMillis();

    private AnimatedSprite(
            Sprite[] frames,
            int delay,
            boolean looping,
            boolean animating,
            boolean blinking,
            int blinkDelay
    ) {
        this.frames = frames;

        this.delay = delay;
        this.blinkDelay = blinkDelay;

        this.looping = looping;
        this.animating = animating;
        this.blinking = blinking;
    }

    public AnimatedSprite(Sprite[] frames, int delay, boolean looping) {
        this(frames, delay, looping, true);
    }

    public AnimatedSprite(Sprite[] frames, int delay, boolean looping, boolean animating) {
        this(frames, delay, looping, animating, false);
    }

    public AnimatedSprite(Sprite[] frames, int delay, boolean looping, boolean animating, boolean blinking) {
        this(frames, delay, looping, animating, blinking, DEFAULT_BLINK_DELAY);
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

    public boolean isAnimating() {
        return animating;
    }

    public void startBlinking() {
        blinking = true;
        lastBlink = 0;
    }

    public void stopBlinking() {
        blinking = false;
        visible = true;
    }

    private void updateBlinking() {
        long now = System.currentTimeMillis();
        if (blinking && now - lastBlink > blinkDelay) {
            visible = !visible;
            lastBlink = now;
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
        if (!visible) {
            return;
        }

        getCurrentFrame().draw(
                graphics,
                x - width / 2,
                y - height / 2,
                width * 2,
                height * 2
        );
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

    public int getCurrentFrameStep(){
        return currentFrame;
    }

    private Sprite getCurrentFrame() {
        if (currentFrame < frames.length) {
            return frames[currentFrame];
        } else {
            return ANIMATION_END;
        }
    }
}
