package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

// add import
import java.awt.Toolkit;
import java.awt.Image;

public class Item1 extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	
	private int step = 10;
	private boolean alive = true;
	
	public Item1(int x, int y) {
		super(x, y, 30, 30);
		
	}

	@Override
	public void draw(Graphics2D g) {
		/*
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		*/
		//g.setColor(Color.BLUE);
		//g.fillRect(x, y, width, height);
		Image img = Toolkit.getDefaultToolkit().getImage("item1.png");
        g.drawImage(img, x, y, width, height, null);
		
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}

	public void getHit(){
		this.alive = false;
	}
	
	public boolean isAlive(){
		return alive;
	}
}