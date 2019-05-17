package designPatterns.structural.flyweight.ex3;

/**
 * Created by DannyG on 19/01/2015.
 */
/**
 * Flyweight Interface
 *
 */
public interface Soldier {

    /**
     * Move Soldier From Old Location to New Location
     * Note that soldier location is extrinsic to the SoldierFlyweight Implementation
     * @param previousLocationX
     * @param previousLocationY
     * @param newLocationX
     * @param newLocationY
     */
    public void moveSoldier(int previousLocationX, int previousLocationY ,
                            int newLocationX  ,int newLocationY);
}