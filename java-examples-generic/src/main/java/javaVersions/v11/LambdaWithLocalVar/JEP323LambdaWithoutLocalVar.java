package javaVersions.v11.LambdaWithLocalVar;

import java.util.Arrays;

public class JEP323LambdaWithoutLocalVar {
    public static void main(String[] args) {

        var arrInteger = new Integer[]{5, 9, 3, 6, 2, 4, 8, 7, 1};

        long cnt = Arrays.stream(arrInteger).filter(
                x -> (x != null && x > 5)).count();
        System.out.println(cnt);
    }

}
