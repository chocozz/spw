package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	//add Enemy2	
	private ArrayList<Enemy2> enemies2 = new ArrayList<Enemy2>();
	//add bullet
	private ArrayList<Bullet> bullet = new ArrayList<Bullet>();
	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();

				//add process2
				process2();
				// add process bullet
				process3();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
    
    // add generateEnemy2
	private void generateEnemy2(){
		Enemy2 e = new Enemy2((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies2.add(e);
	}

	// add bullet
	private void generateBullet(){
		Bullet e = new Bullet((v.x) + (v.width/2), v.y);
		gp.sprites.add(e);
		bullet.add(e);
	}
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
				return;
			}
		}
	}

	// add process2
	private void process2(){
		if(Math.random() < difficulty){
			generateEnemy2();
		}
		
		Iterator<Enemy2> e_iter = enemies2.iterator();
		while(e_iter.hasNext()){
			Enemy2 e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy2 e : enemies2){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
				return;
			}
		}
	}

	// add process3 bullet
	private void process3(){
		
		Iterator<Bullet> e_iter = bullet.iterator();
		while(e_iter.hasNext()){
			Bullet e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double ee;
		
		for(Bullet e : bullet){
			er = e.getRectangle();
			for(Enemy2 e2 : enemies2){
				ee = e2.getRectangle();
				if(er.intersects(ee)){
					e.getHit();
					e2.getHit();
				}
			}
			for(Enemy e1 : enemies){
				ee = e1.getRectangle();
				if(er.intersects(ee)){
					e.getHit();
					e1.getHit();
				}
			}
		}
	}

	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1,0);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1,0);
			break;
		case KeyEvent.VK_UP:
			v.move(-1,1);
			break;
		case KeyEvent.VK_DOWN:
			v.move(1,1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		// add control bullet
		case KeyEvent.VK_A:
			generateBullet();
			break;
		}
	}

	public long getScore(){
		return score;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
