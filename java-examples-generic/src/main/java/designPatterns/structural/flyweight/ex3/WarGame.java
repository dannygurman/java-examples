package designPatterns.structural.flyweight.ex3;

/**
 * Created by DannyG on 19/01/2015.
 he code below shows the war game implementation.
 Note that war game instantiates 5 Soldier clients,
 each client maintains its internal state which is extrinsic to the soldier flyweight.

 And Although 5 clients have been instantiated only one flyweight Soldier has been used.
**
        * Driver : War Game
        */
public class WarGame {

    public static void main(String[] args) {
        // start war

        // draw war terrain

        // create 5 soldiers:
        SoldierClient warSoldiers [] ={
                new SoldierClient(),
                new SoldierClient(),
                new SoldierClient(),
                new SoldierClient(),
                new SoldierClient()
        };

        // move each soldier to his location
        // take user input to move each soldier
        warSoldiers[0].moveSoldier(17, 2112);

        // 	take user input to move each soldier
        warSoldiers[1].moveSoldier(137, 112);

        // note that there is only one SoldierImp ( flyweight Imp)
        // for all the 5 soldiers
        // Soldier Client size is small due to the small state it maintains
        // SoliderImp size might be large or might be small
        // however we saved memory costs of creating 5 Soldier representations
    }
}