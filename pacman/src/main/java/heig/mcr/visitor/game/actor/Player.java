package heig.mcr.visitor.game.actor;

import heig.mcr.visitor.board.Interactable;
import heig.mcr.visitor.board.Interactor;
import heig.mcr.visitor.board.MovableEntity;
import heig.mcr.visitor.game.actor.npc.RandomGhost;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.handler.support.AbstractInteractionVisitor;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.window.sprite.AnimatedSprite;
import heig.mcr.visitor.window.sprite.Sprite;

import java.util.Map;

public class Player extends MovableEntity implements Interactor {

    private final InteractionVisitor normalHandler = new NormalInteractionHandler();
    private final InteractionVisitor superHandler = new SuperInteractionHandler();

    private final Map<Direction, AnimatedSprite> directedSprites;
    private final AnimatedSprite deathSprites;

    private Direction requestedDirection = Direction.UP;
    private boolean alive = true;

    private boolean superMode = false;

    public Player() {
        var sprites = PacmanSprites.getInstance();
        directedSprites = sprites.getPacman();
        deathSprites = sprites.getPacmanDeath();
    }

    public boolean isAlive() {
        return alive;
    }

    public void becomeSuper() {
        superMode = true;
    }

    public void setRequestedDirection(Direction requestedDirection) {
        this.requestedDirection = requestedDirection;
    }

    @Override
    public int getMoveInterval() {
        return 200;
    }

    @Override
    public Direction getNextMove() {
        if (getCell().getNeighbor(requestedDirection).isWalkable()) {
            return requestedDirection;
        }

        return getDirection();
    }

    @Override
    public Sprite getSprite() {
        if (alive) {
            return directedSprites.get(getDirection());
        }

        return deathSprites;
    }

    public void kill() {
        alive = false;
        deathSprites.restart();
    }

    @Override
    public int getLayer() {
        return 100;
    }

    @Override
    public void acceptInteraction(InteractionVisitor v) {
        v.interactWith(this);
    }

    @Override
    public void interactWith(Interactable other) {
        if (superMode) {
            other.acceptInteraction(superHandler);
        } else {
            other.acceptInteraction(normalHandler);
        }
    }


    private class NormalInteractionHandler extends AbstractInteractionVisitor {

        @Override
        public void interactWith(Pellet pellet) {
            pellet.leaveCell();
            // TODO could add points here
        }

        @Override
        public void interactWith(SuperPellet superPellet) {
            System.out.println("Super pellet eaten");
            super.interactWith(superPellet);
        }

        @Override
        public void interactWith(RandomGhost ghost) {
            // Should die
        }
    }

    private class SuperInteractionHandler extends AbstractInteractionVisitor {

        @Override
        public void interactWith(Pellet pellet) {
            pellet.leaveCell();
            // TODO could add points here
        }


        @Override
        public void interactWith(RandomGhost ghost) {
            //Kill ghost
        }

        @Override
        public void interactWith(SuperPellet superPellet) {
            System.out.println("Super pellet eaten");
            super.interactWith(superPellet);
        }
    }
}
