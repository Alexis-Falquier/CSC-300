package algs15.perc;

//import stdlib.*;
import algs15.*;

// Uncomment the import statements above.

// You can test this using InteractivePercolationVisualizer and PercolationVisualizer
// All methods should make at most a constant number of calls to the UF data structure,
// except percolates(), which may make up to N calls to the UF data structure.
public class Percolation {
	// create N-by-N grid, with all sites blocked
	WeightedUF percUnion;
	WeightedUF fullUnion;
	boolean[] open;
	int N;
	int vTop;
	int vBottom;
	
	public Percolation(int N) {
		percUnion = new WeightedUF(N*N+2);
		fullUnion = new WeightedUF(N*N+1);
		this.N = N;
		this.open = new boolean [N*N];
		vTop = N*N;
		vBottom = N*N+1;
		for (int j = 0; j < N; j++){
			percUnion.union(vTop, j);
			percUnion.union(vBottom, (N-1)*N+j);
			fullUnion.union(vTop, j);
		}
		// TODO
	}
	// open site (row i, column j) if it is not already
	public void open(int i, int j) {
		open[i*N+j] = true;
		if (i != 0 && isOpen(i-1,j)){
			percUnion.union((i*N+j), ((i-1)*N+j));
			fullUnion.union((i*N+j), ((i-1)*N+j));
		}
		if (i != N-1 && isOpen(i+1,j)){
			percUnion.union((i*N+j), ((i+1)*N+j));
			fullUnion.union((i*N+j), ((i+1)*N+j));
		}
		if (j != 0 && isOpen(i,j-1)){
			percUnion.union((i*N+j), (i*N+(j-1)));
			fullUnion.union((i*N+j), (i*N+(j-1)));
		}
		if (j != N-1 && isOpen(i,j+1)){
			percUnion.union((i*N+j), (i*N+(j+1)));
			fullUnion.union((i*N+j), (i*N+(j+1)));
		}
		//have to connect unions to adjacent open sites
		// TODO
	}
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		// TODO
		return open[i*N+j];
	}
	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		//check if connected to virtual top site in fullUnion
		if (fullUnion.connected((i*N+j), vTop) && isOpen(i,j)) return true;
		// TODO
		return false;
	}
	// does the system percolate?
	public boolean percolates() {
		//check if virtual bottom site is connected to virtual top site
		if (percUnion.connected(vBottom, vTop)) return true;
		// TODO
		return false;
	}
}
