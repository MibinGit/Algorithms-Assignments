package edu.neu.coe.info6205.sort.simple;

public class InsertionSort<X extends Comparable<X>> implements Sort<X> {

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        this.helper = helper;
    }

    public InsertionSort() {
        this(new Helper<>("InsertionSort"));
    }

    @Override
    public void sort(X[] xs, int from, int to) {
        for (int i = from; i < to; i++)
        {
            // Invariant 1: elements xs[from..i] are in order
            // TO BE IMPLEMENTED ...
            // ... END IMPLEMENTATION
            for (int j = i; j > 0 && getHelper().less(xs[j], xs[j - 1]); j--)
            {
                getHelper().swap(xs, from, to, j, j - 1);
            }
        }
    }

    @Override
    public String toString() {
        return helper.toString();
    }

    @Override
    public Helper<X> getHelper() {
        return helper;
    }

    private final Helper<X> helper;
}
