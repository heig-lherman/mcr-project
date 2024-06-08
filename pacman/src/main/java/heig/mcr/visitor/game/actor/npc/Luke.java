package heig.mcr.visitor.game.actor.npc;

import heig.mcr.visitor.board.Interactable;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.math.Pathfinding;
import heig.mcr.visitor.window.sprite.AnimatedSprite;

import java.util.List;
import java.util.Map;

public class Luke extends Ghost {

    private List<Direction> pathToPlayer;
    private int pathIndex;
    private int moveCounter = 0;

    public Luke() {
        super();
        this.name = "Luke";
        this.pathToPlayer = List.of(); // Initialize with an empty path
        this.pathIndex = 0;
    }

    @Override
    public void acceptInteraction(InteractionVisitor v) {
        v.interactWith(this);
    }

    @Override
    public void interactWith(Interactable other) { }

    @Override
    public Direction getNextMove(){
        moveCounter++;
        if (pathIndex >= pathToPlayer.size() || moveCounter % 16 == 1) {
            updatePathToPlayer();
        }

        if (!pathToPlayer.isEmpty() && pathIndex < pathToPlayer.size()) {
            Direction nextMove = pathToPlayer.get(pathIndex);
            pathIndex++;
            System.out.println(name + " moving through the shortest path");
            return nextMove;
        }

        // Default to random direction if no path found or path completed
        Direction randomDirection = Direction.random();
        return randomDirection;
    }

    @Override
    Map<Direction, AnimatedSprite> getEdibleSprites() {
        return null;
    }

    @Override
    Map<Direction, AnimatedSprite> getInvincibleSprites() {
        return PacmanSprites.getInstance().getLuke();
    }

    @Override
    public void becomeEdible() { }

    private void updatePathToPlayer() {
        Player player = Pathfinding.findNearestEntity(Player.class, this.getCell());
        if (player != null) {
            pathToPlayer = Pathfinding.findShortestPath(this.getCell(), player.getCell());
            pathIndex = 0;
        } else {
            pathToPlayer = List.of();
        }
    }
}
