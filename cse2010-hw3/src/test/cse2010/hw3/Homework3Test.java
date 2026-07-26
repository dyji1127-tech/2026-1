package cse2010.hw3;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class Homework3Test {
    /**
     * Fibonacci Test
     */
    @Test
    void iterative_and_tailrec_results_must_match() {
        int[] iter_result =
                IntStream.rangeClosed(0, 20).map(Fibonacci::fibIter).toArray();

        int[] rec_result =
                IntStream.rangeClosed(0, 20).map(Fibonacci::fib).toArray();

        assertArrayEquals(iter_result, rec_result);
    }

    @Test
    void should_cause_stack_overflow_because_no_tail_call_optimization_in_java() {
        assertThrows(StackOverflowError.class, () -> Fibonacci.fib(50_000));
    }

    /**
     * PigLatin Test
     */
    @Test
    void test_words_starting_with_vowels() {
        List<String> words = List.of("authorize", "eat", "ionize", "own", "utilize");

        List<String> expected = List.of("authorizeay", "eatay", "ionizeay", "ownay", "utilizeay");
        List<String> actual =
                words.stream().map(PigLatin::toPigLatin).collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test
    void test_words_starting_with_consonants() {
        List<String> words = List.of("pig", "latin", "smile", "string", "woops");

        List<String> expected = List.of("igpay", "atinlay", "ilesmay", "ingstray", "oopsway");
        List<String> actual =
                words.stream().map(PigLatin::toPigLatin).collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideInputsAndResults1")
    @DisplayName("Target is 0, and the source is the last index of the boxes of length 5.")
    void test1(int[] box, boolean expected) {
        Box puzzle = new Box(box);
        boolean result = puzzle.start();

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideInputsAndResults2")
    @DisplayName("Target is the last index, and the source is 0 of the boxes of length 5.")
    void test2(int[] box, boolean expected) {
        Box puzzle = new Box(box, 0, 4);
        boolean result = puzzle.start();

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideInputsAndResults3")
    @DisplayName("Target is 0, and the source is the last index of the boxes of length 10.")
    void test3(int[] box, boolean expected) {
        Box puzzle = new Box(box);
        boolean result = puzzle.start();

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideInputsAndResults4")
    @DisplayName("Target is the last index, and the source is 0 of the boxes of length 10.")
    void test4(int[] box, boolean expected) {
        Box puzzle = new Box(box, 0, 9);
        boolean result = puzzle.start();

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideSeedsAndResults")
    @DisplayName("Random test")
    void random_test(long seed, boolean expected) {
        Box puzzle = new Box(20, new Random(seed));
        System.out.println(Arrays.toString(puzzle.getBoxes()));
        boolean result = puzzle.start();

        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideInputsAndResults1() {
        return Stream.of(
                Arguments.of(new int[]{0, 2, 1, 3, 2}, true),
                Arguments.of(new int[]{0, 1, 4, 4, 2}, false),
                Arguments.of(new int[]{0, 2, 4, 2, 1}, false),
                Arguments.of(new int[]{0, 2, 4, 3, 3}, true),
                Arguments.of(new int[]{0, 2, 1, 1, 1}, false)
        );
    }

    private static Stream<Arguments> provideInputsAndResults2() {
        return Stream.of(
                Arguments.of(new int[]{3, 2, 2, 4, 0}, false),
                Arguments.of(new int[]{1, 1, 1, 2, 0}, false),
                Arguments.of(new int[]{3, 1, 3, 1, 0}, true),
                Arguments.of(new int[]{2, 1, 2, 3, 0}, true),
                Arguments.of(new int[]{1, 2, 1, 1, 0}, true)
        );
    }

    private static Stream<Arguments> provideInputsAndResults3() {
        return Stream.of(
                Arguments.of(new int[]{0, 6, 9, 3, 5, 4, 8, 7, 3, 4}, true),
                Arguments.of(new int[]{0, 1, 4, 6, 8, 7, 4, 5, 5, 5}, false),
                Arguments.of(new int[]{0, 4, 6, 8, 6, 4, 8, 3, 8, 1}, true),
                Arguments.of(new int[]{0, 4, 6, 5, 8, 3, 2, 9, 5, 4}, false),
                Arguments.of(new int[]{0, 8, 9, 3, 1, 5, 9, 7, 1, 2}, true),
                Arguments.of(new int[]{0, 9, 2, 2, 1, 8, 4, 6, 3, 5}, false),
                Arguments.of(new int[]{0, 6, 5, 2, 4, 3, 4, 4, 9, 5}, true),
                Arguments.of(new int[]{0, 7, 3, 9, 6, 9, 3, 9, 9, 6}, false),
                Arguments.of(new int[]{0, 1, 5, 3, 1, 7, 8, 2, 8, 8}, true),
                Arguments.of(new int[]{0, 7, 3, 9, 6, 9, 3, 9, 9, 6}, false),
                Arguments.of(new int[]{0, 2, 9, 5, 7, 1, 2, 2, 4, 2}, false)
        );
    }

    private static Stream<Arguments> provideInputsAndResults4() {
        return Stream.of(
                Arguments.of(new int[]{8, 5, 5, 3, 6, 7, 3, 3, 1, 0}, true),
                Arguments.of(new int[]{7, 1, 7, 1, 7, 8, 9, 4, 2, 0}, true),
                Arguments.of(new int[]{7, 1, 6, 1, 5, 8, 9, 4, 2, 0}, true),
                Arguments.of(new int[]{3, 7, 3, 2, 7, 3, 1, 3, 1, 0}, true),
                Arguments.of(new int[]{6, 5, 5, 1, 3, 9, 3, 3, 9, 0}, true),
                Arguments.of(new int[]{4, 4, 6, 3, 3, 2, 7, 8, 6, 0}, false),
                Arguments.of(new int[]{7, 9, 6, 7, 3, 8, 3, 3, 2, 0}, false),
                Arguments.of(new int[]{3, 6, 5, 5, 9, 9, 8, 5, 2, 0}, false),
                Arguments.of(new int[]{3, 1, 3, 2, 9, 1, 1, 7, 1, 0}, false),
                Arguments.of(new int[]{2, 9, 4, 1, 9, 7, 4, 1, 5, 0}, false)
        );
    }

    private static Stream<Arguments> provideSeedsAndResults() {
        return Stream.of(
                Arguments.of(2, false),
                Arguments.of(3, false),
                Arguments.of(11, false),
                Arguments.of(13, true),
                Arguments.of(37, false),
                Arguments.of(73, true),
                Arguments.of(157, false),
                Arguments.of(413, true),
                Arguments.of(773, false),
                Arguments.of(1231, true)
        );
    }
}
