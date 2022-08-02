package training.algorithms;

import org.assertj.core.util.Arrays;

import java.lang.reflect.Array;
import java.util.Comparator;

public class QuickSort implements Sorter {
    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        sort(array, Comparable::compareTo);
    }

    @Override
    public <T> void sort(T[] array, Comparator<T> comparator) {
        internalSort(array, 0, array.length - 1, comparator);
    }

    private <T> void internalSort(T[] array,int begin, int end, Comparator<T> comparator) {
        if (end < begin) {
            return;
        }

        int partitionIndex = partition(array, begin, end, comparator);

        internalSort(array, begin, partitionIndex-1, comparator);
        internalSort(array, partitionIndex+1, end, comparator);
    }

    private <T> int partition(T[] array, int begin, int end, Comparator<T> comparator) {
        T pivot = array[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;

                T swapTemp = array[i];
                array[i] = array[j];
                array[j] = swapTemp;
            }
        }

        T swapTemp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = swapTemp;

        return i + 1;
    }
}
