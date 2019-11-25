import java.awt.Rectangle;
import java.util.ListIterator;

import edu.utc.game.*;

public class Food extends GameObject{
	public Food(int gridX, int gridY) {
		hitbox.x = gridX * Main.blockSize;
		hitbox.y = gridY * Main.blockSize;
		hitbox.width = Main.blockSize - 1;
		hitbox.height = Main.blockSize - 1;
		
		setColor(1, 0, 0);
	}
	
	public void newRandPos() {
		Rectangle newHitBox = new Rectangle();
		boolean collides = true;
		while (collides) {
			collides = false;
			
			//generate new rand x y grid pos
			int[] randCoords = SnakeGame.getRandGridXY();
			newHitBox = new Rectangle(randCoords[0] * Main.blockSize,
												randCoords[1] * Main.blockSize,
												Main.blockSize - 1,
												Main.blockSize - 1);
			
			//check for collision placement with any tails
			ListIterator<Tail> tailIterator = (ListIterator<Tail>) SnakeGame.tailList.iterator();
			while (tailIterator.hasNext())
			{
				Tail thisTail = tailIterator.next();
				if (thisTail.getHitbox().intersects(newHitBox)) {
					collides = true;
					break;
				}
			}
			
			//check for collision with player
			if (SnakeGame.player1.getHitbox().intersects(newHitBox)
			||  SnakeGame.player2.getHitbox().intersects(newHitBox))
				collides = true;
		}
		
		hitbox = newHitBox;
		
	}
	
	
}
