import edu.utc.game.*;

public class Tail extends GameObject {
	int life;
	
	public Tail(int gridX, int gridY, int life) {
		hitbox.x = gridX * Main.blockSize;
		hitbox.y = gridY * Main.blockSize;
		hitbox.width = Main.blockSize - 1;
		hitbox.height = Main.blockSize - 1;
		
		this.life = life;
		
		setColor(0, 1, 0);
	}
}
