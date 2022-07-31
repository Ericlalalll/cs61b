package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int[] nodeTo;
    private boolean cycleFound = false;
    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        nodeTo = new int[maze.V()];

    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfsHelper(-1, 0);

    }


    /** u is the parent node of v */
    private void dfsHelper(int u, int v) {
         marked[v] = true;
         announce();
         for (int w : maze.adj(v)) {
             if (!marked[w]) {
                 nodeTo[w] = v;
                 dfsHelper(v, w);
             } else if (w != u) {
                 // detect a cycle
                 edgeTo[w] = v;
                 announce();
                 for (int x = v; x != w; x = nodeTo[x]) {
                     edgeTo[x] = nodeTo[x];
                     announce();
                 }
                 cycleFound = true;
             }
             if (cycleFound) return;;

             }
    }
}

