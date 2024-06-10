package heig.mcr.visitor;

import heig.mcr.visitor.game.Level;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.map.MapParser;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.window.GameFrame;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Map;

public class GameWindow implements Level.LevelObserver {

    private static final class InstanceHolder {
        private static final GameWindow INSTANCE;

        static {
            try {
                INSTANCE = new GameWindow();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private final Player player;
    private final Level level;
    private final GameFrame frame;

    private GameWindow() throws IOException {
        this.level = MapParser.parse("/levels/default.txt");
        this.level.addObserver(this);
        this.player = level.getPlayer(0);
        this.frame = new GameFrame(level, Map.of(
                KeyEvent.VK_UP, l -> player.setRequestedDirection(Direction.UP),
                KeyEvent.VK_DOWN, l -> player.setRequestedDirection(Direction.DOWN),
                KeyEvent.VK_LEFT, l -> player.setRequestedDirection(Direction.LEFT),
                KeyEvent.VK_RIGHT, l -> player.setRequestedDirection(Direction.RIGHT)
        ), Map.of(
                "Start", () -> level.start(),
                "Stop", () -> level.stop()
        ));
    }

    public void begin() {
        frame.start();
    }

    public Level getActiveLevel() {
        return level;
    }

    @Override
    public void onLevelWon() {
        level.stop();
        JOptionPane.showMessageDialog(frame, "You won!");
    }

    @Override
    public void onLevelLost() {
        level.stop();
        JOptionPane.showMessageDialog(frame, "You lost!");
    }

    public static GameWindow getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
