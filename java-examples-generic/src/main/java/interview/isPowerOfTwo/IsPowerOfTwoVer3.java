package interview.isPowerOfTwo;

public class IsPowerOfTwoVer3 {
    public boolean isPower2(int v) {
        if (v == 1) {
            return true;
        } else if (v % 2 != 0 || v == 0) {
            return false;
        } else {
            return isPower2(v / 2);
        }
    }
}
