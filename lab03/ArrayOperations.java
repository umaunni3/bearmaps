public class ArrayOperations {
    /**
     * Delete the value at the given position in the argument array, shifting
     * all the subsequent elements down, and storing a 0 as the last element of
     * the array.
     */
     
    // public static void main(String[] args) {
    //   int[] values = {1, 2, 3, 4, 5};
    //   insert(values, 0, 0);
    //   int[] afterInsert1 = {0, 1, 2, 3, 4};
    //   // if (!check(afterInsert1, values)) {
    //   //     return false;
    //   // }

    //   insert(values, 4, 7);
    //   int[] afterInsert2 = {0, 1, 2, 3, 7};
    //   // if (!check(afterInsert2, values)) {
    //   //     return false;
    //   // }
    // }
    public static void delete(int[] values, int pos) {
        if (pos < 0 || pos >= values.length) {
            return;
        }
        // TODO: YOUR CODE HERE
        for (int i = 0; i < values.length - 1; i++) {
            if (i >= pos) {
                values[i] = values[i+1];
            }
        }
        values[values.length - 1] = 0;
    }

    /**
     * Insert newInt at the given position in the argument array, shifting all
     * the subsequent elements up to make room for it. The last element in the
     * argument array is lost.
     */
    public static void insert(int[] values, int pos, int newInt) {
        if (pos < 0 || pos >= values.length) {
            return;
        }
        // TODO: YOUR CODE HERE
        int temp = newInt;
        for (int i = 0; i < values.length; i++) {
            if (pos == i) {
                int holder = values[i];
                // temp = values[i+1];
                values[i] = temp;
                temp = holder;
            } else if (i > pos) {
                int holder = values[i];
                values[i] = temp;
                temp = holder;
            }
        }
    }
}
