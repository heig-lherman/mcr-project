package heig.mcr.visitor.game.sprite;

import heig.mcr.visitor.math.RegionOfInterest;
import heig.mcr.visitor.window.sprite.ColorSprite;
import heig.mcr.visitor.window.sprite.Sprite;

import java.awt.*;

public class GroundSprite extends ColorSprite {

    private static final Color GROUND_COLOR = Color.BLACK;

    public GroundSprite() {
        super(GROUND_COLOR);
    }

    @Override
    public Sprite slice(RegionOfInterest roi) {
        return new GroundSprite();
    }
}
