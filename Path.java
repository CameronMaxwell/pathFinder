/**
 * Class Path, that looks for path from starting cell to destination cell
 * @author Cameron Maxwell
 */

public class Path {
	Map cityMap; // references object representing the city map
	
	/**
	 * Constructor
	 * @param theMap
	 */
	public Path (Map theMap)
	{
		cityMap = theMap;
		
	}
	
	public void findPath()
	{
		ArrayStack<MapCell> path = new ArrayStack<MapCell>(5,5,5); // create new stack to store items from MapCell, initialized with initial size, sizeInc, sizeDec
		
		MapCell start = cityMap.getStart();
		path.push(start);
		start.markInStack();
		
		while (!path.isEmpty())
		{
			try
			{
				MapCell current = path.peek();
				if (current.isDestination())
				{
					System.out.print("Path found: " + path.size() + " cells.");
					break; // exit loop with "break" not return
				}
				MapCell next = nextCell(current);
				
				if (next != null)
				{
					path.push(next);
					next.markInStack();
				}
				else
				{
					MapCell remove = path.pop();
					remove.markOutStack();
				}
				
				if (path.size() == 0)
				{
					System.out.print("No path");
				}
			}
			catch(EmptyStackException e)
			{
				System.out.print("stack is empty"); 
			}
		}
	}
	private MapCell nextCell(MapCell cell)
	// i forgot to add condition of checking if neighbour cell is null
	{	
		// destination cell
		for (int i = 0; i < 4; i++)
		{
			if (cell.getNeighbour(i) != null && !cell.getNeighbour(i).isMarked())
				if (cell.getNeighbour(i).isDestination())
					return cell.getNeighbour(i);
		}
			
		// intersection
		for (int i = 0; i < 4; i++)
		{
			if (cell.getNeighbour(i) != null && !cell.getNeighbour(i).isMarked())
				if (cell.getNeighbour(i).isIntersection())
					return cell.getNeighbour(i);
		}
		
		// last choice - NOTE: you need to make a separate loop for each option... just bc u check it once before
		// going to each conditional, it then proceeds to getNeighbour(i) at specific index, can throw null pointer if no valid neighbour at this index
		
		if (cell.getNeighbour(0) != null && !cell.getNeighbour(0).isMarked())
			if ((cell.isIntersection() || cell.isNorthRoad()) && cell.getNeighbour(0).isNorthRoad())
				return cell.getNeighbour(0);
		
		if (cell.getNeighbour(1) != null && !cell.getNeighbour(1).isMarked())
			if ((cell.isIntersection() || cell.isEastRoad()) && cell.getNeighbour(1).isEastRoad())
				return cell.getNeighbour(1);
		
		if (cell.getNeighbour(2) != null && !cell.getNeighbour(2).isMarked())
			if ((cell.isIntersection() || cell.isSouthRoad()) && cell.getNeighbour(2).isSouthRoad())
				return cell.getNeighbour(2);
		
		if (cell.getNeighbour(3) != null && !cell.getNeighbour(3).isMarked())
			if ((cell.isIntersection() || cell.isWestRoad()) && cell.getNeighbour(3).isWestRoad())
				return cell.getNeighbour(3);
		
		// if still cannot find next cell, must return null
		return null;
	}
}
