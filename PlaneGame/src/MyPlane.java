import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;

public class MyPlane {
	private int x, y;
	private int step;
	private Image[] planepic;
	private int picid;
	public int life;
	boolean redy;// ½â¾öËÀËø£»
	private Image[] bombpic;
	private int bombpicid;
	private boolean died;
	List<Bullet> bullets;

	public MyPlane(int x, int y, int life) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.life = life;
		step = 10;
		picid = 0;
		bombpicid = 0;
		redy = true;
		died = false;

		bullets = new LinkedList<Bullet>();

		planepic = new Image[6];
		for (int i = 0; i < planepic.length; i++) {
			planepic[i] = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("plan_" + i + ".png"));
		}

		bombpic = new Image[6];
		for (int i = 0; i < bombpic.length; i++) {
			bombpic[i] = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bomb_enemy_" + i + ".png"));
		}
	}

	void DrawMyPlane(Graphics g, GamePanel p) {

		for (int i = 0; i < life; i++) {
			g.drawImage(planepic[0], i * 33, 10, p);
		}

		if (!died) {
			g.drawImage(planepic[(++picid) % 6], x, y, p);
		} else {
			g.drawImage(bombpic[(++bombpicid) % 6], x, y, p);
		}

		Iterator<Bullet> iter = bullets.iterator();
		while (iter.hasNext()) {
			iter.next().DrawBullet(g, p);
		}
	}

	void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	void goup() {
		y = y - step;
	}

	void godown() {
		y = y + step;
	}

	void goleft() {
		x = x - step;
	}

	void goright() {
		x = x + step;
	}

	void setlife() {
		life--;
	}
	
	boolean isOver() {
		return life==0;
	}
	
	void initMyplane(int x, int y) {
		this.x = x;
		this.y = y;
		died = false;
		picid = 0;
		bombpicid = 0;
	}

	void UpdateMyplane() {
		Iterator<Bullet> iter = bullets.iterator();
		while (iter.hasNext()) {
			while (redy) {
				redy = false;
				Bullet bullet = iter.next();
				bullet.UpdateBullet();
				if (bullet.gety() < 0 || bullet.isDied()) {
					iter.remove();
				}
			}
			redy = true;
		}
	}

	void setDied() {
		died = true;
	}

	boolean getdied() {
		return died;
	}

	void shot() {
		while (redy) {
			redy = false;
			bullets.add(new Bullet(x - 5, y - 40));
		}
		redy = true;
	}

	int getx() {
		return x;
	}

	int gety() {
		return y;
	}

	Rectangle getBounds() {
		return new Rectangle(x, y, 30, 36);
	}

}
