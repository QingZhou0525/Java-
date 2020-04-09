import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.plaf.basic.BasicSliderUI.ScrollListener;

public class Bullet {
	private int x, y;
	private int ystep;
	private Image[] bulletpic;
	private int picid;
	private boolean died;

	public Bullet(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;

		died = false;
		ystep = 10;
		picid = 0;
		bulletpic = new Image[4];
		for (int i = 0; i < bulletpic.length; i++) {
			bulletpic[i] = Toolkit.getDefaultToolkit()
					.getImage(this.getClass().getResource("bullet_" + i + ".png"));
		}
	}

	void DrawBullet(Graphics g, GamePanel p) {
		g.drawImage(bulletpic[(++picid) % 4], x, y, p);
	}

	void UpdateBullet() {
		y = y - ystep;
	}

	int gety() {
		return y;
	}

	void setDied() {
		died = true;
	}

	boolean isDied() {
		return died;
	}

	Rectangle getBounds() {
		return new Rectangle(x, y + 25, 40, 50);
	}
}
