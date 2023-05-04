package ija.ija2022.homework2.game;

import ija.ija2022.homework2.common.Field;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class GhostObject extends BasicObject {

    Logger logger;
    public GhostObject(Field f, Logger logger) {
        super(f);
        this.logger=logger;
    }

    @Override
    public boolean canMove(Field.Direction direction) {
        return true;
    }

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

    @Override
    public boolean moveBack(Field.Direction direction) {
        Field tmp = (Field) this.mainF.nextField(direction);
        tmp.putBack(this);
        this.mainF.removeBack(this);
        return true;
    }

    public boolean isGhost () {
        return true;
    }
}
