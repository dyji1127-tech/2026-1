package cse2010.hw3;

import java.util.Arrays;
import java.util.Random;

public class Box {
    /**
     * Default target index
     */
    private static final int DEFAULT_TARGET_INDEX = 0;

    /**
     * Puzzle Boxes
     */
    private int[] boxes;

    /**
     * Starting index: default source index is the last index of the `boxes`.
     * Target index: default target index is the first index of the `boxes`.
     */
    private int startIndex;
    private int targetIndex = DEFAULT_TARGET_INDEX;

    /**
     * Flag(s) to check if a move to the left or right from a given box was
     * tried before
     */
    private boolean[] left_visited;
    private boolean[] right_visited;

    /**
     * Constructor for the puzzle using default start and target indexes.
     *
     * @param size the size of the puzzle box in terms of the number of boxes
     * DO NOT MODIFY THIS CONSTRUCTOR
     */
    public Box(int size) {
        this(size, size - 1, DEFAULT_TARGET_INDEX);
    }

    /**
     * Constructor for the puzzle using specified indexes.
     *
     * @param size   the size of the puzzle box in terms of the number of boxes
     * @param start the starting index
     * @param target the target index
     * DO NOT MODIFY THIS CONSTRUCTOR
     */
    public Box(int size, int start, int target) {
        startIndex = start;
        targetIndex = target;
        getBuckets(size, new Random(System.currentTimeMillis()));
    }


    /**
     * Generate random values for the puzzle boxes
     *
     * @param size the size of the puzzle box in terms of the number of boxes
     * @param gen  the random number generator
     * DO NOT MODIFY THIS METHOD
     */
    private void getBuckets(int size, Random gen) {
        boxes = new int[size];
        for (int i = 0; i < size; i++) {
            int value = 0;
            // Get next value while ignoring zero
            while ((value = Math.abs(gen.nextInt())) % size == 0)
                ;
            boxes[i] = value % size;
        }
        boxes[targetIndex] = 0; // Final target value is always set to 0.

        initFlags(size);
    }

    /**
     * Initialize the flag(s) for the puzzle
     *
     * @param size the size of the puzzle box in terms of the number of boxes
     */
    private void initFlags(int size) {
        left_visited = new boolean[size];
        right_visited = new boolean[size];
        Arrays.fill(left_visited, false);
        Arrays.fill(right_visited, false);
    }

    /**
     * Core of the puzzle. Move current index to the next index based on the value of the current index.
     *
     * @param current current index
     * @return true if the puzzle is solved, false otherwise
     */
    public boolean move(int current) {


        if (current == targetIndex) {
            return true;
        }

        int jumpDistance = boxes[current];

        int nextLeft = current - jumpDistance;

        if (nextLeft >= 0 && !left_visited[current]) {
            left_visited[current] = true;
            if (move(nextLeft)) {
                return true;
            }
        }
        int nextRight = current + jumpDistance;

        if (nextRight < boxes.length && !right_visited[current]) {
            right_visited[current] = true;
            if (move(nextRight)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Start the puzzle
     *
     * @return true if the puzzle is solved, false otherwise
     * DO NOT MODIFY THIS METHOD
     */
    public boolean start() {
        return move(startIndex);
    }

    /**
     * Get the boxes. Debugging purpose only.
     *
     * @return the boxes
     * DO NOT MODIFY THIS METHOD
     */
    public int[] getBoxes() {
        return boxes;
    }

    /**
     * Constructor for the puzzle using the specified boxes.
     * The start and target indexes are the last and the first indexes of the boxes, respectively.
     * This constructor is used for testing purposes only.
     *
     * @param buckets the boxes
     * DO NOT MODIFY THIS METHOD
     */
    public Box(int[] buckets) {
        this(buckets, buckets.length - 1, DEFAULT_TARGET_INDEX);
    }

    /**
     * Constructor for the puzzle using the specified boxes and indexes.
     * This constructor is used for testing purposes only.
     *
     * @param buckets the boxes
     * @param start  the starting index
     * @param target  the target index
     * DO NOT MODIFY THIS METHOD
     */
    public Box(int[] buckets, int start, int target) {
        this.boxes = buckets;
        startIndex = start;
        targetIndex = target;

        initFlags(buckets.length);
    }

    /**
     * Constructor for the puzzle using default start and target indexes.
     * This constructor is used for testing purposes only.
     *
     * @param size the size of the puzzle box in terms of the number of boxes
     * @param gen  the random number generator
     * DO NOT MODIFY THIS CONSTRUCTOR
     */
    public Box(int size, Random gen) {
        this(size, size - 1, DEFAULT_TARGET_INDEX, gen);
    }

    /**
     * Constructor for the puzzle using specified indexes and random number generator.
     * This constructor is used for testing purposes only.
     *
     * @param size   the size of the puzzle box in terms of the number of boxes
     * @param start the starting index
     * @param target the target index
     * @param gen    the random number generator
     * DO NOT MODIFY THIS CONSTRUCTOR
     */
    public Box(int size, int start, int target, Random gen) {
        startIndex = start;
        targetIndex = target;
        getBuckets(size, gen);
    }

    public static void main(String... args) {
        Box puzzle = new Box(5);
        System.out.println(Arrays.toString(puzzle.getBoxes()));
        System.out.println(puzzle.start());

        Box puzzle2 = new Box(5, 0, 4);
        System.out.println(Arrays.toString(puzzle2.getBoxes()));
        System.out.println(puzzle2.start());

        Box puzzle3 = new Box(5, new Random(1569));
        System.out.println(Arrays.toString(puzzle3.getBoxes()));
        System.out.println(puzzle3.start());
    }
}

