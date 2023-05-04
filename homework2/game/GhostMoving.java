package ija.ija2022.homework2.game;

import ija.ija2022.homework2.a_star.SquareGraph;

import java.awt.*;

public class GhostMoving {
    PathField from,to;
    GhostObject ghost;
    Game maze;
    Thread executeThread;

    public GhostMoving(PathField to,GhostObject ghost,Game maze)
    {
        this.from=(PathField) ghost.getField();
        this.to=to;
        this.ghost=ghost;
        this.maze=maze;
    }

    public void start(){
        System.out.println("kokot");
        executeThread = new Thread(() -> {
            while (true) {
                ObjectMove move = new ObjectMove(from, to, ghost, maze);
                move.execute();
                move = new ObjectMove(to, from, ghost, maze);
                move.execute();
            }

        });
        executeThread.start();
    }

}
