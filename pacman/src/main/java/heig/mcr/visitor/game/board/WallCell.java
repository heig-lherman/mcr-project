package heig.mcr.visitor.game.board;

import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.window.sprite.Sprite;

public class WallCell extends Cell {

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public Sprite getSprite() {
        return PacmanSprites.getInstance().getGround();
    }
}
