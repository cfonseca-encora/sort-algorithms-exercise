package training.algorithms;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SortingTest {

    @ParameterizedTest
    @MethodSource("sorterProvider")
    void sortEmptyArray(Sorter sorter) {
        // Given:
        Integer[] array = new Integer[]{};

        // When:
        sorter.sort(array);

        // Then:
        assertThat(array).isNotNull().isEmpty();
    }

    @ParameterizedTest
    @MethodSource("sorterProvider")
    void sortArrayWithSingleElement(Sorter sorter) {
        // Given:
        Integer[] array = new Integer[]{10};

        // When:
        sorter.sort(array);

        // Then:
        assertThat(array).containsExactly(10);
    }

    @ParameterizedTest
    @MethodSource("sorterProvider")
    void sortArrayWorstCase(Sorter sorter) {
        // Given:
        Integer[] array = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        // When:
        sorter.sort(array);

        // Then:
        assertThat(array).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @ParameterizedTest
    @MethodSource("sorterProvider")
    void sortArrayBestCase(Sorter sorter) {
        // Given:
        Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // When:
        sorter.sort(array);

        // Then:
        assertThat(array).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @ParameterizedTest
    @MethodSource("sorterProvider")
    void sortArrayAnyOrder(Sorter sorter) {
        // Given:
        Integer[] array = new Integer[]{6, 4, 1, 8, 7, 9, 2, 5, 3, 10, 0};

        // When:
        sorter.sort(array);

        // Then:
        assertThat(array).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @ParameterizedTest
    @MethodSource("sorterProvider")
    void sortArrayAnyOrderDuplicated(Sorter sorter) {
        // Given:
        Integer[] array = new Integer[]{6, 4, 1, 8, 7, 6, 9, 2, 10, 5, 3, 10, 0, 9, 10};

        // When:
        sorter.sort(array);

        // Then:
        assertThat(array).containsExactly(0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 9, 10, 10, 10);
    }

    static Stream<Sorter> sorterProvider() {
        return Stream.of(new InsertionSort(), new BubbleSort(), new MergeSort()/*, new QuickSort()*/);
    }
}
