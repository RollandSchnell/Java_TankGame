package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.util.Random;

import Map.Block;
import Map.Vector;
import Map.World;

public class Bot extends Entity {

	Vector moveTo;
	Vector previous = new Vector(0,0);
	Random random = new Random();
	private float speed = 2f, angle = 0f;
	float collisionSize = 28f;
	int counter = 300;
	boolean canShoot = true;
	int alarm = 20;
	
	public Bot(World w, Vector v) {
		
		super(w, v);
		moveTo = new Vector(random.nextInt(800), random.nextInt(600));
	}

	public void onTick() {
		
		setVectorPrevious(new Vector(getVector()));
		
		angle = Vector.calcAngle(getVector(), moveTo);
		getVector().setX((float) (getVector().getX() + speed * Math.sin(Math.toRadians(getAngle()))));
		getVector().setY((float) (getVector().getY() - speed * Math.cos(Math.toRadians(getAngle()))));
		
		checkCollision();
		
		//if the tank hits the wall generate new vector position
		
		if(moveTo.getIntX() - getVector().getIntX() < 4 && moveTo.getIntY() - getVector().getIntY() < 4){
			
			int x = random.nextInt(800);
			int y = random.nextInt(600);
			moveTo.setX(x);
			moveTo.setY(y);
			
			previous.setX(x);
			previous.setY(y);
			
		}
		
		counter--;
		if(counter<=0){
			counter = 300;
			moveTo = new Vector(random.nextInt(800), random.nextInt(600));
		}
		
		if(alarm>0)
			alarm--;
		if(alarm <= 0){
			alarm = 20;
			canShoot = true;
		}
		
		fire();
	}
	
	public void fire(){
		
		if(canShoot && canSee()){
			getWorld().bullets.add(new Bullet(getWorld(), new Vector(getVector()), this, new Vector(getWorld().player.getVector())));
			canShoot = false;
			alarm = 10;
		}
	}

	public void onDraw(Graphics2D g) {
		
		
		g.setColor(Color.RED);
		
		if(Bullet.damage > 5) {
			
			g.setColor(Color.YELLOW);
			g.drawString("Crit ! ", getVector().getIntX()-10, getVector().getIntY()-25);
			g.setColor(Color.RED);
			
		}
		if(previous.getIntX() < getVector().getX()) {
			
			Image img = Toolkit.getDefaultToolkit().getImage("tank_down.png");
			g.drawImage(img, getVector().getIntX()-12, getVector().getIntY()-12, null);
			g.drawString(""+getHealth(), getVector().getIntX()-5, getVector().getIntY()-15);
		}
		
        if((previous.getIntX() > getVector().getIntX())) {
			
			Image img = Toolkit.getDefaultToolkit().getImage("tank_up.png");
			g.drawImage(img, getVector().getIntX()-12, getVector().getIntY()-12, null);
			g.drawString(""+getHealth(), getVector().getIntX()-5, getVector().getIntY()-15);
		}
		
		
        if(previous.getIntY() < getVector().getIntY() && (Math.abs(previous.getIntX() - getVector().getIntX()) < 100 )) {
        	
			Image img = Toolkit.getDefaultToolkit().getImage("tank_right.png");
    			g.drawImage(img, getVector().getIntX()-12, getVector().getIntY()-12, null);
    			g.drawString(""+getHealth(), getVector().getIntX()-5, getVector().getIntY()-15);
    		}
        
        if(previous.getIntY() > getVector().getIntY() && (Math.abs(previous.getIntX() - getVector().getIntX()) < 100 )) {
        	
			Image img = Toolkit.getDefaultToolkit().getImage("tank_left.png");
    			g.drawImage(img, getVector().getIntX()-12, getVector().getIntY()-12, null);
    			g.drawString(""+getHealth(), getVector().getIntX()-5, getVector().getIntY()-15);
    		}
        
        if(previous.getIntY() > getVector().getIntY() && previous.getIntX() == getVector().getIntX()) {
        	
			Image img = Toolkit.getDefaultToolkit().getImage("tank_right.png");
    			g.drawImage(img, getVector().getIntX()-12, getVector().getIntY()-12, null);
    			g.drawString(""+getHealth(), getVector().getIntX()-5, getVector().getIntY()-15);
    		}
        
	}

	public void checkCollision(){
		
		for(Block b: getWorld().walls){
			if(Math.abs(getVector().getX() - b.getVector().getX()) < collisionSize
			&& Math.abs(getVector().getY() - b.getVector().getY()) < collisionSize){
				getVector().setX(getVectorPrevious().getX());
				getVector().setY(getVectorPrevious().getY());
				
				if(Math.abs(getVector().getX() - moveTo.getX()) > Math.abs(getVector().getY() - moveTo.getY())){
					moveTo.setX(getVector().getX());
				}else if(Math.abs(getVector().getX() - moveTo.getX()) < Math.abs(getVector().getY() - moveTo.getY())){
					moveTo.setY(getVector().getY());
				}else{
					System.out.println("Error");
				}
			}
		}
	}
	
	public boolean canSee(){
		
		Vector player = getWorld().player.getVector().clone();
		Line2D.Float between = new Line2D.Float(player.getX(), player.getY(), getVector().getX(), getVector().getY());
		for(Block b: getWorld().walls){
			for(Line2D.Float fl: b.getLines()){
				if(Vector.linesIntersect(between, fl)){
					return false;
				}
			}
		}
		return true;
	}
	
	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
}