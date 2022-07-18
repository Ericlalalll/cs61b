package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* bakcwash prolem is due to the virtual top site and bottom site
 * being misitakenly connected when we call isFull(). To solve it, we need to create to an auxiliary disjont set
 * use it to check isFull()
 */
public class Percolation {
    private boolean [][] grid;
    private int N;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF sites;
    private WeightedQuickUnionUF auxsites; // identitical set without bottom site
    private int topSite;
    private int bottomSite;


    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must > 0");
        }
        this.N = N;
        topSite = N * N;
        bottomSite = N * N + 1;
        sites = new WeightedQuickUnionUF(N * N + 2);
        auxsites = new WeightedQuickUnionUF(N * N + 1);
        numberOfOpenSites = 0;
        grid = new boolean[N][N];
        // union the top row to topsite and bootom to the bottom row
        for (int i = 0; i < N; i++) {
            sites.union(topSite, xyTo1D(0, i));
            sites.union(bottomSite, xyTo1D(N - 1, i));
            auxsites.union(topSite, xyTo1D(0, i));
        }
    }

    public boolean isOpen(int row, int col) {
        validateRang(row, col);
        return grid[row][col];
    }

    private void validateRang(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
    }
    /* convert the 2D index to 1D's */
    private int xyTo1D(int row, int col) {;
        return row * N + col;
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /* open a square on the grid */
    public void open(int row, int col) {
        validateRang(row, col);
        if (isOpen(row,col)) return;
        grid[row][col] = true;
        numberOfOpenSites++;
        unionOpenNeighbor(row, col, row + 1, col);
        unionOpenNeighbor(row, col, row - 1, col);
        unionOpenNeighbor(row, col, row, col + 1);
        unionOpenNeighbor(row, col, row, col - 1);
    }

    private void unionOpenNeighbor(int row, int col, int rowToDetect, int colToDetect) {
        if (rowToDetect < 0 || rowToDetect >= N || colToDetect < 0 || colToDetect >= N) {
            return;
        }
        if (isOpen(rowToDetect, colToDetect)) {
            sites.union(xyTo1D(row, col), xyTo1D(rowToDetect, colToDetect));
            auxsites.union(xyTo1D(row, col), xyTo1D(rowToDetect, colToDetect));
        }

    }
    public boolean isFull (int row, int col) {
        validateRang(row, col);
        if (!isOpen(row, col)) return false;
        return auxsites.connected(topSite, xyTo1D(row, col));
    }

    public boolean percolates() {
        if (numberOfOpenSites == 0) return false;
        return sites.connected(topSite, bottomSite);
    }
}
