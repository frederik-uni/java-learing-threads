package t19;

import java.util.Arrays;

public class MergeThread extends Thread {
    private int[] arr;

    public MergeThread(int[] arr) {
        this.arr = arr;
    }

    public void run() {
        if (arr == null || arr.length == 0) throw new RuntimeException("invalid array");
        if (arr.length == 1) return;
        if (arr.length == 2) {
            arr = arr[0] > arr[1] ? new int[]{arr[1], arr[0]} : arr;
            return;
        }
        int mid = arr.length / 2;
        var left = new MergeThread(Arrays.copyOfRange(arr, 0, mid));
        var right = new MergeThread(Arrays.copyOfRange(arr, mid, arr.length));
        left.start();
        right.start();

        try {
            left.join();
            right.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        arr = merge(left.arr, right.arr);
    }

    private static int[] merge(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                merged[k++] = a[i++];
            } else {
                merged[k++] = b[j++];
            }
        }

        while (i < a.length) merged[k++] = a[i++];
        while (j < b.length) merged[k++] = b[j++];

        return merged;
    }
}
