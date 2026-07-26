package cse2010.hw2;

public class MemoryManager {
    private final DLinkedList<Block> heap = new DLinkedList<>();

    /**
     * Constructs a memory manager with the given capacity.
     * @param capacity the capacity of the memory manager
     */
    public MemoryManager(int capacity) {
        Block initialBlock = new Block(capacity,0,capacity-1);
        Node<Block> newNode = new Node<>(initialBlock, null, null);
        heap.addAfter(heap.getHeader(), newNode);
    }

    /**
     * Allocates a block of memory with the given size.
     * @param size the size of the requested block
     * @return the allocated block
     * @throws OutOfMemoryException if there is no big-enough available block
     */
    public Block malloc(int size) {
        if (size <= 0) return null;
        Node<Block> current = heap.getHeader().getNext();
        while (current != heap.getTrailer()) {
            Block freeBlock = current.getItem();

            if (freeBlock.size >= size) {

                heap.remove(current);

                Block allocatedBlock = new Block(size, freeBlock.start, freeBlock.start + size - 1);


                if (freeBlock.size > size) {
                    int remainSize = freeBlock.size - size;
                    int remainStart = freeBlock.start + size;
                    Block remainBlock = new Block(remainSize, remainStart, freeBlock.end);


                    Node<Block> remainNode = new Node<>(remainBlock, null, null);
                    heap.addAfter(heap.getHeader(), remainNode);
                }

                return allocatedBlock;
            }
            current = current.getNext();
        }

        throw new RuntimeException();


        }


    /**
     * Returns the given block to the memory manager.
     * @param block the block to free (i.e, to return to the memory manager)
     */
    public void free(Block block) {

        Node<Block> newNode = new Node<>(block, null, null);


        Node<Block> header = heap.getHeader();
        Node<Block> nextNode = header.getNext();


        newNode.setNext(nextNode);
        newNode.setPrev(header);


        header.setNext(newNode);
        nextNode.setPrev(newNode);
    }

    /**
     * Returns the number of free blocks in the memory manager.
     * @return the number of free blocks in the memory manager
     */

    public int getFreeBlockCount() {
        return heap.getSize();
    }

    /**
     * Returns the total size of free blocks in the memory manager.
     * @return the total size of free blocks in the memory manager
     */
    public int getTotalFreeSize() {
        int totalSize = 0;


        Node<Block> current = heap.getHeader().getNext();


        while (current != heap.getTrailer()) {

            totalSize ++;


            current = current.getNext();
        }

        return totalSize;
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}


