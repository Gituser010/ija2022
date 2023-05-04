package ija.ija2022.homework2.common;


public interface MazeObject {
    default boolean isPacman() {
        return false;
    }

    default boolean isKey() {
        return false;
    }

    default boolean isTarget() {
        return false;
    }

    default boolean isGhost() {
        return false;
    }

    default boolean canMove(Field.Direction direction){
        return false;
    }

    default boolean move(Field.Direction direction) {
        return false;
    }

    default  boolean moveBack(Field.Direction direction){return  false;}

    Field getField();

    int getLives();

    int getKeys();

    boolean getTarget();
}
