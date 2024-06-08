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
        return loadDirectedSprites("/sprites/pacman.png", 4, 16, 16);
    }

    public Map<Direction, AnimatedSprite> getRedPacman() {
        return loadDirectedSprites("/sprites/pacman-red.png", 4, 16, 16);
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
        return loadDirectedSprites("/sprites/ghost-red.png", 2, 16,16);
    }

    public Map<Direction, AnimatedSprite> getSith() {
        return loadDirectedSprites("/sprites/sith.png", 3, 29, 23);
    }

    public Map<Direction, AnimatedSprite> getEdibleSith() {
        return loadDirectedSprites("/sprites/sith_weak.png", 3, 29, 23);
    }

    public Map<Direction, AnimatedSprite> getLuke() {
        return loadDirectedSprites("/sprites/luke.png", 3, 29, 23);
    }

    public Map<Direction, AnimatedSprite> getVader() {
        return loadDirectedSprites("/sprites/vader.png", 3, 29, 23);
    }

    public Map<Direction, AnimatedSprite> getEdibleVader() {
        return loadDirectedSprites("/sprites/vader_weak.png", 3, 29, 23);
    }

    public Map<Direction, AnimatedSprite> getStormTrooper() {
        return loadDirectedSprites("/sprites/stormtrooper.png", 3, 29, 23);
    }

    public Map<Direction, AnimatedSprite> getEdibleStormTrooper() {
        return loadDirectedSprites("/sprites/stormtrooper_weak.png", 3, 29, 23);
    }

    public Map<Direction, AnimatedSprite> getBobaFett() {
        return loadDirectedSprites("/sprites/bobafett.png", 3, 29, 23);
    }

    public Map<Direction, AnimatedSprite> getEdibleBobaFett() {
        return loadDirectedSprites("/sprites/bobafett_weak.png", 3, 29, 23);
    }



    private Map<Direction, AnimatedSprite> loadDirectedSprites(String resource, int frames, int spriteWidth, int spriteHeight) {
        Map<Direction, AnimatedSprite> sprites = new EnumMap<>(Direction.class);
        Sprite base = loadSprite(resource);

        for (int i = 0; i < DIRECTED_ORDER.length; i++) {
            Sprite directionSprite = base.slice(new RegionOfInterest(
                    0, i * spriteHeight,
                    frames * spriteWidth,
                    spriteHeight
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
