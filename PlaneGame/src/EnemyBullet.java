import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Iterator;

public class EnemyBullet {
	int x,y;
	int ystep;
	Image enemybulletpic;
	boolean died;
	
	public EnemyBullet(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		ystep=10;
		
		enemybulletpic=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("enemybullet.png"));
	}
	
	
	
	void DrawEnemyBullet(Graphics g, GamePanel p) {
		g.drawImage(enemybulletpic,x,y,p);
	}

	void setDied() {
		died=true;
	}
	
	boolean isDied() {
		return died;
	}
	
	void UpdateEnemyBullet() {
		y+=ystep;	
	}
	
	Rectangle getBounds() {
		return new Rectangle(x, y, 10, 10);
	}
	
}
