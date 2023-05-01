package ija.ija2022.homework2.game;

import ija.ija2022.homework2.common.Field;

import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class PacmanObject extends BasicObject {

    int lives;
    int hasKeys;
    boolean hasTarget;
    Logger logger;

    public PacmanObject(Field f, Logger logger) {
        super(f);
        this.lives = 3;
        this.hasKeys = 0;
        this.hasTarget = false;
        this.logger=logger;
    }

    public boolean isPacman () {
        return true;
    }


    @Override
    public int getLives() {
        return this.lives;
    }

    @Override
    public int getKeys() {
        return this.hasKeys;
    }

    @Override
    public boolean getTarget() {
        return this.hasTarget;
    }

    public void hit () {
        this.lives -= 1;
    }

    public void gotKey () {
        this.hasKeys += 1;
    }

    public void gotTarget () {
        this.hasTarget = true;
    }

    @Override
    public boolean canMove(Field.Direction direction) {
        return this.mainF.nextField(direction).canMove();
    }

    @Override
    public boolean move(Field.Direction direction) {
        try {
            logger.info(this.hashCode() + " " + isPacman()+ " " + direction);
            Field tmp = (Field) this.mainF.nextField(direction);
            tmp.put(this);
            this.mainF.remove(this);
            setField(tmp);

        } catch (UnsupportedOperationException exception) {
            System.err.println((exception.getMessage()));
            return false;
        }
        return true;
    }
}
