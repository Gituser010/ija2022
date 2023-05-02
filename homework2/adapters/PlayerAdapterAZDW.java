package ija.ija2022.homework2.adapters;

import ija.ija2022.homework2.Replay.Replay;
import ija.ija2022.homework2.common.Field;
import ija.ija2022.homework2.game.Game;
import ija.ija2022.homework2.game.PacmanObject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class PlayerAdapterAZDW extends KeyAdapter {

    private final PacmanObject pacman;
    private Game maze;

    public static File myFile = new File("MyFile.log");
    public static Replay mewReplay;
    public PlayerAdapterAZDW(PacmanObject pacman, Game maze) {
        this.pacman = pacman;
        this.maze = maze;
        mewReplay= new Replay(maze,myFile);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        new Thread(() -> {
            if (e.getKeyChar() == 'w') {
                this.pacman.move(Field.Direction.U);
            }
            if (e.getKeyChar() == 'a') {
                this.pacman.move(Field.Direction.L);
            }
            if (e.getKeyChar() == 'z') {
                this.pacman.move(Field.Direction.D);
            }
            if (e.getKeyChar() == 'd') {
                this.pacman.move(Field.Direction.R);
            }
            if (e.getKeyChar() == 'r') {
                try {
                    mewReplay.execute();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getKeyChar() == 'n') {
                mewReplay.nextStep();
            }
            if (e.getKeyChar() == 'p') {
                mewReplay.prevStep();

            }
        }).start();
    }
}
