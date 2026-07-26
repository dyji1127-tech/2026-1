package list;
import java.util.Arrays;

public class IntegerArrayList implements IntegerListInterface {
    private int[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 64;

    public IntegerArrayList() {
        array = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    public IntegerArrayList(int initialCapacity) {
        array = new int[initialCapacity];
        size = 0;
    }

    @Override
    public void add(int index, int x) {
        if (size >= array.length || index < 0 || index > size) {
        }
        else {
            for (int i = size; i >= index; i--)
                array[i + 1] = array[i];
                array[index] = x;
                size++;
            }
        }


    @Override
    public Integer remove(int value) {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(int value) {
        return false;
    }

    @Override
    public int[] toArray() {
        return new int[0];
    }

    @Override
    public int[] toArrayCopy() {
        return new int[0];
    }

    @Override
    public int[] toArrayCopy(int start, int end) {
        return new int[0];
    }

    @Override
    public int[] toArray(int start, int end) {
        return new int[0];
    }

    @Override
    public int[] toArray(int[] array) {
        return new int[0];
    }

    @Override
    public int[] toArray(int[] array, int start, int end) {
        return new int[0];
    }

    @Override
    public void append(int value) {
        if (size >= array.length) {

        }
        else {
            array[size] = value;
        }

    }

    @Override
    public void append(int[] value) {

    }

    @Override
    public void append(int[] value, int start, int end) {

    }

    @Override
    public int len() {
        return 0;
    }

    @Override
    public void set(int index, int value) {

    }

    @Override
    public int get(int index) {
        return 0;
    }

    @Override
    public boolean removeItem(int value) {
        return false;
    }

    @Override
    public boolean removeItem(int[] value) {
        return false;
    }

    @Override
    public int indexOf(int value) {
        return 0;
    }

    @Override
    public int lastIndexOf(int value) {
        return 0;
    }

    public static void main(String[] args) {
        IntegerArrayList list = new IntegerArrayList();
        list.add(0,100);
        list.add(1, 5);
        list.add(2, 3);
        list.add(3, 4);
        list.add(4, 5);
        list.add(5, 6);
        list.append(100);
        System.out.println(list);
        for (int i = 0; i <= 64; i++) {
            System.out.println(i + "번 위치의 값: " + list.get(i));
        }

    }
}


