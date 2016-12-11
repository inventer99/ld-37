package ld37.ai;

import java.util.ArrayDeque;
import java.util.ArrayList;

import javafx.scene.control.Cell;
import pixgen.math.Vector;

public class AStar
{
	private ArrayList<Cell> closed;
	private ArrayList<Cell> open;
	
	private String[] graph;
	
	public ArrayDeque<Vector> findPath(String[] graph, Vector start, Vector end)
	{
		closed = new ArrayList<Cell>();
		open = new ArrayList<Cell>();
		
		this.graph = graph;
		
		// Create starting cell
		Cell startCell = new Cell((int) start.x, (int) start.y);
		
		// Starting cell is 0 for everything
		startCell.fCost = 0;
		startCell.gCost = 0;
		startCell.hCost = 0;
		
		// Add starting point to open
		open.add(startCell);
		
		Cell current;
		while(!open.isEmpty())
		{
			// Get cell with lowest fCost from open
			current = getLowestFCost(open);
			
			// Have we reached the end
			if(current.x == (int) end.x && current.y == (int) end.y)
				break;
			
			// Remove current from open, Add it to closed
			open.remove(current);
			closed.add(current);
			
//			for(Cell cell : getAdjacentCells(open))
		}
		
		return null;
	}
	
	private Cell getLowestFCost(ArrayList<Cell> list)
	{
		Cell lowest = list.get(0);
		
		for(Cell cell : list)
			if(cell.fCost < lowest.fCost)
				lowest = cell;
		
		return lowest;
	}
	
	private ArrayList<Cell> getAdjacentCells(ArrayList<Cell> list, Cell center)
	{
		ArrayList<Cell> adjacent = new ArrayList<Cell>();
		
		Cell cell;
		if((cell = getCellByXY(list, center.x - 1, center.y - 1)) != null) adjacent.add(cell);
		if((cell = getCellByXY(list, center.x + 0, center.y - 1)) != null) adjacent.add(cell);
		if((cell = getCellByXY(list, center.x + 1, center.y - 1)) != null) adjacent.add(cell);
		if((cell = getCellByXY(list, center.x - 1, center.y + 0)) != null) adjacent.add(cell);
		if((cell = getCellByXY(list, center.x + 1, center.y + 0)) != null) adjacent.add(cell);
		if((cell = getCellByXY(list, center.x - 1, center.y + 1)) != null) adjacent.add(cell);
		if((cell = getCellByXY(list, center.x + 0, center.y + 1)) != null) adjacent.add(cell);
		if((cell = getCellByXY(list, center.x + 1, center.y + 1)) != null) adjacent.add(cell);
		
		return adjacent;
	}
	
	private Cell getCellByXY(ArrayList<Cell> list, int x, int y)
	{		
		for(Cell cell : list)
			if(cell.x == x && cell.y == y)
				return cell;
		
		return getCellFromGraph(graph, x, y);
	}
	
	private Cell getCellFromGraph(String[] graph, int x, int y)
	{
		Cell cell = new Cell(x, y);
		
		if(y < 0 || y > graph.length)
			return null;
		
		if(x < 0 || x > graph[y].length())
			return null;
		
		if(graph[y].charAt(x) == ' ')
		{
			cell.hCost = Integer.MAX_VALUE;
			cell.gCost = Integer.MAX_VALUE;
			cell.fCost = Integer.MAX_VALUE;
		}
		
		return cell;
	}
	
	private static class Cell
	{
		public Cell parent;
		
		public int x;
		public int y;
		
		public int gCost;
		public int hCost;
		public int fCost;
		
		public Cell(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
}
