import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.HashSet;

public class CodingChallenges {

    public static void main(String[] args) {
        System.out.println("------");
        System.out.println("MissingNumber");

        // test missingNumber
        int[] a = new int[]{0, 1, 2, 4, 5};
        int x = missingNumber(a);
        System.out.println(x);

        int[] b = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16};
        int y = missingNumber(b);
        System.out.println(y);

        int missing = 71;
        int[] c = new int[100];
        int j = 0;
        for (int i = 0; i < 100; i++) {
            if (i != missing) {
                c[i] = j;
                j++;
            } else {
                j++;
            }
        }
        int z = missingNumber(c);
        System.out.println(z);


        System.out.println("------");
        System.out.println("SumTo");

        // test sumTo
        int[] v1 = new int[]{1, 2, 3, 4, 5};
        System.out.println(sumTo(v1, 10));



        System.out.println("------");
        System.out.println("IsPermutation");

        // test isPermutation
        String s1 = "hello";
        String equiv = "lohel"; // is a permutation of s1
        String partial = "helo"; // contains only part of s1
        String extra = "hlleoe"; // is a superset of chars in s1
        String random = "xyz"; // sanity check, should definitely return false
        System.out.println(isPermutation(s1, equiv));
        System.out.println(isPermutation(s1, partial));
        System.out.println(isPermutation(s1, extra));
        System.out.println(isPermutation(s1, random));
    }


    /**
     * Return the missing number from an array of length N - 1 containing all
     * the values from 0 to N except for one missing number.
     */

    public static int missingNumber(int[] values) {
        List<Integer> vals = new ArrayList<>();
        // cast to Integer type and add to array
        for (int i : values) {
            vals.add(i);
        }

        for (int i = 0; i < values.length; i++) {
            // iterate through the values which should be there
            if (vals.get(i) != i) {
                return i;
            }

        }
        return -1;
    }

    /** Returns true if and only if two integers in the array sum up to n. */
    public static boolean sumTo(int[] values, int n) {
        // this mayybe runs in O(N) time?? not sure tbh
        Set<Integer> vals = new HashSet<>();
        // cast to Integer type and add to array
        for (int i : values) {
            vals.add(i);
        }

        for (int i = 0; i < values.length; i++) {
            // iterate through all values; for each value, see
            // if there is a value k = n - vals[i] in vals
            int remainder = n - values[i];
            if (vals.contains(remainder)) {
                return true;
            }
        }
        return false;


    }

    /**
     * Returns true if and only if s1 is a permutation of s2. s1 is a
     * permutation of s2 if it has the same number of each character as s2.
     */
    public static boolean isPermutation(String s1, String s2) {
        // convert both strings to char array; each operation is O(N)
        List<Character> s1List = new ArrayList<>();
        List<Character> s2List = new ArrayList<>();
        char[] s1CA = s1.toCharArray();
        char[] s2CA = s2.toCharArray();
        for (char c : s1CA) {
            s1List.add(c);
        }

        for (char c : s2CA) {
            s2List.add(c);
        }

        for (Character c : s1List) {
            // check each char in s1List and see if it's contained in s2List;
            // if it is contained, remove it from s2List (to ensure that duplicates
            // aren't counted twice
            if (s2List.contains(c)) {
                s2List.remove(c);
            } else {
                return false;
            }
        }
        return s2List.size() == 0;

    }
}
