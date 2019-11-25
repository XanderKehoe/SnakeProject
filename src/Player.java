import java.util.ListIterator;
import java.util.Random;

import org.lwjgl.glfw.GLFW;

import edu.utc.game.*;

public class Player extends GameObject{
	
	public Dir dir = Dir.UP;
	public Dir lastDir = Dir.DOWN;
	int length = 0;
	
	boolean player1;
	
	public Player(boolean player1) {
		hitbox.x = (int) ((Main.gridWidth / 2) * Main.blockSize);
		hitbox.y = (int) ((Main.gridHeight / 2) * Main.blockSize);
		hitbox.width = Main.blockSize - 1;
		hitbox.height = Main.blockSize - 1;
		
		if (player1){
			setColor(0, 1, 0);
			this.player1 = true;
		}
		else{
			setColor(0, 0, 1);
			this.player1 = false;
		}
	}
	
	public Player(int gridX, int gridY, boolean player1) {
		hitbox.x = (int) (gridX * Main.blockSize);
		hitbox.y = (int) (gridY * Main.blockSize);
		hitbox.width = Main.blockSize - 1;
		hitbox.height = Main.blockSize - 1;
		
		if (player1){
			setColor(0, 1, 0);
			this.player1 = true;
		}
		else{
			setColor(0, 0, 1);
			this.player1 = false;
		}
	}
	
	public void update() {
		checkInput();
		draw();
	}
	
	public void periodicUpdate() {
		if (checkFoodCollision())
			eat();
		//if (checkTailCollision())
			//System.out.p
		
		if (length > 0) 
			SnakeGame.tailList.add(new Tail((int) (hitbox.x / 30), (int) (hitbox.y / 30), length, player1)); 
	}
	
	public void move() {
		periodicUpdate();
		
		switch(dir) {
		case UP:
			hitbox.y -= Main.blockSize;
			break;
		case DOWN:
			hitbox.y += Main.blockSize;
			break;
		case LEFT:
			hitbox.x -= Main.blockSize;
			break;
		case RIGHT:
			hitbox.x += Main.blockSize;
			break;
		}
		
		SnakeGame.moveSound.play();
		
		lastDir = dir;
	}
	
	public void eat() {
		SnakeGame.food.newRandPos();
		length++;
		SnakeGame.eatSound.play();
	}
	
	public void checkInput() {
		if (player1){
			if (Main.ui.keyPressed(GLFW.GLFW_KEY_UP) && dir != Dir.DOWN && lastDir != Dir.DOWN) dir = Dir.UP;
			else if (Main.ui.keyPressed(GLFW.GLFW_KEY_DOWN) && dir != Dir.UP && lastDir != Dir.UP) dir = Dir.DOWN;
			else if (Main.ui.keyPressed(GLFW.GLFW_KEY_LEFT) && dir != Dir.RIGHT && lastDir != Dir.RIGHT) dir = Dir.LEFT;
			else if (Main.ui.keyPressed(GLFW.GLFW_KEY_RIGHT) && dir != Dir.LEFT && lastDir != Dir.LEFT) dir = Dir.RIGHT;
		}
		else{
			if (Main.ui.keyPressed(GLFW.GLFW_KEY_W) && dir != Dir.DOWN && lastDir != Dir.DOWN) dir = Dir.UP;
			else if (Main.ui.keyPressed(GLFW.GLFW_KEY_S) && dir != Dir.UP && lastDir != Dir.UP) dir = Dir.DOWN;
			else if (Main.ui.keyPressed(GLFW.GLFW_KEY_A) && dir != Dir.RIGHT && lastDir != Dir.RIGHT) dir = Dir.LEFT;
			else if (Main.ui.keyPressed(GLFW.GLFW_KEY_D) && dir != Dir.LEFT && lastDir != Dir.LEFT) dir = Dir.RIGHT;
		}
	}
	
	public boolean checkFoodCollision() {
		if (SnakeGame.food.intersects(this)) 
			return true;
		else 
			return false;
	}
	
	public boolean outOfMap() {
		if (getHitbox().x < 0 || getHitbox().x > Main.gridWidth * (Main.blockSize)
		|| getHitbox().y < 0 || getHitbox().y > Main.gridHeight * (Main.blockSize))
			return true;
		return false;
	}
	
	/*
	public boolean checkTailCollision() {
		boolean collision = false;
		ListIterator<Tail> tailIterator = (ListIterator<Tail>) SnakeGame.tailList.iterator();
		while (tailIterator.hasNext())
		{
			Tail thisTail = tailIterator.next();
			if (thisTail.getHitbox().intersects(this.getHitbox())) {
				collision = true;
				break;
			}
		}
		
		return collision;
	}*/
}
