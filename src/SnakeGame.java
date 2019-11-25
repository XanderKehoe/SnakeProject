import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import edu.utc.game.Scene;
import edu.utc.game.Sound;
import edu.utc.game.Text;

public class SnakeGame implements Scene {
	
	public static Player player1;
	public static Player player2;
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
		
		player1 = new Player(6, 6, true);
		player2 = new Player(Main.gridWidth - 6, Main.gridHeight - 6, false);
    	
    	int[] randCoords = getRandGridXY();
    	food = new Food(randCoords[0], randCoords[1]);
    	
    	timerMax = frameTime;
    	tailList.clear();
	}
	
	public SnakeGame(Player p1, Player p2, LinkedList<Tail> tailList, Food food, int timerMax) {
		victoryScore = Main.gridWidth * Main.gridHeight;
		
		this.player1 = p1;
		this.player2 = p2;
		this.tailList = tailList;
    	
    	this.food = food;
    	
    	this.timerMax = timerMax;
	}

	public Scene drawFrame(int delta) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
    	if (timer > timerMax) {
    		player1.move();
    		player2.move();
    		
    		ListIterator<Tail> tailIterator = (ListIterator<Tail>) tailList.iterator();
    		while (tailIterator.hasNext())
    		{
    			Tail thisTail = tailIterator.next();
    			if (thisTail.getHitbox().intersects(player1.getHitbox()))
    				return new EndOfGame(false);
    			else if (thisTail.getHitbox().intersects(player2.getHitbox()))
    				return new EndOfGame(true);
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
    	player1.update();
    	player2.update();
    	for (Tail t : tailList)
    		t.draw();
    	
    	
    	//if (player.length+ 1 >= victoryScore) {
    		//return new EndOfGame(true);
    	//}
    	if (player1.outOfMap())
    		return new EndOfGame(false);
    	else if (player2.outOfMap())
    		return new EndOfGame(true);
    	
    	if (Main.ui.keyPressed(GLFW.GLFW_KEY_SPACE))
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
