package ija.ija2022.homework2;

import ija.ija2022.homework2.adapters.PlayerAdapterAZDW;
import ija.ija2022.homework2.adapters.PlayerAdapterMouse;
import ija.ija2022.homework2.game.Game;

import ija.ija2022.homework2.common.Maze;
import ija.ija2022.homework2.view.FieldView;
import java.awt.Dimension;
import java.awt.GridLayout;


import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MazePresenter {

    private JLabel Llives;
    private JLabel LKeys;

    private final Maze maze;

    public MazePresenter(Maze maze) {
        this.maze = maze;
    }

    public void open() {
        try {
            SwingUtilities.invokeAndWait(this::initializeInterface);
        } catch (InvocationTargetException | InterruptedException var2) {
            Logger.getLogger(MazePresenter.class.getName()).log(Level.SEVERE, (String)null, var2);
        }

    }

    private void initializeInterface() {
        JFrame frame = new JFrame("Lidl Pacman");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setPreferredSize(new Dimension(600, 700));
        frame.setResizable(false);
        frame.addKeyListener(new PlayerAdapterAZDW(((Game)maze).pacman()));

        LKeys = new JLabel("Keys: 0");
        Llives = new JLabel("Lives: "+ ((Game)maze).pacman().getLives());
        JPanel scorePanel = new JPanel();
        scorePanel.add(LKeys);
        scorePanel.add(Llives);
        frame.getContentPane().add(scorePanel, "North");

        int rows = this.maze.numRows();
        int cols = this.maze.numCols();
        GridLayout layout = new GridLayout(rows, cols);
        JPanel content = new JPanel(layout);

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                FieldView field = new FieldView(this.maze.getField(i, j), new FieldView.FieldChangedCallback() {
                    @Override
                    public void onFieldChanged() {
                        update();
                    }
                });
                field.addMouseListener(new PlayerAdapterMouse(((Game)maze).pacman(),(Game)maze));
                content.add(field);
            }
        }

        frame.getContentPane().add(content, "Center");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void update() {
        int lives = ((Game) maze).pacman().getLives();
        int keys = ((Game) maze).pacman().getKeys();
        boolean win = ((Game) maze).pacman().getTarget();
        Llives.setText("Lives: " + lives);
        LKeys.setText("Keys: " + keys);
        if (lives <= 0) {
            System.out.print("GAME OVER");
        }
        if (win) {
            System.out.print("YOU WIN");
        }
    }
}
