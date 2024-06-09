package heig.mcr.visitor.game.actor;

import heig.mcr.visitor.board.Interactable;
import heig.mcr.visitor.board.Interactor;
import heig.mcr.visitor.board.MovableEntity;
import heig.mcr.visitor.game.actor.npc.*;
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
    private InteractionVisitor currentHandler = normalHandler;

    private Map<Direction, AnimatedSprite> directedSprites;
    private final AnimatedSprite deathSprites;
    private final int DEFAULT_INTERVAL = 300;
    private Direction requestedDirection = Direction.UP;
    private boolean alive = true;
    private int moveInterval = DEFAULT_INTERVAL;

    public Player() {
        var sprites = PacmanSprites.getInstance();
        directedSprites = sprites.getPacman();
        deathSprites = sprites.getPacmanDeath();
        this.name = "Player";
    }

    public boolean isAlive() {
        return alive;
    }

    public void becomeSuper() {
        System.out.println("Player enters in super mode");
        currentHandler = superHandler;
    }

    public void leaveSuper() {
        System.out.println("Player leaves super mode");
        currentHandler = normalHandler;
        moveInterval = DEFAULT_INTERVAL;
    }

    public void setRequestedDirection(Direction requestedDirection) {
        this.requestedDirection = requestedDirection;
    }

    @Override
    public int getMoveInterval() {
        return moveInterval;
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

    public void changeToRed() {
        System.out.println("Pacman changes to red");
        directedSprites = PacmanSprites.getInstance().getRedPacman();
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
        other.acceptInteraction(currentHandler);
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
        public void interactWith(Vader vader) {
            handleGhostInteraction(vader);
        }

        @Override
        public void interactWith(Luke luke) {
            handleGhostInteraction(luke);
        }

        @Override
        public void interactWith(Sith sith) {
            handleGhostInteraction(sith);
        }

        @Override
        public void interactWith(StormTrooper stormTrooper) {
            handleGhostInteraction(stormTrooper);
        }

        @Override
        public void interactWith(BobaFett bobaFett) {
            handleGhostInteraction(bobaFett);
        }

        private void handleGhostInteraction(Ghost ghost) {
            System.out.println("Killed by " + ghost);
            kill();
        }
    }

    private class SuperInteractionHandler extends AbstractInteractionVisitor {

        @Override
        public void interactWith(Pellet pellet) {
            pellet.leaveCell();
            // TODO could add points here
        }

        @Override
        public void interactWith(Vader vader) {
            handleBaseSuperInteraction(vader);
            changeToRed();
        }

        @Override
        public void interactWith(Luke luke) {
            System.out.println("Killed by " + luke + "while trying to eat him");
            kill();
        }

        @Override
        public void interactWith(Sith sith) {
            handleBaseSuperInteraction(sith);
        }

        @Override
        public void interactWith(StormTrooper stormTrooper) {
            System.out.println(stormTrooper + " eaten and removed from the game");
            stormTrooper.leaveCell();
        }

        @Override
        public void interactWith(BobaFett bobaFett) {
            handleBaseSuperInteraction(bobaFett);
        }

        @Override
        public void interactWith(SuperPellet superPellet) {
            System.out.println("Super pellet eaten");
            super.interactWith(superPellet);
            becomeSuper();
        }

        private void handleBaseSuperInteraction(Ghost ghost) {
            System.out.println(ghost + " eaten and respawned in his cell");
            ghost.setCell(ghost.getInitialCell());
        }
    }
}
