package heig.mcr.visitor.game.actor.handler;

import heig.mcr.visitor.game.actor.Pellet;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.SuperPellet;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.support.AbstractInteractionVisitor;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.window.sprite.AnimatedSprite;
import heig.mcr.visitor.window.sprite.Sprite;

import java.util.Map;

public abstract class PlayerVisitor extends AbstractInteractionVisitor {

    private final Player player;

    private Map<Direction, AnimatedSprite> sprites = PacmanSprites.getInstance().getPacman();

    protected PlayerVisitor(Player player) {
        this.player = player;
    }

    protected final Player getPlayer() {
        return player;
    }

    public Sprite getSprite() {
        return sprites.get(getPlayer().getDirection());
    }

    protected void setRedSprites() {
        sprites = PacmanSprites.getInstance().getRedPacman();
    }

    public abstract boolean isScary();
    public abstract boolean isInteractable();
    public abstract void eatSuperPellet();
    public abstract void kill();

    /**
     * In case the state needs disposal, always called when the state is changed.
     */
    public void dispose() {
    }

    // Standard interactions

    @Override
    public void visit(Pellet pellet) {
        pellet.leaveCell();
    }

    @Override
    public void visit(SuperPellet superPellet) {
        eatSuperPellet();
        super.visit(superPellet);
    }
}
