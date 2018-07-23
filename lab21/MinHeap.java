import java.util.ArrayList;

/* A MinHeap class of Comparable elements backed by an ArrayList. */
public class MinHeap<E extends Comparable<E>> {

    /* An ArrayList that stores the elements in this MinHeap. */
    private ArrayList<E> contents;
    private int size;

    /* Initializes an empty MinHeap. */
    public MinHeap() {
        contents = new ArrayList<>();
        contents.add(null);
    }

    /* Returns the element at index INDEX, and null if it is out of bounds. */
    private E getElement(int index) {
        if (index >= contents.size()) {
            return null;
        } else {
            return contents.get(index);
        }
    }

    /* Sets the element at index INDEX to ELEMENT. If the ArrayList is not big
       enough, add elements until it is the right size. */
    private void setElement(int index, E element) {
        while (index >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, element);
    }

    /* Swaps the elements at the two indices. */
    private void swap(int index1, int index2) {
        E element1 = getElement(index1);
        E element2 = getElement(index2);
        setElement(index2, element1);
        setElement(index1, element2);
    }

    /* Prints out the underlying heap sideways. Use for debugging. */
    @Override
    public String toString() {
        return toStringHelper(1, "");
    }

    /* Recursive helper method for toString. */
    private String toStringHelper(int index, String soFar) {
        if (getElement(index) == null) {
            return "";
        } else {
            String toReturn = "";
            int rightChild = getRightOf(index);
            toReturn += toStringHelper(rightChild, "        " + soFar);
            if (getElement(rightChild) != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + getElement(index) + "\n";
            int leftChild = getLeftOf(index);
            if (getElement(leftChild) != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += toStringHelper(leftChild, "        " + soFar);
            return toReturn;
        }
    }

    /* Returns the index of the left child of the element at index INDEX. */
    private int getLeftOf(int index) {
        return index*2;
    }

    /* Returns the index of the right child of the element at index INDEX. */
    private int getRightOf(int index) {
        return index*2 + 1;
    }

    /* Returns the index of the parent of the element at index INDEX. */
    private int getParentOf(int index) {
        return index/2;
    }

    /* Returns the index of the smaller element. At least one index has a
       non-null element. */
    private int min(int index1, int index2) {
        if (contents.get(index1) == null || contents.get(index1).compareTo(contents.get(index2)) < 0) {
            return index2;
//        } else if (contents.get(index2) == null || contents.get(index1).compareTo(contents.get(index2)) > 0) {
//            return index1;
//        }
        } else { // do i need the above conditional? maybe, maybe not :P we'll see
            return index1;
        }
    }

    /* Returns but does not remove the smallest element in the MinHeap. */
    public E peek() {
        return contents.get(1);
    }

    /* Bubbles up the element currently at index INDEX. */
    private void bubbleUp(int index) {
        E item = contents.get(index);
        E parent = contents.get(getParentOf(index));
        if (parent == null) {
            return;
        }
        if (item.compareTo(parent) < 0) {
            // this item is smaller than its parent! keep bubbling up
            swap(index, getParentOf(index));
            bubbleUp(getParentOf(index));
        } else {
            // we're done! i think?
            return;

        }
    }

    /* Bubbles down the element currently at index INDEX. */
    private void bubbleDown(int index) {
        //
        if (getLeftOf(index) < size() && min(index, getLeftOf(index)) != index) {
            // current item is not smaller than its left child; swap them
            swap(index, getLeftOf(index));
            bubbleDown(getLeftOf(index));
        //
        } else if (getRightOf(index) < size() && min(index, getRightOf(index)) != index) {
            // current item is not smaller than its right child; swap them
            swap(index, getRightOf(index));
            bubbleDown(getRightOf(index));
        }
        // implicit base case: root is smaller than both children, or has no children; returns void
    }

    /* Inserts element into the MinHeap. */
    public void insert(E element) {
        // first, put element in next available spot in array
        contents.add(element);
        size += 1;
        // now, bubble it up
        bubbleUp(contents.size()-1);

    }

    /* Returns the number of elements in the MinHeap. */
    public int size() {
        return contents.size();
    }

    /* Returns the smallest element. */
    public E removeMin() {
        // first, swap the root and the rightmost bottom element
        swap(1, size());
        // now, remove the rightmost bottom element
        E toReturn = contents.remove(size());
        size -= 1;
        bubbleDown(1);
        return toReturn;

    }

    /* Updates the position of ELEMENT inside the MinHeap, which may have been
       mutated since the inital insert. If a copy of ELEMENT does not exist in
       the MinHeap, do nothing.*/
    public void update(E element) {
        if (!contents.contains(element)) {
            return;
        } else {
            int currIndex = contents.indexOf(element);
            // the item can only go up or down (or stay the same). which to do?

            if (getParentOf(currIndex) == 0) {
                bubbleDown(currIndex);
            }
            else if (getParentOf(currIndex) < size() && element.compareTo(contents.get(getParentOf(currIndex))) < 0) {
                // this element is now smaller than its parent; bubble it up!
                bubbleUp(currIndex);
            } else {
                bubbleDown(currIndex);
            }
        }
    }
}
