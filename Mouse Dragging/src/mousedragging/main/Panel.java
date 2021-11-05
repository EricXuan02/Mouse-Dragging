package mousedragging.main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

public class Panel extends JPanel
{

	private static final long serialVersionUID = 1L;
	
	Block b1 = new Block(1, 0, 0, Color.BLUE);
	Block b2 = new Block(2, 200, 200, Color.GREEN);
	Block b3 = new Block(3, 400, 400, Color.RED);
	
	int bCounter = 4;
	
	Handler handler = new Handler();
	
	boolean dragValid = false;
	
	public Point prevPt;
	
	public Panel()
	{
		ClickListener clickListener = new ClickListener();
		DragListener dragListener = new DragListener();
		this.addMouseListener(clickListener);
		this.addMouseMotionListener(dragListener);
		
		handler.addObject(b1);
		handler.addObject(b2);
		handler.addObject(b3);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		handler.render(g);
		g.drawString("Left click and drag to move a block.", 10, 520);
		g.drawString("Right click to add a block. (Must not overlap!)", 10, 550);
	}
	
	private class ClickListener extends MouseAdapter
	{
		public void mousePressed(MouseEvent e)
		{
			if (e.getButton() == 3)
			{
				Block newBlock = new Block(bCounter, e.getX(), e.getY(), Color.GRAY);
				for (Block block : handler.getObjects())
					if (!newBlock.equals(block) && newBlock.intersects(block))
					{
						System.out.println("Can't add a block here!");
						return;
					}
				handler.addObject(newBlock);
				bCounter++;
				System.out.println("New block added!");
				repaint();
				return;
			}
			
			Point point = e.getPoint();
			if (handler.dragBlock(point) != null)
			{
				dragValid = true;
				prevPt = point;
			}
				
		}
	}
	
	private class DragListener extends MouseMotionAdapter
	{
		public void mouseDragged(MouseEvent e)
		{
			if (dragValid)
			{
				Point currentPt = e.getPoint();
				Block currentBlock = handler.dragBlock(currentPt);
				
				if (currentBlock == null)
				{
					dragValid = false;
					return;
				}
				
				currentBlock.point.translate(
						(int) (currentPt.getX() - prevPt.getX()),
						(int) (currentPt.getY() - prevPt.getY())
						);
				
				for (Block block : handler.getObjects())
				{
					if (!currentBlock.equals(block) && currentBlock.intersects(block))
					//If this the current block intersects with any of the other blocks after translation, translate back to its original position
						currentBlock.point.translate(
								(int) (prevPt.getX() - currentPt.getX()),
								(int) (prevPt.getY() - currentPt.getY())
								);
				}
				
				prevPt = e.getPoint();
				repaint();
			}
		}
	}
}
