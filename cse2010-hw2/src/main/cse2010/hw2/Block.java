package cse2010.hw2;

/* Block will be used as a type parameter */
public class Block {
    public int size;
    public int start;
    public int end;

    /**
     * Constructs a block with the given size, start, and end.
     * @param size the size of the block
     * @param start the start index of the block
     * @param end the end index of the block
     */
    public Block(int size, int start, int end) {
        this.size = size;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "(" + size +", " + start + ", " + end + ")";
    }
}
