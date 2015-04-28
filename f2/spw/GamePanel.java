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
	//add imgLive
	private Image imgLive;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);

		imgBg = Toolkit.getDefaultToolkit().getImage("bg6.jpg");
		big.drawImage(imgBg, 0, 0, 400, 600,null);

		imgLive = Toolkit.getDefaultToolkit().getImage("hp.png");
		big.drawImage(imgLive, 0, 0, 400, 600,null);

	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		//big.setColor(Color.YELLOW);
		big.drawImage(imgBg, 0, 0, 400, 600,null);
		
		big.setColor(Color.YELLOW);
		big.drawString(String.format("Scores: %d", reporter.getScore()), 170, 40);
		big.setColor(Color.RED);
		big.drawString(String.format("Lives: %d", reporter.getLive()), 170, 20);
		big.setColor(Color.BLUE);
		big.drawString(String.format("Items: %d", reporter.getScoreItm()), 170, 60); 
        

		for(Sprite s : sprites){
			s.draw(big);
		}

		// add image Live
		for(int i=0, j=220 ; i<reporter.getLive(); i++, j+=20){
			big.drawImage(imgLive,j,0,20,20,null);  // img,x,y,width,hight 
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
