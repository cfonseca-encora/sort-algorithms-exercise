package training.algorithms;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Student {
    String lastName;
    String firstName;
    int code;

    public Student(String lastName, String firstName, int code) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return code == student.code && Objects.equals(lastName, student.lastName) && Objects.equals(firstName, student.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, code);
    }

    @Override
    public String toString() {
        return lastName + ' ' + firstName + " [" + code + ']';
    }
}

class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        if (o1.lastName.compareTo(o2.lastName) != 0)
            return o1.lastName.compareTo(o2.lastName);
        return o1.firstName.compareTo(o2.firstName);
    }
}


public class StabilityTest {
    private final Student[] inputArray = {
            new Student("Gomez", "Karen", 1000),
            new Student("Tammi", "Frank", 1000),
            new Student("Robinson", "Aura", 1000),
            new Student("Eric", "Lindsay", 1000),
            new Student("Wise", "Lynwood", 1000),

            new Student("Jimmy", "Williams", 100),
            new Student("Wise", "Lynwood", 200),

            new Student("Gomez", "Karen", 2000),
            new Student("Tammi", "Frank", 2000),
            new Student("Robinson", "Aura", 2000),
            new Student("Eric", "Lindsay", 2000),
            new Student("Wise", "Lynwood", 2000),

            new Student("Juan", "Wester", 300),
            new Student("Troy", "Taylor", 400),

            new Student("Gomez", "Karen", 3000),
            new Student("Tammi", "Frank", 3000),
            new Student("Robinson", "Aura", 3000),
            new Student("Eric", "Lindsay", 3000),
            new Student("Wise", "Lynwood", 3000),

            new Student("Butler", "Rebecca", 500),
            new Student("Chen", "Robert", 600),

            new Student("Gomez", "Karen", 4000),
            new Student("Tammi", "Frank", 4000),
            new Student("Robinson", "Aura", 4000),
            new Student("Eric", "Lindsay", 4000),
            new Student("Wise", "Lynwood", 4000),

            new Student("Howard", "Colleen", 700),
            new Student("Thomas", "Louann", 800),
    };


    @ParameterizedTest
    @MethodSource("stableSorterProvider")
    void bubbleSortStabilityTest(Sorter sorter) {
        // When:
        sorter.sort(inputArray, new StudentComparator());

        // Then:
        assertThat(inputArray).containsExactly(
                new Student("Butler", "Rebecca", 500),
                new Student("Chen", "Robert", 600),
                new Student("Eric", "Lindsay", 1000),
                new Student("Eric", "Lindsay", 2000),
                new Student("Eric", "Lindsay", 3000),
                new Student("Eric", "Lindsay", 4000),
                new Student("Gomez", "Karen", 1000),
                new Student("Gomez", "Karen", 2000),
                new Student("Gomez", "Karen", 3000),
                new Student("Gomez", "Karen", 4000),
                new Student("Howard", "Colleen", 700),
                new Student("Jimmy", "Williams", 100),
                new Student("Juan", "Wester", 300),
                new Student("Robinson", "Aura", 1000),
                new Student("Robinson", "Aura", 2000),
                new Student("Robinson", "Aura", 3000),
                new Student("Robinson", "Aura", 4000),
                new Student("Tammi", "Frank", 1000),
                new Student("Tammi", "Frank", 2000),
                new Student("Tammi", "Frank", 3000),
                new Student("Tammi", "Frank", 4000),
                new Student("Thomas", "Louann", 800),
                new Student("Troy", "Taylor", 400),
                new Student("Wise", "Lynwood", 1000),
                new Student("Wise", "Lynwood", 200),
                new Student("Wise", "Lynwood", 2000),
                new Student("Wise", "Lynwood", 3000),
                new Student("Wise", "Lynwood", 4000));
    }


    static Stream<Sorter> stableSorterProvider() {
        // Test all the algorithms it is expected to fail with BubbleSort and QuickSort
        return Stream.of(new InsertionSort(), new BubbleSort(), new MergeSort(), new QuickSort());
    }
}
