package Pathfinding;

public class Node {

	public int x;
	public int y;
	public int value;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Node(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
	
	public boolean equals(Node n) {
		if (this.x == n.x && this.y == n.y) {
			return true;
		}
		return false;
	}

	public String toString() {
		return this.x + " " + this.y;/*+ " "+this.value*/
	}
}
