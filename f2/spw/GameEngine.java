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
	
	//add Item2	
	private ArrayList<Item2> item2 = new ArrayList<Item2>();
	//add bullet
	private ArrayList<Bullet> bullet = new ArrayList<Bullet>();
	// add item1
	private ArrayList<Item1> item1 = new ArrayList<Item1>();

	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.08;
	private double diffItem1 = 0.01;
	private double diffItem2 = 0.009;

	// add live
	private int live = 5; 

	// add Score item1
	private long scoreitm = 0;

	private long scoreitmdora = 0;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();

				//add process2 item2 dorayaki
				process2();
				// add process bullet
				process3();
				// add process item1 fan
				process4();
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
    
    // add generateItem2
	private void generateItem2(){
		Item2 e = new Item2((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		item2.add(e);
	}

	// add bullet
	private void generateBullet(){
		Bullet e = new Bullet((v.x) + (v.width/2), v.y);
		gp.sprites.add(e);
		bullet.add(e);
	}

	// add item1
	private void generateItem1(){
		Item1 e = new Item1((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		item1.add(e);
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
				//score += 100;
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();

		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				e.getHit();
				live -= 1;
				if(live == 0)
					die();
				return;
			}
		}
	}

	
	// add process2 item2
	private void process2(){
		if(Math.random() < diffItem2){
			generateItem2();
		}
		
		Iterator<Item2> e_iter = item2.iterator();
		while(e_iter.hasNext()){
			Item2 e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				//score += 100;
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Item2 e : item2){
			er = e.getRectangle();
			if(er.intersects(vr)){
				e.getHit();
				scoreitmdora += 1;
				if(scoreitmdora%10 == 0){
					live += 1;
					scoreitmdora = 0;
				}
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
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double ee;

		for(Bullet e : bullet){
			er = e.getRectangle();
			/*for(Item2 e2 : item2){
				ee = e2.getRectangle();
				if(er.intersects(ee)){
					e.getHit();
					e2.getHit();
					score += 100;
				}
			}*/
			for(Enemy e1 : enemies){
				ee = e1.getRectangle();
				if(er.intersects(ee)){
					e.getHit();
					e1.getHit();
					score += 100;
				}
			}
		}
	}

	// add process4 item1
	private void process4(){
		if(Math.random() < diffItem1){
			generateItem1();
		}
		
		Iterator<Item1> e_iter = item1.iterator();
		while(e_iter.hasNext()){
			Item1 e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				//score += 100;
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Item1 e : item1){
			er = e.getRectangle();
			if(er.intersects(vr)){
				e.getHit();
				//scoreitmdora += 1;
				//if(live == 0)
					//die();
				return;
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
		case KeyEvent.VK_T:
			difficulty -= 0.01;
			break;
		// add control bullet
		case KeyEvent.VK_SPACE:
			generateBullet();
			break;
		}
	}

	public long getScore(){
		return score;
	}

	// add live
	public int getLive(){
		return live;
	}

	// add score itm
	public long getScoreItm(){
		return scoreitm;
	}

	public long getScoreItmDora(){ 
		return scoreitmdora;
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
