package javaVersions.v11.developerFeatures.stringMethods;

public class StringStrip {
    /*
    strip strip() is “Unicode-aware” evolution of trim().

When trim() was introduced, Unicode wasn’t evolved.
 Now, the new strip() removes all kinds of whitespaces leading and  trailing(check the method
 Character.isWhitespace(c) to know if a unicode is whitespace or not)
     */

    public static void main(String[] args) throws Exception {
        // Your code here!

        String str = " JD ";
        System.out.print("Start");
        System.out.print(str.strip());
        System.out.println("End");

        System.out.print("Start");
        System.out.print(str.stripLeading());
        System.out.println("End");

        System.out.print("Start");
        System.out.print(str.stripTrailing());
        System.out.println("End");
    }
}
