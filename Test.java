package Pathfinding;

import java.util.ArrayList;

public class Test {
	
	private final static int OBSTACLE = -1;
	public final int matrixWidth = 5, matrixHeight = 5;
	public int matrix[][] =new int[matrixWidth][matrixHeight];
	
	/*private void print(int matrix[][]) {

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {				
				
				if(matrix[i][j]==OBSTACLE) {
					System.out.print("O ");
				}
				else {
				System.out.print(matrix[i][j] + " ");
				}		
			}
			System.out.println();
		}
	}
	*/
	
	public static void printSolution(int matrix[][],boolean matrixVisited[][], ArrayList<Node> output) {
		
		System.out.println("Shortest Path:");
		for (Node n : output) {
			int x=n.x;
			int y=n.y;
			System.out.println(n.toString());
			matrix[x][y]=0;			
		}
		
		System.out.println("");
	
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				
				if(matrix[i][j]!=0 && matrix[i][j]!=OBSTACLE) {
					matrix[i][j]=1;					
				}
				
				if(matrixVisited[i][j]==false) {
					matrix[i][j]=1;
				}
				
				if(matrix[i][j]==OBSTACLE) {
					System.out.print("O ");
				}
				
				else {
				
				System.out.print(matrix[i][j]+" ");
				}
			 }
			System.out.println("");
		}				
	}
	
public static void main(String[] args) {
		
		LeeAlgorithm l = new LeeAlgorithm(5,5);
		ArrayList<Node> output = l.findPath(new Node(0, 0), new Node(3, 3));
		
		for(Node n: output) {
			System.out.println(n.toString());
		}
		
		/*ArrayList<Node> output2 =l.findPath(new Node(1,1), new Node(3,3));
		
		printSolution(l.matrix,l.matrixVisited,output);
		printSolution(l.matrix,l.matrixVisited,output2);*/
		
	}
}
