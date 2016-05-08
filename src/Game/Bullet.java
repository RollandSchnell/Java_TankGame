package Game;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import Map.Block;
import Map.Vector;
import Map.World;


public class Bullet extends Entity {
	
	private float speed = 5f;
	private float angle = 0f;
	private float collisionSize = 14f;
	Image img = Toolkit.getDefaultToolkit().getImage("bullet.png");
	Vector target;
	private Entity owner; //this tells the program who fires the bullets
	public static int damage = 0;
	
	public Bullet(World w, Vector v, Entity owner, Vector target){
		
		super(w,v);
		this.target = target;
		setAngle(Vector.calcAngle(getVector(), target));
		this.owner = owner;
	}
    /*
     * Makes the propagation of the bullet
     */
	public void onTick() {
		
		getVector().setX((float) (getVector().getX() + speed * Math.sin(Math.toRadians(getAngle()))));
		getVector().setY((float) (getVector().getY() - speed * Math.cos(Math.toRadians(getAngle()))));
		checkCollision();
	}

	public void onDraw(Graphics2D g) {
		
		g.drawImage(img, getVector().getIntX()-2, getVector().getIntY()-2, 6, 6, null);
	}
	
	public void checkCollision(){
		
		for(Block b: getWorld().walls){
			
			if(Math.abs(getVector().getX() - b.getVector().getX()) < collisionSize
			&& Math.abs(getVector().getY() - b.getVector().getY()) < collisionSize){
				this.setActive(false);
			}
		}
		
		if(Math.abs(getVector().getX() - owner.getWorld().player.getVector().getX()) < collisionSize
		&& Math.abs(getVector().getY() - owner.getWorld().player.getVector().getY()) < collisionSize
		&& owner instanceof Bot){
			this.setActive(false);
			
			
			
			if(getWorld().getDamage() == 1) {
				
				damage = World.rand.nextInt(5);
				owner.getWorld().player.damage(damage);
				
			    }
			
			if(getWorld().getDamage() == 2) {
				
				damage = World.rand.nextInt(10);
				owner.getWorld().player.damage(damage);
				}
			
			if(getWorld().getDamage() == 3) {
				
				damage = World.rand.nextInt(15);
				owner.getWorld().player.damage(damage);
				}
		}
		
		for(Bot bot: owner.getWorld().bots){
			if(Math.abs(getVector().getX() - bot.getVector().getX()) < collisionSize
			&& Math.abs(getVector().getY() - bot.getVector().getY()) < collisionSize
			&& owner instanceof Player){
				this.setActive(false);
				
				bot.damage(World.rand.nextInt(5));
			}
		}
	}

	public float getAngle() {
		return angle;
	}
	

	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Entity getOwner() {
		return owner;
	}
	

}