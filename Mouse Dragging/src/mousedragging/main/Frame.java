package mousedragging.main;
import javax.swing.JFrame;

public class Frame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	Panel panel = new Panel();
	
	public Frame()
	{
		this.add(panel);
		this.setTitle("Mouse Dragging");
		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
