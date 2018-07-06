public class UnionFind {

    public int[] vertices;

    /* Creates a UnionFind data structure holding N vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int N) {
        vertices = new int[N];
        for (int i = 0; i < N; i++) {
            vertices[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        while (vertices[v] >= 0) {
            v = vertices[v];
        }
        return -1 * vertices[v];

    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        return vertices[v];
    }

    /* Returns true if nodes V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
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
        try {
            if (vertices[v] < 0) {
                // this node is the root!
                return v;
            } else {
                System.out.println(v);
                int n = find(vertices[v]);
                vertices[v] = n;
                return n;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /* Connects two elements V1 and V2 together. V1 and V2 can be any element,
       and a union-by-size heuristic is used. If the sizes of the sets are
       equal, tie break by connecting V1's root to V2's root. Union-ing a vertex
       with itself or vertices that are already connected should not change the
       structure. */
    public void union(int v1, int v2) {
        int s1 = sizeOf(v1);
        int s2 = sizeOf(v2);

        int r1 = find(v1);
        int r2 = find(v2);

        if (s1 == s2 || s2 > s1) {
            vertices[r2] += vertices[r1];
            vertices[r1] = r2;
        } else if (s1 > s2) {
            vertices[r1] += vertices[r2];
            vertices[r2] = r1;
        }
    }

    public static void main(String[] args) {
        UnionFind u1 = new UnionFind(5);
        u1.union(0, 1);
        u1.find(3);
        u1.find(0);
        int x= u1.sizeOf(1);
    }
}
