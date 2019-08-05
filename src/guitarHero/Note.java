package guitarHero;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Note{
	int y, x;
	int color;
	Color paint;
	
	public Note(int color)
	{
		switch (color)
		{
		case 1:
			paint = Color.GREEN;
			break;
		case 2:
			paint = Color.RED;
			break;
		case 3:
			paint = Color.YELLOW;
			break;
		case 4:
			paint = Color.BLUE;
			break;
		case 5:
			paint = Color.ORANGE;
			break;
		default:
			paint = Color.MAGENTA;
			break;
		}
		this.color = color;
		y = 0;
		x = 225 + 50*color;
	}
	
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getColor()
	{
		return color;
	}
	
	public void fillNote(GraphicsContext g2d)
	{
		update();
		g2d.setFill(paint);
		g2d.fillOval(x, y, 50, 50);
		g2d.setFill(Color.WHITE);
		g2d.fillOval(x+10, y+10, 30, 30);
	}
	
	private void update()
	{
		y+=15;
		//If you want to switch it back, just set it to y+=9 and subtract .8 seconds from the beginning of
		//each song, it runs better if it's set to y+=15 though. It also prevents you from hitting notes too
		//easily because of how forgiving the hitbox is combined with how slow the notes move. -Collin
	}
	
	public String toString()
	{
		return ""+color;
	}
}
