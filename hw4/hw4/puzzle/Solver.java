package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: hw4
 * @BelongsPackage: hw4.puzzle
 * @Author: Eric
 * @CreateTime: 2022-08-03  17:00
 * @Description: TODO
 */
public class Solver {
    private class SearchNode implements Comparable<SearchNode> {

        private WorldState state;
        private int moves;
        private SearchNode preNode;

        public SearchNode(WorldState state, int moves, SearchNode pre) {
            this.state = state;
            this.moves = moves;
            preNode = pre;

        }

        @Override
        public int compareTo(SearchNode o) {
            return this.moves + this.state.estimatedDistanceToGoal() - o.moves - o.state.estimatedDistanceToGoal();
        }
    }

    private MinPQ<SearchNode> fringe = new MinPQ<>();
    private List<WorldState> sol = new ArrayList<>();

/** Constructor which solves the puzzle, computing
 * everything necessary for moves() and solution() to
 * not have to solve the problem again. Solves the
 * puzzle using the A* algorithm. Assumes a solution exists. */
    public Solver(WorldState initial) {
        fringe.insert(new SearchNode(initial, 0, null));
        while (!fringe.min().state.isGoal()) {
            SearchNode curNode = fringe.delMin();
            for (WorldState n : curNode.state.neighbors()) {
                if (!n.equals(curNode.preNode.state) || curNode.preNode == null) {
                    fringe.insert(new SearchNode(n, curNode.moves + 1, curNode));
                }
            }
        }
    }

/** Returns the minimum number of moves to solve the puzzle starting
 * at the initial WorldState. */
    public int moves() {
        return fringe.min().moves;
    }

/** Returns a sequence of WorldStates from the initial WorldState
 * to the solution. */
    public Iterable<WorldState> solution() {
        Stack<WorldState> stack = new Stack<>();
        SearchNode pos = fringe.min();
        while (pos != null) {
            stack.push(pos.state);
            pos = pos.preNode;
        }
        while (!stack.isEmpty()) {
            sol.add(stack.pop());
        }
        return sol;
    }


}

