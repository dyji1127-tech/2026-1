package list;

public interface IntegerListInterface {
    public void add(int index, int x);
    public Integer remove(int value);
    public void clear();
    public int size();
    public boolean isEmpty();
    public boolean contains(int value);
    public int[] toArray();
    public int[] toArrayCopy();
    public int[] toArrayCopy(int start, int end);
    public int[] toArray(int start, int end);
    public int[] toArray(int[] array);
    public int[] toArray(int[] array, int start, int end);
    public void append(int value);
    public void append(int[] value);
    public void append(int[] value, int start, int end);
    public int len();
    public void set(int index, int value);
    public int get(int index);
    public boolean removeItem(int value);
    public boolean removeItem(int[] value);
    public int indexOf(int value);
    public int lastIndexOf(int value);
}
