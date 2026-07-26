package cse2010.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    @Test
    @DisplayName("findIndex should return -1 if no matching element found")
    void test_findIndex1() {
        // Given
        int[] xs = new int[10];
        Arrays.setAll(xs, i -> i + 1);

        // When
        int index = Utils.findIndex(xs, 777);

        // Then
        assertEquals(-1, index);
    }

    @Test
    @DisplayName("findIndex should return an index if the first matching element found")
    void test_findIndex2() {
        // Given
        int[] xs = new int[10];
        Arrays.setAll(xs, i -> i + 1);

        // When
        int index = Utils.findIndex(xs, 5);

        // Then
        assertEquals(4, index);
    }

    @Test
    void sum_returns_total_of_an_entire_array() {
        // Given
        double[] xs = new double[100];
        Arrays.setAll(xs, i -> i + 1);

        // When
        double total = Utils.sum(xs);

        // Then
        assertEquals(5050d, total, 0.1);
    }

    @Test
    void reverse_returns_reversed_array() {
        // Given
        String[] xs = {"Hello", "Mellow", "Yellow"};
        String[] ys = {"Yellow", "Mellow", "Hello"};

        // When
        String[] result = Utils.reverse(xs);

        // Then
        assertEquals(3, result.length);
        assertArrayEquals(ys, result);

        // original array should not be changed
        assertArrayEquals(new String[]{"Hello", "Mellow", "Yellow"}, xs);
    }

    @Test
    void reverse_in_place_returns_reversed_array_without_modifying_input_array() {
        // Given
        String[] xs = {"Hello", "Mellow", "Yellow"};
        String[] expected = {"Yellow", "Mellow", "Hello"};

        // When
        Utils.reverse_in_place(xs);

        // Then
        assertEquals(3, xs.length);
        assertArrayEquals(xs, expected);
    }

    @Test
    @DisplayName("swap should swap two elements in an array")
    void test_swap() {
        // Arrange (Given)
        int[] xs = {1, 2, 3, 4, 5};

        // Act (When)
        Utils.swap(xs, 0, 4);

        // Assert (Then)
        assertArrayEquals(new int[]{5, 2, 3, 4, 1}, xs);
    }

    @Test
    @DisplayName("swap_should_throw_exception_if_index_is_out_of_bound")
    void test_swap_throw_exception() {
        // Arrange (Given)
        int[] xs = {1, 2, 3, 4, 5};

        // Act (When)
        // Assert (Then)
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Utils.swap(xs, 0, 5));
    }

    @ParameterizedTest
    @MethodSource("provideInputsAndResults")
    @DisplayName("average should return running averages")
    void test_average(int[] input, double[] expected) {
        // Given
        // When
        double[] result = Utils.average(input);

        // Then
        assertArrayEquals(expected, result, 0.01);
    }

    private static Stream<Arguments> provideInputsAndResults() {
        return Stream.of(
                Arguments.of(new int[] {1, 3, 5, 7, 9}, new double[] {1.0, 2.0, 3.0, 4.0, 5.0}),
                Arguments.of(new int[] {11, 5, 7, 13}, new double[] {11.0, 8.0, 7.66, 9.0})
        );
    }
}