import static org.lwjgl.opengl.GL11.glClearColor;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import edu.utc.game.Scene;
import edu.utc.game.Sound;
import edu.utc.game.Text;

public class EndOfGame implements Scene {
	
	Text text;
	Text proceedText = new Text(Main.ui.getWidth() / 2 - 150, 50, 20, 15, "press space to continue...");
	Sound victorysong;
	Sound defeatsong;
	
	public EndOfGame(boolean victory) {
		if (victory) {
			text = new Text(Main.ui.getWidth() / 2 - 50, 20, 30, 20, "VICTORY!");
			victorysong = new Sound("res/victorysong.wav");
			victorysong.play();
		}
		else {
			text = new Text(Main.ui.getWidth() / 2 - 50, 20, 30, 20, "DEFEAT");
			defeatsong = new Sound("res/defeatsong.wav");
			defeatsong.play();
		}
	}

	public Scene drawFrame(int delta) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		text.draw();
		proceedText.draw();
		if (Main.ui.keyPressed(GLFW.GLFW_KEY_SPACE))
    		return new Menu(Main.gridWidth * Main.blockSize, Main.gridHeight * Main.blockSize, false);
		return this;
	}

}
