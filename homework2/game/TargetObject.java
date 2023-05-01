package ija.ija2022.homework2.game;

import ija.ija2022.homework2.common.Field;

public class TargetObject extends BasicObject{
    public TargetObject(Field f) {
        super(f);
    }

    public boolean isTarget () {
        return true;
    }
}
