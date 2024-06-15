package heig.mcr.visitor.window;

import heig.mcr.visitor.game.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * The main frame for the game. It contains the level panel and the button panel.
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 */
public class GameFrame extends JFrame {

    private static final int FRAME_RATE = 24;

    private final LevelPanel panel;

    public GameFrame(
            Level level,
            Map<Integer, Consumer<Level>> keybindings,
            Map<String, Runnable> buttonActions
    ) {
        super("Pacman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        KeyListener keys = new KeyListener(keybindings);
        addKeyListener(keys);

        panel = new LevelPanel(level);

        ButtonPanel buttons = new ButtonPanel(buttonActions, this);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(buttons, BorderLayout.SOUTH);
        contentPane.add(panel, BorderLayout.CENTER);

        pack();
    }

    public void start() {
        setVisible(true);
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(panel::repaint, 0, 1000 / FRAME_RATE, TimeUnit.MILLISECONDS);
    }

    private class KeyListener extends KeyAdapter {

        private final Map<Integer, Consumer<Level>> keybindings;

        public KeyListener(Map<Integer, Consumer<Level>> keybindings) {
            this.keybindings = keybindings;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Optional
                    .ofNullable(keybindings.get(e.getKeyCode()))
                    .ifPresent(action -> action.accept(panel.getLevel()));
        }
    }
}
