package javaVersions.v11;

public class StringIsBlank {

    public static void main(String[] args) {
        // Your code here!

        System.out.println(" ".isBlank()); //true

        String s = "Anupam";
        System.out.println(s.isBlank()); //false
        String s1 = "";
        System.out.println(s1.isBlank()); //true

    }
}

