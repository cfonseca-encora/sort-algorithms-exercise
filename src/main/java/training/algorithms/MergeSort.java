package training.algorithms;

import java.util.Comparator;

import static java.lang.reflect.Array.newInstance;

public class MergeSort implements Sorter {

    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        internalSort(array, 0, array.length - 1, Comparable::compareTo);
    }

    @Override
    public <T> void sort(T[] array, Comparator<T> comparator) {
        internalSort(array, 0, array.length - 1, comparator);
    }

    private <T> void internalSort(T[] array, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int middle = left + (right - left) / 2;

            internalSort(array, left, middle, comparator);
            internalSort(array, middle + 1, right, comparator);

            merge(array, left, right, middle, comparator);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> void merge(T[] array, int left, int right, int middle, Comparator<T> comparator) {
        int newLeftLength = middle - left + 1;
        int newRightLength = right - middle;

        T[] leftArray = (T[]) newInstance(Object.class, newLeftLength);
        T[] rightArray = (T[]) newInstance(Object.class, newRightLength);

        /* THIS IS WHAT THE NEXT TWO LINES REPLACES ACCORDING TO THE IDE, WHY???

            for (int i = 0; i < newLeftLength; ++i)
                leftArray[i] = array[left + i];
         */

        if (newLeftLength >= 0)
            System.arraycopy(array, left, leftArray, 0, newLeftLength);

        for (int j = 0; j < newRightLength; ++j)
            rightArray[j] = array[middle + 1 + j];

        int i = 0;
        int j = 0;

        int k = left;
        while (i < newLeftLength && j < newRightLength) {
            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            }
            else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of leftArray[] if any */
        while (i < newLeftLength) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        /* Copy remaining elements of rightArray[] if any */
        while (j < newRightLength) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    @Override
    public String toString() {
        return "Merge{}";
    }
}
