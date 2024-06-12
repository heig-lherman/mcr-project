package heig.mcr.visitor.game.actor;

import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.board.Entity;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.RegionOfInterest;
import heig.mcr.visitor.window.sprite.Sprite;

import java.awt.*;

public class SpeechBubble extends Entity {

    private final String text;
    private final Sprite sprite = new SpeechBubbleSprite();

    public SpeechBubble(Cell initialCell, String text) {
        super(initialCell);
        this.text = text;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public int getLayer() {
        return 1000;
    }

    private class SpeechBubbleSprite implements Sprite {

        private static final int OPACITY_INTERVAL = 20;

        private final Sprite backgroundSprite = PacmanSprites.getInstance().getSpeechBackground();

        private long lastUpdate = System.currentTimeMillis();
        private int opacity = 255;

        @Override
        public void draw(Graphics graphics, int x, int y, int width, int height) {
            var g2d = (Graphics2D) graphics;
            Composite pcomp = g2d.getComposite();
            Font pfont = g2d.getFont();

            g2d.setComposite(AlphaComposite.SrcOver.derive(opacity / 255f));

            Font font = new Font("Monospaced", Font.PLAIN, 12);
            FontMetrics fm = g2d.getFontMetrics(font);

            // draw box with a little padding based on text width and height

            int tw = fm.stringWidth(text);
            int th = fm.getHeight();

            int yoff = (int) (opacity / 255f * 16);

            backgroundSprite.draw(
                    g2d,
                    x - tw / 2,
                    y - th / 2 - 16 + yoff,
                    tw + 32,
                    th + 24
            );

            g2d.setColor(new Color(0, 0, 0, opacity));

            g2d.setFont(font);
            g2d.drawString(text, x - tw / 2 + 16, y + th / 2 - 8 + yoff);

            g2d.setComposite(pcomp);
            g2d.setFont(pfont);

            long now = System.currentTimeMillis();
            if (now - lastUpdate > OPACITY_INTERVAL) {
                lastUpdate = now;
                opacity -= 2;
            }

            if (opacity <= 0) {
                leaveCell();
            }
        }

        @Override
        public Sprite slice(RegionOfInterest roi) {
            return this;
        }

        @Override
        public int getWidth() {
            return backgroundSprite.getWidth();
        }

        @Override
        public int getHeight() {
            return backgroundSprite.getHeight();
        }
    }

    @Override
    public void accept(InteractionVisitor v) {
    }
}
