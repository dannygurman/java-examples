package algorithms.hash.ex1;

public class NumArrayUtils {

    public static int [] generateNumbers() {
       return new int [] { -1 , 4 , 5 ,-3 , -2 , 4, 0 ,2 , 1 ,5 ,-2 ,20 ,3 ,2};
    }

    public static void printTriple ( int tripleIndex , int [] arr , int i , int j , int k) {
        System.out.println("- Tripple index:"+ tripleIndex +" ----------");
        System.out.println("indexes:" + i + "," + j + "," + k);
        System.out.println("values:" + arr[i] + "," + arr[j] + "," + arr[k]);
        System.out.println("-----------");
    }

}
