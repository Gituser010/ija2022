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

    public PlayerAdapterAZDW(PacmanObject pacman) {
        this.pacman = pacman;
    }
    @Override
    public void keyPressed(KeyEvent e) {
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
    }
}
