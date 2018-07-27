package Pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
	

public class LeeAlgorithm {

	public  int matrixWidth , matrixHeight;
	public int matrix[][];// = new int[matrixWidth][matrixHeight];
	public boolean matrixVisited[][];// = new boolean[matrixWidth][matrixHeight];
	public ArrayList<Node> nodeList = new ArrayList<Node>();
	public final int OBSTACLE = -1;
	public final int[] deltaX = { 1, -1, 0, 0 };
	public final int[] deltaY = { 0, 0, 1, -1 };

	
	public LeeAlgorithm(int matrixWidth, int matrixHeight) {
		this.matrixWidth=matrixWidth;
		this.matrixHeight=matrixHeight;
		matrix = new int[matrixWidth][matrixHeight];
		matrixVisited = new boolean[matrixWidth][matrixHeight];		
	}
	
	public ArrayList<Node> findPath(Node start, Node goal) {

		if (nodeList.isEmpty()) {
			nodeList.add(start);
			matrixVisited[start.x][start.y] = true;
		}

		for (int i = 1;; i++) {

			nodeList = waveExpansion(nodeList, i);

			if (matrix[goal.x][goal.y] != 0) {
				System.out.println("Path exists");
				break;
			}

			else if (nodeList.size() == 0) {
				System.out.println("No Path exists");
				break;
			}
		}
		ArrayList<Node> pathList = backtraceFromGoal(goal, start);
		clearMatrix();
		return pathList;
	}

	public ArrayList<Node> waveExpansion(ArrayList<Node> neighborList, int iteration) {

		ArrayList<Node> neighbors = new ArrayList<Node>();

		for (Node node : neighborList) {

			for (int k = 0; k < 4; k++) {

				Node n = new Node(node.x + deltaX[k], node.y + deltaY[k]);

				if (inMatrix(n) == true && matrixVisited[n.x][n.y] == false && matrix[n.x][n.y] != OBSTACLE) {
					neighbors.add(n);
					matrix[n.x][n.y] = iteration;
					matrixVisited[n.x][n.y] = true;
				}
			}
		}
		return neighbors;
	}

	public boolean inMatrix(Node n) {
		if (n.x >= 0 && n.y >= 0 && n.x < matrix.length && n.y < matrix.length) {
			return true;
		}
		return false;
	}

	public ArrayList<Node> backtraceFromGoal(Node fromGoal, Node toStart) {

		ArrayList<Node> pathList = new ArrayList<Node>();

		pathList.add(fromGoal);
		Node currentNode = null;

		while (!pathList.get(pathList.size() - 1).equals(toStart)) {

			currentNode = pathList.get(pathList.size() - 1);
			Node n = getSmallestNeighbor(currentNode);
			pathList.add(n);
			n = currentNode;
		}
		Collections.reverse(pathList);
		return pathList;
	}

	public Node getSmallestNeighbor(Node node) {

		ArrayList<Node> neighbors = new ArrayList<Node>();

		for (int k = 0; k < deltaX.length; k++) {

			if (node.x + deltaX[k] >= 0 && node.y + deltaY[k] >= 0 /*&& node.x+deltaX[k]<matrix.length && node.y+deltaY[k]<matrix.length*/) {
				
				Node n = new Node(node.x + deltaX[k], node.y + deltaY[k],
						matrix[node.x + deltaX[k]][node.y + deltaY[k]]);

				if (inMatrix(n) == true && matrixVisited[n.x][n.y] == true && matrix[n.x][n.y] != OBSTACLE) {
					neighbors.add(n);
				}
			}
		}

		Collections.sort(neighbors, new Comparator<Node>() {
			@Override
			public int compare(Node first, Node second) {
				return first.value - second.value;
			}
		});

		ArrayList<Node> randomNode = new ArrayList<Node>();

		int tmp = neighbors.get(0).value;

		for (Node n : neighbors) {
			if (tmp == n.value) {
				randomNode.add(n);
			}
		}

		Random r = new Random();
		int random = r.nextInt(randomNode.size());

		Node n = randomNode.get(random);
		return n;
	}

	public void clearMatrix() {
        nodeList.clear();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {

				matrixVisited[i][j]=false;
				if (matrix[i][j] != OBSTACLE) {
					matrix[i][j] = 0;
				}
			}
		}
	}
}