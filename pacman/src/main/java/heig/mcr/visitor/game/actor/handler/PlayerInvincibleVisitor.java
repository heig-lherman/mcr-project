package heig.mcr.visitor.game.actor.handler;

import heig.mcr.visitor.GameWindow;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.npc.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class PlayerInvincibleVisitor extends PlayerVisitor {

    private static final int INVINCIBLE_DURATION = 10;
    private static final ScheduledExecutorService EXECUTOR = Executors.newScheduledThreadPool(2);

    private ScheduledFuture<?> endFuture;
    private ScheduledFuture<?> blinkFuture;

    public PlayerInvincibleVisitor(Player player) {
        super(player);
        startTimer();
    }

    @Override
    public boolean isScary() {
        return true;
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    @Override
    public void eatSuperPellet() {
        dispose();
        startTimer();
    }

    @Override
    public void kill() {
        // Player is invincible
    }

    @Override
    public void dispose() {
        endFuture.cancel(false);
        blinkFuture.cancel(false);
    }

    public void startTimer() {
        endFuture = EXECUTOR.schedule(
                this::endInvincibility,
                INVINCIBLE_DURATION, TimeUnit.SECONDS
        );
        blinkFuture = EXECUTOR.schedule(
                () -> GameWindow.getInstance().getActiveLevel().toggleBlinking(),
                INVINCIBLE_DURATION - 2, TimeUnit.SECONDS
        );
    }

    public void endInvincibility() {
        getPlayer().setHandler(new PlayerNormalVisitor(getPlayer()));
        GameWindow.getInstance().getActiveLevel().toggleBlinking();
    }

    // Interactions
    @Override
    public void visit(Vader vader) {
        System.out.printf("%s is eaten by %s%n", vader, getPlayer());
        vader.sendHome();
        setRedSprites();
    }

    @Override
    public void visit(Luke luke) {
        System.out.printf("%s killed by %s while trying to eat him%n", getPlayer(), luke);
        // Special kill case since Luke cannot be eaten
        getPlayer().setHandler(new PlayerDeadVisitor(getPlayer()));
    }

    @Override
    public void visit(Sith sith) {
        System.out.printf("%s is eaten by %s%n", sith, getPlayer());
        sith.sendHome();
    }

    @Override
    public void visit(StormTrooper stormTrooper) {
        System.out.printf("%s is eaten by %s and removed from the game%n", stormTrooper, getPlayer());
        stormTrooper.leaveCell();
    }

    @Override
    public void visit(BobaFett bobaFett) {
        System.out.printf("%s is eaten by %s%n", bobaFett, getPlayer());
        System.out.printf("%s is moving a bit faster after eating %s%n", getPlayer(), bobaFett);
        getPlayer().setMoveInterval(Math.max(50, bobaFett.getMoveInterval() - 20));
        bobaFett.sendHome();
    }
}
