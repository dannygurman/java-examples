package designPatterns.structural.flyweight.ex1;

/**
 * Created by DannyG on 08/01/2015.
 */
import java.util.ArrayList;
import java.util.List;

public class TreeManager {

    private static TreeManager instance = new TreeManager();

    private TreeManager() {
    }

    public int[][] ages = new int[50][50];

    public void addTree(Tree tree) {
        this.ages[tree.x][tree.y] = tree.age;

    }

    public Tree getTree(int x, int y) {
        Tree tree = new Tree(x, y, this.ages[x][y]);

        return tree;
    }

    public static TreeManager getInstance() {
        return instance;
    }

    // This I could make better through the Iterator Pattern but now I focus On the Flyweight pattern
    public List<Tree> getTrees() {

        List<Tree> trees = new ArrayList<Tree>();
        for (int i = 0; i < ages.length; i++) {
            for (int j = 0; j < ages[i].length; j++) {
                if (this.ages[i][j] != 0) {
                    trees.add(new Tree(i, j, this.ages[i][j]));
                }

            }
        }

        return trees;
    }
}