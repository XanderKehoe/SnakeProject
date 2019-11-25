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

import org.lwjgl.opengl.GL11;

import edu.utc.game.Game;
import edu.utc.game.Scene;
import edu.utc.game.SimpleMenu;
import edu.utc.game.UI;

public class Menu extends Game implements Scene{
	
	UI ui;
	SimpleMenu menuG;
	
	public Menu(int sizeX, int sizeY, boolean first) {
		if (first) {
			initUI(sizeX, sizeY, "Snake");
			this.registerGlobalCallbacks();
		}
		
		glClearColor(0f, 0f, 0f, 0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		

		menuG = new SimpleMenu(Game.ui.getWidth() / 2 - 50, 10, 30, 20, "Snakey Duel");
		menuG.addItem(new SimpleMenu.SelectableText(30, 40, 20, 20, "Start (easy)", 1, 0, 0, 1, 1, 1), new SnakeGame(60));
		menuG.addItem(new SimpleMenu.SelectableText(30, 80, 20, 20, "Start (medium)", 1, 0, 0, 1, 1, 1), new SnakeGame(20));
		menuG.addItem(new SimpleMenu.SelectableText(30, 120, 20, 20, "Start (hard)", 1, 0, 0, 1, 1, 1), new SnakeGame(10));
		menuG.addItem(new SimpleMenu.SelectableText(30, 160, 20, 20, "Exit", 1, 0, 0, 1, 1, 1), null);
		menuG.select(0);

	}

	public Scene drawFrame(int delta) {
		// TODO Auto-generated method stub
		return menuG;
	}

}
