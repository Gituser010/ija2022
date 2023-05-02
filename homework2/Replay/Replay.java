package ija.ija2022.homework2.Replay;

import ija.ija2022.homework2.game.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.Thread.sleep;

public class Replay {

    public Game maze;
    public File file;
    public int step=0;
    private Stack<UiAction> doneMoves=new Stack<>();

    public List<UiAction> moves=new ArrayList<>();


    public Replay(Game maze,File file){
        this.maze=maze;
        try {
            this.file =  file;
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.contains("created")) {
                    Move.addObject(data,maze);
                    continue;
                }
                Move newMove = new Move(data);
                moves.add(newMove);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void execute() throws InterruptedException {
        for (UiAction move : moves) {
            Thread.sleep(400);
            move.run();
            step++;
        }
    }

    public void nextStep()
    {
        moves.get(step).run();
        doneMoves.add(moves.get(step));
        step++;
    }

    //how to go back
    public void prevStep()
    {
        doneMoves.pop().undo();
        step--;
    }

}
