package heig.mcr.visitor.game.map;

import heig.mcr.visitor.board.Board;
import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.game.Level;
import heig.mcr.visitor.game.actor.Pellet;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.SuperPellet;
import heig.mcr.visitor.game.actor.npc.Ghost;
import heig.mcr.visitor.game.board.GroundCell;
import heig.mcr.visitor.game.board.WallCell;

import java.util.LinkedList;
import java.util.List;

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

    public LevelBuilder addPellet(int x, int y) {
        Cell cell = new GroundCell();
        cells[x][y] = cell;

        Pellet pellet = new Pellet();
        pellet.setCell(cell);
        return this;
    }

    public LevelBuilder addSuperPellet(int x, int y) {
        Cell cell = new GroundCell();
        cells[x][y] = cell;

        SuperPellet superPellet = new SuperPellet();
        superPellet.setCell(cell);
        return this;
    }

    public LevelBuilder addGhost(int x, int y, Ghost ghost) {
        Cell cell = new GroundCell();
        cells[x][y] = cell;

        ghost.setCell(cell);
        ghosts.add(ghost);
        return this;
    }

    public LevelBuilder addPlayer(int x, int y) {
        Cell cell = new GroundCell();
        cells[x][y] = cell;

        Player player = new Player();

        player.setCell(cell);
        players.add(player);
        return this;
    }

    public Level build() {
        Board board = Board.create(cells);
        return new Level(board, ghosts, players);
    }
}
