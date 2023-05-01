package ija.ija2022.homework2.common;


public interface Field extends Observable{

    void setMaze(Maze maze);

    boolean put(MazeObject object);

    boolean remove(MazeObject object);


    Field nextField(Field.Direction dirs);

    boolean isEmpty();

    MazeObject get();

    boolean canMove();

    boolean contains(MazeObject var1);

    public static enum Direction {
        L(0, -1),
        U(-1, 0),
        R(0, 1),
        D(1, 0);

        private final int r;
        private final int c;

        private Direction(int dr, int dc) {
            this.r = dr;
            this.c = dc;
        }

        public int deltaRow() {
            return this.r;
        }

        public int deltaCol() {
            return this.c;
        }
    }

}
