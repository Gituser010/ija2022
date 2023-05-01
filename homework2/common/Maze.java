package ija.ija2022.homework2.common;



import java.util.List;

public interface Maze{

    Field getField(int row, int col);

    List<MazeObject> ghosts();

    List<MazeObject> keys();

    int numRows();

    int numCols();
}
