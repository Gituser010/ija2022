package ija.ija2022.homework2.game;

import ija.ija2022.homework2.common.Field;

public class KeyObject extends BasicObject{
    public KeyObject(Field f) {
        super(f);
    }

    public boolean isKey () {
        return true;
    }
}
