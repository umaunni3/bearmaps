 /**
  * An SLList is a list of integers, which hides the terrible truth of the
  * nakedness within.
  */
public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;

        @java.lang.Override
        public java.lang.String toString() {
            return "IntNode{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
        }

        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            IntNode intNode = (IntNode) object;

            if (item != intNode.item) return false;
            if (next != null ? !next.equals(intNode.next) : intNode.next != null) return false;

            return true;
        }

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private IntNode sentinel;
    private int size;

    /** Creates an empty SLList. */
    public SLList() {
        sentinel = new IntNode(63, null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(63, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

     public boolean equals(Object object) {
         if (this == object) return true;
         if (object == null || getClass() != object.getClass()) return false;

         SLList slList = (SLList) object;

         if (size != slList.size) return false;
         if (sentinel != null ? !sentinel.equals(slList.sentinel) : slList.sentinel != null) return false;

         return true;
     }

     @java.lang.Override
     public java.lang.String toString() {
         return "SLList{" +
                 "sentinel=" + sentinel +
                 ", size=" + size +
                 '}';
     }

     /** Returns an SLList consisting of the given values. */
    public static SLList of(int... values) {
        SLList list = new SLList();
        for (int i = values.length - 1; i >= 0; i -= 1) {
            list.addFirst(values[i]);
        }
        return list;
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** Adds x to the front of the list. */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    /** Returns the first item in the list. */
    public int getFirst() {
        return sentinel.next.item;
    }

    /** Return the value at the given index. */
    public int get(int index) {
        IntNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /** Adds x to the end of the list. */
    public void addLast(int x) {
        size += 1;
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    /** Recursive helper method for add **/
    public static void addHelper(IntNode N, int index, int x) {
        if (index == 0) {
            N.next = new IntNode(x, N.next);
        } else {
            addHelper(N.next, index-1, x);
        }

    }
    /** Adds x to the list at the specified index. */
    public void add(int index, int x) {
        if (index >= size) {
            addLast(x);
            return;
        }
        else if (index == 0) {
            sentinel.next = new IntNode(x, sentinel.next);
        } else {
            addHelper(sentinel.next, index-1, x);
        }
        size += 1;
    }

    /** Recursive helper method for reverse **/
    private static IntNode reverseHelper(IntNode N) {
        if (N == null || N.next == null) {
            return N;
        } else {
            IntNode first = N;
            IntNode end = reverseHelper(N.next);

            IntNode holder = end;
            IntNode last = null;
            while (holder.next != null && holder.next != last) {
                last = holder;
                holder = holder.next;
            }
            holder.next = first;
            return end;

        }
    }


    public void reverseNew() {
        /** maybe reverse a list? just swap elements by pairs **/
        IntNode prev = null;
        IntNode curr = sentinel.next;
        IntNode subsequent = null;

        while (curr != null) {
            // store next node in a holder bc we're going to delete
            // current reference to it
            subsequent = curr.next;
            curr.next = prev;
            prev = curr;
            curr = subsequent;
        }

    }

    /** Returns the reverse of this list. This method is destructive. */
    public void reverse() {
        IntNode prev = null;
        IntNode curr = sentinel.next;
        IntNode subsequent = null;

        while (curr != null) {
            // store next node in a holder bc we're going to delete
            // current reference to it
            subsequent = curr.next;
            curr.next = prev;
            prev = curr;
            curr = subsequent;
        }
        sentinel.next = prev; // ensure that sentinel points to new head of list

    }
}
