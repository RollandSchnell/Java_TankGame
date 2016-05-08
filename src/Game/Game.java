package Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Map.Vector;
import Map.World;


public class Game extends JPanel implements ActionListener, KeyListener, MouseMotionListener,MouseListener {


	public Timer timer = new Timer(12,this);
	World world = new World(this);
	public JFrame frame;
	Vector mousePosition = null;
	int bots = 1;
	int diff = 1;

	
	
	/*
	 * Adding the listeners
	 */
	
	public Game(){
		
		
			
		frame = new JFrame("Tank game");
		frame.setVisible(true);
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.add(this);
		frame.addKeyListener(this);
		
		
		
		timer.start();
	
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		this.setFocusable(true);
		
	}
	
	/*
	 * Constant update of the world/player/bot
	 */
	public void update(){
		
		world.onTick();
	}
	
	public void paint(Graphics gg){
		
		super.paint(gg);
		
		Graphics2D g = (Graphics2D)gg;
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		world.draw(g);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		
		update();
		repaint();
	}
	
	public boolean canPlay() {
		
		bots = Integer.parseInt(JOptionPane.showInputDialog(frame,"Number of boots ?"));
		diff = Integer.parseInt(JOptionPane.showInputDialog(frame,"Difficulty ? 1 = easy 2 = medium 3 = hard"));
		
		if(bots != 0 || diff != 0) {
		return true;		
		} else 
			return false;
	}
	
	public int getBot() {
		
		return bots;
	}
	
	public int getDamage() {
		
		return diff;
	}

	public void keyPressed(KeyEvent e) {
		
		world.keyPressed(e.getKeyCode());
	
		
	}

	public void keyReleased(KeyEvent e) {
		
		world.keyReleased(e.getKeyCode());
	}

	public void keyTyped(KeyEvent arg0) {
		
	}

	public void mouseDragged(MouseEvent e) {
		
		world.mousePressed(e.getButton());
		
		if(mousePosition == null){
			mousePosition = new Vector(e.getX(), e.getY());
		}else{
			mousePosition.setX(e.getX());
			mousePosition.setY(e.getY());
		}
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	
        world.mousePressed(e.getButton());
		
		if(mousePosition == null){
			mousePosition = new Vector(e.getX(), e.getY());
		}else{
			mousePosition.setX(e.getX());
			mousePosition.setY(e.getY());
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		world.mouseReleased();
		
	}

	
	public static void main(String[] args){
		
		new Game();
	}
}