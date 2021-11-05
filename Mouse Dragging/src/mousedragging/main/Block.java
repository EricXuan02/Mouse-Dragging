package mousedragging.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Block
{
	final int WIDTH = 120;
	final int HEIGHT = 120;
	
	int num;
	
	public Point point = new Point();
	
	Color color;
	
	public Block(int num, int x, int y, Color color)
	{
		this.num = num;
		this.point.x = x;
		this.point.y = y;
		this.color = color;
	}

	public Point getPoint() {return this.point;}
	public Color getColor() {return this.color;}
	
	public boolean intersects(Point p)
	//Returns true if the parameter point is within the bounds of the block
	{
		return (p.getX() > this.point.x) &&
				(p.getX() < this.point.x + WIDTH) &&
				(p.getY() > this.point.y) &&
				(p.getY() < this.point.y + HEIGHT);
	}
	
	public boolean intersects(Block b)
	//Returns true if the parameter block intersects with this block
	{
		Rectangle thisRect = new Rectangle(point, new Dimension(WIDTH, HEIGHT));
		Rectangle paramRect = new Rectangle(b.point, new Dimension(b.WIDTH, b.HEIGHT));
		
		return thisRect.intersects(paramRect);
	}
	
	public void render(Graphics g)
	{
		g.setColor(color);
		g.fillRect(point.x, point.y, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Sans", 20, 20));
		g.drawString("" + num, point.x + WIDTH/2, point.y + HEIGHT/2);
	}
	
}
