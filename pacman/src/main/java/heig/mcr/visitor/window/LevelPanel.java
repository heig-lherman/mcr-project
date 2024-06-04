package heig.mcr.visitor.window;

import heig.mcr.visitor.board.Board;
import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.board.Entity;
import heig.mcr.visitor.game.Level;
import heig.mcr.visitor.game.sprite.PacmanSprites;

import javax.swing.*;
import java.awt.*;

public class LevelPanel extends JPanel {

    public static final int SQUARE_SIZE = 32;
    private final Level level;

    public LevelPanel(Level level) {
        super();
        this.level = level;

        int width = level.getBoard().getWidth();
        int height = level.getBoard().getHeight();
        Dimension size = new Dimension(width * SQUARE_SIZE, height * SQUARE_SIZE);
        setMinimumSize(size);
        setPreferredSize(size);
    }

    public Level getLevel() {
        return level;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        render(level.getBoard(), g2d);
    }

    private void render(Board board, Graphics2D g) {
        int cellWidth = getWidth() / board.getWidth();
        int cellHeight = getHeight() / board.getHeight();

        PacmanSprites.getInstance().getBackground().draw(
                g,
                0,
                0,
                getWidth(),
                getHeight()
        );

        for (int y = 0; y < board.getHeight(); y++) {
            int cellY = y * cellHeight;
            for (int x = 0; x < board.getWidth(); x++) {
                int cellX = x * cellWidth;
                Cell cell = board.getCell(x, y);
                renderCell(cell, g, cellX, cellY, cellWidth, cellHeight);
            }
        }
    }

    private void renderCell(Cell cell, Graphics2D g, int x, int y, int width, int height) {
        cell.getSprite().draw(g, x, y, width, height);
        for (Entity occupant : cell.getOccupants()) {
            occupant.getSprite().draw(g, x, y, width, height);
        }
    }
}
