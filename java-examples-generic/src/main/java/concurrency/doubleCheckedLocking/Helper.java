package concurrency.doubleCheckedLocking;

public class Helper {

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int x;

}
