package heig.mcr.visitor.game.board;

import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.board.Entity;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.window.sprite.DefaultSprite;
import heig.mcr.visitor.window.sprite.Sprite;

public class DoorCell extends Cell {

    @Override
    public boolean isWalkableBy(Entity entity) {
        return !(entity instanceof Player);
    }

    @Override
    public Sprite getSprite() {
        return new DefaultSprite();
    }
}
