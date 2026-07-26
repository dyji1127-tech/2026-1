package cse2010.hw2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DLinkedListTest {
    @Test
    void create_empty_list() {
        // Arrange (Given)

        // Act (When)
        DLinkedList<Integer> list = new DLinkedList<>();

        // Assert (Then)
        assertEquals(0, list.getSize());
        assertEquals(list.getHeader().getNext(), list.getTrailer());
        assertEquals(list.getTrailer().getPrev(), list.getHeader());
    }

    @Test
    void should_add_first() {
        // Arrange (Given)
        DLinkedList<Integer> list = new DLinkedList<>();

        // Act (When)
        list.addFirst(42);

        // Assert (Then)
        assertEquals(1, list.getSize());
        assertEquals(42, list.getFirst().getItem());
    }

    @Test
    void should_add_last() {
        // Arrange (Given)
        DLinkedList<Integer> list = new DLinkedList<>();

        // Act (When)
        list.addFirst(42);

        // Assert (Then)
        assertEquals(1, list.getSize());
        assertEquals(42, list.getLast().getItem());
    }

    @Test
    void should_remove_first() {
        // Arrange (Given)
        DLinkedList<Integer> list = new DLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);

        // Act (When)
        int value = list.removeFirst();

        // Assert (Then)
        assertEquals(1, list.getSize());
        assertEquals(2, value);
        assertEquals(1, list.getLast().getItem());
        assertEquals(1, list.getFirst().getItem());
    }

    @Test
    void should_remove_last() {
        // Arrange (Given)
        DLinkedList<Integer> list = new DLinkedList<>();
        list.addLast(1);
        list.addLast(2);

        // Act (When)
        int value = list.removeLast();

        // Assert (Then)
        assertEquals(1, list.getSize());
        assertEquals(2, value);
        assertEquals(1, list.getLast().getItem());
        assertEquals(1, list.getFirst().getItem());
    }
}
