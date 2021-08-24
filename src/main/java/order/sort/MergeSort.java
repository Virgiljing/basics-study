package order.sort;

/**
 * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。将已有序的子序列合并，
 * 得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = new int[]{3,5,2,8,1,9,7};
        int[] ints = mergeSort(array);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    /**
     * 把长度为n的输入序列分成两个长度为n/2的子序列；
     * 对这两个子序列分别采用归并排序；
     * 将两个排序好的子序列合并成一个最终的排序序列。
     * @param nums
     * @return
     */
    public static int[] mergeSort(int[] nums) {
        //创建与原数组相同长度的数组
        int[] temp = new int[nums.length];
        mergeSort(nums, temp, 0, nums.length-1);
        return nums;
    }

    private static void mergeSort(int[] nums, int[] temp, int left, int right) {

        if(left < right) {
            //从中间将数组分成两半
            int mid = (left + right) >> 1;
            mergeSort(nums, temp, left, mid);
            mergeSort(nums, temp, mid+1, right);
            //将两个数组重新合并
            merge(nums, temp, left, mid+1, right);
        }
    }

    private static void merge(int[] nums, int[] temp,
                              int leftPos, int rightPos, int rightEnd) {

        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        //对比左右两个数组并将较小的数先放到辅助数组
        while(leftPos <= leftEnd && rightPos <= rightEnd) {
            if(nums[leftPos] <= nums[rightPos]) {
                temp[tmpPos++] = nums[leftPos++];
            }else {
                temp[tmpPos++] = nums[rightPos++];
            }
        }
        //把左边数组剩下的原数放到辅助数组
        while(leftPos <= leftEnd) {
            temp[tmpPos++] = nums[leftPos++];
        }
        //把右边数组剩下的原数放到辅助数组
        while(rightPos <= rightEnd) {
            temp[tmpPos++] = nums[rightPos++];
        }
        //把辅助数组复制到原数组
        for(int i = 0; i < numElements; i++,rightEnd--) {
            nums[rightEnd] = temp[rightEnd];
        }
    }


}
