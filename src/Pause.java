import static org.lwjgl.opengl.GL11.glClearColor;

import java.util.LinkedList;

import org.lwjgl.opengl.GL11;

import edu.utc.game.Game;
import edu.utc.game.Scene;
import edu.utc.game.SimpleMenu;

public class Pause implements Scene{
	
	SimpleMenu menuP;
	
	public Pause(Scene resumeScene) {
		glClearColor(0f, 0f, 0f, 0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		

		menuP = new SimpleMenu(Game.ui.getWidth() / 2 - 50, 10, 30, 20, "pause");
		menuP.addItem(new SimpleMenu.SelectableText(30, 60, 20, 20, "Resume", 1, 0, 0, 1, 1, 1), resumeScene);
		menuP.addItem(new SimpleMenu.SelectableText(30, 90, 20, 20, "Exit", 1, 0, 0, 1, 1, 1), null);
		menuP.select(0);
	}

	public Scene drawFrame(int delta) {
		// TODO Auto-generated method stub
		return menuP;
	}

}
