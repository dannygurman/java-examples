package designPatterns.creational.prototype.ex1;

public class PrototypeTest {
    public static void main(String args[]) {
        MonsterRegistry rm = new MonsterRegistry();
        try {
            UsingPrototype(rm);

            UsingPrototypeManager(rm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void UsingPrototypeManager(MonsterRegistry rm) throws Exception {
        rm.registerMonster("Alien", new Alien());
        rm.registerMonster("Cyborg", new Cyborg());
        rm.registerMonster("Geth", new Geth());

        System.out.println("Getting instances of monsters through clonning");

        Monster cyborg1 = rm.getMonster("Cyborg");
        cyborg1.Attack();

        Monster alien1 = rm.getMonster("Alien");
        alien1.Attack();

        Monster geth1 = rm.getMonster("Geth");
        geth1.Attack();

        System.out.println("Getting clones of monsters");

        Monster cyborg2 = cyborg1.Clone();
        cyborg2.Attack();

        Monster alien2 = alien1.Clone();
        alien2.Attack();

        Monster geth2 = geth1.Clone();
        geth2.Attack();
    }

    private static void UsingPrototype(MonsterRegistry rm) throws CloneNotSupportedException {
        Monster cyborg = rm.createMonster("Cyborg");
        cyborg.Attack();
        Monster alien = rm.createMonster("Alien");
        alien.Attack();
        Monster geth = rm.createMonster("Geth");
        geth.Attack();

        System.out.println("Clonning monsters...");

        Monster cyborg1 = cyborg.Clone();
        cyborg1.Attack();
        Monster alien1 = alien.Clone();
        alien1.Attack();
        Monster geth1 = geth.Clone();
        geth1.Attack();

    }
}