// import edu.princeton.cs.algs4.QuickFindUF;
// import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    int[][] layout; // -1 -> open; 0 -> blocked; 1 -> full
    int dimension;
    int openSites;
    WeightedQuickUnionUF grid;

    /* Creates an N-by-N grid with all sites initially blocked. */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        layout = new int[N][N]; // default value is 0; all blocked
        grid = new WeightedQuickUnionUF(N*N +2); // +1 for water source and bottom node
        dimension = N;
        openSites = 0;
    }

    /* Opens the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        try {
            // check each adjacent neighbor
            if (layout[row][col] != 0) {
                // space already is open/full
                return;
            }
            layout[row][col] = -1;
            openSites++;
            if (row == 0) {
                // in top row; connect to water source
                grid.union(xyTo1D(row, col), dimension*dimension);
            }
            if (row == dimension - 1) {
                // in bottom row; connect to bottom node
                grid.union(xyTo1D(row, col), dimension*dimension+1);
            }
            int[][] neighbors = {{row+1, col}, {row-1, col}, {row, col+1}, {row, col-1}};
            for (int[] coords : neighbors) {
                int i = coords[0];
                int j = coords[1];

                if (valid(i, j)) {
                    // confirmed that the neighbor exist
                    if (isOpen(i, j)) {
                        grid.union(xyTo1D(i, j), xyTo1D(row, col));
                    }
                } else {
                    continue;
                }

            }
        } catch (Exception e){
            System.out.println(col);
            System.out.println(row);
            System.out.println("````````");
            System.out.println(e);
            System.out.println("--------");
            throw new IndexOutOfBoundsException();
        }

    }

    /* Returns true if the site at (row, col) is open. */
    public boolean isOpen(int row, int col) {
        try {
            return layout[row][col] == -1;
        } catch (Exception e){
            throw new IndexOutOfBoundsException();
        }
    }

    /* Returns true if the site (row, col) is full. */
    public boolean isFull(int row, int col) {
        try {
            return grid.connected(xyTo1D(row, col),dimension*dimension);
        } catch (Exception e){
            throw new IndexOutOfBoundsException();
        }
    }

    /* Returns the number of open sites. */
    public int numberOfOpenSites() {
        return openSites;


    }

    /* Returns true if the system percolates. */
    public boolean percolates() {
        return grid.connected(dimension*dimension, dimension*dimension+1);
    }

    /* Converts row and column coordinates into a number. This will be helpful
       when trying to tie in the disjoint sets into our NxN grid of sites. */
    private int xyTo1D(int row, int col) {
        // numbering scheme starts at 0 in top left, goes to
        // the right, then loops back to left and down one row
        // when it finishes each row
        if (row == 0) {
            return col;
        } else {
            return row * dimension + col;
        }
    }
    /* Returns true if (row, col) site exists in the NxN grid of sites.
       Otherwise, return false. */
    private boolean valid(int row, int col) {
        return row < dimension && col < dimension
                && row >=0 && col >= 0;
    }
}
