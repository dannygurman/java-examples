package javaVersions.v11.developerFeatures.lambdaLocalVariable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;


public class JEP323LambdaWithLocalVar {

    public static void main(String[] args) {

        var arrInteger = new Integer[]{5, 9, 3, 6, 2, 4, 8, 7, 1};

        long cnt = Arrays.stream(arrInteger)
            .filter((@NotNull var a) -> (a != null && a > 5))
            .count();

           System.out.println(cnt);
    }
}

