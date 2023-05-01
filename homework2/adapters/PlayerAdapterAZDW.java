package ija.ija2022.homework2.adapters;

import ija.ija2022.homework2.common.Field;
import ija.ija2022.homework2.game.PacmanObject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerAdapterAZDW extends KeyAdapter {

    private final PacmanObject pacman;

    public PlayerAdapterAZDW(PacmanObject pacman) {
        this.pacman = pacman;
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
        }).start();
    }
}
