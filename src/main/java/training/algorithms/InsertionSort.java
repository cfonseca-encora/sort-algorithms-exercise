package training.algorithms;

import java.util.Comparator;

/**
 * Algorithm complexity:
 * Worst case: O(n^2)
 * Best case: O(n)
 */

public class InsertionSort implements Sorter {
    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        sort(array, Comparable::compareTo);
    }

    @Override
    public <T> void sort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            T key = array[i];
            int j = i - 1;
            /*
              Move elements of arr[0..i-1], that are
              greater than key, to one position ahead
              of their current position
             */
            while(j >= 0 && comparator.compare(array[j], key) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    @Override
    public String toString() {
        return "Insertion{}";
    }
}
