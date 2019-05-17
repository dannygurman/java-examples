package algorithms.arrays.watercontainer;

//Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
// n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
// Find two lines, which together with x-axis forms a container, such that the container contains the most water.


public class ContainerWithMostWater {

    public static void main(String[] args) {
        int [] heights = new int [] { 2 , 5 , 2 , 3 , 6 ,5 ,3 , 10};
        System.out.println("Max area:" +  maxArea(heights) );
    }

    public static int maxArea (int [] heights) {

        int left =0;
        int right = heights.length -1 ;
        int maxArea = 0;


        while (left < right ) {
            int rightHeight = heights[right];
            int leftHeight = heights[left];

            int currentMatchArea = getContainerArea(right , left , rightHeight , leftHeight);
            maxArea = Math.max(maxArea , currentMatchArea );
            if (leftHeight < rightHeight){
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    static int getContainerArea (int rightIndex , int leftIndex , int rightHeight , int leftHeight) {
        return  Math.min(rightHeight , leftHeight) * (rightIndex - leftIndex);
    }

}
