import edu.utc.game.Game;
import edu.utc.game.GameObject;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.opengl.GL11;

import edu.utc.game.Scene;
import edu.utc.game.SimpleMenu;
import edu.utc.game.Sound;
import edu.utc.game.Text;
import edu.utc.game.XYPair;

public class Main extends Game implements Scene {
	
	static Menu g;
	
	static final int blockSize = 30;
	static final int gridWidth = 23;
	static final int gridHeight = 18;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		g = new Menu(gridWidth * blockSize, gridHeight * blockSize, true);
		g.gameLoop();
		
		System.exit(0);

	}

	public Scene drawFrame(int delta) {
		// TODO Auto-generated method stub
		return null;
	}

}
