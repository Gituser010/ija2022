package ija.ija2022.homework2.game;

import ija.ija2022.homework2.common.Field;
import ija.ija2022.homework2.common.Maze;
import ija.ija2022.homework2.common.MazeObject;
import ija.ija2022.homework2.common.AbstractObservable;

import java.util.ArrayList;
import java.util.List;

public class PathField extends AbstractObservable implements Field  {
    private final int mainRow;
    private final int mainCol;
    private MazeObject obj;
    private Maze mapMaze;
    public List<MazeObject> pole;

    private int numK;

    public PathField(int row, int col) {
        this.mapMaze = null;
        this.mainRow = row;
        this.mainCol = col;
        this.obj = null;
        this.pole = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PathField && ((PathField) obj).mainCol == this.mainCol && ((PathField) obj).mainRow == this.mainRow;
    }

    public void setMaze(Maze maze, int Keys) {
        this.mapMaze = maze;
        this.numK = Keys;
    }

    @Override
    public Field nextField(Field.Direction dirs) {
        if (!this.canMove()) {
            throw new UnsupportedOperationException("Cannot find next field from this field\n");
        }
        if (this.mapMaze == null) {
            return null;
        }
        return switch (dirs) {
            case D -> this.mapMaze.getField(this.mainRow + 1, this.mainCol);
            case L -> this.mapMaze.getField(this.mainRow, this.mainCol - 1);
            case R -> this.mapMaze.getField(this.mainRow, this.mainCol + 1);
            case U -> this.mapMaze.getField(this.mainRow - 1, this.mainCol);
        };
    }


    public boolean put(MazeObject object) {
        if (!this.canMove()) {
            throw new UnsupportedOperationException("Cannot put object on this type of field\n");
        }
        if (object.isPacman()) {
            if (this.get() instanceof BasicObject basic) {
                if (basic.isTarget()) {
                    if (object.getKeys() == numK) {
                        ((PacmanObject) object).gotTarget();
                    }
                }
                if (basic.isKey()) {
                    ((PacmanObject) object).gotKey();
                    remove(basic);
                }
                if (basic.isGhost()) {
                    ((PacmanObject) object).hit();
                }
            }
        }
        else {
            if(this.get() instanceof GhostObject ghost) {
                PacmanObject pacman = (PacmanObject) object;
                pacman.hit();
            }
        }

        pole.removeIf(o -> o.getClass().equals(object.getClass()));
        this.pole.add(object);
        notifyObservers();
        return true;
    }


    public boolean remove(MazeObject object) {
        if (!this.canMove()) {
            throw new UnsupportedOperationException("Cannot remove object from this type of field\n");
        }


        if(this.contains(object)) {
            pole.remove(object);
            notifyObservers();
            return true;
        }
        if (object != this.obj) {
            notifyObservers();
            return false;
        }

        this.obj = null;
        notifyObservers();
        return true;
    }


    public int getMainRow(){
        return mainRow;
    }

    public int getMainCol(){
        return mainCol;
    }
    @Override
    public boolean isEmpty() {
        return pole.isEmpty();
    }

    @Override
    public MazeObject get() {
        if (!pole.isEmpty()) {
            for (MazeObject i : pole) {
                if (i.isPacman()) {

                    return i;
                }
            }
            return pole.get(0);
        }
        return null;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean contains(MazeObject MazeObject) {
        for (MazeObject i : pole) {
            if (i.equals(MazeObject)) {
                return true;
            }
        }
        return false;
    }

}
