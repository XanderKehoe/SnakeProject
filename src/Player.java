import java.util.ListIterator;

import org.lwjgl.glfw.GLFW;

import edu.utc.game.*;

public class Player extends GameObject{
	
	public Dir dir = Dir.UP;
	int length = 0;
	
	public Player() {
		hitbox.x = (int) ((Main.gridWidth / 2) * Main.blockSize);
		hitbox.y = (int) ((Main.gridHeight / 2) * Main.blockSize);
		hitbox.width = Main.blockSize - 1;
		hitbox.height = Main.blockSize - 1;
		
		setColor(0, 1, 0);
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
			SnakeGame.tailList.add(new Tail((int) (hitbox.x / 30), (int) (hitbox.y / 30), length)); 
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
	}
	
	public void eat() {
		SnakeGame.food.newRandPos();
		length++;
	}
	
	public void checkInput() {
		 if (Main.ui.keyPressed(GLFW.GLFW_KEY_UP) && dir != Dir.DOWN) dir = Dir.UP;
		 else if (Main.ui.keyPressed(GLFW.GLFW_KEY_DOWN) && dir != Dir.UP) dir = Dir.DOWN;
		 else if (Main.ui.keyPressed(GLFW.GLFW_KEY_LEFT) && dir != Dir.RIGHT) dir = Dir.LEFT;
		 else if (Main.ui.keyPressed(GLFW.GLFW_KEY_RIGHT) && dir != Dir.LEFT) dir = Dir.RIGHT;
	}
	
	public boolean checkFoodCollision() {
		if (SnakeGame.food.intersects(this)) 
			return true;
		else 
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
