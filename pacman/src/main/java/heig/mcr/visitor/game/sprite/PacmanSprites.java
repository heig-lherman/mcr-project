package heig.mcr.visitor.game.sprite;

import heig.mcr.visitor.window.sprite.Sprite;
import heig.mcr.visitor.window.sprite.SpriteRegister;

public final class PacmanSprites extends SpriteRegister {

    private static final PacmanSprites INSTANCE = new PacmanSprites();

    private PacmanSprites() {
    }

    public Sprite getWall() {
        return new WallSprite();
    }

    public Sprite getGround() {
        return new GroundSprite();
    }

    public Sprite getPellet() {
        return loadSprite("/sprites/pellet.png");
    }

    public static PacmanSprites getInstance() {
        return INSTANCE;
    }
}
