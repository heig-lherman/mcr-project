package heig.mcr.visitor.game.actor.handler;

import heig.mcr.visitor.GameWindow;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.npc.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class PlayerInvincibleState extends PlayerState {

    private static final int INVINCIBLE_DURATION = 10;
    private static final ScheduledExecutorService EXECUTOR = Executors.newScheduledThreadPool(2);

    private ScheduledFuture<?> endFuture;
    private ScheduledFuture<?> blinkFuture;

    public PlayerInvincibleState(Player player) {
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
        getPlayer().setState(new PlayerNormalState(getPlayer()));
        GameWindow.getInstance().getActiveLevel().toggleBlinking();
    }

    // Interactions
    @Override
    public void interactWith(Vader vader) {
        System.out.printf("%s is eaten by %s%n", vader, getPlayer());
        vader.setCell(vader.getInitialCell());
        setRedSprites();
    }

    @Override
    public void interactWith(Luke luke) {
        System.out.printf("%s killed by %s while trying to eat him%n", getPlayer(), luke);
        // Special kill case since Luke cannot be eaten
        getPlayer().setState(new PlayerDeadState(getPlayer()));
    }

    @Override
    public void interactWith(Sith sith) {
        System.out.printf("%s is eaten by %s%n", sith, getPlayer());
        sith.setCell(sith.getInitialCell());
    }

    @Override
    public void interactWith(StormTrooper stormTrooper) {
        System.out.printf("%s is eaten by %s and removed from the game%n", stormTrooper, getPlayer());
        stormTrooper.leaveCell();
    }

    @Override
    public void interactWith(BobaFett bobaFett) {
        System.out.printf("%s is eaten by %s%n", bobaFett, getPlayer());
        System.out.printf("%s is moving a bit faster after eating %s%n", getPlayer(), bobaFett);
        bobaFett.setCell(bobaFett.getInitialCell());
        getPlayer().setMoveInterval(Math.min(50, bobaFett.getMoveInterval() - 100));
    }
}
