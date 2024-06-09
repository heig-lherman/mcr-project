package heig.mcr.visitor.game.map;

import heig.mcr.visitor.board.Board;
import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.game.Level;
import heig.mcr.visitor.game.actor.Pellet;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.SuperPellet;
import heig.mcr.visitor.game.actor.npc.Ghost;
import heig.mcr.visitor.game.board.DoorCell;
import heig.mcr.visitor.game.board.GroundCell;
import heig.mcr.visitor.game.board.WallCell;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class LevelBuilder {

    private final Cell[][] cells;

    private final List<Ghost> ghosts = new LinkedList<>();
    private final List<Player> players = new LinkedList<>();

    private LevelBuilder(
            int width,
            int height
    ) {
        cells = new Cell[width][height];
    }

    public static LevelBuilder start(int width, int height) {
        return new LevelBuilder(width, height);
    }

    public LevelBuilder addWall(int x, int y) {
        cells[x][y] = new WallCell();
        return this;
    }

    public LevelBuilder addGround(int x, int y) {
        cells[x][y] = new GroundCell();
        return this;
    }

    public LevelBuilder addDoor(int x, int y) {
        cells[x][y] = new DoorCell();
        return this;
    }

    public LevelBuilder addPellet(int x, int y) {
        Cell cell = new GroundCell();
        cells[x][y] = cell;

        new Pellet(cell);
        return this;
    }

    public LevelBuilder addSuperPellet(int x, int y) {
        Cell cell = new GroundCell();
        cells[x][y] = cell;

        new SuperPellet(cell);
        return this;
    }

    public LevelBuilder addGhost(int x, int y, Function<Cell, Ghost> ghostFactory) {
        Cell cell = new GroundCell();
        cells[x][y] = cell;

        ghosts.add(ghostFactory.apply(cell));
        return this;
    }

    public LevelBuilder addPlayer(int x, int y) {
        Cell cell = new GroundCell();
        cells[x][y] = cell;

        players.add(new Player(cell));
        return this;
    }

    public Level build() {
        Board board = Board.create(cells);
        return new Level(board, ghosts, players);
    }
}
