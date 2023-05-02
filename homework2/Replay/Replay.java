package ija.ija2022.homework2.Replay;

import ija.ija2022.homework2.game.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Replay {

    public Game maze;
    public File file;

    public List<Move> moves;


    public Replay(Game maze,File file){
        this.maze=maze;
        try {
            this.file =  file;
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.contains("created")) {
                    Move.addObject(data,maze);
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

}
