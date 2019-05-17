package designPatterns.structural.flyweight.ex1;

import java.util.List;

/**
 * Created by DannyG on 08/01/2015.
 */
public class Main {

    public static void main(String[] args) {

        TreeManager manager = TreeManager.getInstance();

        Tree t1 = new Tree(1, 1, 2);
        Tree t2 = new Tree(2, 2, 4);
        Tree t3 = new Tree(3, 3, 6);

        manager.addTree(t1);
        manager.addTree(t2);
        manager.addTree(t3);

        Tree out = manager.getTree(1, 1);

        System.out.println(out.x);
        System.out.println(out.y);
        System.out.println(out.age);

        out = manager.getTree(2, 2);

        System.out.println(out.x);
        System.out.println(out.y);
        System.out.println(out.age);

        List<Tree> trees = manager.getTrees();
        for(Tree tree : trees) {
            tree.show();
        }


    }

}