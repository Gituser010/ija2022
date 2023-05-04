package ija.ija2022.homework2.adapters;

import ija.ija2022.homework2.Replay.Replay;
import ija.ija2022.homework2.common.Field;
import ija.ija2022.homework2.game.Game;
import ija.ija2022.homework2.game.PacmanObject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class ReplayAdapter extends KeyAdapter {

    public static File myFile = new File("MyFile.log");
    public static Replay mewReplay;
    Thread executeThread;

    Thread executeBackThread;

    public ReplayAdapter(PacmanObject pacman, Game maze) {

        mewReplay= new Replay(maze,myFile);
    }

    public void stop(){
        try {
            executeBackThread.interrupt();
        }
        catch (Exception e){}

        try {
            executeThread.interrupt();
        }
        catch (Exception e) {}
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'r') {
            stop();
            executeThread = new Thread(() -> {
                try {
                    mewReplay.execute();
                } catch (InterruptedException ev) {
                }
            });
            executeThread.start();
        }
        if (e.getKeyChar() == 'n') {
            stop();
            mewReplay.nextStep();
        }
        if (e.getKeyChar() == 'p') {
            stop();
            mewReplay.prevStep();
        }
        if(e.getKeyChar()=='b')
        {
            stop();
            executeBackThread = new Thread(() -> {
                try {
                    mewReplay.executeBackward();
                } catch (InterruptedException ev) {
                }
            });
            executeBackThread.start();
        }

        if (e.getKeyChar() == ' ') {
            System.out.println("hello");

            stop();
        }
    }
}
