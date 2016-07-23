import java.util.*;
public class MineSweeper {
	//FIELDS
	private boolean isFirstClick;
	private boolean gameOver;
	private int[][] grid;
	private int[][] flagGrid;
	private int numFlags;
	private int endGameX, endGameY;
	private int flagCounter;
	private static int SUBDIVISIONS;//40
	private static final int DRAWING_SIZE = 920;
	private int numBombs;
	private double SUBDIVISION_SIZE;
	private boolean gameWon;
	//grid, open spots, closed spots, mined spots (int array)
	//
	//CONSTRUCTORS0

	public MineSweeper(int s)
	{
		SUBDIVISIONS = s;
		SUBDIVISION_SIZE = DRAWING_SIZE/SUBDIVISIONS;
		gameWon = false;
		endGameX = 0;
		endGameY = 0;
		isFirstClick = true;
		gameOver = false;
		grid = new int[SUBDIVISIONS][SUBDIVISIONS];
		flagGrid = new int[SUBDIVISIONS][SUBDIVISIONS];
		for (int i = 0; i < SUBDIVISIONS; i++)
		{
			for (int j = 0; j < SUBDIVISIONS; j++)
			{
				grid[i][j] = 0;
				flagGrid[i][j] = 0;
			}
		}
		//generate bombs
		//define numbers
	}
	//METHODS
	public void setNumDivisions(int d)
	{
		SUBDIVISIONS = d;
		SUBDIVISION_SIZE = DRAWING_SIZE/SUBDIVISIONS;
	}
	public boolean gameWon()
	{
		return gameWon;
	}
	public void clear()
	{
		for (int i = 0; i < SUBDIVISIONS; i++)
		{
			for (int j = 0; j < SUBDIVISIONS; j++)
			{
				grid[i][j] = 0;
				flagGrid[i][j] = 0;
			}
		}
		flagCounter = 0; 
		isFirstClick = true;
		gameOver = false;
		gameWon = false;
	}
	public void setNumFlags(int n)
	{
		numFlags = n;
		numBombs = n;
	}
	public int getFlags()
	{
		return numFlags-flagCounter;
	}
	public int getEndGameX()
	{
		return endGameX;
	}
	public int getEndGameY()
	{
		return endGameY;
	}
	public boolean isGameOver()
	{
		return gameOver;
	}
	public int[][] getFlagGrid()
	{
		return flagGrid;
	}
	//method to generate bombs
	//method to set number distances
	//SUBDIVISIONS*2 represents bombs
	//-1 represents spaces that have not yet been clicked on
	//show everything that is positive, store numNextTo as negative
	public void generateBombs(int numBombs, int x, int y) //verify this
	{
		numFlags = numBombs;
		int randX;
		int randY;
		for (int i = 0; i < numBombs; i++)
		{
			randX = (int)(Math.random()*100%SUBDIVISIONS);
			randY = (int)(Math.random()*100%SUBDIVISIONS);
			while (grid[randX][randY]==9 || (randX == x && randY == y))
			{
				randX = (int)(Math.random()*100%SUBDIVISIONS);
				randY = (int)(Math.random()*100%SUBDIVISIONS);
			}
			//System.out.println("rand is " + randX + ", " + randY + " and counter is " + i);
			//grid[randX][randY] = SUBDIVISIONS*2;
			grid[randX][randY] = 9;
			if (randX>0)
			{
				if (randY-1 > -1 && grid[randX-1][randY-1] != 9)
				{
					grid[randX-1][randY-1]++;
				}
				if (grid[randX-1][randY]!=9)
				{
					grid[randX-1][randY]++;
				}
				if (randY+1<SUBDIVISIONS && grid[randX-1][randY+1]!=9)
				{
					grid[randX-1][randY+1]++;
				}
			}
			if (randX+1<SUBDIVISIONS)
			{
				if (randY-1 > -1 && grid[randX+1][randY-1]!=9) 
				{
					grid[randX+1][randY-1]++;
				}
				if (grid[randX+1][randY]!=9)
				{
					grid[randX+1][randY]++;
				}
				if (randY+1<SUBDIVISIONS && grid[randX+1][randY+1]!=9)
				{
					grid[randX+1][randY+1]++;
				}
			}
			if (randY+1<SUBDIVISIONS && grid[randX][randY+1]!=9)
			{
				grid[randX][randY+1]++;
			}
			if (randY>0 && grid[randX][randY-1]!=9)
			{
				grid[randX][randY-1]++;
			}
		}
	}
	public int getNum(int i, int j)
	{
		return grid[i][j];
	}
	public void clickSquare(int x, int y, boolean isFlag)
	{
		if (gameOver)
			return;
		int xCoord = (int)(x/(SUBDIVISION_SIZE));
		int yCoord = (int)(y/(SUBDIVISION_SIZE));
		if (xCoord >= 40 || yCoord >= 40)
			return;
		if (isFirstClick && !isFlag)
		{
			generateBombs(numBombs, yCoord, xCoord);
			isFirstClick=false;
		}
		else if (isFirstClick && isFlag)
		{
			return;
		}
		else if (isFlag)
		{
			//set flags.    
			//grid[yCoord][xCoord]=10;
			if (flagGrid[yCoord][xCoord] == 0 && flagCounter < numFlags && !(grid[yCoord][xCoord] < 0))
			{
				flagGrid[yCoord][xCoord]=1;
				flagCounter++;	
			}
			else if (flagCounter>0 && flagGrid[yCoord][xCoord] == 1)
			{
				
				flagGrid[yCoord][xCoord] = 0;
				flagCounter--;
			}
			return;
		}
		int c = 0;
		
		if (flagCounter == numFlags)
		{
			for (int i = 0; i < SUBDIVISIONS; i++)
			{
				for (int j = 0; j < SUBDIVISIONS; j++)
				{
					if (flagGrid[i][j] == 1 && grid[i][j] == 9)
					{
						c++;
					}
				}
			}
		}
		if (c == numFlags)
		{
			gameOver=true;
			gameWon=true;
			System.out.println("Congratulations!");
		}
		
		//System.out.println("xycoord is " + xCoord + " " + yCoord);
		if (grid[yCoord][xCoord] == 9)
		{
			gameOver=true;
			endGameX = yCoord;
			endGameY = xCoord;
			//System.out.println("game over");
			//System.exit(0);
		}
		else if (grid[yCoord][xCoord] == 0)
		{
			//get all nearby 0's
			grid[yCoord][xCoord] = -9;
			//findSurrounding(yCoord, xCoord, -9);
			while(findSurrounding());
		}
		else if (grid[yCoord][xCoord] <0)
		{}
		else
		{
			if (flagGrid[yCoord][xCoord] != 1)
				grid[yCoord][xCoord]*=-1;
		}
	}
	private boolean findSurrounding()
	{
		boolean found=false;
		for (int i = 0; i < SUBDIVISIONS; i++)
		{
			for (int j = 0; j < SUBDIVISIONS; j++)
			{
				if (grid[i][j] == -9)
				{
					if (i>0)
					{
						if (j-1 > -1 && grid[i-1][j-1] ==0)
						{
							grid[i-1][j-1]=-9;
							found = true;
						}
						if (grid[i-1][j]==0)
						{
							grid[i-1][j]=-9;
							found = true;
						}
						if (j+1<SUBDIVISIONS && grid[i-1][j+1]==0)
						{
							grid[i-1][j+1]=-9;
							found = true;
						}
					}
					if (i+1<SUBDIVISIONS)
					{
						if (j-1 > -1 && grid[i+1][j-1]==0) 
						{
							grid[i+1][j-1]=-9;
							found = true;
						}
						if (grid[i+1][j]==0)
						{
							grid[i+1][j]=-9;
							found = true;
						}
						if (j+1<SUBDIVISIONS && grid[i+1][j+1]==0)
						{
							grid[i+1][j+1]=-9;
							found = true;
						}
						
					}
					if (j+1<SUBDIVISIONS && grid[i][j+1]==0)
					{
						grid[i][j+1]=-9;
						found = true;
					}
					if (j>0 && grid[i][j-1]==0)
					{
						grid[i][j-1]=-9;
						found = true;
					}
				}
			}
		}
		return found;
	}
	public void setNumbers()
	{
		for (int i = 0; i < SUBDIVISIONS; i++)
		{
			for (int j = 0; j < SUBDIVISIONS; j++)
			{
				System.out.print(""+grid[i][j]);
			}
			System.out.println("");
		}
		//find out how close each thing is from a bomb
		//set -1*distance as the number, unless the number is 0. 
	}
	private int findDistance(int x, int y)
	{
		return 0;
	}
}
