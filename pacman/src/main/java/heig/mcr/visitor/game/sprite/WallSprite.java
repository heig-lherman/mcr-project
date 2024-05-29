package heig.mcr.visitor.game.sprite;

import heig.mcr.visitor.math.RegionOfInterest;
import heig.mcr.visitor.window.sprite.ColorSprite;
import heig.mcr.visitor.window.sprite.Sprite;

import java.awt.*;

public class WallSprite extends ColorSprite {

    private static final Color WALL_COLOR = Color.decode("#3900fc");

    public WallSprite() {
        super(WALL_COLOR);
    }

    @Override
    public Sprite slice(RegionOfInterest roi) {
        return new WallSprite();
    }
}
