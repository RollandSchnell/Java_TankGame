package Game;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import Map.Block;
import Map.Vector;
import Map.World;

public class Player extends Entity {

	float speed = 3.4f;
	float collisionSize = 28f;
	int alarm = 10;
	boolean canShoot = true;

	
	public Player(World w, Vector v) {
		
		super(w, v);
		
	}

	/*
	 * Updates the game
	 * this method makes moving 
	 * the objects
	 */
	public void onTick() {
		
		
		updateMovement();
		updateShooting();
		
		if(alarm>0)
			alarm--;
		if(alarm <= 0){
			alarm = 10;
			canShoot = true;
		}
	}

	public void onDraw(Graphics2D g) {
		
	
		
		if(getWorld().lastKey == 0) {
			Image img = Toolkit.getDefaultToolkit().getImage("tank_up.png");
			g.drawImage(img, getVector().getIntX()-12, getVector().getIntY()-12, null);
			g.drawString(""+getHealth(), getVector().getIntX()-5, getVector().getIntY()-15);
			
		}
		if(getWorld().keys[KeyEvent.VK_W]){
	    	Image img = Toolkit.getDefaultToolkit().getImage("tank_up.png");
	    	g.drawImage(img, getVector().getIntX()-12, getVector().getIntY()-12, null);
	    	g.drawString(""+getHealth(), getVector().getIntX()-7, getVector().getIntY()-15);
		} else if (!getWorld().keys[KeyEvent.VK_W] && getWorld().lastKey == KeyEvent.VK_W) {
			
			Image img = Toolkit.getDefaultToolkit().getImage("tank_up.png");
	    	g.drawImage(img, getVector().getIntX()-12, getVector().getIntY()-12, null);
	    	g.drawString(""+getHealth(), getVector().getIntX()-7, getVector().getIntY()-15);
		}
		if(getWorld().keys[KeyEvent.VK_D]){
			Image img = Toolkit.getDefaultToolkit().getImage("tank_right.png");
			g.drawImage(img, getVector().getIntX()-12, getVector().getIntY()-12, null);
			g.drawString(""+getHealth(), getVector().getIntX()-7, getVector().getIntY()-15);
			} else if(!getWorld().keys[KeyEvent.VK_D] && getWorld().lastKey == KeyEvent.VK_D) {
				
				Image img = Toolkit.getDefaultToolkit().getImage("tank_right.png");
				g.drawImage(img, getVector().getIntX()-12, getVector().getIntY()-12, null);
				g.drawString(""+getHealth(), getVector().getIntX()-7, getVector().getIntY()-15);
			}
		if(getWorld().keys[KeyEvent.VK_A]){
			Image img = Toolkit.getDefaultToolkit().getImage("tank_left.png");
			g.drawImage(img, getVector().getIntX()-12, getVector().getIntY()-12, null);
			g.drawString(""+getHealth(), getVector().getIntX()-7, getVector().getIntY()-15);
			} else if(!getWorld().keys[KeyEvent.VK_A] && getWorld().lastKey == KeyEvent.VK_A) {
				
				Image img = Toolkit.getDefaultToolkit().getImage("tank_left.png");
				g.drawImage(img, getVector().getIntX()-12, getVector().getIntY()-12, null);
				g.drawString(""+getHealth(), getVector().getIntX()-7, getVector().getIntY()-15);
			}
		if(getWorld().keys[KeyEvent.VK_S]){
			Image img = Toolkit.getDefaultToolkit().getImage("tank_down.png");
			g.drawImage(img, getVector().getIntX()-12, getVector().getIntY()-12, null);
			g.drawString(""+getHealth(), getVector().getIntX()-7, getVector().getIntY()-15);
			} else if(!getWorld().keys[KeyEvent.VK_S] && getWorld().lastKey == KeyEvent.VK_S) {
				
				Image img = Toolkit.getDefaultToolkit().getImage("tank_down.png");
				g.drawImage(img, getVector().getIntX()-12, getVector().getIntY()-12, null);
				g.drawString(""+getHealth(), getVector().getIntX()-7, getVector().getIntY()-15);
			}
		
		
		
	}

	public void updateShooting(){
		
		
		if(getWorld().mouse[0]) {
			fire();
		} 
			
		}
	
	
	public void updateMovement(){
		
		if(getWorld().keys[KeyEvent.VK_W]){
			setVectorPrevious(new Vector(getVector()));
			getVector().setY(getVector().getY() - speed);
			checkCollision();
		}
		if(getWorld().keys[KeyEvent.VK_S]){
			setVectorPrevious(new Vector(getVector()));
			getVector().setY(getVector().getY() + speed);
			checkCollision();
		}
		if(getWorld().keys[KeyEvent.VK_A]){
			setVectorPrevious(new Vector(getVector()));
			getVector().setX(getVector().getX() - speed);
			checkCollision();
		}
		if(getWorld().keys[KeyEvent.VK_D]){
			setVectorPrevious(new Vector(getVector()));
			getVector().setX(getVector().getX() + speed);
			checkCollision();
		}
	}
	
	public void checkCollision(){
		
		for(Block b: getWorld().walls){
			if(Math.abs(getVector().getX() - b.getVector().getX()) < collisionSize
			&& Math.abs(getVector().getY() - b.getVector().getY()) < collisionSize){
				getVector().setX(getVectorPrevious().getX());
				getVector().setY(getVectorPrevious().getY());
				
			}
		}
	}
	
	public void fire(){
		
		if(canShoot){
			getWorld().bullets.add(new Bullet(getWorld(), getVector().clone(), this, getWorld().game.mousePosition.clone()));
			canShoot = false;
			alarm = 10;  //interval between bullets
		}
	}
}