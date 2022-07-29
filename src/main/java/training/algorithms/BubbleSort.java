package training.algorithms;

import java.util.Comparator;

/**
 * Algorithm complexity:
 * Worst case: O(n^2)
 * Best case: O(n)
 */
public class BubbleSort implements Sorter {
    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        sort(array, Comparable::compareTo);
    }

    @Override
    public <T> void sort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    // swap arr[j+1] and arr[j]
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Bubble{}";
    }
}
