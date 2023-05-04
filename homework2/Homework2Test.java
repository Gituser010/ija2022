/*
 * IJA 2022/23: Úloha 2
 * Testovací třída.
 */
package ija.ija2022.homework2;

import java.util.List;



import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

//--- Importy z implementovaneho reseni ukolu
import ija.ija2022.homework2.game.MazeConfigure;
import ija.ija2022.homework2.common.Maze;
import ija.ija2022.homework2.common.MazeObject;
import ija.ija2022.homework2.common.Field;
//---

//--- Importy z baliku dodaneho nastroje


//---


/**
 * Testovací třída pro druhý úkol z předmětu IJA 2022/23.
 * @author Radek Kočí
 */
public class Homework2Test {
    
    private Maze maze;
       
    /**
     * Vytvoří bludiště, nad kterým se provádějí testy.
     */
    @Before
    public void setUp() {
        MazeConfigure cfg = new MazeConfigure();
        cfg.startReading(4, 3);
        cfg.processLine("T.G");
        cfg.processLine(".XK");
        cfg.processLine("KX.");
        cfg.processLine(".SG");
        cfg.stopReading();       
        maze = cfg.createMaze();
    }

    /**
     * Test existence objektu, který reprezentuje ducha.
     * 2 body
     */
    @Test
    public void testGhosts() {
        List<MazeObject> lstGhost = maze.ghosts();
        Assert.assertEquals("Bludiste obsahuje jednoho ducha", 2, lstGhost.size());
        MazeObject obj = lstGhost.remove(1);
        MazeObject obj2 = lstGhost.remove(0);
        Assert.assertEquals("Bludiste obsahuje jednoho ducha", 2, maze.ghosts().size());
        Assert.assertFalse("Objekt neni pacman", obj.isPacman());
        Assert.assertEquals("Objekt je na spravne pozici",
                maze.getField(4, 3),
                obj.getField());

        Assert.assertEquals("Bludiste obsahuje jednoho ducha", 2, maze.ghosts().size());
        Assert.assertFalse("Objekt neni pacman", obj2.isPacman());
        Assert.assertEquals("Objekt je na spravne pozici",
                maze.getField(1, 3),
                obj2.getField());

    }


    /**
     * Test existence objektu, který reprezentuje klic.
     * 2 body
     */
    /**
    @Test
    public void testKeys() {
        List<MazeObject> lstKeys = maze.keys();
        Assert.assertEquals("Bludiste obsahuje jednoho ducha", 2, lstKeys.size());
        MazeObject obj = lstKeys.remove(1);
        MazeObject obj2 = lstKeys.remove(0);
        Assert.assertEquals("Bludiste obsahuje jednoho ducha", 2, maze.keys().size());
        Assert.assertFalse("Objekt neni pacman", obj.isPacman());
        Assert.assertEquals("Objekt je na spravne pozici",
                maze.getField(3, 1),
                obj.getField());

        Assert.assertEquals("Bludiste obsahuje jednoho ducha", 2, maze.keys().size());
        Assert.assertFalse("Objekt neni pacman", obj2.isPacman());
        Assert.assertEquals("Objekt je na spravne pozici",
                maze.getField(2, 3),
                obj2.getField());

    }
*/
    /**
     * Test správného pohybu ducha po bludišti.
     * 2 body
     */
    @Test
    public void testGhostMoving() {
        // Ghost na pozici 1,3
        MazeObject obj = maze.ghosts().get(0);
        Assert.assertTrue("Presun na policko se podari.", obj.move(Field.Direction.D));
        Assert.assertTrue("Presun na policko se podari.", obj.move(Field.Direction.D));
        Assert.assertTrue("Presun na policko se podari.", obj.move(Field.Direction.D));
        Assert.assertFalse("Presun na policko se nepodari.", obj.move(Field.Direction.R));
    }    
   
    /**
     * Test správného chování při setkání ducha s pacmanem (sníží se počet životů pacmana).
     * 3 body
     */
    @Test
    public void testGhostMeetsPacman() {
        // Ghost na pozici 1,3
        MazeObject ghost = maze.ghosts().get(0);

        // Pacman na pozici 4,2
        System.out.println("test");
        Assert.assertFalse("Policko [4,2] neni prazdne", maze.getField(4, 2).isEmpty());
        MazeObject pacman = (MazeObject) maze.getField(4, 2).get();
        Assert.assertTrue("Objekt je pacman", pacman.isPacman());
        Assert.assertEquals("Pocet zivotu pacmana", 3, pacman.getLives());
        Assert.assertEquals("Pocet zivotu pacmana", 0, pacman.getKeys());
        Assert.assertFalse("Pocet zivotu pacmana", pacman.getTarget());
        
        Assert.assertTrue("Presun na policko se podari.", ghost.move(Field.Direction.D));
        Assert.assertEquals("Pocet zivotu pacmana", 3, pacman.getLives());
        Assert.assertTrue("Presun na policko se podari.", ghost.move(Field.Direction.D));
        Assert.assertEquals("Pocet zivotu pacmana", 3, pacman.getLives());
        Assert.assertTrue("Presun na policko se podari.", ghost.move(Field.Direction.D));
        Assert.assertEquals("Pocet zivotu pacmana", 3, pacman.getLives());
        Assert.assertTrue("Presun na policko se podari.", ghost.move(Field.Direction.L));
        Assert.assertEquals("Pocet zivotu pacmana", 2, pacman.getLives());
    }    

    /**
     * Testování notifikací při přesunu objektu (ducha).
     * 5 bodů
     */
    /*@Test
    public void testNotificationGhostMoving() {
        MazeTester tester = new MazeTester((CommonMaze) maze);
       
        // Ghost na pozici 1,3
        MazeObject obj = maze.ghosts().get(0);

        /* Testy, kdy se presun podari.
         * Dve prezentace policka (view) budou notifikovana o zmene (odebrani objektu a vlozeni objektu).
         * Kazde takove view bude notifikovano prave jednou.
         * Ostatni notifikovana nebudou.
         *//*
        testNotificationGhostMoving(tester, true, obj, Field.Direction.L);
        testNotificationGhostMoving(tester, true, obj, Field.Direction.L);
        testNotificationGhostMoving(tester, true, obj, Field.Direction.D);
        
        /* Testy, kdy se presun nepodari (pokus vstoupit do zdi).
         * Nikdo nebude notifikovan.
         *//*
        testNotificationGhostMoving(tester, false, obj, Field.Direction.R);
    }

    /**
     * Pomocná metoda pro testování notifikací při přesunu objektu.
     * @param tester Tester nad bludištěm, který provádí vyhodnocení notifikací.
     * @param success Zda se má přesun podařit nebo ne
     * @param obj Přesouvaný objekt
     * @param dir Směr přesunu
     *//*
    private void testNotificationGhostMoving(MazeTester tester, boolean success, MazeObject obj, Field.Direction dir) {
        StringBuilder msg;
        boolean res;

        // Policko, na kterem byl objekt pred zmenou
        Field previous = obj.getField();
        // Policko, na kterem ma byt objekt po zmene
        Field current = previous.nextField(dir);

        // Zadna notifikace zatim neexistuje
        res = tester.checkEmptyNotification();
        Assert.assertTrue("Zadna notifikace.", res);
        
        // Pokud se ma presun podarit
        if (success) {
            Assert.assertTrue("Presun na policko se podari.", obj.move(dir));
            msg = new StringBuilder();
            // Overeni spravnych notifikaci
            res = tester.checkNotification(msg, (CommonMazeObject) obj, (CommonField) current, (CommonField) previous);
            Assert.assertTrue("Test notifikace: " + msg, res);
        } 
        // Pokud se nema presun podarit
        else {
            Assert.assertFalse("Presun na policko se nepodari.", obj.move(dir));            
            // Zadne notifikace nebyly zaslany
            res = tester.checkEmptyNotification();
            Assert.assertTrue("Zadna notifikace.", res);
        }
    }*/
}
