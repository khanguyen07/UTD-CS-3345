import java.util.Random;
import java.util.Scanner;

public class MazeProject {

	public static class Maze 
   {
		int rows, columns;
		boolean wallHorizontal[][], wallVertical[][];

		//construct the maze
		public Maze(int r, int c) 
      {
			rows = r;
			columns = c;

			if (rows > 1) {
				wallHorizontal = new boolean[columns][rows];
				for (int j = 0; j < rows; j++) {
					for (int i = 0; i < columns; i++) {
						wallHorizontal[i][j] = true;
					}
				}
			}

			if (columns > 1) {
				wallVertical = new boolean[columns][rows];
				for (int i = 0; i < columns; i++) {
					for (int j = 0; j < rows; j++) {
						wallVertical[i][j] = true;
					}
				}
			}
		}

      //draw the maze using " " , "_" , and "|"
		public String toString() 
      {
			int i, j;
			String s = "  ";

			wallHorizontal[columns - 1][rows - 1] = false;

			for (i = 0; i < columns - 1; i++) {
				s = s + " _";
			}
			s = s + " \n";

			for (j = 0; j < rows; j++) {
				s = s + "|";
				for (i = 0; i < columns; i++) {
					if (wallHorizontal[i][j]) {
						s = s + "_";
					} else {
						s = s + " ";
					}
					if (i < columns - 1) {
						if (wallVertical[i][j]) {
							s = s + "|";
						} else {
							s = s + " ";
						}
					}
				}
				s = s + "|\n";
			}
			return s + "\n";
		}

		//remove wall
		public boolean removeWall(int r, int c, int dir) 
      {
			if (dir == 0) 
         {
				if (wallHorizontal[r][c] == true) 
            {
					wallHorizontal[r][c] = false;
					return true;
				} 
            else
					return false;
			}
         else 
         {
				if (wallVertical[r][c] == true) 
            {
					wallVertical[r][c] = false;
					return true;
				} 
            else
					return false;
			}
		}
	}

	public static void main(String[] args) 
   {
		int rows, columns;
		int internalWallHorizontal, internalWallVertical;
      int maze1_row, maze1_column, maze2_row, maze2_column;
		int maze1, maze2, set1, set2;
      
		Scanner input = new Scanner(System.in);

      //Take user input
		System.out.println("Please enter number of rows (2 or more): ");
		rows = input.nextInt();
		while (rows < 2) 
      {
			System.out.println("Rows must be 2 or more.");
			rows = input.nextInt();
		}

		System.out.println("Please enter number of columns (2 or more): ");
		columns = input.nextInt();
		while (columns < 2) 
      {
			System.out.println("Columns must be 2 or more.");
			columns = input.nextInt();
		}

		DisjSets ds = new DisjSets(rows * columns);
      
      Maze maze = new Maze(rows, columns);

		Random r1 = new Random();
		Random r2 = new Random();

      int size = rows * columns;
      
		//Generating 2 mazes with random walls
		while (size > 1) {
         
         //random a wall direction (0 or 1)
			int wallDirection = r1.nextInt(2);
         
         //0 is a horizontal wall, 1 is a vertical wall
			if (wallDirection == 0) 
         {
				
				internalWallHorizontal = r2.nextInt(columns);
				internalWallVertical = r2.nextInt(rows - 1);

				maze1_row = internalWallVertical + 1;
				maze1_column = internalWallHorizontal + 1;
				
				maze2_row = internalWallVertical + 2;
				maze2_column = internalWallHorizontal + 1;
				
				maze1 = (maze1_row - 1) * columns + maze1_column - 1;
				maze2 = (maze2_row - 1) * columns + maze2_column - 1;
			} 
         
         else 
         {
				
				internalWallHorizontal = r2.nextInt(columns - 1);
				internalWallVertical = r2.nextInt(rows);

				maze1_row = internalWallVertical + 1;
				maze1_column = internalWallHorizontal + 1;

				maze2_row = internalWallVertical + 1;
				maze2_column = internalWallHorizontal + 2;

				maze1 = (maze1_row - 1) * columns + maze1_column - 1;
				maze2 = (maze2_row - 1) * columns + maze2_column - 1;
			}

         //find path compression of 2 mazes
			set1 = ds.find(maze1);
			set2 = ds.find(maze2);
         
         //remove the walls to create a random maze
			if (set1 != set2) {
				
				if (maze.removeWall(internalWallHorizontal, internalWallVertical,
						wallDirection) == true) {
					size--;
					ds.union(set1, set2);
				}
			}
		}
      
      //print out maze
      System.out.println("---------------------------------");
		System.out.printf("Maze with %d rows and %d columns:", rows, columns);
      System.out.println();
      System.out.print(maze);
	}
}