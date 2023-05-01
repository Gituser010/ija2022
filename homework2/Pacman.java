package ija.ija2022.homework2;

import ija.ija2022.homework2.common.Maze;
import ija.ija2022.homework2.game.Game;
import ija.ija2022.homework2.game.MazeConfigure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Pacman {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: MazeConfigure <filename>");
            System.exit(1);
        }
        Game game =null;

        MazeConfigure mc = new MazeConfigure();
        String filename = args[0];
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            int rows = Integer.parseInt(line.split(" ")[0]);
            int cols = Integer.parseInt(line.split(" ")[1]);


            mc.startReading(rows, cols);

            while ((line = br.readLine()) != null) {
                if (!mc.processLine(line)) {
                    System.err.println("Invalid input format");
                    System.err.println(line);
                    System.exit(1);
                }
            }

            if (!mc.stopReading() || !mc.isInputValid()) {
                System.err.println("Invalid input format");
                System.exit(1);
    }

            Maze maze = mc.createMaze();
            game=(Game)maze;
            // Do something with the created maze
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
            System.exit(1);
        }        


        MazePresenter mazePresenter = new MazePresenter(game);
        mazePresenter.open();


    }
}
