public class UnionFind {

    /* TODO: Add instance variables here. */
    public int[] vertices;

    /* Creates a UnionFind data structure holding N vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        vertices = new int[N];
        for (int i = 0; i < N; i++) {
            vertices[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        int x = v;
        while (vertices[x] >= 0) {
            x = vertices[x];
        }
        return vertices[x];

    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE

        return vertices[v];
    }

    /* Returns true if nodes V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        int v1P = parent(v1);
        int v2P = parent(v2);
        if ((v1P < 0 && v2P == v1) || (v1P < 0 && v1P == v2)) {
            // one node is a root and the other is a child of the first node
            return true;
        } else if (v1P < 0 && v2P < 0) {
            // both nodes are roots; can't be connected
            return false;
        } else {
            return v1P == v2P;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid vertices are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (vertices[v] < 0) {
            // this node is the root!
            return v;
        } else {
            int n = find(vertices[v]);
            vertices[v] = n;
            return n;
        }
    }

    /* Connects two elements V1 and V2 together. V1 and V2 can be any element,
       and a union-by-size heuristic is used. If the sizes of the sets are
       equal, tie break by connecting V1's root to V2's root. Union-ing a vertex
       with itself or vertices that are already connected should not change the
       structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        int s1 = sizeOf(v1);
        int s2 = sizeOf(v2);

        if (s1 == s2 || s2 > s1) {
            vertices[v1] = v2;
        } else if (s1 > s2) {
            vertices[v2] = v1;
        }
    }
}
