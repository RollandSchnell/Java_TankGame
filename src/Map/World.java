package Map;


import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Game.Bot;
import Game.Bullet;
import Game.Game;
import Game.Player;

public class World {

	public Game game;
	public boolean[] keys = new boolean[256];
	public boolean[] mouse = new boolean[4];
	public Player player = new Player(this, new Vector(256,108));
	public List<Bot> bots = new ArrayList<Bot>();
	public List<Block> walls = new ArrayList<Block>();
	public List<Bullet> bullets = new ArrayList<Bullet>();
	ImageIcon floor, wall;
	public static Random rand = new Random();
	public static int lastKey;
	public int damage;

	public World(Game g) {
		
		game = g;
		try {
			floor = new ImageIcon(ImageIO.read(new File("floor.jpeg")));
			wall = new ImageIcon(ImageIO.read(new File("wall_block.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		addBlocks();
		addBots();
	}

	
	public void onTick() {
		
		for(Block b: walls){
			b.doTick();
		}

		player.doTick();
		for(Bot bot: bots)
			bot.doTick();

		for(Bullet b: bullets){
			b.doTick();
		}

		checkHealth();
	}

	public void draw(Graphics2D g){
		
		for(int x=0;x<800;x+=128){
			for(int y=0;y<600;y+=128){
				g.drawImage(floor.getImage(), x, y, null);    //draws the floor map
			}
		}
		for(Block b: walls){
			b.doDraw(g);     //draws the world blocks
		}
		player.doDraw(g);   //draws the player
		for(Bot bot: bots)
			bot.doDraw(g);    //draws the bots
		for(Bullet b: bullets){
			b.doDraw(g);     // draws the bullets
		}
		
	
	}

	public void checkHealth(){
		
		if(player.getHealth() <= 0){
			if(JOptionPane.showConfirmDialog(null, "Game Over! Restart?") == JOptionPane.YES_OPTION){
				
				Game.main(null);
				game.timer.stop();
				game.frame.dispose();
			}else{
				System.exit(0);
			}
		}
		List<Boolean> bool = new ArrayList<Boolean>();
		for(Bot bot: bots){
			if(bot.getHealth() <= 0){
				bot.setActive(false);
			}
			bool.add(bot.isActive());
		}
		if(!bool.contains(true)){
			if(JOptionPane.showConfirmDialog(null, "You Win ! Play again?") == JOptionPane.YES_OPTION){
				
				Game.main(null);
				game.timer.stop();
				game.frame.dispose();
				
			}else{
				System.exit(0);
			}
		}
	}

	public void keyPressed(int keyCode) {
		
		lastKey = keyCode;
		keys[keyCode] = true;
	}
	
	public void mousePressed(int button) {
		
	
		mouse[button] = true;
	}

	public void mouseReleased() {
		
		mouse[0] = false;
	}

	public void keyReleased(int keyCode) {
		
		keys[keyCode] = false;
	}

	public void addBlocks(){
		walls.add(new Block(this, new Vector(16,16)));
		walls.add(new Block(this, new Vector(48,16)));
		walls.add(new Block(this, new Vector(80,16)));
		walls.add(new Block(this, new Vector(112,16)));
		walls.add(new Block(this, new Vector(144,16)));
		walls.add(new Block(this, new Vector(176,16)));
		walls.add(new Block(this, new Vector(208,16)));
		walls.add(new Block(this, new Vector(240,16)));
		walls.add(new Block(this, new Vector(272,16)));
		walls.add(new Block(this, new Vector(304,16)));
		walls.add(new Block(this, new Vector(336,16)));
		walls.add(new Block(this, new Vector(368,16)));
		walls.add(new Block(this, new Vector(400,16)));
		walls.add(new Block(this, new Vector(432,16)));
		walls.add(new Block(this, new Vector(464,16)));
		walls.add(new Block(this, new Vector(496,16)));
		walls.add(new Block(this, new Vector(528,16)));
		walls.add(new Block(this, new Vector(560,16)));
		walls.add(new Block(this, new Vector(592,16)));
		walls.add(new Block(this, new Vector(624,16)));
		walls.add(new Block(this, new Vector(656,16)));
		walls.add(new Block(this, new Vector(688,16)));
		walls.add(new Block(this, new Vector(720,16)));
		walls.add(new Block(this, new Vector(752,16)));
		walls.add(new Block(this, new Vector(784,16)));
		walls.add(new Block(this, new Vector(784,48)));
		walls.add(new Block(this, new Vector(784,80)));
		walls.add(new Block(this, new Vector(784,112)));
		walls.add(new Block(this, new Vector(784,144)));
		walls.add(new Block(this, new Vector(784,176)));
		walls.add(new Block(this, new Vector(784,208)));
		walls.add(new Block(this, new Vector(784,240)));
		walls.add(new Block(this, new Vector(784,272)));
		walls.add(new Block(this, new Vector(784,304)));
		walls.add(new Block(this, new Vector(784,336)));
		walls.add(new Block(this, new Vector(784,368)));
		walls.add(new Block(this, new Vector(784,400)));
		walls.add(new Block(this, new Vector(784,432)));
		walls.add(new Block(this, new Vector(784,464)));
		walls.add(new Block(this, new Vector(784,496)));
		walls.add(new Block(this, new Vector(784,528)));
		walls.add(new Block(this, new Vector(784,560)));
		walls.add(new Block(this, new Vector(752,560)));
		walls.add(new Block(this, new Vector(720,560)));
		walls.add(new Block(this, new Vector(688,560)));
		walls.add(new Block(this, new Vector(656,560)));
		walls.add(new Block(this, new Vector(624,560)));
		walls.add(new Block(this, new Vector(592,560)));
		walls.add(new Block(this, new Vector(560,560)));
		walls.add(new Block(this, new Vector(528,560)));
		walls.add(new Block(this, new Vector(496,560)));
		walls.add(new Block(this, new Vector(464,560)));
		walls.add(new Block(this, new Vector(432,560)));
		walls.add(new Block(this, new Vector(400,560)));
		walls.add(new Block(this, new Vector(368,560)));
		walls.add(new Block(this, new Vector(336,560)));
		walls.add(new Block(this, new Vector(304,560)));
		walls.add(new Block(this, new Vector(272,560)));
		walls.add(new Block(this, new Vector(240,560)));
		walls.add(new Block(this, new Vector(208,560)));
		walls.add(new Block(this, new Vector(176,560)));
		walls.add(new Block(this, new Vector(144,560)));
		walls.add(new Block(this, new Vector(112,560)));
		walls.add(new Block(this, new Vector(80,560)));
		walls.add(new Block(this, new Vector(48,560)));
		walls.add(new Block(this, new Vector(16,560)));
		walls.add(new Block(this, new Vector(16,528)));
		walls.add(new Block(this, new Vector(16,496)));
		walls.add(new Block(this, new Vector(16,464)));
		walls.add(new Block(this, new Vector(16,432)));
		walls.add(new Block(this, new Vector(16,400)));
		walls.add(new Block(this, new Vector(16,368)));
		walls.add(new Block(this, new Vector(16,336)));
		walls.add(new Block(this, new Vector(16,304)));
		walls.add(new Block(this, new Vector(16,272)));
		walls.add(new Block(this, new Vector(16,240)));
		walls.add(new Block(this, new Vector(16,208)));
		walls.add(new Block(this, new Vector(16,176)));
		walls.add(new Block(this, new Vector(16,144)));
		walls.add(new Block(this, new Vector(16,112)));
		walls.add(new Block(this, new Vector(16,80)));
		walls.add(new Block(this, new Vector(16,48)));
		walls.add(new Block(this, new Vector(176,144)));
		walls.add(new Block(this, new Vector(176,176)));
		walls.add(new Block(this, new Vector(176,208)));
		walls.add(new Block(this, new Vector(176,240)));
		walls.add(new Block(this, new Vector(176,272)));
		walls.add(new Block(this, new Vector(176,304)));
		walls.add(new Block(this, new Vector(176,336)));
		walls.add(new Block(this, new Vector(176,368)));
		walls.add(new Block(this, new Vector(176,400)));
		walls.add(new Block(this, new Vector(176,432)));
		walls.add(new Block(this, new Vector(272,272)));
		walls.add(new Block(this, new Vector(304,272)));
		walls.add(new Block(this, new Vector(336,272)));
		walls.add(new Block(this, new Vector(368,272)));
		walls.add(new Block(this, new Vector(400,272)));
		walls.add(new Block(this, new Vector(432,272)));
		walls.add(new Block(this, new Vector(464,272)));
		walls.add(new Block(this, new Vector(496,272)));
		walls.add(new Block(this, new Vector(528,272)));
		walls.add(new Block(this, new Vector(560,272)));
		walls.add(new Block(this, new Vector(592,272)));
		walls.add(new Block(this, new Vector(432,304)));
		walls.add(new Block(this, new Vector(432,432)));
		walls.add(new Block(this, new Vector(368,432)));
		walls.add(new Block(this, new Vector(400,432)));
		walls.add(new Block(this, new Vector(240,432)));
		walls.add(new Block(this, new Vector(208,432)));
		walls.add(new Block(this, new Vector(272,144)));
		walls.add(new Block(this, new Vector(304,144)));
		walls.add(new Block(this, new Vector(336,144)));
		walls.add(new Block(this, new Vector(368,144)));
		walls.add(new Block(this, new Vector(400,144)));
		walls.add(new Block(this, new Vector(432,144)));
		walls.add(new Block(this, new Vector(464,144)));
		walls.add(new Block(this, new Vector(496,144)));
		walls.add(new Block(this, new Vector(528,144)));
		walls.add(new Block(this, new Vector(560,144)));
		walls.add(new Block(this, new Vector(592,144)));
		walls.add(new Block(this, new Vector(624,144)));
		walls.add(new Block(this, new Vector(656,144)));
		walls.add(new Block(this, new Vector(240,144)));
		walls.add(new Block(this, new Vector(208,144)));
		walls.add(new Block(this, new Vector(752,368)));
		walls.add(new Block(this, new Vector(656,368)));
		walls.add(new Block(this, new Vector(656,432)));
		walls.add(new Block(this, new Vector(656,400)));
		walls.add(new Block(this, new Vector(656,464)));
		walls.add(new Block(this, new Vector(592,464)));
		walls.add(new Block(this, new Vector(624,464)));
		walls.add(new Block(this, new Vector(528,528)));
		walls.add(new Block(this, new Vector(496,528)));
		walls.add(new Block(this, new Vector(464,528)));
	}
	
	public int getDamage() {
		
		
		return damage;
	}

	public void addBots(){
		
	
		if(game.canPlay()) {

		damage = game.getDamage();
		
		for(int i = 0;i < game.getBot();i++) {
			
		bots.add(new Bot(this, new Vector(50 + new Random().nextInt(100),200)));
		}
		
		
	}
	}
}