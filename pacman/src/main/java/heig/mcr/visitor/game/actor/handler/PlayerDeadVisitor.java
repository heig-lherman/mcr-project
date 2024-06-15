package heig.mcr.visitor.game.actor.handler;

import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.window.sprite.AnimatedSprite;
import heig.mcr.visitor.window.sprite.Sprite;

/**
 * A visitor for the player when he is dead.
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 */
public class PlayerDeadVisitor extends PlayerVisitor {

    private final AnimatedSprite deathSprites = PacmanSprites.getInstance().getPacmanDeath();

    protected PlayerDeadVisitor(Player player) {
        super(player);
        deathSprites.restart();
    }

    @Override
    public boolean isScary() {
        return false;
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void eatSuperPellet() {
        // Can't do anything
    }

    @Override
    public void kill() {
    }

    @Override
    public Sprite getSprite() {
        return deathSprites;
    }
}
