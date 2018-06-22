/**
 * A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 */
public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Returns an IntList consisting of the given values. */
    public static IntList of(int... values) {
        if (values.length == 0) {
            return null;
        }
        IntList p = new IntList(values[0], null);
        IntList front = p;
        for (int i = 1; i < values.length; i++) {
            p.rest = new IntList(values[i], null);
            p = p.rest;
        }
        return front;
    }

    /** Returns the size of the list. */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + rest.size();
    }

    /** Returns [position]th value in this list. */
    public int get(int position) {
        // TODO: YOUR CODE HERE
        // first, get to the top of the list

        IntList L = this;
        int i = 0;
        while (L != null) {
            if (i == position) {
                return L.first;
            } else {
                L = L.rest;
            }
            i++;
        }
        return -1; // returns -1 when the index is out of bounds

    }

    /** Returns the string representation of the list. */
    public String toString() {
        // TODO: YOUR CODE HERE
        if (this.rest == null) {
            return Integer.toString(this.first);
        } else {
            return Integer.toString(this.first) + " " + this.rest.toString();
        }
        
    }

    /** Returns whether this and the given list or object are equal. */
    public boolean equals(Object o) {
        IntList other = (IntList) o;
        // TODO: YOUR CODE HERE
        if (this.rest == null && other.rest == null) {
            return this.first == other.first;
        } else if (this.rest == null && other.rest != null || this.rest != null && other.rest == null) {
            return false;
        } else {
            if (this.first == other.first) {
                return this.rest.equals(other.rest);
            } else {
                return false;
            }
        }
    }
}
