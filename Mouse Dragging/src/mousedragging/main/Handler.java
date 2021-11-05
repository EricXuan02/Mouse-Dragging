package mousedragging.main;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

public class Handler
{
	private LinkedList<Block> objects = new LinkedList<Block>();
	
	public void render(Graphics g)
	{
		for (Block object : objects)
		{
			object.render(g);
		}
	}
	
	public Block dragBlock(Point p)
	//Returns the first block in the list that can be dragged
	//Returns null if no block can be dragged
	{
		for (Block object : objects)
		{
			if (object.intersects(p))
				return object;
		}
		return null;
	}
	
	public LinkedList<Block> getObjects()
	{
		return objects;
	}
	
	public void addObject(Block object)
	{
		objects.add(object);
	}
}
