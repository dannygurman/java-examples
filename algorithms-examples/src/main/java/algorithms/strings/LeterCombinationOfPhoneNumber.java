package algorithms.strings;

import java.util.LinkedList;
import java.util.List;

public class LeterCombinationOfPhoneNumber {
    //Given a digit String , return all possible letter combination that nuber could represent
    // A mapping of digit to number like Telephone is given


    public static void main(String[] args) {
        String digits = "23";
        List results = LeterCombinationOfPhoneNumber.letterCombinations(digits);
        results.forEach(str ->System.out.println(str));
    }


    public static List<String> letterCombinations (String digits) {
        LinkedList<String> ans = new LinkedList<>();

        String[] mappings = new String[]{
                "0", "1", "abc", "def", "ghi",
                "jkl", "mno", "pqrs", "tuv", "wxyz"};

        ans.add("");

        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            // Retrieves, but does not remove, the head (first element) of this lis
            while (ans.peek().length() == i) {
                String t = ans.remove();
                for (char s : mappings[x].toCharArray()) {
                    ans.add(t + s);
                }
            }

        }
        return  ans;
    }
}
