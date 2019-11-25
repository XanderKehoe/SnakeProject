import edu.utc.game.*;

public class Tail extends GameObject {
	int life;
	boolean player1;
	
	public Tail(int gridX, int gridY, int life, boolean player1) {
		hitbox.x = gridX * Main.blockSize;
		hitbox.y = gridY * Main.blockSize;
		hitbox.width = Main.blockSize - 1;
		hitbox.height = Main.blockSize - 1;
		
		this.life = life;
		
		if (player1){
			setColor(0, 1, 0);
			player1 = true;
		}
		else{
			setColor(0, 0, 1);
			player1 = false;
		}
	}
}
