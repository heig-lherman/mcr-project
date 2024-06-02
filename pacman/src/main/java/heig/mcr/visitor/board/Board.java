package heig.mcr.visitor.board;

import heig.mcr.visitor.math.Direction;

import java.util.stream.Stream;

public final class Board {

    private final Cell[][] cells;

    private Board(Cell[][] cells) {
        this.cells = cells;
    }

    public Stream<Cell> streamCells() {
        return Stream.of(cells).flatMap(Stream::of);
    }

    public int getWidth() {
        return cells.length;
    }

    public int getHeight() {
        return cells[0].length;
    }

    public Cell getCell(int x, int y) {
        if (!withinBounds(x, y)) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }

        return cells[x][y];
    }

    public boolean withinBounds(int x, int y) {
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }

    /**
     * Utility method to create a board from a 2D array of cells, linking them together
     * @param cells the cells
     * @return the board
     */
    public static Board create(Cell[][] cells) {
        Board board = new Board(cells);

        // Link cells together
        int width = board.getWidth();
        int height = board.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell cell = board.cells[x][y];
                for (var dir : Direction.values()) {
                    int dx = (width + x + dir.dx()) % width;
                    int dy = (height + y + dir.dy()) % height;
                    Cell neighbor = board.cells[dx][dy];
                    cell.linkNeighbor(dir, neighbor);
                }
            }
        }

        return board;
    }
}
