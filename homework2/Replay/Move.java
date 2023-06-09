package ija.ija2022.homework2.Replay;

import ija.ija2022.homework2.common.Field;
import ija.ija2022.homework2.common.MazeObject;
import ija.ija2022.homework2.game.BasicObject;
import ija.ija2022.homework2.game.Game;

import java.util.Dictionary;
import java.util.List;

public class Move {
    public boolean isPacman;
    public Field.Direction direction;
    public int objectId = 0;
    public BasicObject mazeObject =null;
    static Dictionary<Integer,BasicObject> basicObjectsDictionary;

    public Move(String line) {

        String[] strArr= line.split(" ");


        objectId=Integer.parseInt(strArr[0]);
        if(basicObjectsDictionary.get(objectId)!=null) {
            mazeObject=basicObjectsDictionary.get(objectId);
        }
        if (strArr[2].equalsIgnoreCase("U")) this.direction= Field.Direction.U;
        else if (strArr[2].equalsIgnoreCase("D")) this.direction= Field.Direction.D;
        else if (strArr[2].equalsIgnoreCase("R")) this.direction= Field.Direction.R;
        else if (strArr[2].equalsIgnoreCase("L")) this.direction= Field.Direction.L;
        if (strArr[1].equalsIgnoreCase("true")) {
            this.isPacman=true;
        }
        else {
            this.isPacman=false;
        }
        this.mazeObject=basicObjectsDictionary.get(objectId);
    }

    public void execute() {
        basicObjectsDictionary.get(objectId).move(this.direction);
    }

    public static void addObject(String line, Game maze){
        String[] strArr= line.split(" ");
        int row=0;
        int col=0;
        Field f;
        BasicObject o;
        int objectId=0;
        objectId= Integer.parseInt(strArr[1]);
        row=Integer.parseInt(strArr[3]);
        col=Integer.parseInt(strArr[4]);
        f=maze.getField(row,col);
        o= (BasicObject) f.get();
        basicObjectsDictionary.put(objectId,o);
        return;
    }
}
