package heig.mcr.visitor.game.actor.handler;

import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.npc.BobaFett;

public class PlayerNormalState extends PlayerState {

    public PlayerNormalState(Player player) {
        super(player);
    }

    @Override
    public boolean isScary() {
        return false;
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    @Override
    public void eatSuperPellet() {
        getPlayer().setState(new PlayerInvincibleState(getPlayer()));
    }

    @Override
    public void kill() {
        getPlayer().setState(new PlayerDeadState(getPlayer()));
    }

    // Interactions

    @Override
    public void visit(BobaFett bobaFett) {
        System.out.printf("%s is not dangerous and is moving a bit faster after meeting %s%n", bobaFett, getPlayer());
        bobaFett.moveFaster();
    }
}
