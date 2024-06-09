package heig.mcr.visitor.game.actor.handler;

import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.window.sprite.AnimatedSprite;
import heig.mcr.visitor.window.sprite.Sprite;

public class PlayerDeadState extends PlayerState {

    private final AnimatedSprite deathSprites = PacmanSprites.getInstance().getPacmanDeath();

    protected PlayerDeadState(Player player) {
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
    }

    @Override
    public void kill() {
    }

    @Override
    public Sprite getSprite() {
        return deathSprites;
    }
}
