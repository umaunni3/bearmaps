/**
 * A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 *
 * This is a dummy implementation to allow IntListTest to compile. Replace this
 * file with your own IntList class.
 */
public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
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
        if (this.rest == null) {
            return Integer.toString(this.first);
        } else {
            return Integer.toString(this.first) + " " + this.rest.toString();
        }

    }

    /** Returns whether this and the given list or object are equal. */
    public boolean equals(Object o) {
        IntList other = (IntList) o;
        if (this.rest == null && other.rest == null) {
            return this.first == other.first;
        } else if (this.rest == null && other.rest != null
                || this.rest != null && other.rest == null) {
            return false;
        } else {
            if (this.first == other.first) {
                return this.rest.equals(other.rest);
            } else {
                return false;
            }
        }
    }

    /** Returns an IntList consisting of the given values. */
    public static IntList of(int... values) {
        if (values.length == 0) {
            return null;
        }
        IntList p = new IntList(values[0], null);
        IntList front = p;
        for (int i = 1; i < values.length; i += 1) {
            p.rest = new IntList(values[i], null);
            p = p.rest;
        }
        return front;
    }


    public void add(int value) {
        IntList p = this; // pointer to the list's head
        while (p.rest != null) {
            p = p.rest;
        }
        p.rest = new IntList(value, null);

    }

    public int smallest() {
        int min = this.first;
        IntList p = this;
        while (p != null) {
            if (p.first < min) {
                min = p.first;
            }
            p = p.rest;
        }
        return min;

    }

    public int squaredSum() {
        int sum = 0;
        IntList p = this;
        while (p != null) {
            sum += p.first * p.first;
            p = p.rest;
        }
        return sum;
    }

    public static void dSquareList(IntList L) {
        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    /** Return a new IntList with the same contents as A **/
    public static IntList copy(IntList A) {
        if (A == null) {
            return null;
        }
        IntList ret = new IntList(A.first, null);
        A = A.rest;
        IntList holder = ret;
        while (A != null) {
            ret.rest = new IntList(A.first, null);
            ret = ret.rest;
            A = A.rest;
        }
        return holder;
    }

    public static IntList catenate(IntList A, IntList B) {
        if (A == null) {
            return copy(B);
        } else if (B == null) {
            return copy(A);
        }
        IntList pA = A.rest;
        IntList pB = B;
        IntList newList = new IntList(A.first, null); // to return
        IntList pNew = newList; // pointer to new list
        while (pA != null) {
            pNew.rest = new IntList(pA.first, null);
            pNew = pNew.rest;
            pA = pA.rest;
        }
        // by here, newList should be a copy of A
        // now, start adding the elements of list B
        while (pB != null) {
            pNew.rest = new IntList(pB.first, null);
            pNew = pNew.rest;
            pB = pB.rest;
        }

        return newList;


    }

    public static IntList dcatenate(IntList A, IntList B) {
        if (A == null) {
            return B;
        } else if (B == null) {
            return A;
        }
        IntList p = A;
        while (p.rest != null) {
            // get to the last node of A
            p = p.rest;
        }
        p.rest = B;
        return A;

    }
}
