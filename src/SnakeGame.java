import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import edu.utc.game.Scene;
import edu.utc.game.Sound;
import edu.utc.game.Text;

public class SnakeGame implements Scene {
	
	public static Player player;
	public static Food food;
	
	public static LinkedList<Tail> tailList = new LinkedList<Tail>();
	
	int victoryScore;
	
	int timer = 0;
	int timerMax;
	
	static Sound eatSound = new Sound("res/eat.wav");
	static Sound moveSound = new Sound("res/move.wav");
	
	Text scoreText = new Text(0, 0, 40, 40, "Score: "+(tailList.size()+1));
	
	public SnakeGame(int frameTime) {
		victoryScore = Main.gridWidth * Main.gridHeight;
		
		player = new Player();
    	
    	int[] randCoords = getRandGridXY();
    	food = new Food(randCoords[0], randCoords[1]);
    	
    	timerMax = frameTime;
    	tailList.clear();
	}
	
	public SnakeGame(Player p, LinkedList<Tail> tailList, Food food, int timerMax) {
		victoryScore = Main.gridWidth * Main.gridHeight;
		
		this.player = p;
		this.tailList = tailList;
    	
    	this.food = food;
    	
    	this.timerMax = timerMax;
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
    				return new EndOfGame(false);
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
    	
    	scoreText = new Text(0, 0, 40, 40, "Score: "+(player.length+1));
    	scoreText.draw();
    	
    	if (player.length+ 1 >= victoryScore) {
    		return new EndOfGame(true);
    	}
    	else if (Main.ui.keyPressed(GLFW.GLFW_KEY_SPACE))
    		return new Pause(this);

    	
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
