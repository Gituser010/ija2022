package ija.ija2022.homework2.adapters;

import ija.ija2022.homework2.a_star.Node;
import ija.ija2022.homework2.a_star.SquareGraph;
import ija.ija2022.homework2.common.Field;
import ija.ija2022.homework2.game.Game;
import ija.ija2022.homework2.game.PacmanObject;
import ija.ija2022.homework2.game.PathField;
import ija.ija2022.homework2.view.FieldView;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PlayerAdapterMouse extends MouseAdapter {
    private final PacmanObject pacman;
    private final Game maze;

    private boolean lock;

    public PlayerAdapterMouse(PacmanObject pacman, Game maze) {
        this.pacman = pacman;
        this.maze = maze;
        this.lock =false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(this.lock)
        {
            return;
        }
        new Thread(() -> {
            this.lock=true;
            FieldView fv = (FieldView) e.getSource();
            PathField field= (PathField) fv.getField();
            int fieldCol = field.getMainCol();
            int fieldRow = field.getMainRow();
            int pacmanRow = ((PathField)pacman.getField()).getMainRow();
            int pacmanCol = ((PathField)pacman.getField()).getMainCol();


            SquareGraph graph= maze.getGraph();

            Point target = new Point(fieldRow,fieldCol);
            graph.setTargetPosition(target);
            Point start = new Point(pacmanRow,pacmanCol);
            graph.setStartPosition(start);
            ArrayList<Node> nodes= graph.executeAStar();

            while (fieldCol!=pacmanCol || fieldRow!=pacmanRow) {
                try {
                    System.out.println("hello");
                    for(int i=0;i< nodes.size();i++)
                    {
                        System.out.println(nodes.get(i));

                        int row = nodes.get(i).getX();
                        int col = nodes.get(i).getY();
                        int moveRow = row - pacmanRow;
                        int moveCol = col - pacmanCol;

                        System.out.println(nodes);
                        System.out.println(row);
                        System.out.println(pacmanRow);
                        System.out.println(col);
                        System.out.println(pacmanCol);
                        graph.printPath(nodes);


                        if(moveRow>0)
                        {
                            this.pacman.move(Field.Direction.D);
                        }
                        if(moveRow<0)
                        {
                            this.pacman.move(Field.Direction.U);
                        }
                        if(moveCol<0)
                        {
                            this.pacman.move(Field.Direction.L);
                        }
                        if(moveCol>0)
                        {
                            this.pacman.move(Field.Direction.R);
                        }

                        pacmanRow = ((PathField) this.pacman.getField()).getMainRow();
                        pacmanCol = ((PathField) this.pacman.getField()).getMainCol();
                        Thread.sleep(400);
                    }
                    graph.clean();
                    this.lock=false;
                }
                catch (Exception er) {
                    System.err.println(er);
                    this.lock=false;
                    return;
                }
                }
        }).start();

    }
}
