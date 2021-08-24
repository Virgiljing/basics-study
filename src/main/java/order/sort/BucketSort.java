package order.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.List;

public class BucketSort {

    int bucketSize = 10;

    int arraySize = 1000;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BucketSort bs = new BucketSort();
        int[] array = bs.getArray();
        bs.bucketSort(array);
    }

    public int[] getArray() {
        int[] arr = new int[arraySize / 3];
        Random r = new Random();
        for (int i = 0; i < arraySize / 3; i++) {
            arr[i] = r.nextInt(100000);
        }
        return arr;

    }

    public void bucketSort(int[] a) {
        List<Integer> bucket[] = new ArrayList[bucketSize];

        for (int i = 0; i < a.length; i++) {
            int temp = a[i] / 10000;
            if (bucket[temp] == null) {
                bucket[temp] = new
                        ArrayList<Integer>();
            }
            bucket[temp].add(a[i]);
        }

        //对桶内各个元素进行排序
        for (int j = 0; j < bucketSize; j++) {
            intsertSort(bucket[j]);
            printList(bucket[j]);
        }
    }

    private void printList(List<Integer> list) {
        // TODO Auto-generated method stub
        while (list.size() > 0) {
            System.out.print(list.remove(0) + "\t");
        }
    }

    private void intsertSort(List<Integer> list) {
        // TODO Auto-generated method stub
        Collections.sort(list);
    }

}