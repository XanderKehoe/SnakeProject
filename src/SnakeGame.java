import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import edu.utc.game.Scene;

public class SnakeGame implements Scene {
	
	public static Player player;
	public static Food food;
	
	public static LinkedList<Tail> tailList = new LinkedList<Tail>();
	int victoryLength;
	
	int timer = 0;
	int timerMax;
	
	public SnakeGame(int frameTime) {
		victoryLength = Main.gridWidth * Main.gridHeight;
		
		player = new Player();
    	
    	int[] randCoords = getRandGridXY();
    	food = new Food(randCoords[0], randCoords[1]);
    	
    	timerMax = frameTime;
	}

	@Override
	public Scene drawFrame(int delta) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
    	if (timer > timerMax) {
    		player.move();
    		
    		ListIterator<Tail> tailIterator = (ListIterator<Tail>) tailList.iterator();
    		while (tailIterator.hasNext())
    		{
    			Tail thisTail = tailIterator.next();
    			if (thisTail.getHitbox().intersects(player.getHitbox()))
    				System.out.println("GAME OVER");
    			if (thisTail.life <= 0)
    				tailIterator.remove();
    			else
    				thisTail.life--;
    		}
    		
    		timer = 0;
    	}
    	else 
    		timer++;
    	
    	food.draw();
    	player.update();
    	for (Tail t : tailList)
    		t.draw();
    	
        return this;
	}
	
	static int[] getRandGridXY() {
    	Random rand = new Random();
    	int[] xy = new int[2];
    	xy[0] = rand.nextInt(Main.gridWidth);
    	xy[1] = rand.nextInt(Main.gridHeight);
    	
    	return xy;
    }

}
