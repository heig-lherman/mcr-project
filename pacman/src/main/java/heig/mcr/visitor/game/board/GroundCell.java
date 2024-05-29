package heig.mcr.visitor.game.board;

import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.window.sprite.Sprite;

public class GroundCell extends Cell {

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public Sprite getSprite() {
        return PacmanSprites.getInstance().getGround();
    }
}
