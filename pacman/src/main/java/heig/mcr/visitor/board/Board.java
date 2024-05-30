package heig.mcr.visitor.board;

import java.util.stream.Stream;

public final class Board {

    private final Cell[][] cells;

    public Board(Cell[][] cells) {
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
}
