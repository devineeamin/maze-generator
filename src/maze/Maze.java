// Devinee Amin
// Project 4

package maze;

import java.util.Random;
import java.util.Scanner;

public class Maze
{

    public static void main(String[] args)
    {
        // get size of maze from user
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        int rows = scan.nextInt();
        System.out.print("Enter the number of columns: ");
        int cols = scan.nextInt();
        
        // create initial disjoint sets
        DisjSets sets = new DisjSets(rows*cols);
        
        // create initial maze
        Cell[][] maze = new Cell[rows][cols];
        
        int num = 0;
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                maze[i][j] = new Cell();
                maze[i][j].setNum(num);
                num++;
            }
        }
        
        maze[0][0].setNorth(false);
        maze[0][0].setWest(false);
        
        maze[rows-1][cols-1].setEast(false);
        maze[rows-1][cols-1].setSouth(false);
        
        // find and unions
        int unionCount = 0;
        while((unionCount != (rows*cols-1)) || (sets.find(0) != sets.find(rows*cols-1)))
        {
            boolean validNum2 = false;
            Random rand = new Random();
            
            int cur = rand.nextInt(rows*cols);
            int i = cur / cols;
            int j = cur % cols;
            int target  = -1;
            
            do
            {
                int num2 = rand.nextInt(3); // 0 = north, 1 = east, 2 = south, 3 = west
                
                // adjacent pair is north
                if(num2 == 0 && i-1 >= 0)
                {
                    validNum2 = true;
                    target = maze[i-1][j].getNum();
                    if(sets.find(cur) != sets.find(target))
                    {
                        sets.union(sets.find(cur), sets.find(target));
                        maze[i][j].setNorth(false);
                        maze[i-1][j].setSouth(false);
                        unionCount++;
                    }
                }
                // adjacent pair is east
                else if(num2 == 1 && j+1 < cols)
                {
                    validNum2 = true;
                    target = maze[i][j+1].getNum();
                    if(sets.find(cur) != sets.find(target))
                    {
                        sets.union(sets.find(cur), sets.find(target));
                        maze[i][j].setEast(false);
                        maze[i][j+1].setWest(false);
                        unionCount++;
                    }
                }
                // adjacent pair is south
                else if(num2 == 2 && i+1 < rows)
                {
                    validNum2 = true;
                    target = maze[i+1][j].getNum();
                    if(sets.find(cur) != sets.find(target))
                    {
                        sets.union(sets.find(cur), sets.find(target));
                        maze[i][j].setSouth(false);
                        maze[i+1][j].setNorth(false);
                        unionCount++;
                    }
                }
                // adjacent pair is west
                else if(num2 == 3 && j-1 >= 0)
                {
                    validNum2 = true;
                    target = maze[i][j-1].getNum();
                    if(sets.find(cur) != sets.find(target))
                    {
                        sets.union(sets.find(cur), sets.find(target));
                        maze[i][j].setWest(false);
                        maze[i][j-1].setEast(false);
                        unionCount++;
                    }
                }
            }while(!validNum2);
            
        }
        
        // display maze
        displayMaze(maze, rows, cols);
        System.out.println(unionCount);
    }
    
    // display the maze using _ and |
    public static void displayMaze(Cell[][] maze, int rows, int cols)
    {
        // upper border for the first row
        for(int j = 0; j < cols; j++)
        {
            if(maze[0][j].isNorth())
                System.out.print(" _");
            else
                System.out.print("  ");
        }
        System.out.println();
        
        // print the rest of the maze
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                if(maze[i][j].isWest())
                    System.out.print("|");
                else
                    System.out.print(" ");
                if(maze[i][j].isSouth())
                    System.out.print("_");
                else
                    System.out.print(" ");
                
                if(j == cols-1 && maze[i][j].isEast())
                    System.out.print("|");
                
            }
            System.out.println();
        }
    }
}
