package algorithms.arrays.sortcolor;

public class SortColorTrivialTwoPass {

    public static void sortColors(int [] array) {
        int[] colorCounts = new int[3];
        for (int val : array) {
            if (val == 0) {
                colorCounts[0]++;
            } else if (val == 1) {
                colorCounts[1]++;
            } else {
                colorCounts[2]++;
            }
        }

        int startIndex = 0;//start index for color
        int endIndexForColor = 0;
        for (int colorIndex =0 ; colorIndex < colorCounts.length ; colorIndex ++) {
            int colorVal = colorIndex;//value match the index
            int colorCount = colorCounts[colorIndex];
            endIndexForColor = startIndex + colorCount;
            fillArray( array , startIndex , endIndexForColor,  colorVal );
            startIndex += colorCount;
        }
    }

    public static void fillArray(int [] array , int startIndex , int endIndex, int val ){
        for (int i = startIndex ; i < endIndex ; i++){
            array[i] = val;
        }
    }

}
