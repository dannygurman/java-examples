package interview.fibunacci;

class FibonacciExample1 {

    public static void main(String args[]) {
        int n1 = 0, n2 = 1;
        int n3, i;
        int count = 10;
        System.out.print(n1 + " " + n2);//printing 0 and 1

        //loop starts from 2 because 0 and 1 are already printed
        for (i = 2; i < count; i++) {
            n3 = n1 + n2;
            System.out.print(" " + n3);
            n1 = n2;
            n2 = n3;
        }

    }
}
