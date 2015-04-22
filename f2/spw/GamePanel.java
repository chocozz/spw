package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

// add import
import java.awt.Toolkit;
import java.awt.Image;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;
	private Image imgBg;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);

		imgBg = Toolkit.getDefaultToolkit().getImage("bg.jpg");
		big.drawImage(imgBg, 0, 0, 400, 600,null);
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		//big.setColor(Color.YELLOW);
		big.drawImage(imgBg, 0, 0, 400, 600,null);
		
		big.setColor(Color.YELLOW);
		big.drawString(String.format("%08d", reporter.getScore()), 250, 20);
		big.setColor(Color.RED);
		big.drawString(String.format("%d", reporter.getLive()), 50, 20);

		//EXTEND  DrawImage 
        

		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
