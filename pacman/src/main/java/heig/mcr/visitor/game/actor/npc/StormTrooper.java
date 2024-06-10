package heig.mcr.visitor.game.actor.npc;

import heig.mcr.visitor.GameWindow;
import heig.mcr.visitor.board.Board;
import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.board.Interactable;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.math.RandomGenerator;
import heig.mcr.visitor.window.sprite.AnimatedSprite;
import heig.mcr.visitor.window.sprite.SpecialRender;
import heig.mcr.visitor.window.sprite.Sprite;

import java.awt.*;
import java.util.Map;

public class StormTrooper extends Ghost implements SpecialRender {

    private static final Map<Direction, AnimatedSprite> EDIBLE_SPRITES = PacmanSprites.getInstance().getEdibleStormTrooper();
    private static final Map<Direction, AnimatedSprite> INVINCIBLE_SPRITES = PacmanSprites.getInstance().getStormTrooper();
    private final InteractionVisitor handler = new StormTrooperInteractionVisitor();
    private final AnimatedSprite teleportSprite;
    private boolean teleporting = false;

    public StormTrooper(Cell initialCell) {
        super(initialCell, 12);
        this.teleportSprite = PacmanSprites.getInstance().getTeleport();
    }

    @Override
    Map<Direction, AnimatedSprite> getEdibleSprites() {
        return EDIBLE_SPRITES;
    }

    @Override
    Map<Direction, AnimatedSprite> getInvincibleSprites() {
        return INVINCIBLE_SPRITES;
    }

    @Override
    public void accept(InteractionVisitor v) {
        v.visit(this);
    }

    @Override
    public void interactWith(Interactable other) {
        other.accept(handler);
    }


    public void teleportRandomly() {
        Board board = GameWindow.getInstance().getActiveLevel().getBoard();
        Cell newCell;

        do {
            int x = RandomGenerator.getInstance().nextInt(board.getWidth());
            int y = RandomGenerator.getInstance().nextInt(board.getHeight());
            newCell = board.getCell(x, y);
        } while (!newCell.isWalkableBy(this) || isNearPlayer(newCell));

        setCell(newCell);
    }

    private boolean isNearPlayer(Cell cell) {
        for (Direction direction : Direction.values()) {
            Cell neighbor = cell.getNeighbor(direction);
            if (neighbor.getOccupants().stream().anyMatch(Player.class::isInstance)) {
                return true;
            }
        }
        return false;
    }

    public void triggerTeleport() {
        teleporting = true;
        teleportSprite.restart();
    }

    @Override
    public Sprite getSprite() {
        if (teleporting) {
            return teleportSprite;
        } else {
            return super.getSprite();
        }
    }

    @Override
    public boolean hasSpecialRendering(){
        return teleporting;
    }

    @Override
    public void renderSpecial(Graphics2D g, int x, int y, int width, int height) {
        if (teleporting) {
            teleportSprite.draw(g, x, y, width, height);
            if (teleportSprite.getCurrentFrameStep() == 3) {
                teleportRandomly();
            }
            if (!teleportSprite.isAnimating()) {
                teleporting = false;
            }
        }
    }

    private class StormTrooperInteractionVisitor extends GhostInteractionVisitor {
        public void visit(Sith sith) {
            triggerTeleport();
        }
    }

    @Override
    public String toString() {
        return "Storm Trooper";
    }
}
