package lab11.graphs;

import java.lang.annotation.Target;
import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private int targetX, targetY;
    private boolean targetFound = false;
    private Maze maze;

    private class Node implements Comparable<Node> {
        private int v;
        private int priority;

        public Node(int v) {
            this.v = v;
            priority = distTo[v] + h(v);
        }

        @Override
        public int compareTo(Node n) {
            return this.priority - n.priority;
        }
    }


    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        this.targetX = targetX;
        this.targetY = targetY;
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int sourceX = maze.toX(v);
        int sourceY = maze.toY(v);
        return Math.abs(sourceX - targetX) + Math.abs(sourceY - targetY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }
    

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        PriorityQueue<Node> fringe = new PriorityQueue<>();
        Node curNode = new Node(s);
        fringe.add(curNode);
        marked[s] = true;
        while (!fringe.isEmpty()) {
            curNode = fringe.poll();
            book[curNode.v] = true;
            for (int w : maze.adj(curNode.v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = curNode.v;
                    distTo[w] = distTo[curNode.v] + 1;
                    announce();
                    if (w == t) {
                        return;
                    } else {
                        fringe.add(new Node(w));
                    }
                }
            }
        }

    }

    @Override
    public void solve() {
        astar(s);
    }

}

