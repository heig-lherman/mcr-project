package heig.mcr.visitor.window.sprite;

import heig.mcr.visitor.math.RegionOfInterest;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A register that loads and caches sprites.
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 */
public abstract class SpriteRegister {

    private final Map<String, Sprite> spriteCache = new HashMap<>();

    public Sprite loadSprite(String resource) {
        return spriteCache.computeIfAbsent(resource, this::loadResource);
    }

    public AnimatedSprite createAnimation(Sprite resource, int frames, int delay, boolean loop) {
        int frameWidth = resource.getWidth() / frames;

        Sprite[] animation = new Sprite[frames];
        for (int i = 0; i < frames; i++) {
            animation[i] = resource.slice(new RegionOfInterest(
                    i * frameWidth, 0,
                    frameWidth, resource.getHeight()
            ));
        }

        return new AnimatedSprite(animation, delay, loop);
    }


    private Sprite loadResource(String resource) {
        try (var stream = getClass().getResourceAsStream(resource)) {
            if (stream == null) {
                throw new IllegalArgumentException("Resource not found: " + resource);
            }

            return new ImageSprite(ImageIO.read(stream));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load resource: " + resource, e);
        }
    }
}
