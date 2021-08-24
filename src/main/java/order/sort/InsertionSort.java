package order.sort;


/**
 * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 */
public class InsertionSort {
    public static int[] insertionSort(int[] array){
        for (int i = 1; i < array.length; i++) {
            int preIndex = i - 1;
            int current = array[i];

            while (preIndex>=0&&array[preIndex]>current){
                array[preIndex + 1] = array[preIndex];
                preIndex -- ;
            }
            array[preIndex+1] = current;
        }
        return array;
    }


    public static void main(String[] args) {
        int[] array = new int[]{3,5,2,8,1,9,7};
        int[] ints = insertionSort(array);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
