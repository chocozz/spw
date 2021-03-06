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
import java.awt.Font;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;
	private Image imgBg;
	//add imgLive
	private Image imgLive;
	//add imgItm
	private Image imgItm;	
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

		imgItm = Toolkit.getDefaultToolkit().getImage("dorayaki.png");
		//big.drawImage(imgItm, 0, 0, 400, 600,null);

	}

	public void updateEndGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		//big.setColor(Color.YELLOW);
		big.drawImage(imgBg, 0, 0, 400, 600,null);
		
		big.setColor(Color.RED);
		big.drawString(String.format("Scores: %d", reporter.getScore()), 170, 40);
		big.setColor(Color.RED);
		big.drawString(String.format("Lives: %d", reporter.getLive()), 170, 20);
		big.setColor(Color.BLUE);
		big.drawString(String.format("Dorayaki: %d", reporter.getScoreItmDora()), 170, 60);
		big.setColor(Color.RED);

		for(Sprite s : sprites){
			s.draw(big);
		}

		// add image Live
		for(int i=0, j=220 ; i<reporter.getLive(); i++, j+=20){
			big.drawImage(imgLive,j,0,20,20,null);  // img,x,y,width,hight 
		}

		big.drawImage(imgItm, 2, 76, 25, 15,null);
		big.setColor(Color.BLUE);
		big.drawString(String.format("(10)  =         (1)"),30, 90);
		big.drawImage(imgLive, 70, 75, 20, 20,null);

		big.setColor(Color.YELLOW);
		Font f = new Font("",Font.BOLD,50);
		big.setFont(f);
		big.drawString(String.format("END GAME"),60, 300); 
        

		repaint();
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		//big.setColor(Color.YELLOW);
		big.drawImage(imgBg, 0, 0, 400, 600,null);
		
		big.setColor(Color.RED);
		big.drawString(String.format("Scores: %d", reporter.getScore()), 170, 40);
		//big.setColor(Color.RED);
		big.drawString(String.format("Lives: %d", reporter.getLive()), 170, 20);
		//big.setColor(Color.BLUE);
		big.drawString(String.format("Dorayaki: %d", reporter.getScoreItmDora()), 170, 60); 
        

		for(Sprite s : sprites){
			s.draw(big);
		}

		// add image Live
		for(int i=0, j=220 ; i<reporter.getLive(); i++, j+=20){
			big.drawImage(imgLive,j,0,20,20,null);  // img,x,y,width,hight 
		}

		big.drawImage(imgItm, 2, 580, 25, 15,null);
		big.setColor(Color.BLUE);
		big.drawString(String.format("(10)  =         (1)"),30, 590);
		big.drawImage(imgLive, 70, 576, 20, 20,null);
		//big.setColor(Color.YELLOW);
		big.drawString(String.format(", bullet = Spacebar"),120, 590);

		repaint();
	}

	public void StartGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		//big.setColor(Color.YELLOW);
		//Font f = new Font("",Font.BOLD,50);
		//big.setFont(f);
		big.drawString(String.format("START GAME"),130, 300);
		big.drawString(String.format("Please Enter"),130, 380); 
	}
	

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
