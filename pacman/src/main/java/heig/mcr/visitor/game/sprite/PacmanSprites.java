package heig.mcr.visitor.game.sprite;

import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.math.RegionOfInterest;
import heig.mcr.visitor.window.sprite.AnimatedSprite;
import heig.mcr.visitor.window.sprite.Sprite;
import heig.mcr.visitor.window.sprite.SpriteRegister;

import java.util.EnumMap;
import java.util.Map;

public final class PacmanSprites extends SpriteRegister {

    private static final int SPRITE_SIZE = 16;
    private static final int ANIMATION_DELAY = 100;
    private static final Direction[] DIRECTED_ORDER = {
            Direction.DOWN,
            Direction.LEFT,
            Direction.RIGHT,
            Direction.UP,
    };

    private static final PacmanSprites INSTANCE = new PacmanSprites();

    private PacmanSprites() {
    }

    public Map<Direction, AnimatedSprite> getPacman() {
        return loadDirectedSprites("/sprites/pacman.png", 4);
    }

    public AnimatedSprite getPacmanDeath() {
        Sprite base = loadSprite("/sprites/pacman-death.png");
        return createAnimation(base, 11, ANIMATION_DELAY, false);
    }

    public Sprite getBackground() {
        return loadSprite("/sprites/background.png");
    }

    public Sprite getPellet() {
        return loadSprite("/sprites/pellet.png");
    }

    public Sprite getSuperPellet() {
        return loadSprite("/sprites/superpellet.png");
    }

    public Map<Direction, AnimatedSprite> getRedGhost() {
        return loadDirectedSprites("/sprites/ghost-red.png", 2);
    }

    private Map<Direction, AnimatedSprite> loadDirectedSprites(String resource, int frames) {
        Map<Direction, AnimatedSprite> sprites = new EnumMap<>(Direction.class);
        Sprite base = loadSprite(resource);

        for (int i = 0; i < DIRECTED_ORDER.length; i++) {
            Sprite directionSprite = base.slice(new RegionOfInterest(
                    0, i * SPRITE_SIZE,
                    frames * SPRITE_SIZE, SPRITE_SIZE
            ));

            AnimatedSprite animation = createAnimation(
                    directionSprite,
                    frames,
                    ANIMATION_DELAY,
                    true
            );

            animation.start();
            sprites.put(DIRECTED_ORDER[i], animation);
        }

        return sprites;
    }


    public static PacmanSprites getInstance() {
        return INSTANCE;
    }
}
