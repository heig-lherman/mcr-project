package heig.mcr.visitor;

import heig.mcr.visitor.actor.*;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.math.DiscreteCoordinates;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class GameWindow {

    private static final GameWindow INSTANCE = new GameWindow();

    public static GameWindow getInstance() {
        return INSTANCE;
    }

    private final JFrame frame;
    private final GraphicsPanel contentPane;

    private final Player player = new Player();

    public GameWindow() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new GraphicsPanel();
        frame.setContentPane(contentPane);

        frame.addKeyListener(new PlayerKeyListener());

        frame.pack();
        frame.setResizable(false);
    }

    public void spawn(Drawable entity) {
        contentPane.entities.add(entity);
    }

    public void despawn(Entity entity) {
        contentPane.entities.remove(entity);
    }

    public void implode() {
        contentPane.entities.clear();
        contentPane.useBackground = true;
    }

    public void show() {
        frame.setVisible(true);
    }

    public void update() {
        for (Drawable entity : contentPane.entities) {
            if (entity instanceof Interactable e && e.getCoordinates().equals(player.getCoordinates())) {
                player.interactWith(e);
            }
        }

        contentPane.repaint();
    }

    private class GraphicsPanel extends JPanel {

        private final List<Drawable> entities = new LinkedList<>();
        private final BufferedImage pacmanBg;

        private boolean useBackground = false;

        public GraphicsPanel() {
            setSize(640, 800);
            setPreferredSize(getSize());
            setBackground(Color.WHITE);

            try {
                pacmanBg = ImageIO.read(getClass().getResource("/pacman.png"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D graph = (Graphics2D) g;
            graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (useBackground) {
                graph.drawImage(
                        pacmanBg.getSubimage(0, 0, pacmanBg.getWidth() / 3, pacmanBg.getHeight()),
                        0, 0,
                        getWidth(), getHeight(),
                        this
                );
            }

            for (Drawable entity : entities) {
                entity.draw(graph);
            }

            player.draw(graph);
        }
    }

    private class PlayerKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player.moveTo(shiftCoordinates(e, player.getCoordinates()));
            update();
        }

        public DiscreteCoordinates shiftCoordinates(KeyEvent e, DiscreteCoordinates orig) {
            return switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> orig.up();
                case KeyEvent.VK_DOWN -> orig.down();
                case KeyEvent.VK_LEFT -> orig.left();
                case KeyEvent.VK_RIGHT -> orig.right();
                default -> orig;
            };
        }
    }
}
