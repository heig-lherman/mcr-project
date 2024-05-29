package heig.mcr.visitor;

import heig.mcr.visitor.actor.entity.CircleEntity;
import heig.mcr.visitor.actor.entity.TriangleEntity;

public class Main {

    public static void main(String[] args) {
        GameWindow.getInstance().spawn(new CircleEntity());
        GameWindow.getInstance().spawn(new TriangleEntity());
        GameWindow.getInstance().show();
    }
}
